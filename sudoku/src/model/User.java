/**
 * @author Jake Bode, Dimitri Koliopouilus & Joey Mauriello
 * User class to store info about each new login event
 */

package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1851456258495989919L;
	
	private String username;
	private String password;
	private boolean loggedIn;
	
	private int totalGamesPlayed;
	private int totalGamesWon;
	private int easyGamesPlayed;
	private int easyGamesWon;
	private int mediumGamesPlayed;
	private int mediumGamesWon;
	private int hardGamesPlayed;
	private int hardGamesWon;


	/**
	 * Set up username and password and log in
	 * @param username The desired username
	 * @param password The desired password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		loggedIn = true;
	}
	
	/**
	 * Get the users username
	 * @return A string of the users username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get the users password
	 * @return A string of the users password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Log the user out
	 */
	public void logOut() {
		loggedIn = false;
	}

	/**
	 * Log the user in
	 */
	public void logIn() {
		loggedIn = true;
	}
	
	/**
	 * Determine if the user is logged in or not
	 * @return A boolean based on if the user is logged in
	 */
	public boolean getLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Get the percentage of total games won
	 * @return A double of the percent of total games the user has won
	 */
	public double getTotalWinPercentage() {
		if (totalGamesPlayed > 0)
			return totalGamesWon * 100.0 / totalGamesPlayed;
		return 0;
	}
	
	/**
	 * Get the total number of games played
	 * @return An integer of the total games the user has played
	 */
	public int getTotalGamesPlayed() {
		return totalGamesPlayed;
	}

	/**
	 * Increase the total number of games played by 1
	 */
	public void setTotalGamesPlayed() {
		totalGamesPlayed += 1;
	}

	/**
	 * Get the total number of games won
	 * @return An integer of the total games the user has won
	 */
	public int getTotalGamesWon() {
		return totalGamesWon;
	}
 
	/**
	 * Increase the total number of games won by 1
	 */
	public void setTotalGamesWon() {
		totalGamesWon += 1;
	}
	
	/**
	 * Get the percentage of easy games won
	 * @return A double of the percent of easy games the user has won
	 */
	public double getEasyWinPercentage() {
		if (easyGamesPlayed > 0)
			return easyGamesWon * 100.0 / easyGamesPlayed;
		return 0;
	}
	
	/**
	 * Get the number of easy games the user has played
	 * @return An integer of the number of easy games played
	 */
	public int getEasyGamesPlayed() {
		return easyGamesPlayed;
	}
	
	/**
	 * Increase the number of easy games played by 1
	 */
	public void setEasyGamePlayed() {
		easyGamesPlayed++;
	}
	
	/**
	 * Get the number of easy games the user has won
	 * @return An integer of the number of easy games won
	 */
	public int getEasyGamesWon() {
		return easyGamesWon;
	}
	
	/**
	 * Increase the number of easy games won by 1
	 */
	public void setEasyGameWon() {
		easyGamesWon++;
	}
	
	/**
	 * Get the percentage of medium games won
	 * @return A double of the percent of medium games the user has won
	 */
	public double getMediumWinPercentage() {
		if (mediumGamesPlayed > 0)
			return mediumGamesWon * 100.0 / mediumGamesPlayed;
		return 0;
	}
	
	/**
	 * Get the number of medium games the user has played
	 * @return An integer of the number of medium games played
	 */
	public int getMediumGamesPlayed() {
		return mediumGamesPlayed;
	}
	
	/**
	 * Increase the number of medium games played by 1
	 */
	public void setMediumGamePlayed() {
		mediumGamesPlayed++;
	}
	
	/**
	 * Get the number of medium games the user has won
	 * @return An integer of the number of medium games won
	 */
	public int getMediumGamesWon() {
		return mediumGamesWon;
	}
	
	/**
	 * Increase the number of medium games won by 1
	 */
	public void setMediumGameWon() {
		mediumGamesWon++;
	}
	
	/**
	 * Get the percentage of hard games the user has won
	 * @return A double of the percent of hard games won
	 */
	public double getHardWinPercentage() {
		if (hardGamesPlayed > 0)
			return hardGamesWon * 100.0 / hardGamesPlayed;
		return 0;
	}
	
	/**
	 * Get the number of hard games the user has played
	 * @return An integer of the number of hard games played
	 */
	public int getHardGamesPlayed() {
		return hardGamesPlayed;
	}
	
	/**
	 * Increase the number of hard games played by 1
	 */
	public void setHardGamePlayed() {
		hardGamesPlayed++;
	}
	
	/**
	 * Get the number of hard games the user has won
	 * @return An integer of the number of hard games won
	 */
	public int getHardGamesWon() {
		return hardGamesWon;
	}
	
	/**
	 * Increase the number of hard games won by 1
	 */
	public void setHardGameWon() {
		hardGamesWon++;
	}
}
