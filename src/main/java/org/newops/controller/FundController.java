package org.newops.controller;

import org.newops.model.Fund;
import org.newops.model.Holding;
import org.newops.repository.FundRepository;
import org.newops.repository.HoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/fund")
public class FundController {

    @Autowired
    FundRepository repository;

    //  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/")
    public ResponseEntity<?> getFund() throws ParseException {
        System.out.println("Number of rows in repository are" + repository.count());

        //   Date AsOfDate =  formatter1.parse(holdingdate);
        //   String testfund =   "Aditya Birla Sun Life Equity Advantage Fund";
        List<Fund> funds = repository.findAll();

        return new ResponseEntity<>(funds, HttpStatus.OK);
        //         (((Fund(ArrayList)) funds).get(0)).SchemeName,HttpStatus.OK) ;
        //( "ALL GOOD", HttpStatus.OK);
    }

    @PostMapping("/{amficode}")
    public ResponseEntity<?> updateAMCCode(@PathVariable("amficode") Integer amficode, @RequestParam("amccode") String amccode) {

        Fund f = repository.findByamfiCode(amficode);

        f.setAmcCode(amccode);

        repository.save(f);

        return new ResponseEntity<>(f, HttpStatus.OK);

    }


}
