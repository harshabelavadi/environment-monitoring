package com.philips.test.environmentMonitor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.services.environmentMonitor.FactorValidator;

/**
 * This ProcessSenderDataTest is a unit test class
 * to test if the parameters in range or not
 */

public class ProcessSenderDataTest {

	private final String temperatureParamName = StringConstants.TEMPERATURE.get();
	private final String humidityParamName = StringConstants.HUMIDITY.get();
	
	private FactorValidator testObj = new FactorValidator();
	
	
	@Test
	public void isParameterInRange() {
		
		
		assertEquals(testObj.isParameterInRange(humidityParamName, 25.36),true);
		assertEquals(testObj.isParameterInRange(temperatureParamName, 5.36),false);
		assertEquals(testObj.isParameterInRange(humidityParamName, 2.36),false);
		assertEquals(testObj.isParameterInRange(temperatureParamName, 45.36),false);
		assertEquals(testObj.isParameterInRange(humidityParamName, 81.36),false);
	}
	
	public void isTemperatureInRange() {
		
		assertEquals(testObj.isTemperatureInRange(51.01),false);
		assertEquals(testObj.isTemperatureInRange(24.01),true);
		
	}
	
	public void isHumidityInRange() {
		assertEquals(testObj.isHumidityInRange(30.12),true);
		assertEquals(testObj.isHumidityInRange(73.12),false);
		
		
	}
	

}
