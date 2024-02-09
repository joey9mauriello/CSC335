/**
 * @author Jake Bode & Joey Mauriello
 * Login Panel to display user controls for JavaFX Application
 */

package view_controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.TreeMap;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.AccountController;
import model.User;

public class LoginPanel extends GridPane {

	// Objects for login page
	private Label label;
	
	private Label usernameLabel;
	private Label passwordLabel;
	
	private TextField usernameBox;
	private PasswordField passwordBox;
	
	private Button login;
	private Button create;
	
	// Objects for welcome page
	private Button logout;
	private Label welcomeLabel;
	private Label statsLabel;
	private Label totalGamesPlayed;
	private Label easyGamesPlayed;
	private Label mediumGamesPlayed;
	private Label hardGamesPlayed;
	private Label totalGamesWon;
	private Label easyGamesWon;
	private Label mediumGamesWon;
	private Label hardGamesWon;
	private Label totalWinPercent;
	private Label easyWinPercent;
	private Label mediumWinPercent;
	private Label hardWinPercent;

	private AccountController controller;
	
	/**
	 * Constructor for LoginPanel object, extends GridPane, instantiates view
	 */
	public LoginPanel() {
		getSerialized();
		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-padding: 18px;");
		this.setMinWidth(374);
		
		generateLoginPanel();
		generateLoggedInPanel();
		
		displayLoginPanel();
	}
	
	/**
	 * Gets saved account info from previous sessions for saved user accounts
	 */
	@SuppressWarnings("unchecked")
	private void getSerialized() {
		String file = "Accounts.ser";
		FileInputStream rawBytes;
		try {
			rawBytes = new FileInputStream(file);
			ObjectInputStream inFileAcc = new ObjectInputStream(rawBytes);
			
			TreeMap<String, User> accountsSer = new TreeMap<String, User>();
			accountsSer = (TreeMap<String, User>) inFileAcc.readObject();
			controller = new AccountController(accountsSer);
			inFileAcc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Serializes the accounts, saving new login info on exit
	 */
	public void serializeAccounts() {
		controller.serializeAccounts();
	}
	
	/**
	 * getter for AccountController object
	 * @return AccountController object used throughout program
	 */
	public AccountController getController() {
		return controller;
	}

	/**
	 * Sets up display on new session or logout event
	 */
	private void displayLoginPanel() {
		this.getChildren().clear();
		
		this.add(label, 1, 0);
		this.add(usernameLabel, 0, 4);
		this.add(passwordLabel, 0, 5);
		this.add(usernameBox, 1, 4);
		this.add(passwordBox, 1, 5);
		this.add(login, 0, 6);
		this.add(create, 1, 6);
	}
	
	/**
	 * Sets up statistics display on login event
	 */
	public void displayLoggedInPanel() {
		if (!controller.isLoggedIn())
			return;
		
		this.getChildren().clear();
		
		User currUser = controller.getCurrentUser();
		
		welcomeLabel.setText("   Welcome " + currUser.getUsername());
		this.add(welcomeLabel, 0, 0);
		this.add(statsLabel, 0, 1);
		
		totalGamesPlayed.setText("Total Games Played: " + currUser.getTotalGamesPlayed());
		this.add(totalGamesPlayed, 0, 2);
		totalGamesWon.setText("Total Games Won: " + currUser.getTotalGamesWon());
		this.add(totalGamesWon, 0, 3);
		totalWinPercent.setText("Total Win Percentage: " + currUser.getTotalWinPercentage());
		this.add(totalWinPercent, 0, 4);
		
		easyGamesPlayed.setText("Easy Games Played: " + currUser.getEasyGamesPlayed());
		this.add(easyGamesPlayed, 0, 6);
		easyGamesWon.setText("Easy Games Won: " + currUser.getEasyGamesWon());
		this.add(easyGamesWon, 0, 7);
		easyWinPercent.setText("Easy Win Percentage: " + currUser.getEasyWinPercentage());
		this.add(easyWinPercent, 0, 8);
		
		mediumGamesPlayed.setText("Medium Games Played: " + currUser.getMediumGamesPlayed());
		this.add(mediumGamesPlayed, 0, 10);
		mediumGamesWon.setText("Medium Games Won: " + currUser.getMediumGamesWon());
		this.add(mediumGamesWon, 0, 11);
		mediumWinPercent.setText("Medium Win Percentage: " + currUser.getMediumWinPercentage());
		this.add(mediumWinPercent, 0, 12);

		hardGamesPlayed.setText("Hard Games Played: " + currUser.getHardGamesPlayed());
		this.add(hardGamesPlayed, 0, 14);
		hardGamesWon.setText("Hard Games Won: " + currUser.getHardGamesWon());
		this.add(hardGamesWon, 0, 15);
		hardWinPercent.setText("Hard Win Percentage: " + currUser.getHardWinPercentage());
		this.add(hardWinPercent, 0, 16);

		this.add(logout, 0, 18);
	}
	
	/**
	 * Generates JavaFX field objects for login display
	 */
	private void generateLoginPanel() {
		label = new Label("Login or Create Account");
		label.setStyle("-fx-font-weight: bold;");
		label.setFont(new Font("Cambria Math", 20));
		
		usernameLabel = new Label("Username: ");
		usernameLabel.setFont(new Font("Cambria Math", 15));
		usernameBox = new TextField();
		usernameBox.setMaxWidth(135);
		usernameBox.setPromptText("Username");

		passwordLabel = new Label("Password: ");
		passwordLabel.setFont(new Font("Cambria Math", 15));
		passwordBox = new PasswordField();
		passwordBox.setMaxWidth(135);
		passwordBox.setPromptText("Password");
		
		login = new Button("Login");
		login.setStyle("-fx-background-color: #DDDDDD;");
		login.setFont(new Font("Consolas", 15.0));
		login.setOnAction((event) -> {
			String username = usernameBox.getText();
			String password = passwordBox.getText();
			if (controller.loggingIn(username, password))
				displayLoggedInPanel();
			else
				label.setText("Invalid Login");
		});
		
		create = new Button("Create Account");
		create.setStyle("-fx-background-color: #DDDDDD;");
		create.setFont(new Font("Consolas", 15.0));
		create.setOnAction((event) -> {
			String username = usernameBox.getText();
			String password = passwordBox.getText();
			String returnedText = controller.createAccount(username, password);
			usernameBox.setText("");
			passwordBox.setText("");
			label.setText(returnedText);
		});
		
		logout = new Button("Log Out");
		logout.setStyle("-fx-background-color: #DDDDDD;");
		logout.setFont(new Font("Consolas", 15.0));
		logout.setOnAction((event) -> {
			if (controller.logOut())
				displayLoginPanel();
		});
	}
	
	/**
	 * Generates JavaFX fields for logged-in user display
	 */
	private void generateLoggedInPanel() {
		Font font = new Font("Cambria Math", 12);
		
		welcomeLabel = new Label();
		welcomeLabel.setFont(font);
		statsLabel = new Label("Stats:");
		statsLabel.setFont(font);
		
		totalGamesPlayed = new Label();
		statsLabel.setFont(font);
		totalGamesWon = new Label();
		statsLabel.setFont(font);
		totalWinPercent = new Label();
		statsLabel.setFont(font);
		
		easyGamesPlayed = new Label();
		statsLabel.setFont(font);
		easyGamesWon = new Label();
		statsLabel.setFont(font);
		easyWinPercent = new Label();
		statsLabel.setFont(font);
		
		mediumGamesPlayed = new Label();
		statsLabel.setFont(font);
		mediumGamesWon = new Label();
		statsLabel.setFont(font);
		mediumWinPercent = new Label();
		statsLabel.setFont(font);
		
		hardGamesPlayed = new Label();
		statsLabel.setFont(font);
		hardGamesWon = new Label();
		statsLabel.setFont(font);
		hardWinPercent = new Label();
		statsLabel.setFont(font);
	}
}
