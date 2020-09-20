package com.philips.interfaces;

public interface IAlert {
	void sendMessage(String communicationMode, String parameterName, Double value, String message);
}
