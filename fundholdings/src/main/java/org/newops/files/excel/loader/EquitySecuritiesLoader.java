package org.newops.files.excel.loader;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EquitySecuritiesLoader {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

   // private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EquitySecuritiesLoader.class);
    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Security> csvToEquitySecurities(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Security> securities = new ArrayList<Security>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy");

            for (CSVRecord csvRecord : csvRecords) {

               log.info("Procesing: " + csvRecord.get("SYMBOL"));

               Security security = new Security(
                        csvRecord.get("SYMBOL"),
                        csvRecord.get("NAME OF COMPANY"),
                        csvRecord.get("SERIES"),
                        formatter1.parse(csvRecord.get("DATE OF LISTING")),
                        Double.parseDouble(csvRecord.get("PAID UP VALUE")),
                        Integer.parseInt(csvRecord.get("MARKET LOT")),
                        csvRecord.get("ISIN NUMBER"),
                        Double.parseDouble(csvRecord.get("FACE VALUE"))
                );

                securities.add(security);
            }

            return securities;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
