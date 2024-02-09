// Jake Bode
// Joey Mauriello

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JukeboxAccount> accounts;
	
	private JukeboxAccount currUser;
	
	public AccountController() {
		accounts = new ArrayList<JukeboxAccount>();
		currUser = null;
	}
	
	public void addNew(JukeboxAccount newAcc) {
		accounts.add(newAcc);
	}
	
	public boolean validLogin(String user, String pass) {
		for (JukeboxAccount acc: accounts) {
			if (acc.getUser().equals(user)) {
				boolean correct = acc.validatePassword(pass);
				if (correct) {
					currUser = acc;
				} else {
					return false;
				}
				currUser.nextDay();
				return correct;
			}
		}
		return false;
	}
	
	public void logout() {
		currUser = null;
	}
	
	public boolean userExists(String username) {
		for (JukeboxAccount acc: accounts) {
			if (acc.getUser().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public JukeboxAccount getAccount(String username) {
		for (JukeboxAccount acc : accounts) {
			if (acc.getUser().equals(username)) {
				return acc;
			}
		}
		return null;
	}
	
	public JukeboxAccount getCurrUser() {
		return currUser;
	}
	
	public boolean loggedIn() {
		return currUser != null;
	}
	
}
