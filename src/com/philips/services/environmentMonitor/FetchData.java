package com.philips.services.environmentMonitor;

import java.io.BufferedReader;
import java.io.IOException;

import com.philips.constants.EnvironmentConstants.LogMessageConstants;
import com.philips.constants.EnvironmentConstants.NumberConstants;
import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.interfaces.common.ILogger;
import com.philips.services.fileHandler.FileOperations;
import com.philips.services.fileHandler.FileValidator;

public class FetchData {
	
	private final String receiverInputPath = StringConstants.RECEIVERINPUTPATH.get();
	private final String receiverLogsPath = StringConstants.RECEIVERLOGSPATH.get();
	private final String waitingForSender = LogMessageConstants.WAITFORSENDER.get();
	private final int milliseconds = NumberConstants.MILLISECONDS.get();
	private final int receiverWait = NumberConstants.RECEIVERWAIT.get();
	
	private ProcessSenderData processData = new ProcessSenderData();
	
	private FileOperations fileOperations;
	private FileValidator fileValidate;
	private ILogger receiverLogger;
	private String receivedData;
	private BufferedReader reader;
	private int waitLimit; 
	
	public FetchData() {
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
					setWaitLimit(waitLimit + milliseconds);
					
					if (waitLimit == NumberConstants.TIMEOUT.get()) {
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