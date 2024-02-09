/**
 * @author Jake Bode & Joey Mauriello
 * Game Panel to display game state and controls for JavaFX Application
 */

package view_controller;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import model.Game;

public class GamePanel extends GridPane {

	private StackPane gameView;
	private BorderPane instructions;
	private Label message;
	
	private Game game;
	private TilePane sudokuGrid;
	private CellBlock[][] cells;
	
	private GridPane difficultySelectors;
	private Button easyButton;
	private Button mediumButton;
	private Button hardButton;
	
	private GridPane otherControls;
	private Label mistakes;
	
	private Timer timer;
	private int currTime;
	private Label timeView;
	
	private boolean paused;
	private Button pause;
	
	private GridPane gameControls;
	private Button clear;
	private Label newGame;
	private Button addNote;
	private LoginPanel loginPanel;
	
	private MediaPlayer mediaPlayer;
	
	/**
	 * Constructor for GamePanel object, extends GridPane, instantiates view
	 * @param game - Game model object from main method
	 * @param loginPanel - Login Panel, used for updating statistics on game won
	 */
	public GamePanel(Game game, LoginPanel loginPanel) {
		this.game = game;
		this.loginPanel = loginPanel;
		this.game.setAccController(this.loginPanel.getController());
		
		this.setHgap(30.0);
		this.setVgap(20.0);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-padding: 20px 49.3px 80px 49.3px;");
		
		sudokuGrid = generateGridLayout();
		instructions = generateInstructionPane();
		gameView = new StackPane(sudokuGrid, instructions);
		gameView.setMouseTransparent(true);
		this.add(gameView, 0, 1);
		
		difficultySelectors = generateDiffSelectors();
		this.add(difficultySelectors, 0, 0);
		
		startTimer();
		otherControls = generateOther();
		this.add(otherControls, 1, 0);
		
		gameControls = generateGameControls();
		this.add(gameControls, 1, 1);
	}
	
	/**
	 * Setup for for the GUI of the grid of the game display
	 * @return grid layout as a TilePane
	 */
	private TilePane generateGridLayout() {
		TilePane grid = new TilePane();
		grid.setPrefColumns(3);
		grid.setPrefRows(3);
		grid.setTileAlignment(Pos.CENTER);
		cells = game.generateCellTiles(false);
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				CellBlock cellBlocks = cells[row][col];
				grid.getChildren().add(cellBlocks);
			}
		}
		return grid;
	}
	
	/**
	 * Setup for the GUI of the instruction pane to appear on 
	 * inactive game state
	 * @return - instruction panel object
	 */
	private BorderPane generateInstructionPane() {
		BorderPane pane = new BorderPane();
		message = new Label("Select a difficulty above or click "
				+ "'New Game' to start a new game.");
		pane.setCenter(message);
		message.setFont(new Font("Cambria Math", 23.0));
		message.setWrapText(true);
		pane.setStyle("-fx-border-style: solid;"
				+ "-fx-background-color: #FFFFFF;"
				+ "-fx-padding: 20;");
		pane.setMaxSize(400.0, 250.0);
		return pane;
	}
	
	/**
	 * Setup for the game control buttons and appearance
	 * @return - GridPane containing buttons to control the gameplay
	 */
	private GridPane generateGameControls() {
		GridPane gameControls = new GridPane();
		gameControls.setHgap(20.0);
		gameControls.setVgap(20.0);
		gameControls.setMouseTransparent(true);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int buttonVal = 3*i + j + 1;
				Button b = new Button(""+buttonVal);
				b.setFont(new Font("Consolas", 30.0));
				b.setStyle("-fx-background-color: #DDDDDD;");
				b.setMinSize(80.0, 80.0);
				b.setOnMouseClicked(event -> {
					if (game.noteMode()) {
						game.changeNote(buttonVal, false);
					} else {
						game.setValueToAddNext(buttonVal, false);
						if (game.gameWon()) {
							playMedia();
							message.setText("Puzzle completed!\n"
									+ "Select a difficulty above or click"
									+ "'New Game' to start a new game");
							game.updateStats();
							gameView.getChildren().add(instructions);
							gameView.setMouseTransparent(true);
							gameControls.setMouseTransparent(true);
							pause.setMouseTransparent(true);
						} else if (game.gameLost()) {				
							message.setText("Game over -- 3 mistakes made.\n"
									+ "Select a difficulty above or click "
									+ "'New Game' to start a new game");
							game.updateStats();
							gameView.getChildren().add(instructions);
							gameView.setMouseTransparent(true);
							gameControls.setMouseTransparent(true);
							pause.setMouseTransparent(true);
						}
						loginPanel.displayLoggedInPanel();
						updateMistakeCounter();
					}
				});
				gameControls.add(b, j, i);
			}
		}
		
		generateControlButtons();
		
		gameControls.add(newGame, 0, 3, 3, 1);
		gameControls.add(clear, 0, 4);
		gameControls.add(addNote, 1, 4);
		
		return gameControls;
	}
	
	/**
	 * Plays the sound when the game is won
	 */
	private void playMedia() {
		Media media = new Media(new File("media/Capture.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
	}
	
	/**
	 * Updates the label when a mistake is made
	 */
	private void updateMistakeCounter() {
		mistakes.setText("Mistakes: " + game.getMistakes() + "/3");
	}
	
	/**
	 * Setup for the GUI of more of the game control buttons
	 */
	private void generateControlButtons() {
		newGame = new Label("To start a new game, click on a difficulty.");
		newGame.setWrapText(true);
		newGame.setFont(new Font("Cambria Math", 20.0));
		newGame.setStyle("-fx-background-color: #DDDDDD;"
				+ "-fx-padding: 10px;");
		newGame.setMinWidth(280.0);
		newGame.setMaxWidth(280.0);
		
		clear = new Button("Clear");
		clear.setFont(new Font("Cambria Math", 20.0));
		clear.setStyle("-fx-background-color: #DDDDDD;");
		clear.setMinHeight(40);
		clear.setOnMouseClicked((event) -> {
			game.eraseCellContents(false);
		});
		
		addNote = new Button("Notes");
		addNote.setFont(new Font("Cambria Math", 20.0));
		addNote.setStyle("-fx-background-color: #FF0000;");
		addNote.setMinHeight(40);
		addNote.setOnMouseClicked((event) -> {
			game.switchNoteMode();
			if (game.noteMode()) {
				addNote.setStyle("-fx-background-color: #00FF00;");
			} else {
				addNote.setStyle("-fx-background-color: #FF0000;");
			}
		});
	}
	
	/**
	 * Setup of the GUI for the difficulty selectors
	 * @return - GridPane containing the difficulty selector buttons
	 */
	private GridPane generateDiffSelectors() {
		GridPane difficulties = new GridPane();
		difficulties.setHgap(20.0);
		difficulties.setVgap(20.0);
		
		Label diff = new Label("Difficulty:");
		diff.setFont(new Font("Cambria Math", 15.0));
		diff.setStyle("-fx-font-weight: bold;");
		
		easyButton = new Button("Easy");
		setUpDifficultyButton(easyButton, 1);
		mediumButton = new Button("Medium");
		setUpDifficultyButton(mediumButton, 2);
		hardButton = new Button("Hard");
		setUpDifficultyButton(hardButton, 3);
		
		difficulties.add(diff, 1, 1);
		difficulties.add(easyButton, 2, 1);
		difficulties.add(mediumButton, 3, 1);
		difficulties.add(hardButton, 4, 1);
		
		return difficulties;
	}
	
	/**
	 * Changes style and creates event handlers for difficulty buttons
	 * @param b - button to make changes to
	 * @param diff - difficulty corresponding to button (1-3, easy to hard, respectively)
	 */
	private void setUpDifficultyButton(Button b, int diff) {
		b.setFont(new Font("Cambria Math", 15.0));
		b.setStyle("-fx-background-color: #FFFFFF;");
		b.setOnMouseEntered(event -> {
			b.setStyle("-fx-background-color: #ADD8E6;");
		});
		b.setOnMouseExited(event -> {
			if (game.getDifficulty() == 0 || game.getDifficulty() != diff) {
				b.setStyle("-fx-background-color: #FFFFFF;");
			}
		});
		b.setOnMouseClicked(event -> {
			gameView.getChildren().remove(instructions);
			gameView.setMouseTransparent(false);
			gameControls.setMouseTransparent(false);
			pause.setMouseTransparent(false);
			buttonAppearanceReset();
			b.setStyle("-fx-background-color: #ADD8E6;");
			game.startNewGame(diff);
			updateMistakeCounter();
			gameView.getChildren().remove(sudokuGrid);
			sudokuGrid = generateGridLayout();
			gameView.getChildren().add(sudokuGrid);
			currTime = 0;
		});
	}
	
	/**
	 * Resets the timer when a new game is started
	 */
	private void startTimer() {
		currTime = 0;
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					if (!game.gameLost() && !game.gameWon() && !game.paused()) {
						currTime++;
						timeView.setText(currTime/60 + ":" + String.format("%02d", currTime%60));
					}
				});
			}
		}, 0, 1000);
	}
	
	/**
	 * Resets the buttons to original appearance when new one is clicked
	 */
	private void buttonAppearanceReset() {
		easyButton.setStyle("-fx-background-color: #FFFFFF;");
		mediumButton.setStyle("-fx-background-color: #FFFFFF;");
		hardButton.setStyle("-fx-background-color: #FFFFFF;");
	}
	
	/**
	 * Setup for the GUI of the other miscellaneous controls
	 * @return - GridPane containing mistakes, timer, pause button
	 */
	private GridPane generateOther() {
		GridPane otherDisplay = new GridPane();
		otherDisplay.setHgap(18.0);
		otherDisplay.setVgap(20.0);
		
		mistakes = new Label("Mistakes: 0/3");
		mistakes.setFont(new Font("Cambria Math", 15.0));
		mistakes.setStyle("-fx-font-weight: bold;");
		
		timer = new Timer();
		timeView = new Label("0:00");
		timeView.setFont(new Font("Cambria Math", 15.0));
		
		pause = new Button("Pause");
		pause.setMinWidth(47);
		pause.setStyle("-fx-background-color: #DDDDDD;");
		pause.setMouseTransparent(true);
		pause.setOnMouseClicked(event -> {
			paused = !paused;
			game.pause(); 
			String label = paused? "Start" : "Pause";
			pause.setText(label);
			if (paused) {
				message.setText("Game Paused");
				gameView.getChildren().add(instructions);
				gameView.setMouseTransparent(true);
				gameControls.setMouseTransparent(true);
			} else {
				gameView.getChildren().remove(instructions);
				gameView.setMouseTransparent(false);
				gameControls.setMouseTransparent(false);
			}
		});
		
		otherDisplay.add(mistakes, 0, 1);
		otherDisplay.add(timeView, 5, 1);
		otherDisplay.add(pause, 6, 1);
		
		return otherDisplay;
	}
}
