package com.philips.services.environmentMonitor;

import java.util.Date;
import java.util.Random;

import com.philips.constants.EnvironmentConstants.FactorConstants;
import com.philips.constants.EnvironmentConstants.LogMessageConstants;
import com.philips.constants.EnvironmentConstants.NumberConstants;
import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.interfaces.common.ILogger;
import com.philips.services.fileHandler.FileValidator;

public class MonitorEnvironmentCondition {
	private final int temperatureMax = NumberConstants.TEMPERATURE_MAX.get();
	private final int temperatureMin = NumberConstants.TEMPERATURE_MIN.get();
	private final int humidityMax = NumberConstants.HUMIDITY_MAX.get();
	private final int humidityMin = NumberConstants.HUMIDITY_MIN.get();
	private final int milliseconds = NumberConstants.MILLISECONDS.get();
	
	private final String commaString = StringConstants.COMMA.get();
	private final String newLineString = StringConstants.NEWLINE.get();
	private String generatedRecordMessage = LogMessageConstants.GENERATED_RECORD.get();
	private final String monitorLogsPath = StringConstants.MONITORLOGSPATH.get();
	private final String receiverInputPath = StringConstants.RECEIVERINPUTPATH.get();
	
	private final String temperatureParamName = StringConstants.TEMPERATURE.get();
	private final String humidityParamName = StringConstants.HUMIDITY.get();	
	private final Double decimalScale = FactorConstants.DECIMAL_SCALE.get();
	
	private ILogger environmentConditionLogger;
	private ILogger addRecordToInputFile;
	private ILogger sendRecordToReceiverInput;
	private FileValidator fileValidate;

	private String filePath;
	private String contentType;
	private String fileFormatErrorMessage;
	private String record;
	private Random randomGenerator;
	private Double temperature;
	private Double humidity;
	
	public MonitorEnvironmentCondition (String filePath, String contentType, String fileFormatErrorMessage) {
		this.filePath = filePath;
		this.contentType = contentType;
		this.fileFormatErrorMessage = fileFormatErrorMessage;
	}
	
	public void monitor()
	{
		addRecordToInputFile = new Logger(filePath);
		environmentConditionLogger = new Logger(monitorLogsPath);
		sendRecordToReceiverInput = new Logger(receiverInputPath);
		fileValidate = new FileValidator(filePath, contentType);
		while(true)
		{
			if (fileValidate.fileExists() && !fileValidate.checkFormat()) {
				environmentConditionLogger.logger(fileFormatErrorMessage);
				return;
			}			
			sleep(milliseconds);
			generateRecord();
			addRecordToInputFile.invokeWriteOperationToFile(record);
			environmentConditionLogger.logger(String.format(generatedRecordMessage, new Date().toString(), temperature, humidity));
			sendRecordToReceiverInput.invokeWriteOperationToFile(record);
		}
	}
	
	private void generateRecord() {
		
		randomGenerator = new Random();
		int temperatureDifference = temperatureMax - temperatureMin;
		int humidityDifference = humidityMax - humidityMin;
				
		temperature = temperatureMin + randomGenerator.nextDouble() * temperatureDifference;
		humidity = humidityMin + randomGenerator.nextDouble() * humidityDifference;
		
		temperature = Math.round(temperature * decimalScale) / decimalScale;
		humidity = Math.round(humidity * decimalScale) / decimalScale;
		
		record = temperatureParamName + commaString + temperature.toString() + newLineString +
				 humidityParamName + commaString + humidity.toString()+ newLineString;
	
	}
	
	private void sleep(int milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
}