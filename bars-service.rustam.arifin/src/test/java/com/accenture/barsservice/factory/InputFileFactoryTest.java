package com.accenture.barsservice.factory;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;


import com.accenture.bars.factory.InputFileFactory;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.file.CSVInputFileImpl;
import com.accenture.bars.file.TextInputFileImpl;

public class InputFileFactoryTest {

	@Test
	public void testGetInstance() {
	InputFileFactory factory = InputFileFactory.getInstance();
	assertTrue(factory instanceof InputFileFactory); }




	@Test
	public void testGetInputFileTxt() {
	String filePath = "C:/BARS_TEST/valid-txt.txt";
	InputFileFactory factory = InputFileFactory.getInstance();
	File file = new File(filePath);
	AbstractInputFile txtInputFile = factory.getInputFile(file);
	assertTrue(txtInputFile instanceof TextInputFileImpl);
	}
	
	@Test
	public void testGetInputFileCsv() {
	String filePath = "C:/BARS_TEST/valid-csv.csv";
	InputFileFactory factory = InputFileFactory.getInstance();
	File file = new File(filePath);
	AbstractInputFile csvInputFile = factory.getInputFile(file);
	assertTrue(csvInputFile instanceof CSVInputFileImpl);
	}
	
//	@Test
//	public void testGetInputFileUnsupported() {
//	String filePath = "C:/BARS_TEST/unsupported-file.png";
//	String error = "No supported request file found in the file path";
//	InputFileFactory factory = InputFileFactory.getInstance();
//	File file = new File(filePath);
//	AbstractInputFile unsupportedInputFile = factory.getInputFile(file);
////	assertTrue(unsupportedInputFile instanceof CSVInputFileImpl);
////	assertTrue(unsupportedInputFile instanceof TextInputFileImpl);
//	assertEquals(unsupportedInputFile,error );
//	}
}
