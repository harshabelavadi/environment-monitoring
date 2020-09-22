package com.philips.interfaces;

/**
 * This IAlert interface
 * is for generating Email and SMS alert messages on warning/error condition
 	 
 */
public interface IAlert {
	void sendMessage(String communicationMode, String parameterName, Double value, String message);
}
