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
import java.util.List;

public class PPDISecuritiesLoader {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PPDISecuritiesLoader.class);
    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


    public static List<Security> csvToPPDISecurities(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Security> securities = new ArrayList<Security>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            SimpleDateFormat formatter1=new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");


            for (CSVRecord csvRecord : csvRecords) {

               logger.info("Procesing: " + csvRecord.get("SCRIP_NAME"));

               Security security = new Security();

               security.IssueDescription = csvRecord.get("SCRIP_NAME");

                security.NameOfCompany = csvRecord.get("COMPANY_NAME");

                if (csvRecord.get("ALLOT_DATE").compareTo("")==0)
                    security.AllotmentDate = null;
                else
                    security.AllotmentDate  = formatter1.parse(csvRecord.get("ALLOT_DATE"));

                if (csvRecord.get("REDEMPTION_DATE").compareTo("")==0)
                    security.RedemptionDate = null;
                else
                    security.RedemptionDate  = formatter1.parse(csvRecord.get("REDEMPTION_DATE"));

                security.ISIN = csvRecord.get("ISIN");

                securities.add(security);
            }

            return securities;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
