package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.AccountController;
import model.Cell;
import model.Game;
import model.Grid;
import view_controller.CellBlock;
import view_controller.CellTile;

class GameTest {

	@Test
	void testInitGame() {
		Cell[][] grid1 = new Cell[9][9];
		grid1[0][0] = new Cell(0, 0, 2, true);
		grid1[0][1] = new Cell(0, 1, 1, false);
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		assertEquals(grid, game.getGrid());
	}
	
	@Test
	void testAccController() {
		Game game = new Game();
		assertNull(game.getAccCtrl());
		AccountController ctrl = new AccountController();
		game.setAccController(ctrl);
		assertEquals(ctrl, game.getAccCtrl());
	}
	
	@Test
	void testUpdateStats() {
		Game game = new Game();
		AccountController ctrl = new AccountController();
		
		
		ctrl.createAccount("test", "123");
		game.setAccController(ctrl);
		game.updateStats();
		ctrl.loggingIn("test", "123");
		game.setAccController(ctrl);
		
		assertEquals(0, game.getAccCtrl().getCurrentUser().getTotalGamesPlayed());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getEasyGamesPlayed());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getMediumGamesPlayed());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getHardGamesPlayed());
		
		game.startNewGame(1);
		game.updateStats();
		
		assertEquals(1, game.getAccCtrl().getCurrentUser().getTotalGamesPlayed());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getEasyGamesPlayed());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getMediumGamesPlayed());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getHardGamesPlayed());
		
		

		game.startNewGame(2);
		game.updateStats();
		
		assertEquals(2, game.getAccCtrl().getCurrentUser().getTotalGamesPlayed());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getEasyGamesPlayed());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getMediumGamesPlayed());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getHardGamesPlayed());
		
		game.startNewGame(3);
		game.updateStats();
		
		assertEquals(3, game.getAccCtrl().getCurrentUser().getTotalGamesPlayed());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getEasyGamesPlayed());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getMediumGamesPlayed());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getHardGamesPlayed());
		
		Cell[][] grid1 = new Cell[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell1 = new Cell(i, j, 2, true);
				grid1[i][j] = cell1;
			}
		}
		Grid grid = new Grid(grid1);
		
		game = new Game();
		game.setAccController(ctrl);
		
		game.startNewGame(1);
		game.setGrid(grid);
		game.updateStats();
		assertEquals(1, game.getAccCtrl().getCurrentUser().getTotalGamesWon());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getEasyGamesWon());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getMediumGamesWon());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getHardGamesWon());
		
		game.startNewGame(2);
		game.setGrid(grid);
		game.updateStats();
		assertEquals(2, game.getAccCtrl().getCurrentUser().getTotalGamesWon());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getEasyGamesWon());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getMediumGamesWon());
		assertEquals(0, game.getAccCtrl().getCurrentUser().getHardGamesWon());
		
		game.startNewGame(3);
		game.setGrid(grid);
		game.updateStats();
		assertEquals(3, game.getAccCtrl().getCurrentUser().getTotalGamesWon());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getEasyGamesWon());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getMediumGamesWon());
		assertEquals(1, game.getAccCtrl().getCurrentUser().getHardGamesWon());
		
	}
	
	@Test
	void testStartNewGame() {
		Game game = new Game();
		Cell[][] baseGrid = game.generateGrid("gamepresets/EmptyGrid.txt").getGrid();
		Cell[][] newGrid = game.getGrid().getGrid();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(baseGrid[i][j].getCorrect(), newGrid[i][j].getCorrect());
			}
		}
		
		game.startNewGame(1);
		newGrid = game.getGrid().getGrid();
		Cell[][] easyGrid1 = game.generateGrid("gamepresets/easyGames/Game1.txt").getGrid();
		Cell[][] easyGrid2 = game.generateGrid("gamepresets/easyGames/Game2.txt").getGrid();
		Cell[][] easyGrid3 = game.generateGrid("gamepresets/easyGames/Game3.txt").getGrid();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue(easyGrid1[i][j].getCorrect() == newGrid[i][j].getCorrect() ||
						easyGrid2[i][j].getCorrect() == newGrid[i][j].getCorrect() ||
						easyGrid3[i][j].getCorrect() == newGrid[i][j].getCorrect());

			}
		}
		
		game.startNewGame(2);
		newGrid = game.getGrid().getGrid();
		Cell[][] mediumGrid1 = game.generateGrid("gamepresets/mediumGames/Game1.txt").getGrid();
		Cell[][] mediumGrid2 = game.generateGrid("gamepresets/mediumGames/Game2.txt").getGrid();
		Cell[][] mediumGrid3 = game.generateGrid("gamepresets/mediumGames/Game3.txt").getGrid();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue(mediumGrid1[i][j].getCorrect() == newGrid[i][j].getCorrect() ||
						mediumGrid2[i][j].getCorrect() == newGrid[i][j].getCorrect() ||
						mediumGrid3[i][j].getCorrect() == newGrid[i][j].getCorrect());

			}
		}
		
		game.startNewGame(3);
		newGrid = game.getGrid().getGrid();
		Cell[][] hardGrid1 = game.generateGrid("gamepresets/hardGames/Game1.txt").getGrid();
		Cell[][] hardGrid2 = game.generateGrid("gamepresets/hardGames/Game2.txt").getGrid();
		Cell[][] hardGrid3 = game.generateGrid("gamepresets/hardGames/Game3.txt").getGrid();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue(hardGrid1[i][j].getCorrect() == newGrid[i][j].getCorrect() ||
						hardGrid2[i][j].getCorrect() == newGrid[i][j].getCorrect() ||
						hardGrid3[i][j].getCorrect() == newGrid[i][j].getCorrect());

			}
		}
	}
	
	@Test
	void testNoteMode() {
		Game game = new Game();
		assertFalse(game.noteMode());
	}
	
	@Test
	void testSwitchNoteMode() {
		Game game = new Game();
		assertFalse(game.noteMode());
		game.switchNoteMode();
		assertTrue(game.noteMode());
	}
	
	@Test
	void testSelectCell() {
		Game game = new Game();
		Cell newCell = new Cell();
		CellTile cell = new CellTile(newCell, game, true);
		game.selectCell(cell, true);
		assertEquals(cell, game.getSelectedCell());
		
	}
	
	@Test
	void testSetValueToAddNext() {
		Cell[][] grid1 = new Cell[9][9];
		Cell cell1 = new Cell(0, 0, 2, false);
		grid1[0][0] = cell1;
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		game.setValueToAddNext(0, true);
		CellTile cellTile1 = new CellTile(cell1, game, true);
		game.selectCell(cellTile1, true);
		game.setValueToAddNext(2, true);
		assertEquals(2, game.getGrid().getVal(0, 0));
	}
	
	@Test
	void testChangeNote() {
		Cell[][] grid1 = new Cell[9][9];
		Cell cell1 = new Cell(0, 0, 2, false);
		grid1[0][0] = cell1;
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		CellTile cellTile1 = new CellTile(cell1, game, true);
		game.changeNote(0, true);
		game.selectCell(cellTile1, true);
		game.changeNote(2, true);
		assertTrue(game.getGrid().getGrid()[0][0].getNotes()[1]);
		game.changeNote(2, true);
		assertFalse(game.getGrid().getGrid()[0][0].getNotes()[1]);

	}
	
	@Test
	void testEraseCellContents() {
		Cell[][] grid1 = new Cell[9][9];
		Cell cell1 = new Cell(0, 0, 2, false);
		grid1[0][0] = cell1;
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		CellTile cellTile1 = new CellTile(cell1, game, true);
		game.eraseCellContents(true);
		game.selectCell(cellTile1, true);
		game.changeNote(1, true);
		game.setValueToAddNext(4, true);
		assertEquals(4, game.getGrid().getVal(0, 0));
		game.eraseCellContents(true);
		assertFalse(game.getGrid().getGrid()[0][0].getNotes()[0]);
		assertEquals(0, game.getGrid().getVal(0, 0));
	}
	
	@Test
	void testGameLost() {
		Cell[][] grid1 = new Cell[9][9];
		Cell cell1 = new Cell(0, 0, 2, false);
		grid1[0][0] = cell1;
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		CellTile cellTile1 = new CellTile(cell1, game, true);
		game.selectCell(cellTile1, true);
		assertFalse(game.gameLost());
		game.setValueToAddNext(3, true);
		assertFalse(game.gameLost());
		game.setValueToAddNext(4, true);
		assertFalse(game.gameLost());
		game.setValueToAddNext(5, true);
		assertTrue(game.gameLost());
	}

	@Test
	void testGameWon() {
		Cell[][] grid1 = new Cell[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell1 = new Cell(i, j, 2, true);
				grid1[i][j] = cell1;
			}
		}
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		assertTrue(game.gameWon());
	}
	
	@Test
	void testsGenerateCellTiles() {
		Cell[][] grid1 = new Cell[9][9];
		Cell cell1 = new Cell(0, 0, 2, false);
		grid1[0][0] = cell1;
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		CellBlock[][] cells = game.generateCellTiles(true);
		assertNotNull(cells);
	}
	
	@Test
	void testGetDifficulty() {
		Game game = new Game();
		game.startNewGame(1);
		assertEquals(1, game.getDifficulty());
		game.startNewGame(2);
		assertEquals(2, game.getDifficulty());
		game.startNewGame(3);
		assertEquals(3, game.getDifficulty());
	}
	
	@Test
	void testGetMistakes() {
		Cell[][] grid1 = new Cell[9][9];
		Cell cell1 = new Cell(0, 0, 2, false);
		grid1[0][0] = cell1;
		Grid grid = new Grid(grid1);
		Game game = new Game(grid);
		CellTile cellTile1 = new CellTile(cell1, game, true);
		game.selectCell(cellTile1, true);
		assertEquals(0, game.getMistakes());
		game.setValueToAddNext(3, true);
		assertEquals(1, game.getMistakes());
		game.setValueToAddNext(4, true);
		assertEquals(2, game.getMistakes());
		game.setValueToAddNext(5, true);
		assertEquals(3, game.getMistakes());
	}
	
	@Test
	void testPause() {
		Game game = new Game();
		assertFalse(game.paused());
		game.pause();
		assertTrue(game.paused());
		game.pause();
		assertFalse(game.paused());
	}
	
	
}
