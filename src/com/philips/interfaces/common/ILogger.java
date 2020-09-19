package com.philips.interfaces.common;


public interface ILogger {
	public void logger(String message);
	public void invokeWriteOperationToFile(String message);
}
