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
		if (isError(parameterName, value)) {
			sendAlerts(parameterName, value, emailAlertErrorMessage, smsAlertErrorMessage);
			environmentConditionLogger.logger(String.format(recievedErrorMessage, new Date().toString(), parameterName, value));
		}
		else {
			sendAlerts(parameterName, value, emailAlertWarningMessage, smsAlertWarningMessage);
			environmentConditionLogger.logger(String.format(recievedWarningMessage, new Date().toString(), parameterName, value));
		}
	}
	
	/**
	 * This isError method
	 * return true on error and false on warning
	 */
	
	@Override
	public boolean isError(String parameterName, Double value) {
		if ( isParameterName(parameterName) ) {
			return checkForError(value, temperatureErrorLow, temperatureErrorHigh);
		}
		return checkForError(value, humidityErrorLow, humidityErrorHigh);
	}
	
	public boolean isParameterName(String parameterName) {
		return parameterName.equals(temperatureParamName) ? true : false;
	}
	
	public boolean checkForError(Double value, Double low, Double high ) {
		return (value < low || value > high);
	}
	
	public void sendAlerts(String parameterName, Double value, String emailMessage, String smsMessage) {
		new ReportAlarm(new EmailAlert(), emailAddress, parameterName, value, emailMessage);
		new ReportAlarm(new SMSAlert(), sms, parameterName, value, smsMessage);
	}
}