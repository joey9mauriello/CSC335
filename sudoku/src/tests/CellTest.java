package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Cell;

class CellTest {

	@Test
	void testGetRow() {
		Cell c = new Cell(2, 3, 4, true);
		assertEquals(2, c.getRow());
	}
	
	@Test
	void testGetCol() {
		Cell c = new Cell(2, 3, 4, true);
		assertEquals(3, c.getCol());
	}
	
	@Test
	void testSetValue() {
		Cell c = new Cell(2, 3, 4, true);
		c.setValue(4);
		assertEquals(4, c.getValue());
	}
	
	@Test
	void testGetValue() {
		Cell c = new Cell(2, 3, 4, true);
		c.setValue(4);
		assertEquals(4, c.getValue());
		c.setValue(3);
		assertEquals(3, c.getValue());
	}
	
	@Test
	void testEmptyCell() {
		Cell c = new Cell(2, 3, 4, true);
		c.setValue(4);
		assertEquals(4, c.getValue());
		c.emptyCell();
		assertEquals(0, c.getValue());
	}
	
	@Test
	void testIsEmpty() {
		Cell c = new Cell();
		assertTrue(c.isEmpty());
		c.setValue(1);
		assertFalse(c.isEmpty());
		c.emptyCell();
		assertTrue(c.isEmpty());
	}

	@Test
	void testGetCorrect() {
		Cell c = new Cell(2, 3, 4, true);
		assertEquals(4, c.getCorrect());
	}
	
	@Test
	void testSetCorrect() {
		Cell c = new Cell(2, 3, 4, true);
		assertEquals(4, c.getCorrect());
		c.setCorrect(6);
		assertEquals(6, c.getCorrect());

	}
	
	@Test
	void testIsCorrect() {
		Cell c = new Cell(2, 3, 4, true);
		c.setValue(2);
		assertFalse(c.isCorrect());
		c.setValue(4);
		assertTrue(c.isCorrect());
	}
	
	@Test
	void testIsPermanent() {
		Cell c = new Cell(2, 3, 4, true);
		assertTrue(c.isPermanent());
	}
	
	@Test
	void testContainsNote() {
		Cell c = new Cell(2, 3, 4, true);
		c.addNote(3);
		assertTrue(c.containsNote(3));
		assertFalse(c.containsNote(2));
	}
	
	@Test
	void testContainsNotes() {
		Cell c = new Cell(2, 3, 4, true);
		assertFalse(c.containsNotes());
		c.addNote(3);
		assertTrue(c.containsNotes());
	}
	
	@Test
	void testGetNotes() {
		Cell c = new Cell(2, 3, 4, true);
		c.addNote(2);
		assertTrue(c.getNotes()[1]);
	}
	
	@Test
	void testAddNote() {
		Cell c = new Cell(2, 3, 4, true);
		assertFalse(c.containsNotes());
		c.addNote(2);
		assertTrue(c.containsNotes());
	}
	
	@Test
	void testRemoveNote( ) {
		Cell c = new Cell(2, 3, 4, true);
		c.addNote(2);
		assertTrue(c.containsNote(2));
		c.removeNote(2);
		assertFalse(c.containsNote(2));
	}
	
	@Test
	void testClearNotes() {
		Cell c = new Cell(2, 3, 4, true);
		c.addNote(3);
		c.addNote(4);
		c.addNote(2);
		assertTrue(c.containsNotes());
		c.clearNotes();
		assertFalse(c.containsNotes());
	}

}
