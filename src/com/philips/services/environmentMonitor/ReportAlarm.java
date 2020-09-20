package com.philips.services.environmentMonitor;

import com.philips.interfaces.IAlert;

public class ReportAlarm {

	private IAlert sendAlert;
	
	public ReportAlarm(IAlert sendAlert, String communicationMode, String parameterName, Double value, String message)
	{
		this.sendAlert = sendAlert;
		this.sendAlert.sendMessage(communicationMode, parameterName, value, message);
	}
}
