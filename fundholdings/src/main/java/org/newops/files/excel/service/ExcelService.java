package org.newops.files.excel.service;

import java.io.IOException;
import java.util.List;

import org.newops.files.excel.loader.GenericHoldingLoader;
import org.newops.files.excel.loader.DebtSecuritiesLoader;
import org.newops.files.excel.loader.FundSchemesLoader;
import org.newops.files.excel.loader.PPDISecuritiesLoader;
import org.newops.files.excel.loader.WDMSecuritiesLoader;
import org.newops.files.excel.loader.EquitySecuritiesLoader;
import org.newops.model.IndiaAMCHoldingsXLS;
import org.newops.model.Security;
import org.newops.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.newops.model.Holding;
import org.newops.model.Fund;
import org.newops.repository.HoldingRepository;
import org.newops.repository.FundRepository;

    @Service
    public class ExcelService {
        @Autowired
        HoldingRepository repository;

        @Autowired
        FundRepository fundRepository;

        @Autowired
        SecurityRepository securityRepository;

        public void saveGeneric(IndiaAMCHoldingsXLS amcFile, MultipartFile file) {
            try {
                List<Holding> holdings = GenericHoldingLoader.excelToHoldings(amcFile, file.getInputStream());
                repository.saveAll(holdings);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }

        public void saveAMFIFundSchemes(MultipartFile file) {
            try {
                List<Fund> funds = FundSchemesLoader.csvToFunds(file.getInputStream());
                fundRepository.saveAll(funds);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }

        public void saveEquitySecurities(MultipartFile file) {
            try {
                List<Security> securities = EquitySecuritiesLoader.csvToEquitySecurities(file.getInputStream());
                securityRepository.saveAll(securities);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }

        public void saveDebtSecurities(MultipartFile file) {
            try {
                List<Security> securities = DebtSecuritiesLoader.csvToDebtSecurities(file.getInputStream());
                securityRepository.saveAll(securities);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }


        public void saveWDMSecurities(MultipartFile file) {
            try {
                List<Security> securities = WDMSecuritiesLoader.csvToWDMSecurities(file.getInputStream());
                securityRepository.saveAll(securities);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }

        public void savePPDISecurities(MultipartFile file) {
            try {
                List<Security> securities = PPDISecuritiesLoader.csvToPPDISecurities(file.getInputStream());
                securityRepository.saveAll(securities);
            } catch (IOException e) {
                throw new RuntimeException("fail to store excel data: " + e.getMessage());
            }
        }

        //   public List<Tutorial> getAllTutorials() {
      //      return repository.findAll();
       // }
    }

