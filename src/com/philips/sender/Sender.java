package com.philips.sender;

import com.philips.constants.EnvironmentConstants.LogMessageConstants;
import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.services.environmentMonitor.MonitorEnvironmentCondition;

public class Sender {
	
	public static void main(String[] args)
	{
		new MonitorEnvironmentCondition(StringConstants.CSVFILEPATH.get(),
										StringConstants.CSV.get(), 
										LogMessageConstants.CSVFILEFORMATERROR.get())
										.monitor();
	}
}
