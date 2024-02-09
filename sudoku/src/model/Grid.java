/**
 * @author Jake Bode & Joey Mauriello
 * Grid class, contains an array of Cells and operations on grid state
 */

package model;

import view_controller.CellBlock;

public class Grid {
	
	private Cell[][] grid;
	private CellBlock[][] cellBlocks;
	
	/**
	 * Constructor for new grid
	 * @param grid - cell array used for grid
	 */
	public Grid(Cell[][] grid) {
		this.grid = grid;
	}

	/**
	 * Generate the cell tiles for the GUI
	 * @param game The game that is associated with the grid
	 * @param testing Whether the method is being used for testing or not 
	 * @return A 2D array of CellBlock objects that are used for the GUI
	 */
	public CellBlock[][] generateGridDisplay(Game game, boolean testing) {
		CellBlock[][] cellBlocks = new CellBlock[3][3];
		for (int row = 0; row < 9; row+=3) {
			for (int col = 0; col < 9; col+=3) {
				Cell[][] block = new Cell[3][3];
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						block[i][j] = grid[row+i][col+j];
					}
				}
				cellBlocks[row/3][col/3] = new CellBlock(block, game, testing);
			}
		}
		this.cellBlocks = cellBlocks;
		return cellBlocks;
	}
	
	/**
	 * Check if the cell at the given row and column is permanent
	 * @param row The row of the cell to be checked
	 * @param col The column of the cell to be checked
	 * @return A boolean based on if the cell at t given row and column is permanent
	 */
	public boolean isCellPermanent(int row, int col) {
		return grid[row][col].isPermanent();
	}	
	
	/**
	 * Add a value to the cell at the given row and column
	 * @param row The row of the cell to be added to
	 * @param col The column of the cell to be added to
	 * @param value The value to be added at the cell
	 * @param testing Whether the method is being used for testing or not 
	 */
	public void addValue(int row, int col, int value, boolean testing) {
		grid[row][col].clearNotes();
		grid[row][col].setValue(value);
		if (!testing) {
			cellBlocks[row/3][col/3].getCellTile(row%3, col%3).update();
		}
	}
	
	/**
	 * Add a note to the cell at the given row and column
	 * @param row The row of the cell that's getting a new note
	 * @param col The column of the cell that's getting a new note
	 * @param value The value of the note to be added
	 * @param testing Whether the method is being used for testing or not 
	 */
	public void addNote(int row, int col, int value, boolean testing) {
		grid[row][col].emptyCell();
		grid[row][col].addNote(value);
		if (!testing) {
			cellBlocks[row/3][col/3].getCellTile(row%3, col%3).update();
		}
	}
	
	/**
	 * Remove a note of the cell at the given row and column
	 * @param row The row of the cell that's losing a note
	 * @param col The column of the cell that's losing a note
	 * @param value The value of the note to be removed
	 * @param testing Whether the method is being used for testing or not 
	 */
	public void removeNote(int row, int col, int value, boolean testing) {
		grid[row][col].emptyCell();
		grid[row][col].removeNote(value);
		if (!testing) {
			cellBlocks[row/3][col/3].getCellTile(row%3, col%3).update();
		}
	}
	
	/**
	 * Get the collection of Cell objects that make up the grid
	 * @return A 2D array of Cell objects that make up the grid
	 */
	public Cell[][] getGrid(){
		return grid;
	}
	
	/**
	 * Get the value of the cell at the given row and column
	 * @param row The row of the desired cell
	 * @param col The column of the desired cell
	 * @return An integer of the value at the desired cell
	 */
	public int getVal(int row, int col) {
		return grid[row][col].getValue();
	}
	
	/**
	 * Determine whether the cell at the given row and column is permanent or not
	 * @param row The row of the desired cell
	 * @param col The column of the desired cell
	 * @return A boolean based on if the desired cell is permanent or not
	 */
	public boolean getPermanent(int row, int col) {
		return grid[row][col].isPermanent();
	}

	/**
	 * Determine if the grid is complete and correct
	 * @return A boolean based on if the grid is complete and correct
	 */
	public boolean isComplete() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!grid[i][j].isCorrect()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Clear the GUI highlights in the grid
	 */
	public void clearHighlights() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cellBlocks[row][col].clearHighlights();
			}
		}
	}
	/**
	 * Highlight the row, column, and box that surround the selected cell
	 * @param row The row to be highlighted
	 * @param col The column to be hightlighted
	 */
	public void setSurroundingSelected(int row, int col) {
		cellBlocks[row/3][col/3].setBlockSelected();
		
		cellBlocks[row/3][(col/3+1)%3].setRowSelected(row);
		cellBlocks[row/3][(col/3+2)%3].setRowSelected(row);
		
		cellBlocks[(row/3+1)%3][col/3].setColSelected(col);
		cellBlocks[(row/3+2)%3][col/3].setColSelected(col);
	}
	
	/**
	 * Highlights all values that match the value given
	 * @param val - values to search for match in grid
	 */
	public void setMatchingValuesSelected(int val) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cellBlocks[row][col].setMatchingValueSelected(val);
			}
		}
	}
}
