package com.philips.services.environmentMonitor;

import java.util.Date;

import com.philips.constants.EnvironmentConstants.FactorConstants;
import com.philips.constants.EnvironmentConstants.LogMessageConstants;
import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.interfaces.IFactorValidator;
import com.philips.interfaces.ILogger;


public class FactorValidator implements IFactorValidator {
	private final String receiverLogsPath = StringConstants.RECEIVERLOGSPATH.get();
	private final String normalCondition = LogMessageConstants.NORMALCONDITION.get();
	
	private final String temperatureParamName = StringConstants.TEMPERATURE.get();
	private final String humidityParamName = StringConstants.HUMIDITY.get();
	
	private ILogger environmentConditionLogger = new Logger(receiverLogsPath);;
	private final Double temperatureWarnLow = FactorConstants.TEMPERATURE_WARNLOW.get();
	private final Double temperatureWarnHigh = FactorConstants.TEMPERATURE_WARNHIGH.get();
	
	private final Double humidityWarnLow = FactorConstants.HUMIDITY_WARNLOW.get();
	private final Double humidityWarnHigh = FactorConstants.HUMIDITY_WARNHIGH.get();
	

	@Override
	public boolean isParameterInRange(String parameterName, Double value) {
		if (parameterName.equals(temperatureParamName))
			return isTemperatureInRange(value); //this is a boolean but why is it returning value
		return isHumidityInRange(value);
	}

	public boolean isTemperatureInRange(Double value) {
		
		if (value > temperatureWarnLow && value < temperatureWarnHigh) {
			environmentConditionLogger.logger(String.format(normalCondition, new Date().toString(), temperatureParamName, value));
			return true;
		}
		
		return false;
	}
	
	public boolean isHumidityInRange(Double value) {
		
		if (value > humidityWarnLow && value < humidityWarnHigh) {
			environmentConditionLogger.logger(String.format(normalCondition, new Date().toString(), humidityParamName, value));
			return true;
		}
		
		return false;
	}
}
