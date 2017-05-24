package Model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

@SuppressWarnings("serial")
abstract class ModelColle<T, K> implements Serializable {
	protected ArrayList<T> collection;
	protected boolean duplicationAllowance;
	
	protected ModelColle() {
		collection = new ArrayList<T>();
		duplicationAllowance = false;
	}
	protected ModelColle(int size) {
		collection = new ArrayList<T>(size);
		initializeCollection();
		duplicationAllowance = false;
	}
	
	protected boolean addToCollection(T item) {
		K keyOfNewItem = getKey(item);
		if (!isDuplicated(keyOfNewItem)) {
			return collection.add(item);
		}
		return false;
	}
	
	protected boolean removeFromCollection(K key) {
		T item = searchCollection(key);
		if (item != null) {
			collection.remove(item);
			return true;
		}
		return false;
	}
	
	protected boolean removeFromCollection(int index) {
		if (index <= collection.size()) {
			collection.remove(index);
			return true;
		}
		return false;
	}
	
	protected int getCollectionSize() {
		return collection.size();
	}
	
	protected T searchCollection(K key) {
		for (T element : collection) { //Added during debug
			if (element != null) {
				//System.out.println("SearchColl " + getKey(element));
				if (matchKey(getKey(element), key)) {
					return element;
				}
			}
		}
		return null;
	}
	
	protected T searchCollection(int index) {
		if (index <= collection.size()) {
			return collection.get(index);
		}
		return null;
	}
	
	protected int indexOf(K key) {
		for (int i = 0; i < collection.size(); i++) { //Added during debug
			T element = collection.get(i);
			if (element != null) {
				//System.out.println("SearchColl " + getKey(element));
				if (matchKey(getKey(element), key)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Testing for item duplication in collection.
	 * @param key
	 * @return True ~ Item already exist in collection
	 * @return False ~ Item doesn't exist in collection
	 */
	protected boolean isDuplicated(K key) {
		boolean output = !duplicationAllowance; //If duplication is allowed output is always false.
		if (!duplicationAllowance) {
			if (searchCollection(key) == null) {
				output = false;
			}
			else {
				output = true;
			}
		}
		return output;
	}
	
	protected void setDuplicationAllowance(boolean allowance) {
		this.duplicationAllowance = allowance;
	}
	
	protected abstract void initializeCollection();
	
	protected abstract K getKey(T item);
	
	protected abstract boolean matchKey(K k1, K k2);
}