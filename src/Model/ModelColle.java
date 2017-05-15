package Model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

@SuppressWarnings("serial")
abstract class ModelColle<T, K> implements Serializable {
	protected ArrayList<T> collection;
	
	protected ModelColle() {
		collection = new ArrayList<T>();
	}
	protected ModelColle(int size) {
		collection = new ArrayList<T>(size);
		initializeCollection();
	}
	
	protected boolean addToCollection(T item) {
		K keyOfNewItem = getKey(item);
		T testForDuplication = searchCollection(keyOfNewItem);
		
		if (testForDuplication == null) {
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
	
	protected abstract void initializeCollection();
	
	protected abstract K getKey(T item);
	
	protected abstract boolean matchKey(K k1, K k2);
}