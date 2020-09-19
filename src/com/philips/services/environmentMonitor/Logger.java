package com.philips.services.environmentMonitor;

import java.io.FileWriter;

import com.philips.interfaces.common.ILogger;
import com.philips.services.fileHandler.FileOperations;

public class Logger implements ILogger {
	private FileOperations fileOperations;
	private FileWriter writer;
	private String filePath;
	
	public Logger(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void logger(String message) {
		System.out.print(message);
		invokeWriteOperationToFile(message);
	}

	@Override
	public void invokeWriteOperationToFile(String message) {
		fileOperations = new FileOperations(filePath);
		writer = fileOperations.openWriterStream();
		fileOperations.appendFile(writer, message);
		fileOperations.closeWriter(writer);
	}
	
}
