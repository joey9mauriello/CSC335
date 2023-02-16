package model;

public class TemperatureConverter {

	public static double FtoC(double d) {
		return (d - 32.0) * (5.0/9.0);
	}

	public static double CtoF(double d) {
		return d*1.8 + 32;
	}

}
