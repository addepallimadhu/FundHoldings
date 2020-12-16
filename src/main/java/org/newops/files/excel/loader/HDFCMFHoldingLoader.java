package org.newops.files.excel.loader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.newops.model.Holding;
import org.springframework.util.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HDFCMFHoldingLoader {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "BANKETF";

    private static final Logger logger = Logger.getLogger(HDFCMFHoldingLoader.class);

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
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
    public static List<Holding> excelToHoldings(InputStream is) {;
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Iterator<Sheet> sheets =  workbook.sheetIterator();
            //      List<Tutorial> tutorials = new ArrayList<Tutorial>();
            List<Holding> holdings = new ArrayList<Holding>();
            Date AsOfDate = null;

            SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");


            int sheetNumber = 0;
            while (sheets.hasNext()) {
                Sheet currentSheet = sheets.next();
                // Do not process the Index sheet
                if(currentSheet.getSheetName().compareTo("Hyperlinks") == 0 )
                    continue;;

                Iterator<Row> rows = currentSheet.iterator();

                int rowNumber = 0;
                while (rows.hasNext()) {
                    Row currentRow = rows.next();

                    Iterator<Cell> cellsInRow = currentRow.iterator();

                    logger.debug("Processing - Sheet Name : " +currentSheet.getSheetName() + " Row Number: " + currentRow.getRowNum());

                    // Logic to not process sub-totals with bold format and blank lines based on first column

                    int cellIdx = 0;

                    Cell firstCell = currentRow.getCell(1);
                    XSSFCellStyle style = (XSSFCellStyle) firstCell.getCellStyle();
                    XSSFFont font = style.getFont();
                    boolean isBold = font.getBold();



                    Cell thirdCell = currentRow.getCell(3);

                    if (firstCell.getStringCellValue().compareTo("Grand Total") == 0 )
                        break;
                    if (isBold || thirdCell.toString().isEmpty())
                    {
                        if (currentRow.getRowNum() == 1) {
                            AsOfDate = formatter1.parse(firstCell.toString().substring(16));
                            continue;
                        }
                        else
                            continue;
                    }

                    // Add coupon to instrument name if it is available
                    String InstrumentName ;

                    if(currentRow.getCell(2).toString().isEmpty())
                        InstrumentName = currentRow.getCell(3).getStringCellValue();
                    else
                        InstrumentName = String.format("%.2f",currentRow.getCell(2).getNumericCellValue()) + "% " + currentRow.getCell(3).getStringCellValue();

                    String IndustryRating;

                    if (isNumeric(currentRow.getCell(4).toString()))
                        IndustryRating = null;
                    else
                        IndustryRating = currentRow.getCell(4).getStringCellValue();

                    // Get Numeric values correctly

                    Double Quantity;

                    if (currentRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty())
                        Quantity = null;
                    else
                        Quantity = currentRow.getCell(5).getNumericCellValue();

                    Double MarketValue;

                    if (currentRow.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty())
                        MarketValue = null;
                    else
                        MarketValue = currentRow.getCell(6).getNumericCellValue();

                    Double NetAssetsPercentage;

                    if (currentRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty() || (currentRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("@") == 0) || (currentRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("$0.00%") == 0))
                        NetAssetsPercentage = null;
                    else
                        NetAssetsPercentage = currentRow.getCell(7).getNumericCellValue();

                    Double Yield;

                    if (currentRow.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().isEmpty() ||  (currentRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().compareTo("^^") == 0))
                        Yield = null;
                    else
                        Yield = currentRow.getCell(8).getNumericCellValue();


                    Holding holding = new Holding(currentSheet.getSheetName()
                            ,currentSheet.getRow(0).getCell(0).getStringCellValue()
                            ,InstrumentName
                            ,currentRow.getCell(1).getStringCellValue()
                            ,IndustryRating
                            ,Quantity
                            ,MarketValue
                            ,NetAssetsPercentage
                            ,Yield
                            ,AsOfDate
                    );

                    holdings.add(holding);

                    //          logger.debug("Holding Processed- Fund Code: " + holding.getFundCode() + " Instrument Name: " + holding.getInstrumentname() + "Row Number: " + currentRow.getRowNum());
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
