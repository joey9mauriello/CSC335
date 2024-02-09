// Jake Bode
// Joey Mauriello

package controller_view;

import model.AccountController;
import model.JukeboxAccount;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginCreateAccountPane extends GridPane {
	
	private Label info;
	
	// Objects for user login page
	private Label userLoginLabel;
	private TextField userLoginInput;
	
	private Label passLoginLabel;
	private PasswordField passLoginInput;
	
	private Button loginButton;
	
	private Button newAccountButton;
	
	// Objects for create new account page
	private Label newUserLabel;
	private TextField newUserInput;
	
	private Label newPasswordLabel;
	private PasswordField newPasswordInput;
	
	private Label confirmPasswordLabel;
	private PasswordField confirmPasswordInput;
	
	private Button createAccountButton;
	private Button returnToLogin;
	
	// Controller for account info/login/logout
	private AccountController accountController;
	
	// Objects for user logged in page
	private Label welcomeUser;
	private Label songsPlayed;
	private Label accountInfo;
	
	private String loggedIn;
	private Button logoutButton;
	

	public LoginCreateAccountPane(AccountController controller) {
		accountController = controller;
		this.setMinHeight(100.0);
		this.setMinWidth(700.0);
		this.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-padding: 5px");
		info = new Label();
		createButtons();
		registerHandlers();
		showLoginDisplay();
	}
	
	public void update() {
		if (accountController.loggedIn()) {
			showLoggedInDisplay();
		}
	}
	
	private void addInfoLabel() {
		GridPane.setConstraints(info, 0, 0, GridPane.REMAINING, 1);
		this.getChildren().add(info);
	}
	
	private void createButtons() {
		loginButton = new Button("Login");
		newAccountButton = new Button("Create Account");
		createAccountButton = new Button("Create Account");
		returnToLogin = new Button("Go Back");
		logoutButton = new Button("Log Out");
	}
	
	private void showLoginDisplay() {
		this.getChildren().clear();
		
		addInfoLabel();
		info.setText("  Login below or click Create New Account:");
		
		userLoginLabel = new Label("Username: ");
		userLoginInput = new TextField();
		passLoginLabel = new Label("Password: ");
		passLoginInput = new PasswordField();
		
		userLoginInput.setMinWidth(70.0);
		passLoginInput.setMinWidth(70.0);
		
		this.setHgap(10.0);
		this.setVgap(10.0);
		
		this.add(userLoginLabel, 1, 2);
		this.add(userLoginInput, 2, 2);
		this.add(passLoginLabel, 3, 2);
		this.add(passLoginInput, 4, 2);
		this.add(loginButton, 5, 2);
		this.add(newAccountButton, 5, 3);
	}
	
	private void showCreateAccountDisplay() {
		this.getChildren().clear();
		
		addInfoLabel();
		info.setText("  Create a new account below:");
		
		newUserLabel = new Label("Username: ");
		newPasswordLabel = new Label("Password: ");
		confirmPasswordLabel = new Label("Confirm Password: ");
		
		newUserInput = new TextField();
		newPasswordInput = new PasswordField();
		confirmPasswordInput = new PasswordField();
		
		newUserInput.setMinWidth(50.0);
		newPasswordInput.setMinWidth(50.0);
		confirmPasswordInput.setMinWidth(50.0);
		
		
		this.setHgap(5.0);
		this.setVgap(5.0);
		
		this.add(newUserLabel, 1, 2);
		this.add(newUserInput, 2, 2);
		this.add(newPasswordLabel, 3, 2);
		this.add(newPasswordInput, 4, 2);
		this.add(confirmPasswordLabel, 3, 3);
		this.add(confirmPasswordInput, 4, 3);
		this.add(createAccountButton, 8, 2);
		
		GridPane.setConstraints(returnToLogin, 1, 4, GridPane.REMAINING, 1);
		this.getChildren().add(returnToLogin);
	}
	
	private void showLoggedInDisplay() {
		this.getChildren().clear();
		
		addInfoLabel();
		info.setText("Double click on any song in the table above to add it to the playlist.");
		
		welcomeUser = new Label("Welcome back, " + loggedIn);
		songsPlayed = new Label("Each user can choose to add up to three songs to the playlist "
				+ "each day (resets at 12 AM local time).");
		accountInfo = new Label("Each user can choose to add up to three songs to the playlist " 
				+ "each day (resets at 12 AM local time). \nYou have " 
				+ accountController.getCurrUser().getPlays() + " plays available today. "
				+ "You have played a total of " + accountController.getCurrUser().getTotalPlayed()
				+ " songs all time!");
		
		accountInfo.setWrapText(true);
		this.setVgap(10.0);
		
		this.add(welcomeUser, 1, 2);
		this.add(accountInfo, 1, 3);
		this.add(logoutButton, 1, 4);
	}

	private void registerHandlers() {
		loginButton.setOnAction((event) -> {
			String user = userLoginInput.getText();
			String pass = passLoginInput.getText();
			if (!user.equals("") && accountController.validLogin(user, pass)) {
				loggedIn = user;
				showLoggedInDisplay();
			} else {
				Alert invalidLogin = new Alert(AlertType.INFORMATION);
				invalidLogin.setTitle("Invalid Login");
				invalidLogin.setHeaderText("The username or password you entered are incorrect.");
				invalidLogin.setContentText("Please enter a valid username and password.");
				invalidLogin.showAndWait();
			}
		});
		
		newAccountButton.setOnAction((event) -> {
			showCreateAccountDisplay();
		});
		
		createAccountButton.setOnAction((event) -> {
			String user = newUserInput.getText();
			String pass = newPasswordInput.getText();
			String confirmPass = confirmPasswordInput.getText();
			if (user.length() < 2) {
				Alert usernameInvalid = new Alert(AlertType.INFORMATION);
				usernameInvalid.setTitle("Username Invalid");
				usernameInvalid.setHeaderText("The username you entered is too short.");
				usernameInvalid.setContentText("Please enter a valid username of at least 2 characters.");
				usernameInvalid.showAndWait();
			} else if (accountController.userExists(user)) {
				Alert userExists = new Alert(AlertType.INFORMATION);
				userExists.setTitle("Username Taken");
				userExists.setHeaderText("An account already exists under this username.");
				userExists.setContentText("Please log in or enter a different username.");
				userExists.showAndWait();
			} else if (!pass.equals(confirmPass)) {
				Alert passwordsDiffer = new Alert(AlertType.INFORMATION);
				passwordsDiffer.setTitle("Password Mismatch");
				passwordsDiffer.setHeaderText("The passwords you entered do not match.");
				passwordsDiffer.setContentText("Please make sure both password entries match.");
				passwordsDiffer.showAndWait();
			} else if (pass.length() < 3) {
				Alert passwordTooShort = new Alert(AlertType.INFORMATION);
				passwordTooShort.setTitle("Invalid");
				passwordTooShort.setHeaderText("The password you entered is too short.");
				passwordTooShort.setContentText("Please make sure your password is at least 3 characters.");
				passwordTooShort.showAndWait();
			} else {
				accountController.addNew(new JukeboxAccount(user, pass));
				showLoginDisplay();
			}
		});
		
		returnToLogin.setOnAction((event) -> {
			showLoginDisplay();
		});
		
		logoutButton.setOnAction((event) -> {
			accountController.logout();
			showLoginDisplay();
		});
		
	}

}