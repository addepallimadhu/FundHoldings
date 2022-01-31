package org.newops.files.excel.controller;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.file.Paths;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExcelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSchemesFileUpload() throws Exception {

        Path path = Paths.get("./data/SchemeData1403211732SS.csv");

        MockMultipartFile actualFile = new MockMultipartFile("file", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/")
                .file(actualFile)
                .param("type","fund")
                .param("securityclass","dummy"))
                .andExpect(status().isOk());

    }

    @Test
    public void testEquitySecuritiesFileUpload() throws Exception {

        Path path = Paths.get("./data/EQUITY_L.csv");

        MockMultipartFile actualFile = new MockMultipartFile("file", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/")
                .file(actualFile)
                .param("type","security")
                .param("securityclass","Equity"))
                .andExpect(status().isOk());

    }

    @Test
    public void testDebtSecuritiesFileUpload() throws Exception {

        Path path = Paths.get("./data/DEBT.csv");

        MockMultipartFile actualFile = new MockMultipartFile("file", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/")
                .file(actualFile)
                .param("type","security")
                .param("securityclass","Debt"))
                .andExpect(status().isOk());

    }

    @Test
    public void testWDMSecuritiesFileUpload() throws Exception {

        Path path = Paths.get("./data/wdmlist_20012022.csv");

        MockMultipartFile actualFile = new MockMultipartFile("file", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/")
                .file(actualFile)
                .param("type","security")
                .param("securityclass","WDM"))
                .andExpect(status().isOk());

    }

    @Test
    public void testPPDISecuritiesFileUpload() throws Exception {

        Path path = Paths.get("./data/PPDI_Dec_2021.csv");

        MockMultipartFile actualFile = new MockMultipartFile("file", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/")
                .file(actualFile)
                .param("type","security")
                .param("securityclass","PPDI"))
                .andExpect(status().isOk());

    }

    @Test
    public void testICICFOFFileUpload() throws Exception {

        Path path = Paths.get("./data/FOF.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"instrumentNameColumnIndex\": \"1\"}".getBytes());


        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                        .file(holdingFileJson)
                        .file(actualFile))
                .andExpect(status().isOk());

    }

    @Test
    public void testICICIEquityFileUpload() throws Exception {

        Path path = Paths.get("./data/Equity.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"instrumentNameColumnIndex\": \"1\"}".getBytes());


        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                .file(holdingFileJson)
                .file(actualFile))
                .andExpect(status().isOk());

    }

    @Test
    public void testICICIDebtFileUpload() throws Exception {

        Path path = Paths.get("./data/Debt.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"instrumentNameColumnIndex\": \"1\"}".getBytes());


        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                .file(holdingFileJson)
                .file(actualFile))
                .andExpect(status().isOk());

    }

    @Test
    public void testICICIFMPFileUpload() throws Exception {

        Path path = Paths.get("./data/FMP.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"instrumentNameColumnIndex\": \"1\"}".getBytes());


        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                .file(holdingFileJson)
                .file(actualFile))
                .andExpect(status().isOk());

    }

    @Test
    public void testICICIHybridFileUpload() throws Exception {

        Path path = Paths.get("./data/Hybrid.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"instrumentNameColumnIndex\": \"1\"}".getBytes());


        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                .file(holdingFileJson)
                .file(actualFile))
                .andExpect(status().isOk());

    }
    @Test
    public void testICICICPOFFileUpload() throws Exception {

        Path path = Paths.get("./data/CPOF.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"instrumentNameColumnIndex\": \"1\"}".getBytes());


        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                .file(holdingFileJson)
                .file(actualFile))
                .andExpect(status().isOk());

    }

    @Test
    public void testICICIMYFFileUpload() throws Exception {

        Path path = Paths.get("./data/MYF.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"instrumentNameColumnIndex\": \"1\"}".getBytes());


        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc.perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                .file(holdingFileJson)
                .file(actualFile))
                .andExpect(status().isOk());

    }

    @Test
    public void testUploadABSLHoldingsFile() throws Exception {
     //   Path path = Paths.get("./data/SEBI_Monthly_Portfolio October 20.xlsx");
        Path path = Paths.get("./data/SEBI_Monthly_Portfolio 31 Dec 2021.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,
                "application/json", "{\"fundNameRowIndex\":\"0\",\"fundNameColumnIndex\":\"1\",\"industryRatingColumnIndex\": \"3\",\"quantityColumnIndex\": \"4\",\"marketValueColumnIndex\": \"5\",\"netAssetsPercentageColumnIndex\": \"6\",\"yieldColumnIndex\": \"7\",\"boldExcludeException\": [\"Net Receivables / (Payables)\"],\"endOfDataColumnString\": \"GRAND TOTAL\",\"asOfDatePattern\": \"MMMM dd,yyyy\",\"asOfDatePrefix\": \"Monthly Portfolio Statement as on \" }".getBytes());

        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc
                .perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                        .file(holdingFileJson)
                        .file(actualFile))
                .andExpect(status().isOk());
    }

    @Test
    public void testUploadHDFCHoldingsFile() throws Exception {
        Path path = Paths.get("./data/Monthly Portfolios for October 2020_0.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,"application/json", ("{\"instrumentNameColumnIndex\": \"3\"," + //).getBytes());
               // "\"instrumentNameColumnIndex\": \"4\",").getBytes());
                "\"isinColumnIndex\": \"1\"," +
                "\"couponColumnIndex\": \"2\"," +
                "\"industryRatingColumnIndex\": \"4\"," +
                "\"quantityColumnIndex\": \"5\"," +
                "\"marketValueColumnIndex\": \"6\"," +
                "\"netAssetsPercentageColumnIndex\": \"7\"," +
                "\"yieldColumnIndex\": \"8\"," +
                "\"endOfDataColumnString\": \"Grand Total\"," +
                "\"asOfDateColumnIndex\": \"0\"," +
                "\"asOfDateRowIndex\": \"1\"," +
                "\"asOfDatePattern\": \"dd-MMM-yyyy\"," +
                "\"asOfDatePrefix\": \"Portfolio as on \"," +
                "\"boldExcludeException\": []," +
             "\"ignoreSheets\": [\"Hyperlinks\"]}").getBytes());

        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc
                .perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                        .file(holdingFileJson)
                        .file(actualFile))
                .andExpect(status().isOk());
    }

    @Test
    public void testUploadFundsFile() throws Exception {
        Path path = Paths.get("./data/Monthly Portfolios for October 2020_0.xlsx");

        MockMultipartFile holdingFileJson = new MockMultipartFile("amcFile", null,"application/json", ("{\"instrumentNameColumnIndex\": \"3\"," + //).getBytes());
                // "\"instrumentNameColumnIndex\": \"4\",").getBytes());
                "\"isinColumnIndex\": \"1\"," +
                "\"couponColumnIndex\": \"2\"," +
                "\"industryRatingColumnIndex\": \"4\"," +
                "\"quantityColumnIndex\": \"5\"," +
                "\"marketValueColumnIndex\": \"6\"," +
                "\"netAssetsPercentageColumnIndex\": \"7\"," +
                "\"yieldColumnIndex\": \"8\"," +
                "\"endOfDataColumnString\": \"Grand Total\"," +
                "\"asOfDateColumnIndex\": \"0\"," +
                "\"asOfDateRowIndex\": \"1\"," +
                "\"asOfDatePattern\": \"dd-MMM-yyyy\"," +
                "\"asOfDatePrefix\": \"Portfolio as on \"," +
                "\"boldExcludeException\": []," +
                "\"ignoreSheets\": [\"Hyperlinks\"]}").getBytes());

        MockMultipartFile actualFile = new MockMultipartFile("amcDataFile", Files.readAllBytes(path));

        mockMvc
                .perform(multipart("/api/excel/upload/holdings/IndiaAMC")
                        .file(holdingFileJson)
                        .file(actualFile))
                .andExpect(status().isOk());
    }

}