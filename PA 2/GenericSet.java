/**
 * Author Joey Mauriello
 * 
 * This program represents a generic set and 
 * methods to manipulate the set.
 */

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class GenericSet<T> {

	private ArrayList<T> list = new ArrayList<>();

	/**
	* This gets the element at index i.
	* 
	* @param i An integer of an index
	* @return The element at index i
	*/
	public T get(int i) {
		return list.get(i);
	}

	/**
	* This adds a value to the set.
	* 
	* @param value The generic value to be added
	* @return A boolean based on if the value was succesfully added
	*/
	public boolean add(T value) {
		if (list.contains(value)) {
			return false;
		}
		list.add(value);
		return true;
	}

	/**
	* This gets the size of the set.
	* 
	* @return An integer of the size of the set
	*/
	public int size() {
		return list.size();
	}

	/**
	* This determines if the set contains a specific value.
	* 
	* @param value The generic value to be checked in the set
	* @return A boolean based on if the set contains the value
	*/
	public boolean contains(T value) {
		if (list.contains(value)) {
			return true;
		}
		return false;
	}

	/**
	* This unionizes the current set with another set.
	* 
	* @param list2 The s
	public GenericSet<T> unionWith(GenericSet<T> list2) {
		GenericSet<T> finalSet = this;
		for (int i = 0; i < list2.size()-1; i++) {
			T value = list2.get(i);
			finalSet.add(value);
		}
		return finalSet;
	}

	public GenericSet<T> intersectionWith(GenericSet<T> list2) {
		GenericSet<T> finalSet = new GenericSet<>();
		for (int i = 0; i < list.size(); i++) {
			if (list2.contains(list.get(i))) {
				finalSet.add(list.get(i));
			}
		}
		return finalSet;
	}
}
