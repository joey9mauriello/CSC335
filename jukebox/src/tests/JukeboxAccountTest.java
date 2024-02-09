// Joey Mauriello
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.JukeboxAccount;

class JukeboxAccountTest {

	@Test
	void testGetUser() {
		JukeboxAccount j = new JukeboxAccount("test", "123");
		assertEquals("test", j.getUser());
	}

	@Test
	void testValidatePassword() {
		JukeboxAccount j = new JukeboxAccount("test", "123");
		assertTrue(j.validatePassword("123"));
	}

	@Test
	void testGetPlays() {
		JukeboxAccount j = new JukeboxAccount("test", "123");
		assertEquals(3, j.getPlays());
	}
	
	@Test
	void testNextDay() {
		JukeboxAccount j = new JukeboxAccount("test", "123");
		assertFalse(j.nextDay());
	}
	
	@Test
	void testReducePlays() {
		JukeboxAccount j = new JukeboxAccount("test", "123");
		j.reducePlays();
		assertEquals(2, j.getPlays());
		j.reducePlays();
		assertEquals(1, j.getPlays());
	}
	
	@Test
	void testHasPlays() {
		JukeboxAccount j = new JukeboxAccount("test", "123");
		assertTrue(j.hasPlays());
		j.reducePlays();
		j.reducePlays();
		j.reducePlays();
		assertFalse(j.hasPlays());
	}

}
