package temperatures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.TemperatureConverter;

class TempConverterTest {

	@Test
	void test() {
		assertEquals(100.0, TemperatureConverter.FtoC(212.0), 0.01);
		assertEquals(32.0, TemperatureConverter.CtoF(0.0), 0.01);
	}

}
