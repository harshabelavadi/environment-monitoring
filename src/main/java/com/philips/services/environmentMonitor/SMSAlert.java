package com.philips.services.environmentMonitor;

import java.util.Date;

import com.philips.interfaces.IAlert;

public class SMSAlert implements IAlert {

	
	@Override
	public void sendMessage(String sms, String parameterName, Double value, String message) {
		System.out.print(String.format(message, sms, new Date().toString(), parameterName, value));
	}
}
