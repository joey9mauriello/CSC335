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

	public T get(int i) {
		return list.get(i);
	}

	public boolean add(T value) {
		if (list.contains(value)) {
			return false;
		}
		list.add(value);
		return true;
	}

	public int size() {
		return list.size();
	}

	public boolean contains(T value) {
		if (list.contains(value)) {
			return true;
		}
		return false;
	}

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
