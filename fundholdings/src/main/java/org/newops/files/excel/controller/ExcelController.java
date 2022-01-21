package org.newops.files.excel.controller;

import org.newops.model.IndiaAMCHoldingsXLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import org.newops.files.excel.message.ResponseMessage;
//import com.bezkoder.spring.files.excel.model.Tutorial;
import org.newops.files.excel.service.ExcelService;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    ExcelService fileService;

    @PostMapping(value = "upload/holdings/IndiaAMC")
    public ResponseEntity<?> uploadFileIndiaAMCHoldings(@RequestPart("amcFile") IndiaAMCHoldingsXLS amcFile, @RequestPart("amcDataFile") MultipartFile amcDataFile)
    {
        fileService.saveGeneric(amcFile,amcDataFile);
        return ResponseEntity.status(HttpStatus.OK).body("GOOD TO GO");
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("type") String Type, @RequestParam("securityclass") String SecurityClass ) {

        String message = "";
        try {
                switch(Type)
                {
                    case "fund":
                        fileService.saveAMFIFundSchemes(file);
                    case "security":
                        switch(SecurityClass)
                        {
                            case("Equity"):
                                fileService.saveEquitySecurities(file);
                                break;
                            case("Debt"):
                                fileService.saveDebtSecurities(file);
                                break;
                            case("WDM"):
                                fileService.saveWDMSecurities(file);
                                break;
                            case("PPDI"):
                                fileService.savePPDISecurities(file);
                                break;

                            default:
                                break;
                        }
                    default:
                        break;


                }
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {

                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
       }

    //    message = "Please upload an excel file!";
     //   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
      //  return  ResponseEntity.status((HttpStatus.OK)).body("SUCCESS");
   // }

  /*  @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        try {
            List<Tutorial> tutorials = fileService.getAllTutorials();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   */
}