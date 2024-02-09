/**
 * @author Jake Bode & Joey Mauriello
 * Main class for JavaFX Sudoku Application
 */

package view_controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Game;

public class SudokuGUI extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Start method for JavaFX application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane everything = new GridPane();
		everything.setStyle("-fx-background-color: white;"
						  + "-fx-grid-lines-visible: true;");
		
		Game game = new Game();
		
		// sets up title at top of GUI
		Label title = new Label("Sudoku");
		title.setFont(new Font("Cambria Math", 50.0));
		
		Label credits = new Label("Kramerica Industries");
		credits.setFont(new Font("Cambria Math", 20.0));
		credits.setStyle("-fx-background-color: #CFCFCF;");
		
		// creates game and login UI panels
		LoginPanel loginPanel = new LoginPanel();
		GamePanel gamePanel = new GamePanel(game, loginPanel);
		
		everything.add(title, 1, 1, 2, 1);
		everything.add(credits, 3, 1);
		everything.add(loginPanel, 3, 2);
		everything.add(gamePanel, 1, 2, 2, 1);
		
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setHalignment(credits, HPos.CENTER);
		
		Scene scene = new Scene(everything, 1253, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				loginPanel.serializeAccounts();
				Platform.exit();
				System.exit(0);
			}
		});
	}
}
