/**
 * @author Jake Bode, Dimitri Koliopoulis & Joey Mauriello
 * Account Controller class, which deals with login/logout and storing user info
 */

package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

public class AccountController {

	private TreeMap<String, User> accounts;
	private String currentUser;

	/**
	 * Constructor for new AccountController
	 */
	public AccountController() {
		accounts = new TreeMap<String, User>();
	}

	/**
	 * Constructor for AccountController from previous start
	 * @param accountsSer - serialized user info stored in TreeMap type
	 */
	public AccountController(TreeMap<String, User> accountsSer) {
		accounts = accountsSer;
		System.out.println(accounts.get("mikeal").getPassword());
	}

	/**
	 * Save the account data of all accounts to a ser file
	 */
	public void serializeAccounts() {
		if (currentUser != null)
			accounts.get(currentUser).logOut();
		String fileName = "Accounts.ser";
		try {
			FileOutputStream bytesToDisk = new FileOutputStream(fileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(accounts);
			outFile.close();
		} catch (IOException ioe) {
			System.out.println("Writing objects failed");
		}
	}
	
	/**
	 * Check if there is a user currently logged in
	 * @return A boolean based on if there is a user logged in or not
	 */
	public boolean isLoggedIn() {
		return currentUser != null;
	}

	/**
	 * Get the current logged in user
	 * @return The logged in user, or null if no one is logged in
	 */
	public User getCurrentUser() {
		if (currentUser == null) {
			return null;
		}
		return accounts.get(currentUser);
	}

	/**
	 * Log out the current user
	 * @return A boolean based on if the the current user was logged out
	 */
	public boolean logOut() {
		if (currentUser != null) {
			accounts.get(currentUser).logOut();
			currentUser = null;
			return true;
		}
		return false;
	}

	/**
	 * Create new valid account
	 * @param username The desired user name
	 * @param password The desired password
	 * @return A string containing the action that happened
	 */
	public String createAccount(String username, String password) {
		if (accounts.containsKey(username) == true) {
			return "Username already taken";
		}
		if (accounts.containsKey(username) == false) {
			if (username != "" && password != "") {
				User account = new User(username, password);
				accounts.put(username, account);
				return "Account created";
			}
		}
		return "Account not created";
	}

	/**
	 * Try to log a user in
	 * @param username The attempted user name
	 * @param password The attempted password
	 * @return A boolean based on if the user was successfully logged in
	 */
	public boolean loggingIn(String username, String password) {
		if (currentUser != null) {
			return false;
		} else if (accounts.containsKey(username) == false || username.length() > 20) {
			return false;
		} else if (currentUser == null && accounts.get(username).getPassword().equals(password)) {
			currentUser = username;
			accounts.get(currentUser).logIn();
			return true;
		}
		return false;
	}

}
