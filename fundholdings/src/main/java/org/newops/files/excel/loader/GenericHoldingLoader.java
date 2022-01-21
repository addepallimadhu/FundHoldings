package org.newops.files.excel.loader;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.newops.model.Holding;
import org.newops.model.IndiaAMCHoldingsXLS;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
public class GenericHoldingLoader {
    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static List<Holding> excelToHoldings(IndiaAMCHoldingsXLS amcFile ,InputStream is) {

        try {

            Workbook workbook = new XSSFWorkbook(is);
            Iterator<Sheet> sheets =  workbook.sheetIterator();
            List<Holding> holdings = new ArrayList<Holding>();

            Date AsOfDate = null;
            SimpleDateFormat formatter1=new SimpleDateFormat(amcFile.getAsOfDatePattern());

            int sheetNumber = 0;
            while (sheets.hasNext()) {

                Sheet currentSheet = sheets.next();

                if(stringContainsItemFromList(currentSheet.getSheetName(),amcFile.getIgnoreSheets() ))
                    continue;

                Iterator<Row> rows = currentSheet.iterator();

                int rowNumber = 0;
                while (rows.hasNext()) {
                    Row currentRow = rows.next();

                    Iterator<Cell> cellsInRow = currentRow.iterator();

                    log.debug("Processing - Sheet Name : " +currentSheet.getSheetName() + " Row Number: " + currentRow.getRowNum());

                    // Logic to not process sub-totals with bold format and blank lines based on first column

                    int cellIdx = 0;

                    Cell firstCell = currentRow.getCell(amcFile.getSubHeadingColumnIndex());
                    XSSFCellStyle style = (XSSFCellStyle) firstCell.getCellStyle();
                    XSSFFont font = style.getFont();
                    boolean isBold = font.getBold();

                    if (firstCell.getStringCellValue().compareTo(amcFile.getEndOfDataColumnString()) == 0)
                        break;
                    if ((isBold || firstCell.toString().isEmpty()) && !(stringContainsItemFromList(firstCell.toString(),amcFile.getBoldExcludeException()))
                    )
                    {
                        if (currentRow.getRowNum() == amcFile.getAsOfDateRowIndex()) {
                            AsOfDate = formatter1.parse(firstCell.toString().substring(amcFile.getAsOfDatePrefix().length()));
                            continue;
                        }
                        else
                            continue;
                    }

                    // Add coupon to instrument name if it is available
                    String InstrumentName;
                    if( amcFile.getCouponColumnIndex() != null && isNumeric(currentRow.getCell(amcFile.getCouponColumnIndex(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).toString())) //currentRow.getCell(3).toString().isEmpty())
                        InstrumentName = String.format("%.2f",currentRow.getCell(amcFile.getCouponColumnIndex()).getNumericCellValue()) + "% " + currentRow.getCell(amcFile.getInstrumentNameColumnIndex()).getStringCellValue();
                    else
                        InstrumentName = currentRow.getCell(amcFile.getInstrumentNameColumnIndex()).getStringCellValue();

                    // Get Numeric values correctly
                    String IndustryRating;

                    if (isNumeric(currentRow.getCell(amcFile.getIndustryRatingColumnIndex()).toString()))
                        IndustryRating = null;
                    else
                        IndustryRating = currentRow.getCell(amcFile.getIndustryRatingColumnIndex()).getStringCellValue();

                    Double Quantity;

                    if (currentRow.getCell(amcFile.getQuantityColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty())
                        Quantity = null;
                    else
                        Quantity = currentRow.getCell(amcFile.getQuantityColumnIndex()).getNumericCellValue();

                    Double MarketValue;

                    if (currentRow.getCell(amcFile.getMarketValueColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty())
                        MarketValue = null;
                    else
                        MarketValue = currentRow.getCell(amcFile.getMarketValueColumnIndex()).getNumericCellValue();

                    Double NetAssetsPercentage;

                    if (currentRow.getCell(amcFile.getNetAssetsPercentageColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty() || (currentRow.getCell(amcFile.getNetAssetsPercentageColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("-") == 0) || (currentRow.getCell(amcFile.getNetAssetsPercentageColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("^") == 0) || (currentRow.getCell(amcFile.getNetAssetsPercentageColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("@") == 0) || (currentRow.getCell(amcFile.getNetAssetsPercentageColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("$0.00%") == 0))
                        NetAssetsPercentage = null;
                    else
                        NetAssetsPercentage = currentRow.getCell(amcFile.getNetAssetsPercentageColumnIndex()).getNumericCellValue();

                    Double Yield;

                    if (currentRow.getCell(amcFile.getYieldColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty() || currentRow.getCell(amcFile.getYieldColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("^^") ==0)
                        Yield = null;
                    else
                        Yield = currentRow.getCell(amcFile.getYieldColumnIndex()).getNumericCellValue();

                    Holding holding = new Holding(currentSheet.getSheetName()
                            ,currentSheet.getRow(amcFile.getFundNameRowIndex()).getCell(amcFile.getFundNameColumnIndex()).getStringCellValue()
                            ,InstrumentName
                            ,currentRow.getCell(amcFile.getIsinColumnIndex()).getStringCellValue()
                            ,IndustryRating
                            ,Quantity
                            ,MarketValue
                            ,NetAssetsPercentage
                            ,Yield
                            ,AsOfDate
                    );

                    holdings.add(holding);
                }

            }
            workbook.close();

            return holdings;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }

        catch (IllegalStateException | ParseException e){
            throw new RuntimeException("Null Value Error encountered" + e.getMessage());

        }
    }

}
