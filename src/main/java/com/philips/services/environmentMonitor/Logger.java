package com.philips.services.environmentMonitor;

import java.io.FileWriter;

import com.philips.interfaces.IFileOperations;
import com.philips.interfaces.ILogger;
import com.philips.services.fileHandler.FileOperations;

public class Logger implements ILogger {
	private IFileOperations fileOperations;
	private FileWriter writer;
	private String filePath;
	
	public Logger(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void logger(String message) {
		System.out.print(message);
		invokeAppendOperationToFile(message);
	}

	@Override
	public void invokeAppendOperationToFile(String message) {
		fileOperations = new FileOperations(filePath);
		writer = fileOperations.openWriterStream();
		fileOperations.appendFile(writer, message);
		fileOperations.closeWriter(writer);
	}
	
}
