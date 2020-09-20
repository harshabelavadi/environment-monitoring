package com.philips.services.environmentMonitor;

import java.util.Date;

import com.philips.constants.EnvironmentConstants.FactorConstants;
import com.philips.constants.EnvironmentConstants.LogMessageConstants;
import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.interfaces.ILogger;
import com.philips.interfaces.ITriggerAlarm;

public class TriggerAlarm implements ITriggerAlarm {

	private ILogger environmentConditionLogger;
	
	private final String emailAddress = LogMessageConstants.EMAIL_ADDRESS.get();
	private final String sms = LogMessageConstants.SMS_NUMBER.get();
	
	private final String temperatureParamName = StringConstants.TEMPERATURE.get();
	private final String humidityParamName = StringConstants.HUMIDITY.get();
	
	private final String receiverLogsPath = StringConstants.RECEIVERLOGSPATH.get();
	private final String recievedWarningMessage = LogMessageConstants.WARNING.get();
	private final String recievedErrorMessage = LogMessageConstants.ERROR.get();
	
	private final String emailAlertWarningMessage = LogMessageConstants.EMAILALERTWARNING.get();
	private final String emailAlertErrorMessage = LogMessageConstants.EMAILALERTERROR.get();
	private final String smsAlertWarningMessage = LogMessageConstants.SMSALERTWARNING.get();
	private final String smsAlertErrorMessage = LogMessageConstants.SMSALERTERROR.get();
	
	private final Double temperatureErrorLow = FactorConstants.TEMPERATURE_ERRORLOW.get();
	private final Double temperatureErrorHigh = FactorConstants.TEMPERATURE_ERRORHIGH.get();
	
	private final Double humidityErrorLow = FactorConstants.HUMIDITY_ERRORLOW.get();
	private final Double humidityErrorHigh = FactorConstants.HUMIDITY_ERRORHIGH.get();
	
	
	@Override
	public void trigger(String parameterName, Double value) {
		environmentConditionLogger = new Logger(receiverLogsPath);
		if (isError(parameterName, value))
			environmentConditionLogger.logger(String.format(recievedErrorMessage, new Date().toString(), parameterName, value));
		else
			environmentConditionLogger.logger(String.format(recievedWarningMessage, new Date().toString(), parameterName, value));
	}
	
	@Override
	public boolean isError(String parameterName, Double value) {
		if (parameterName.equals(temperatureParamName))
			return checkTemperature(value);
		return checkHumidity(value);
	}
	
	public boolean checkTemperature(Double value) {
		if (value < temperatureErrorLow || value > temperatureErrorHigh) {
			new ReportAlarm(new EmailAlert(), emailAddress, temperatureParamName, value, emailAlertWarningMessage);
			new ReportAlarm(new SMSAlert(), sms, temperatureParamName, value, smsAlertWarningMessage);
			return true;
		}
		
		new ReportAlarm(new EmailAlert(), emailAddress, temperatureParamName, value, emailAlertErrorMessage);
		new ReportAlarm(new SMSAlert(), sms, temperatureParamName, value, smsAlertErrorMessage);
		return false;
	}
	
	public boolean checkHumidity(Double value) {
		if (value < humidityErrorLow || value > humidityErrorHigh) {
			new ReportAlarm(new EmailAlert(), emailAddress, humidityParamName, value, emailAlertWarningMessage);
			new ReportAlarm(new SMSAlert(), sms, humidityParamName, value, smsAlertWarningMessage);
			return true;
		}
			
		new ReportAlarm(new EmailAlert(), emailAddress, humidityParamName, value, emailAlertErrorMessage);
		new ReportAlarm(new SMSAlert(), sms, humidityParamName, value, smsAlertErrorMessage);
		return false;
	}
}
