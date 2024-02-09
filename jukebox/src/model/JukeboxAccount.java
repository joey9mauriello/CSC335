// Joey Mauriello
// Jake Bode

package model;

import java.io.Serializable;
import java.time.LocalDate;

public class JukeboxAccount implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private LocalDate today;
    
    private String username;
    private String password;
    
    private int numOfPlays;
    
    private int totalPlayed;

    public JukeboxAccount(String user, String pass) {
    	today = LocalDate.now();
    	this.username = user;
    	this.password = pass;
    	this.numOfPlays = 3;
    	this.totalPlayed = 0;
    }
    
    public String getUser() {
    	return username;
    }
    
    public boolean validatePassword(String passAttempt) {
    	return password.equals(passAttempt);
    }
    
    public int getPlays() {
    	return numOfPlays;
    }
    
    public boolean nextDay() {
    	LocalDate now = LocalDate.now();
    	if (now.getDayOfYear() == today.getDayOfYear()) {
    		return false;
    	} else {
    		today = now;
    		numOfPlays = 3;
    		return true;
    	}
    }
    
    public void reducePlays() {
    	numOfPlays--;
    	totalPlayed++;
    }
    
    public boolean hasPlays() {
    	return numOfPlays > 0;
    }
    
    public int getTotalPlayed() {
    	return totalPlayed;
    }
}
