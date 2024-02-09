/**
 * @author Jake Bode & Joey Mauriello
 * Game class, handles gameplay and user interaction with GUI
 */

package model;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import view_controller.CellBlock;
import view_controller.CellTile;

public class Game {
	
	private boolean paused;
	
	private Grid grid;
	private int mistakes;
	private boolean noteMode;

	private CellTile selectedCell;
	
	private int difficulty;
	private final int EASY = 5;
	private int nextEasy;
	private final int MEDIUM = 5;
	private int nextMedium;
	private final int HARD = 5;
	private int nextHard;
	
	private AccountController accCtrl;
	
	/**
	 * Constructor for new Game class
	 */
	public Game() {
		Random rand = new Random();
		nextEasy = rand.nextInt(EASY);
		nextMedium = rand.nextInt(MEDIUM);
		nextHard = rand.nextInt(HARD);
		
		grid = generateGrid("gamepresets/EmptyGrid.txt");
		mistakes = 0;
		paused = false;
	}
	
	/**
	 * Constructor for testing game class
	 * @param customGrid - grid to use for testing
	 */
	public Game(Grid customGrid) {
		grid = customGrid;
		mistakes = 0;
		paused = false;
	}
	
	/**
	 * Set the current account controller
	 * @param accCtrl The desired account controller
	 */
	public void setAccController(AccountController accCtrl) {
		this.accCtrl = accCtrl;
	}
	
	/**
	 * Get the current account controller
	 * @return The current account controller
	 */
	public AccountController getAccCtrl() {
		return accCtrl;
	}
	
	/**
	 * Update the current users stats based on the result of the game
	 */
	public void updateStats() {
		if (accCtrl.isLoggedIn()) {
			User currUser = accCtrl.getCurrentUser();
			if (difficulty == 1) {
				currUser.setEasyGamePlayed();
				if (gameWon()) {
					currUser.setEasyGameWon();
				}
			} else if (difficulty == 2) {
				currUser.setMediumGamePlayed();
				if (gameWon()) {
					currUser.setMediumGameWon();
				}
			} else if (difficulty == 3) {
				currUser.setHardGamePlayed();
				if (gameWon()) {
					currUser.setHardGameWon();
				}
			}
			currUser.setTotalGamesPlayed();
			if (gameWon()) {
				currUser.setTotalGamesWon();
			}
		}
	}
	
	/**
	 * Start a new game with a new grid
	 * @param diff The desired difficulty of the new game
	 */
	public void startNewGame(int diff) {
		difficulty = diff;
		switch (diff) {
		case 1: grid = generateGrid("gamePresets/easyGames/Game" + ++nextEasy + ".txt");
				nextEasy = nextEasy % EASY;
				break;
		case 2: grid = generateGrid("gamePresets/mediumGames/Game" + ++nextMedium + ".txt");
				nextMedium = nextMedium % MEDIUM;
				break;
		case 3: grid = generateGrid("gamePresets/hardGames/Game" + ++nextHard + ".txt");
				nextHard = nextHard % HARD;
				break;
		}
		mistakes = 0;
		noteMode = false;
	}
	
	/**
	 * Generate the grid from a game file
	 * @param fname The name of the file with the new grid
	 * @return The new grid of the desired difficulty
	 */
	public Grid generateGrid(String fname) {
		Cell[][] tempGrid = new Cell[9][9];
		
		File f = new File(fname);
		try {
			Scanner fileReader = new Scanner(f);
			int rowNum = 0;
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				line.replace("\n", "");
				String[] row = line.split(" ");
				for (int col = 0; col < 9; col++) {
					boolean isPermanent = row[col].length() == 2;
					int value = Integer.parseInt(row[col].substring(0, 1));
					tempGrid[rowNum][col] = new Cell(rowNum, col, value, isPermanent);
				}
				rowNum++;
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Grid(tempGrid);
	}
	
	/**
	 * Switch the mode so any numbers selected are added as notes
	 */
	public void switchNoteMode() {
		noteMode = !noteMode;
	}
	
	/**
	 * Check if the game is in note mode
	 * @return A boolean based on if the game is in note mode
	 */
	public boolean noteMode() {
		return noteMode;
	}
	
	/**
	 * Select a cell for number placement
	 * @param cell The cell the user wishes to select
	 * @param testing Whether the method is being used for testing or not 
	 */
	public void selectCell(CellTile cell, boolean testing) {
		selectedCell = cell;
		if (!testing) {
			grid.clearHighlights();
			grid.setSurroundingSelected(cell.getRow(), cell.getCol());
			grid.setMatchingValuesSelected(cell.getVal());
		}
	}
	
	/**
	 * Attempt to add a user value to the selected cell
	 * @param val The value the user wants to add
	 * @param testing Whether the method is being used for testing or not
	 */
	public void setValueToAddNext(int val, boolean testing) {
		if (selectedCell == null)
			return;
		makeMove(selectedCell.getRow(), selectedCell.getCol(), val, testing);
	}
	
	private void makeMove(int row, int col, int value, boolean testing) {
		if (!grid.isCellPermanent(row, col)) {
			grid.addValue(row, col, value, testing);
			grid.setMatchingValuesSelected(value);
		}
		if (!selectedCell.getCell().isCorrect()) {
			mistakes++;
		}
	}
	
	/**
	 * Change the desired note by either adding it or removing it
	 * @param val The note value to be either added or removed
	 * @param testing Whether the method is being used for testing or not
	 */
	public void changeNote(int val, boolean testing) {
		if (selectedCell == null)
			return;
		int row = selectedCell.getRow();
		int col = selectedCell.getCol();
		if (!grid.isCellPermanent(row, col)) {
			if (selectedCell.getCell().containsNote(val)) {
				grid.removeNote(row, col, val, testing);
			} else {
				grid.addNote(row, col, val, testing);
			}
		}
	}
	
	/**
	 * Completely delete the user value and all notes belonging to the cell
	 * @param testing Whether the method is being used for testing or not
	 */
	public void eraseCellContents(boolean testing) {
		if (selectedCell != null) {
			selectedCell.getCell().clearNotes();
			selectedCell.getCell().emptyCell();
			if (!testing) {
				selectedCell.update();
			}
		}
	}
	
	/**
	 * Check if the user lost the game
	 * @return A boolean based on if the user lost the game
	 */
	public boolean gameLost() {
		return mistakes > 2;
	}
	
	/**
	 * Check if the user won the game
	 * @return A boolean based on if the user won the game
	 */
	public boolean gameWon() {
		return grid.isComplete();
	}
	
	/**
	 * Generate the cell tiles of the grid for the GUI
	 * @param testing Whether the method is being used for testing or not
	 * @return A 2D array of CellBlocks that holds the GUI info of the grid
	 */
	public CellBlock[][] generateCellTiles(boolean testing) {
		return grid.generateGridDisplay(this, testing);
	}
	
	/**
	 * Get the difficulty of the current game
	 * @return An integer representing the difficulty of the game
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Get how many mistakes have been made this game
	 * @return An integer representing how many mistakes have been made
	 */
	public int getMistakes() {
		return mistakes;
	}
	
	/**
	 * Either pause or un-pause the current game
	 */
	public void pause() {
		paused = !paused;
	}
	
	/**
	 * Get whether the game is paused or not
	 * @return A boolean based on if the game is paused
	 */
	public boolean paused() {
		return paused;
	}
	
	/**
	 * Get the current grid for this game
	 * @return A grid of the sudoku board cells
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Get the current CellTile that is selected
	 * @return A CellTile of what the user selected
	 */
	public CellTile getSelectedCell() {
		return selectedCell;
	}
	
	/*
	 * Sets the current grid to a new grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
