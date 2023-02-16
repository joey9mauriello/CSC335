/**
* Joey Mauriello
*/
package model;

public class TemperatureConverter {

	/**
	* This converts from fahrenheit to celcius.
	*
	* @param d A double of the degrees in fahrenheit
	* @return A double of the degrees in celcius
	*/
	public static double FtoC(double d) {
		return (d - 32.0) * (5.0/9.0);
	}

	/**
	* This converts from celcius to fahrenheit.
	*
	* @param d A double of the degrees in celcius
	* @return A double of the degrees in fahrenheit
	*/
	public static double CtoF(double d) {
		return d*1.8 + 32;
	}

}
