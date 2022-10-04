package com.accenture.bars.factory;

import com.accenture.bars.exception.BarsException;
import com.accenture.bars.file.AbstractInputFile;
import com.accenture.bars.file.CSVInputFileImpl;
import com.accenture.bars.file.TextInputFileImpl;
import java.io.File;


public class InputFileFactory {
    
    private static InputFileFactory factory;
    
    private InputFileFactory(){
        //default Constructor
    }

    public static InputFileFactory getInstance() {
    if (factory == null) {
    	
        factory = new InputFileFactory();
      }
        return factory;
    }

    public AbstractInputFile getInputFile(File file) throws BarsException {

		if (file.getName().endsWith("txt")) {
			return new TextInputFileImpl();
		} else if (file.getName().endsWith("csv")) {
			return new CSVInputFileImpl();
		} else if (!file.exists()) {
            throw new BarsException(BarsException.PATH_DOES_NOT_EXIST);
        } else {
			throw new BarsException(BarsException.FILE_NOT_SUPPORTED);
		}
	}
        
}