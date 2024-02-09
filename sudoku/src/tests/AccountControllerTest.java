package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import model.AccountController;
import model.User;

class AccountControllerTest {

	@Test
	void testIsLoggedIn() {
		AccountController acc = new AccountController();
		assertFalse(acc.isLoggedIn());
		acc.createAccount("joey", "123");
		acc.loggingIn("joey", "123");
		assertTrue(acc.isLoggedIn());
	}
	
	@Test
	void testGetCurrentUser() {
		TreeMap<String, User> accounts = new TreeMap<>();
		User user = new User("joey", "123");
		accounts.put("joey", user);
		AccountController acc = new AccountController(accounts);
		assertEquals(null, acc.getCurrentUser());
		acc.loggingIn("joey", "123");
		assertEquals(user, acc.getCurrentUser());
	}
	
	@Test
	void testLogOut() {
		AccountController acc = new AccountController();
		assertFalse(acc.logOut());
		acc.createAccount("joey", "123");
		acc.loggingIn("joey", "123");
		assertTrue(acc.logOut());
		assertEquals(null, acc.getCurrentUser());
	}
	
	@Test
	void testCreateAccount() {
		AccountController acc = new AccountController();
		acc.createAccount("joey", "123");
		assertEquals("Username already taken", acc.createAccount("joey", "234"));
		assertEquals("Account created", acc.createAccount("test", "123"));
		assertEquals("Account not created", acc.createAccount("hello", ""));
		acc.loggingIn("joey", "123");
		assertEquals("joey", acc.getCurrentUser().getUsername());
	}
	
	@Test
	void testLoggingIn() {
		AccountController acc = new AccountController();
		acc.createAccount("joey", "123");
		acc.loggingIn("joey", "123");
		assertFalse(acc.loggingIn("hello", "test"));
		acc.logOut();
		assertFalse(acc.loggingIn("test", "123"));
		assertFalse(acc.loggingIn("joey", "345"));
		assertTrue(acc.loggingIn("joey", "123"));
		assertEquals("joey", acc.getCurrentUser().getUsername());
	}
	
	@Test
	void testSerializeAccounts() {
		AccountController acc = new AccountController();
		acc.createAccount("joey", "123");
		acc.loggingIn("joey", "123");
		acc.serializeAccounts();
	}

}
