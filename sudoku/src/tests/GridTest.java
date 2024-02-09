package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Cell;
import model.Game;
import model.Grid;
import view_controller.CellBlock;

class GridTest {
		
	@Test
	void testGenerateGridDisplay() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 1, 1, false);
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		CellBlock[][] cells = grid.generateGridDisplay(game, true);
		assertNotNull(cells);
	}
	
	@Test
	void testIsCellPermanent() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 1, 1, false);
		Grid grid = new Grid(grid1);
		assertTrue(grid.isCellPermanent(0, 0));
		assertFalse(grid.isCellPermanent(0, 1));
		assertTrue(grid.getPermanent(0, 0));
	}
	
	@Test
	void testAddValue() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 1, 1, false);
		Grid grid = new Grid(grid1);
		grid.addValue(0, 0, 5, true);
		assertEquals(5, grid.getVal(0, 0));
	}
	
	@Test
	void testAddNote() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 0, 1, false);
		Grid grid = new Grid(grid1);
		grid.addNote(0, 0, 1, true);
		assertTrue(grid.getGrid()[0][0].getNotes()[0]);
	}
	
	@Test
	void testRemoveNote() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 0, 1, false);
		Grid grid = new Grid(grid1);
		grid.addNote(0, 0, 1, true);
		assertTrue(grid.getGrid()[0][0].getNotes()[0]);
		grid.removeNote(0, 0, 1, true);
		assertFalse(grid.getGrid()[0][0].getNotes()[0]);
	}
	
	@Test
	void testGetGrid() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 0, 1, false);
		Grid grid = new Grid(grid1);
		assertEquals(grid1, grid.getGrid());
	}
	
	@Test
	void testGetVal() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 0, 1, false);
		Grid grid = new Grid(grid1);
		assertEquals(2, grid.getVal(0, 0));
	}
	
	@Test
	void testGetPermanent() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 0, 1, false);
		Grid grid = new Grid(grid1);
		assertTrue(grid.isCellPermanent(0, 0));
		assertFalse(grid.isCellPermanent(0, 1));
	}
	
	@Test
	void testIsComplete() {
		Cell[][] grid1 = new Cell[9][9];
		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid1[i][j] = new Cell(i, j, 1, false);
				grid1[i][j].setValue(1);
			}
		}
		Grid grid = new Grid(grid1);
		assertTrue(grid.isComplete());
		grid.addValue(0, 0, 4, true);
		assertFalse(grid.isComplete());
	}


}
