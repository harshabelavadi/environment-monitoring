package com.philips.constants;

public class EnvironmentConstants {
	
	public enum StringConstants
	{
		TEXT("text/plain"), HTML("text/html"), CSV("application/vnd.ms-excel"),
		TEMPERATURE("Temperature"), HUMIDITY("Humidity"),
		CSVFILEPATH("environmentCondition.csv"), SENDERLOGSPATH("senderLogs.txt"),
		MONITORLOGSPATH("monitorLogs.txt"), RECEIVERINPUTPATH("receiverInput.txt"),
		RECEIVERLOGSPATH("receiverLogs.txt"), COMMA(","), NEWLINE("\n"), EMPTY("");
		
		private final String stringConstant;
		private StringConstants(final String constant)
		{
			this.stringConstant = constant;
		}
		
		public String get()
		{
			return this.stringConstant;
		}
	}
	
	public enum LogMessageConstants
	{
		CSVFILEFORMATERROR("File format is incorrect: Please create a new csv file\n"),
		WAITFORSENDER("Waiting for sender to send data.\n"),
		
		NORMALCONDITION("Record recieved at : %s, %s : %s, Condition : Normal\n"),
		WARNING("Record recieved at : %s, %s : %s, Condition : WARNING!\n"),
		ERROR("Record recieved at : %s, %s : %s, Condition : ERROR!\n"),
		
		EMAILALERTWARNING("\nEmail Alert sent to : %s at : %s, %s : %s, Condition : WARNING!\n"),
		EMAILALERTERROR("\nEmail Alert sent to : %s at : %s, %s : %s, Condition : ERROR!\n"),
		SMSALERTWARNING("SMS Alert sent to SMS : %s at : %s, %s : %s, Condition : WARNING!\n\n"),
		SMSALERTERROR("SMS Alert sent to SMS : %s at : %s, %s : %s, Condition : ERROR!\n\n"),
		
		GENERATED_RECORD("Record generated at : %s Temperature : %s, Humidity : %s\n"),
		TIMEOUT("The receiver waiting limit exceeded! No response from the sender. Terminating Application!\n"),
		EMAIL_ADDRESS("user@philips.com"), SMS_NUMBER("+91-9488394212");
				
		private final String logMessage;
		private LogMessageConstants(final String constant)
		{
			this.logMessage = constant;
		}
		
		public String get()
		{
			return this.logMessage;
		}
	}
	
	public enum NumberConstants 
	{
		ZERO(0), ONE(1), MILLISECONDS(5000), TEMPERATURE_MAX(60), RECEIVERWAIT(2500),
		HUMIDITY_MAX(40), TEMPERATURE_MIN(15), HUMIDITY_MIN(10), TIMEOUT(60000);
		
		private final int number;
		private NumberConstants(final int number)
		{
			this.number = number;
		}
		
		public int get()
		{
			return this.number;
		}
	}
	
	public enum FactorConstants 
	{
		TEMPERATURE_WARNLOW(8.0), TEMPERATURE_WARNHIGH(45.0), TEMPERATURE_ERRORLOW(5.0), TEMPERATURE_ERRORHIGH(50.0),
		HUMIDITY_WARNLOW(15.0), HUMIDITY_WARNHIGH(35.0), HUMIDITY_ERRORLOW(10.0), HUMIDITY_ERRORHIGH(40.0),
		DECIMAL_SCALE(1000.0);
		
		private final Double number;
		private FactorConstants(final Double number)
		{
			this.number = number;
		}
		
		public Double get()
		{
			return this.number;
		}
	}
}
