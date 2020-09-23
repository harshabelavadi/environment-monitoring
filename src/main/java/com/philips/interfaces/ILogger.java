package com.philips.interfaces;

/**
 * This ILogger interface
 * is to log output on console and files
 	 
 */
public interface ILogger {
	public void logger(String message);
	public void invokeAppendOperationToFile(String message);
}
