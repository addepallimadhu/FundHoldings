package org.newops.files.excel.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import java.text.SimpleDateFormat;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import org.newops.model.Fund;

@Slf4j
public class FundSchemesLoader {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

   // private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FundSchemesLoader.class);
    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Fund> csvToFunds(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Fund> funds = new ArrayList<Fund>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy");

            for (CSVRecord csvRecord : csvRecords) {

               log.info("Procesing: " + csvRecord.get("Code"));

               Date ClosureDate;

               if (csvRecord.get("Closure Date").compareTo("")==0)
                   ClosureDate = null;
               else
                   ClosureDate = formatter1.parse(csvRecord.get("Closure Date"));

                Date LaunchDate;

                if (csvRecord.get("Launch Date").compareTo("")==0)
                    LaunchDate = null;
                else
                    LaunchDate = formatter1.parse(csvRecord.get("Launch Date"));

                Fund fund = new Fund(
                        csvRecord.get("AMC"),
                        Integer.parseInt(csvRecord.get("Code")),
                        csvRecord.get("Scheme Name"),
                        csvRecord.get("Scheme Type"),
                        csvRecord.get("Scheme Category"),
                        csvRecord.get("Scheme NAV Name"),
                        csvRecord.get("Scheme Minimum Amount"),
                        LaunchDate,
                        ClosureDate,
                        csvRecord.get("ISIN Div Payout/ ISIN GrowthISIN Div Reinvestment"),
                        null
                );

                funds.add(fund);
            }

            return funds;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
