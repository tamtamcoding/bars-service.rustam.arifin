package com.accenture.bars.controller;

import com.accenture.bars.domain.Record;
import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class BarsController {

    private static final Logger log = 
    		LoggerFactory.getLogger(BarsController.class);

    @Autowired
    private FileProcessor fileProcessor;

    public BarsController() {

    	//empty constructor
    }
    
    @RequestMapping("/hello")
    public String hello() {
    	return "hello, this is working";
    }


    @GetMapping("/bars")
    public List<Record> requestBilling
    (@RequestParam("filePath") String fileName) 
    		throws BarsException, IOException {

        File filepath = new File("C:\\BARS_TEST\\" + fileName);
        log.info("File Path====== " + fileName);
        log.info("File to process : " + filepath);

        List<Request> request = 
        		fileProcessor.execute(filepath);

        List<Record> records = 
        		fileProcessor.retrieveRecordfromDB(request);

        return records;

    }
}




