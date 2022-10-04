package com.accenture.barsservice.TextInputFileImplTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.accenture.bars.domain.Request;
import com.accenture.bars.exception.BarsException;
import com.accenture.bars.factory.InputFileFactory;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.file.TextInputFileImpl;



public class TextInputFileImplTest {

	@Test
	public void testReadValidRequestParameter() throws BarsException, NumberFormatException, IOException {
		InputFileFactory factory = InputFileFactory.getInstance();
		String filePath = "C:/BARS_TEST/valid-txt.txt";
		String expected ="[Request[billingCycle=1, startDate=2013-01-15, endDate=2013-02-14]" + 
				"[Request[billingCycle=1, startDate=2016-01-15, endDate=2016-02-14]";
		
		File file  = new File(filePath);
		AbstractInputFile fileRead = factory.getInputFile(file);
		fileRead.setFile(file);
		List <Request> requests = fileRead.readFile();
		assertEquals(requests.toString(),expected);
	}
	
	@Test
	public void readInvalidRequest() throws IOException,BarsException {
		String filePath = "C:/BARS_TEST/billing-cycle-not-on-range-txt.txt";
		InputFileFactory factory = InputFileFactory.getInstance();
		File file = new File(filePath);
		AbstractInputFile txtInputFile = factory.getInputFile(file);
		assertTrue(txtInputFile instanceof TextInputFileImpl);
	}
	@Test
	public void readInvalidRequestStartDate() throws IOException,BarsException {
		String filePath = "C:/BARS_TEST/invalid-start-date-txt.txt";
		InputFileFactory factory = InputFileFactory.getInstance();
		File file = new File(filePath);
		AbstractInputFile txtInputFile = factory.getInputFile(file);
		assertTrue(txtInputFile instanceof TextInputFileImpl);
	}
	@Test
	public void readInvalidRequestEndDate() throws IOException,BarsException {
		String filePath = "C:/BARS_TEST/invalid-end-date-txt.txt";
		InputFileFactory factory = InputFileFactory.getInstance();
		File file = new File(filePath);
		AbstractInputFile txtInputFile = factory.getInputFile(file);
		assertTrue(txtInputFile instanceof TextInputFileImpl);
	}
}
