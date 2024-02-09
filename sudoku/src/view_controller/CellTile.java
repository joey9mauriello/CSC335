/**
 * @author Jake Bode & Joey Mauriello
 * CellTile object handles UI and event with users selecting individual cells
 */

package view_controller;

import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import model.Cell;
import model.Game;

public class CellTile extends TilePane {
	
	private Game game;
	
	private Cell cell;
	
	private Label cellDisplay;

	/**
	 * Constructor for Cell Tile
	 * @param cell - cell in model corresponding to UI component
	 * @param game - game object used to control cell tile
	 * @param testing - whether model is being tested
	 */
	public CellTile(Cell cell, Game game, boolean testing) {
		this.cell = cell;
		this.game = game;
		
		if (!testing) {
			initLabels();
			setDefaultStyle();
		}
		
		this.setMinSize(50.0, 50.0);
		this.setMaxSize(50.0, 50.0);
		
		if (!testing) {
			update();
		}
	}
	
	/**
	 * Initializes display of cell based on initial game state
	 */
	private void initLabels() {
		cellDisplay = new Label();
		cellDisplay.setFont(new Font("Consolas", 40.0));
		this.getChildren().add(cellDisplay);
		
		this.setOnMouseClicked(event -> {
			game.selectCell(this, false);
			highlight();
		});
	}
	
	/**
	 * Updates display of cell after changed game state
	 */
	public void update() {
		setValue();
		if (cell.containsNotes()) {
			setNotes();
		}
	}
	
	/**
	 * Getter for the row index of this cell in the grid
	 * @return - row index of this cell in grid
	 */
	public int getRow() {
		return cell.getRow();
	}
	
	/**
	 * Getter for the column index of this cell in the grid
	 * @return - column index of this cell in grid
	 */
	public int getCol() {
		return cell.getCol();
	}
	
	/**
	 * Getter for the value in this cell
	 * @return - integer value 1-9 contained in this cell
	 */
	public int getVal() {
		return cell.getValue();
	}
	
	/**
	 * Getter for the cell object corresponding to this UI component
	 * @return - Cell object contained in this position in the grid model
	 */
	public Cell getCell() {
		return cell;
	}
	
	/**
	 * Updates the UI of this cell when value is changed in this cell
	 */
	private void setValue() {
		int val = cell.getValue();
		if (val != 0) {
			cellDisplay.setText(""+val);
			if (!cell.isPermanent())
				cellDisplay.setStyle("-fx-text-fill: #367BEB;");
			if (!cell.isCorrect())
				cellDisplay.setStyle("-fx-text-fill: #FF0000;");
		} else {
			cellDisplay.setText("");
		}
	}
	
	/**
	 * Updates the notes in this cell when they are changed by user
	 */
	private void setNotes() {
		highlight();
		boolean[] notes = cell.getNotes();
		String notesLabel = "";
		for (int i = 0; i < 9; i++) {
			if (notes[i]) {
				notesLabel += i+1;
			} else {
				notesLabel += "  ";
			}	
			if (i % 3 == 2) {
				notesLabel += "\n";
			} else {
				notesLabel += "   ";
			}
		}
		cellDisplay.setText(notesLabel);
	}
	
	/**
	 * Sets the UI style of this cell tile to the default
	 */
	public void setDefaultStyle() {
		if (cell.containsNotes()) {
			this.setStyle("-fx-background-color: #FFFFFF;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-width: 1;"
				+ "-fx-border-color: #D3D3D3;"
				+ "-fx-padding: 1 2 1 6;");
			cellDisplay.setStyle("-fx-font-size: 10.5;"
				+ "-fx-font-weight: bold;");
		} else {
			this.setStyle("-fx-border-style: solid; "
				+ "-fx-border-width: 1;"
				+ "-fx-border-color: #D3D3D3;"
				+ "-fx-padding: 15px;");
		}
	}
	
	/**
	 * Sets the UI style of this cell tile to be selected
	 */
	public void setSelected() {
		if (cell.containsNotes()) {
			this.setStyle("-fx-background-color: #DFE8EB;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-width: 1;"
				+ "-fx-border-color: #D3D3D3;"
				+ "-fx-padding: 1 2 1 6;");
			cellDisplay.setStyle("-fx-font-size: 10.5;"
				+ "-fx-font-weight: bold;");
		} else {
			this.setStyle("-fx-background-color: #DFE8EB;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-width: 1;"
				+ "-fx-border-color: #D3D3D3;"
				+ "-fx-padding: 15px;");
		}
	}
	
	/**
	 * Sets the UI style of this cell tile to be highlighted lightly
	 */
	public void setSameValueHighlight() {
		this.setStyle("-fx-background-color: #DFE8FF;"
			+ "-fx-border-style: solid;"
			+ "-fx-border-width: 1;"
			+ "-fx-border-color: #D3D3D3;"
			+ "-fx-padding: 15px;");
	}
	
	/**
	 * Sets the UI style of this cell tile to be highlighted
	 */
	public void highlight() {
		if (cell.containsNotes()) {
			this.setStyle("-fx-background-color: #AFBFFF;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-width: 1;"
				+ "-fx-border-color: #D3D3D3;"
				+ "-fx-padding: 1 2 1 6;");
			cellDisplay.setStyle("-fx-font-size: 10.5;"
				+ "-fx-font-weight: bold;");
		} else {
			this.setStyle("-fx-background-color: #AFBFFF;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-width: 1;"
				+ "-fx-border-color: #D3D3D3;"
				+ "-fx-padding: 15px;");
		}
	}
}
