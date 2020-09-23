package com.philips.environmentMonitor;

import static org.junit.Assert.*;
import org.junit.Test;
import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.services.environmentMonitor.TriggerAlarm;

/**
 * This TriggerAlarmTest is a unit test class
 * to test if the parameters have breached the limit
 */

public class TriggerAlarmTest {
	private final String temperatureParamName = StringConstants.TEMPERATURE.get();
	private final String humidityParamName = StringConstants.HUMIDITY.get();
	private TriggerAlarm triggerAlaramTest = new TriggerAlarm();
	
	@Test
	public void isError() {

		assertEquals(triggerAlaramTest.isError(temperatureParamName, 45.172),true );
		assertEquals(triggerAlaramTest.isError(humidityParamName, 100.0),true );
		assertEquals(triggerAlaramTest.isError(temperatureParamName, 32.2),false );
		assertEquals(triggerAlaramTest.isError(humidityParamName, 15.56),false );
		assertEquals(triggerAlaramTest.isError(temperatureParamName, -5.7),true );
		assertEquals(triggerAlaramTest.isError(humidityParamName, 10.12),false );
	}

}