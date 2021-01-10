package org.newops.files.excel.loader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.newops.model.Security;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DebtSecuritiesLoader {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DebtSecuritiesLoader.class);
    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Security> csvToDebtSecurities(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Security> securities = new ArrayList<Security>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy");



            for (CSVRecord csvRecord : csvRecords) {

               logger.info("Procesing: " + csvRecord.get("SYMBOL"));

                Double IPRate;
                if (csvRecord.get("IP RATE").compareTo("") == 0)
                    IPRate = null;
                else
                    IPRate = Double.parseDouble(csvRecord.get("IP RATE"));

                Date AllotmentDate;
                    if (csvRecord.get("DATE OF ALLOTMENT").compareTo("")==0)
                        AllotmentDate = null;
                    else
                        AllotmentDate = formatter1.parse(csvRecord.get("DATE OF ALLOTMENT"));

                Date RedemptionDate;
                if (csvRecord.get("REDEMPTION DATE").compareTo("")==0)
                    RedemptionDate = null;
                else
                    RedemptionDate = formatter1.parse(csvRecord.get("REDEMPTION DATE"));

                Integer RedemptionAmount;
                if (csvRecord.get("REDEMPTION AMT").compareTo("")==0)
                    RedemptionAmount = null;
                else
                    RedemptionAmount = Integer.parseInt(csvRecord.get("REDEMPTION AMT"));

                Date ConversionDate;
                if (csvRecord.get("CONVERSION DATE").compareTo("")==0)
                    ConversionDate = null;
                else
                    ConversionDate = formatter1.parse(csvRecord.get("CONVERSION DATE"));

                Integer ConversionAmount;
                if (csvRecord.get("CONVERSION AMT").compareTo("")==0)
                    ConversionAmount = null;
                else
                    ConversionAmount = Integer.parseInt(csvRecord.get("CONVERSION AMT"));

                Date InterestPaymentDate;
                if (csvRecord.get("INTEREST PAYMENT DT").compareTo("")==0)
                    InterestPaymentDate = null;
                else
                    InterestPaymentDate = formatter1.parse(csvRecord.get("INTEREST PAYMENT DT"));

               Security security = new Security(
                        csvRecord.get("SYMBOL"),
                        csvRecord.get("NAME OF COMPANY"),
                        csvRecord.get("SERIES"),
                        Double.parseDouble(csvRecord.get("FACE VALUE")),
                        Double.parseDouble(csvRecord.get("PAID UP VALUE")),
                        Integer.parseInt(csvRecord.get("MKT LOT")),
                        IPRate,
                        formatter1.parse(csvRecord.get("DATE OF LISTING")),
                       AllotmentDate,
                       RedemptionDate,
                       RedemptionAmount,
                       ConversionDate,
                       ConversionAmount,
                       InterestPaymentDate,
                       csvRecord.get("ISIN")

                );

                securities.add(security);
            }

            return securities;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
