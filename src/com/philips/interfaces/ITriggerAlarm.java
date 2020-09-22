package com.philips.interfaces;

/**
 * This ITriggerAlarm interface
 * is to trigger warning/error Alarm on breach in limits
 	 
 */

public interface ITriggerAlarm {
	void trigger(String parameterName, Double value);
	boolean isError(String parameterName, Double value);
}
