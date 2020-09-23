package com.philips.interfaces;

/**
 * This IFactorValidator interface
 * is to check whether the given parameters are in range
 	 
 */
public interface IFactorValidator {
	boolean isParameterInRange(String parameterName, Double value);
}
