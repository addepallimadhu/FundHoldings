package org.newops.controller;

import org.newops.model.Holding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.newops.repository.HoldingRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/holding")
public class HoldingController {

    @Autowired
    HoldingRepository repository;

    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/{fundcode}")
    public ResponseEntity<?> getFundHolding(@PathVariable("fundcode") String fundcode, @RequestParam("holdingdate") String holdingdate) throws ParseException {
    System.out.println("Number of rows in repository are" + repository.count());

    Date AsOfDate =  formatter1.parse(holdingdate);

    List<Holding> holdings =  repository.findByFundcodeAndAsofdate(fundcode,AsOfDate);

        return new ResponseEntity<>(holdings,HttpStatus.OK) ;
        //( "ALL GOOD", HttpStatus.OK);
    }

}
