package com.philips.services.environmentMonitor;

import java.io.BufferedReader;
import java.io.IOException;

import com.philips.constants.EnvironmentConstants.LogMessageConstants;
import com.philips.constants.EnvironmentConstants.NumberConstants;
import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.interfaces.IFileOperations;
import com.philips.interfaces.IFileValidator;
import com.philips.interfaces.ILogger;
import com.philips.services.fileHandler.FileOperations;
import com.philips.services.fileHandler.FileValidator;

public class ReceiverReadData {

	private final String receiverInputPath = StringConstants.RECEIVERINPUTPATH.get();
	private final String receiverLogsPath = StringConstants.RECEIVERLOGSPATH.get();
	private final String waitingForSender = LogMessageConstants.WAITFORSENDER.get();
	private final int milliseconds = NumberConstants.MILLISECONDS.get();
	private final int receiverWait = NumberConstants.RECEIVERWAIT.get();
	
	private ProcessSenderData processData = new ProcessSenderData();
	
	private IFileOperations fileOperations;
	private IFileValidator fileValidate;
	private ILogger receiverLogger;
	private String receivedData;
	private BufferedReader reader;
	private int waitLimit; 
	
	/**
	 * This ReceiverReadData class
	 * is to initially validate file and wait for sender to create if it doesn't exist  
	 * wait for sender to generate a record for reading
	 * wait has been set to predefined milliseconds in the constant file 
	 * and Terminate after the breach in predefined wait limit
	 */

	
	public ReceiverReadData() {
		try {
			
			fileOperations = new FileOperations(receiverInputPath);
			fileValidate = new FileValidator(receiverInputPath, StringConstants.TEXT.get());
			receiverLogger = new Logger(receiverLogsPath);
			
			if (!fileValidate.fileExists()) {
				receiverLogger.logger(waitingForSender);
				Thread.sleep(milliseconds); 
			} 
			
			reader = fileOperations.openReaderStream();
			fileOperations.readFile(reader);
			setReceivedData(reader.readLine());
			
			while (reader!=null) {
				
				setWaitLimit(NumberConstants.ZERO.get());
				while (receivedData == null) {
					receiverLogger.logger(waitingForSender);
					Thread.sleep(receiverWait);
					setReceivedData(reader.readLine());
					setWaitLimit(waitLimit + receiverWait);
					
					if (waitLimit >= NumberConstants.TIMEOUT.get()) {
						System.out.println(LogMessageConstants.TIMEOUT.get());
						fileOperations.closeReader(reader);
						return;
					}
				}			
				
				processData.processFileData(receivedData);
				Thread.sleep(receiverWait);
				setReceivedData(reader.readLine());
			}
		} catch (InterruptedException | IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void setReceivedData(String receivedData) {
		this.receivedData = receivedData;
	}

	public void setWaitLimit(int waitLimit) {
		this.waitLimit = waitLimit;
	}
}