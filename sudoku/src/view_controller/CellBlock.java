/**
 * @author Jake Bode & Joey Mauriello
 * Cell Block handles UI and events clicking a cell from a 3x3 block of cells
 */

package view_controller;

import javafx.scene.layout.TilePane;
import model.Cell;
import model.Game;

public class CellBlock extends TilePane {

	private Game game;
	
	private CellTile[][] cellTiles;
	
	/**
	 * Initialize CellBlock by adding CellTiles to list
	 * @param cells 2D array of Cell objects to be mapped
	 * @param game The current game
	 * @param testing Whether the method is being used for testing or not 
	 */
	public CellBlock(Cell[][] cells, Game game, boolean testing) {
		this.game = game;
		generateBlocks(cells, testing);
		this.setStyle("-fx-border-style: solid; "
				+ "-fx-border-width: 2;"
				+ "-fx-border-color: #000000;");
		this.setMinSize(156.0, 156.0);
		this.setMaxSize(156.0, 156.0);
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				this.getChildren().add(cellTiles[row][col]);
			}
		}
	}
	
	/**
	 * Creates new array of CellTiles from grid
	 * @param cells - underlying array of grid object
	 * @param testing - whether or not user is testing
	 */
	private void generateBlocks(Cell[][] cells, boolean testing) {
		cellTiles = new CellTile[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cellTiles[row][col] = new CellTile(cells[row][col], game, testing);
			}
		}
	}
	
	/**
	 * Get the CellTile at the desired location
	 * @param row The row of the desired CellTile
	 * @param col The column of the desired CellTile
	 * @return A CellTile at the desired location
	 */
	public CellTile getCellTile(int row, int col) {
		return cellTiles[row][col];
	}

	/**
	 * Update the values of all the CellTiles
	 */
	public void update() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cellTiles[row][col].update();
			}
		}
	}
	
	/**
	 * Clear the highlights of all the CellTiles
	 */
	public void clearHighlights() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cellTiles[row][col].setDefaultStyle();
			}
		}
	}
	
	/**
	 * Set all of the CellTiles in the block to be selected
	 */
	public void setBlockSelected() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cellTiles[row][col].setSelected();
			}
		}
	}
	
	/**
	 * Set all of the CellTiles in the given row to be selected
	 * @param row The desired row
	 */
	public void setRowSelected(int row) {
		for (int col = 0; col < 3; col++) {
			cellTiles[row%3][col].setSelected();
		}
	}
	
	/**
	 * Set all of the CellTiles in the given column to be selected
	 * @param col The desired column
	 */
	public void setColSelected(int col) {
		for (int row = 0; row < 3; row++) {
			cellTiles[row][col%3].setSelected();
		}
	}
	
	/**
	 * Set all CellTiles that match the value to be highlighted
	 * @param val The desired value to be matched with
	 */
	public void setMatchingValueSelected(int val) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				CellTile cell = cellTiles[row][col];
				if (cell.getVal() == val) {
					if (cell.getCell().isCorrect() && cell.getVal() != 0)
						cell.setSameValueHighlight();
				}
			}
		}
	}
}
