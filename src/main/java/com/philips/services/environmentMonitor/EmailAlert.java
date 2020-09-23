package com.philips.services.environmentMonitor;

import java.util.Date;

import com.philips.interfaces.IAlert;

public class EmailAlert implements IAlert {

	@Override
	public void sendMessage(String email, String parameterName, Double value, String message) {
		System.out.print(String.format(message, email, new Date().toString(), parameterName, value));
	}

}
