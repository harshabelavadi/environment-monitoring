package com.philips.interfaces.common;

public interface ITriggerAlarm {
	void trigger(String parameterName, Double value);
	boolean isError(String parameterName, Double value);
}
