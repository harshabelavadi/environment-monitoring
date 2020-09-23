package com.philips.services.environmentMonitor;

import com.philips.constants.EnvironmentConstants.NumberConstants;

import com.philips.constants.EnvironmentConstants.StringConstants;
/**
 * This ProcessSenderData class
 * is to process the received data from sender's output  
 * by invoking FactorValidator object to check in range
 * and triggering triggerAlarm object if breach occured
 */

public class ProcessSenderData {
	private FactorValidator checkCondition = new FactorValidator();
	private TriggerAlarm triggerAlarm;
	private boolean isInRange;
	
	private final String commaDelimiter = StringConstants.COMMA.get();
	private final int indexZero = NumberConstants.ZERO.get();
	private final int indexOne = NumberConstants.ONE.get();
	
	public void processFileData(String receivedData) {
		triggerAlarm = new TriggerAlarm();
		
		String parameterName = receivedData.split(commaDelimiter) [indexZero];
		Double value = Double.parseDouble(receivedData.split(commaDelimiter) [indexOne]);
        
		this.setInRange(checkCondition.isParameterInRange(parameterName, value));
        if (!this.isInRange)
        	triggerAlarm.trigger(parameterName, value);
    }

	public boolean isInRange() {
		return isInRange;
	}

	public void setInRange(boolean isInRange) {
		this.isInRange = isInRange;
	}

}
