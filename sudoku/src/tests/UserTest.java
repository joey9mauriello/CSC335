package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.User;

class UserTest {

	@Test
	public void testGetUsername() {
		User user = new User("testuser", "password");
		assertEquals("testuser", user.getUsername());
		assertEquals("password", user.getPassword());
		assertTrue(user.getLoggedIn());
		user.logOut();
		assertFalse(user.getLoggedIn());
		user.logIn();
		assertTrue(user.getLoggedIn());
	}

	
	@Test
	public void testGetWinPercentageWhenNoGamesPlayed() {
		User user = new User("testuser", "password");
		assertEquals(0, user.getTotalWinPercentage(), 0);
		assertEquals(0, user.getEasyWinPercentage(), 0);
		assertEquals(0, user.getMediumWinPercentage(), 0);
		assertEquals(0, user.getHardWinPercentage(), 0);
	}
	
	@Test
	public void testGetWinPercentageWhenGamesPlayed() {
		User user = new User("testuser", "password");
		user.setTotalGamesPlayed();
		user.setTotalGamesWon();
		
		user.setEasyGamePlayed();
		user.setEasyGameWon();
		
		user.setMediumGamePlayed();
		user.setMediumGameWon();
		
		user.setHardGamePlayed();
		user.setHardGameWon();
		
		assertEquals(user.getTotalWinPercentage(), 100.0);
		assertEquals(user.getEasyWinPercentage(), 100.0);
		assertEquals(user.getMediumWinPercentage(), 100.0);
		assertEquals(user.getHardWinPercentage(), 100.0);
	}

	@Test
	public void testGetGamesPlayed() {
		User user = new User("testuser", "password");
		assertEquals(0, user.getTotalGamesPlayed());
		assertEquals(0, user.getEasyGamesPlayed());
		assertEquals(0, user.getMediumGamesPlayed());
		assertEquals(0, user.getHardGamesPlayed());
	}

	@Test
	public void testSetGamesPlayed() {
		User user = new User("testuser", "password");
		user.setTotalGamesPlayed();
		user.setEasyGamePlayed();
		user.setMediumGamePlayed();
		user.setHardGamePlayed();
		
		assertEquals(1, user.getTotalGamesPlayed());
		assertEquals(1, user.getEasyGamesPlayed());
		assertEquals(1, user.getMediumGamesPlayed());
		assertEquals(1, user.getHardGamesPlayed());
	}

	@Test
	public void testGetGamesWon() {
		User user = new User("testuser", "password");
		assertEquals(0, user.getTotalGamesWon());
		assertEquals(0, user.getEasyGamesWon());
		assertEquals(0, user.getMediumGamesWon());
		assertEquals(0, user.getHardGamesWon());
	}

	@Test
	public void testSetGamesWon() {
		User user = new User("testuser", "password");
		user.setTotalGamesWon();
		user.setEasyGameWon();
		user.setMediumGameWon();
		user.setHardGameWon();
		
		assertEquals(1, user.getTotalGamesWon());
		assertEquals(1, user.getEasyGamesWon());
		assertEquals(1, user.getMediumGamesWon());
		assertEquals(1, user.getHardGamesWon());
	}
}
