package com.philips.environmentMonitor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.services.environmentMonitor.FactorValidator;

/**
 * This ProcessSenderDataTest is a unit test class
 * to test if the parameters in range or not
 */

public class FactorValidatorTest {

	private final String temperatureParamName = StringConstants.TEMPERATURE.get();
	private final String humidityParamName = StringConstants.HUMIDITY.get();
	private FactorValidator factorValidatorTest = new FactorValidator();
	
	@Test
	public void isParameterInRange() {		
		assertEquals(factorValidatorTest.isParameterInRange(humidityParamName, 25.36),false);
		assertEquals(factorValidatorTest.isParameterInRange(temperatureParamName, 5.36),true);
		assertEquals(factorValidatorTest.isParameterInRange(humidityParamName, 2.36),false);
		assertEquals(factorValidatorTest.isParameterInRange(temperatureParamName, 45.36),false);
		assertEquals(factorValidatorTest.isParameterInRange(humidityParamName, 60.36),true);
	}
	
	@Test
	public void isTemperatureInRange() {
		
		assertEquals(factorValidatorTest.isTemperatureInRange(5.71),true);
		assertEquals(factorValidatorTest.isTemperatureInRange(38.01),false);
		assertEquals(factorValidatorTest.isTemperatureInRange(3.1),false);
		
	}
	
	@Test
	public void isHumidityInRange() {
		assertEquals(factorValidatorTest.isHumidityInRange(38.89),true);
		assertEquals(factorValidatorTest.isHumidityInRange(73.12),false);
		assertEquals(factorValidatorTest.isHumidityInRange(20.56),false);		
	}
	
}