
/**
 * Author: Joey Mauriello
 * 
 * This program tests the methods of GenericSet.java.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GenericSetTest {

	@Test
	void testAdd() {
		GenericSet<String> set = new GenericSet<>();
		assertTrue(set.add("Joey"));
		assertEquals(1, set.size());
	}

	@Test
	void testSize() {
		GenericSet<String> set = new GenericSet<>();
		set.add("Jeoy");
		set.add("gos");
		set.add("sdfs");
		assertEquals(3, set.size());
	}

	@Test
	void testContains() {
		GenericSet<Integer> set = new GenericSet<>();
		set.add(23);
		set.add(45);
		set.add(56);
		assertTrue(set.contains(45));
		assertFalse(set.contains(3));
	}

	@Test
	void testUnion() {
		GenericSet<Integer> set1 = new GenericSet<>();
		set1.add(23);
		set1.add(45);
		set1.add(56);

		GenericSet<Integer> set2 = new GenericSet<>();
		set2.add(23);
		set2.add(36);
		set2.add(69);

		GenericSet<Integer> set3 = new GenericSet<>();
		set3.add(23);
		set3.add(45);
		set3.add(56);
		set3.add(36);
		set3.add(69);

		GenericSet<Integer> finalSet = set1.unionWith(set2);
		assertEquals(5, finalSet.size());
		for (int i = 0; i < set3.size(); i++) {
			assertEquals(set3.get(i), finalSet.get(i));
		}
	}

	@Test
	void testIntersection() {
		GenericSet<Integer> set1 = new GenericSet<>();
		set1.add(23);
		set1.add(45);
		set1.add(56);

		GenericSet<Integer> set2 = new GenericSet<>();
		set2.add(23);
		set2.add(45);
		set2.add(69);

		GenericSet<Integer> set3 = new GenericSet<>();
		set3.add(23);
		set3.add(45);

		GenericSet<Integer> finalSet = set1.intersectionWith(set2);
		assertEquals(2, finalSet.size());
		for (int i = 0; i < finalSet.size(); i++) {
			assertTrue(set3.contains(finalSet.get(i)));
		}
	}
}
