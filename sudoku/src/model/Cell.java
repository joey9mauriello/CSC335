/**
 * @author Jake Bode & Joey Mauriello
 * Cell class, contains correct value and position of Cell in grid
 */

package model;

public class Cell {
	
	private int row;
	private int col;
	
	private int userValue;
	private int correctValue;
	private int numNotes;
	private boolean[] notes;
	private boolean isPermanent;

	/**
	 * Constructor for Cell object
	 * @param row - row index of cell in grid
	 * @param col - col index of cell in grid
	 * @param correct - correct value belonging in cell
	 * @param permanent - whether cell is set as permanent
	 */
	public Cell(int row, int col, int correct, boolean permanent) {
		correctValue = correct;
		isPermanent = permanent;
		notes = new boolean[9];
		numNotes = 0;
		userValue = permanent ? correctValue : 0;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Constructor for Cell object in empty grid
	 */
	public Cell() {
		userValue = 0;
		isPermanent = false;
		notes = new boolean[9];
		numNotes = 0;
		correctValue = -1;
	}
	
	/**
	 * Get the row of the current cell
	 * @return An integer of the row the cell is in
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Get the column of the current cell
	 * @return An integer of the column the cell is in
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Get the value the user has placed
	 * @return An integer of the user selected value
	 */
	public int getValue() {
		return userValue;
	}

	/**
	 * Set the cell to the value the user wants to place
	 * @param value The value the user wants to place
	 */
	public void setValue(int value) {
		userValue = value;
	}
	
	/**
	 * Clear the value the user placed
	 */
	public void emptyCell() {
		userValue = 0;
	}
	
	/**
	 * Check if the cell has no user value
	 * @return A boolean based on if the user has placed a value in this cell
	 */
	public boolean isEmpty() {
		return userValue == 0;
	}
	
	/**
	 * Get the correct value of the cell
	 * @return An integer of the correct value
	 */
	public int getCorrect() {
		return correctValue;
	}
	
	/**
	 * Set the cell's correct value
	 * @param val The correct value of this cell
	 */
	public void setCorrect(int val) {
		correctValue = val;
	}
	
	/**
	 * Check if the users value is correct
	 * @return A boolean based on if the users value is correct
	 */
	public boolean isCorrect() {
		return userValue == correctValue;
	}
	
	/**
	 * Check if the cell is a permanent cell
	 * @return A boolean based on if the cell is permanent
	 */
	public boolean isPermanent() {
		return isPermanent;
	}
	
	/**
	 * Check if a certain value is in the cell's notes
	 * @param val The value to be checked against the cell's notes
	 * @return A boolean based on if the cell's notes contains the value
	 */
	public boolean containsNote(int val) {
		return notes[val - 1];
	}
	
	/**
	 * Check if the cell contains any notes
	 * @return A boolean based on if the cell contains any notes
	 */
	public boolean containsNotes() {
		return numNotes > 0;
	}

	/**
	 * Get the list of the cell's notes
	 * @return A list of booleans identifying the cell's notes
	 */
	public boolean[] getNotes() {
		return notes;
	}
	
	/**
	 * Add a note to the cell's notes
	 * @param note The note to be added to the cell's notes
	 */
	public void addNote(int note) {
		notes[note - 1] = true;
		numNotes++;
	}
	
	/**
	 * Remove a note from the cell's notes
	 * @param note The note to be removed from the cell's notes
	 */
	public void removeNote(int note) {
		notes[note - 1] = false;
		numNotes--;
	}

	/**
	 * Clear all of the cell's notes
	 */
	public void clearNotes() {
		for (int i = 0; i < 9; i++) {
			notes[i] = false;
		}
		numNotes = 0;
	}

}
