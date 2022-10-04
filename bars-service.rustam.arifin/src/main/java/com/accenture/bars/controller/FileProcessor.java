package com.accenture.bars.controller;


import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.Request;
import com.accenture.bars.entities.Billing;
import com.accenture.bars.exception.BarsException;
import com.accenture.bars.factory.InputFileFactory;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.repository.BillingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileProcessor {
	 private static final Logger log = 
			 LoggerFactory.getLogger(FileProcessor.class);

    @Autowired
    BillingRepository billingRepository;

    public FileProcessor() {
        //empty constructor
    }


	
    public List<Request> execute(File file) throws IOException {

        InputFileFactory inputFileFactory = 
        		InputFileFactory.getInstance();
        
        AbstractInputFile abstractInputFile = 
        		inputFileFactory.getInputFile(file);
        
        abstractInputFile.setFile(file);
        
        List<Request> requests =
        		abstractInputFile.readFile();
        
        if(requests.isEmpty()) {
 		   throw new BarsException(BarsException.NO_REQUESTS_TO_READ);
 	   }
      return requests;
    }


   public List<Record> retrieveRecordfromDB(List<Request> requests) {

       List<Record> records = new ArrayList<>();
        for (Request request : requests) {

            Billing billing = billingRepository
            		.findByBillingCycleAndStartDateAndEndDate(
                            request.getBillingCycle()
                            , request.getStartDate()
                            , request.getEndDate());

            if (billing != null) {
            Record newRecord = new Record();
            newRecord.setBillingCycle(billing.getBillingCycle());
            newRecord.setStartDate(billing.getStartDate());
            newRecord.setEndDate(billing.getEndDate());
            newRecord.setAccountName(billing.getAccountId()
            		.getAccountName());
            newRecord.setFirstName(billing.getAccountId()
            		.getCustomerId().getFirstName());
            newRecord.setLastName(billing.getAccountId()
            		.getCustomerId().getLastname());
            newRecord.setAmount(billing.getAmount());
                // add new record
                records.add(newRecord);
            }
        }

        if (records.isEmpty()) {
        	log.info("hi");
            throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
        } else {
            return records;
        }
    }

}