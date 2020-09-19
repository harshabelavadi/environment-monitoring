package com.philips.interfaces.common;

public interface IAlert {
	void sendMessage(String communicationMode, String parameterName, Double value, String message);
}
