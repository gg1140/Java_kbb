package Model;

import java.io.Serializable;
import java.lang.reflect.Array;

import Model.OptionSet.Option;

abstract class Collection<T, K> implements Serializable {
	T[] collection;
	
	protected Collection() {
		collection = null;
	}
	protected Collection(Class<T> c, int size) {
		collection = (T[])Array.newInstance(c, size);
		initializeCollection();
	}
	
	protected boolean addToCollection(T item) {
		K keyOfNewItem = getKey(item);
		T testForDuplication = searchCollection(keyOfNewItem);
		if(testForDuplication == null) {
			for(int i = 0; i < collection.length; i++) {
				if(collection[i] == null) { //Add to the first empty index
					collection[i] = item;
					//System.out.println(collection[i].toString());
					return true;
				}
			}
			/*for(T element: collection) { //Element is a local variable for the for loop so assignment to it doesn't affect the array
				if(element == null) {
					element = item;
					System.out.println(element.toString());
					return true;
				}
			}*/
		}
		return false;
	}
	
	protected boolean removeFromCollection(K key) {
		T item = searchCollection(key);
		if(item != null) {
			item = null;
			return true;
		}
		return false;
	}
	
	protected int getCollectionSize() {
		return collection.length;
	}
	
	protected T searchCollection(K key) {
		for(T element : collection) { //Added during debug
			if(element != null) {
				//System.out.println("SearchColl " + getKey(element));
				if(matchKey(getKey(element), key)) {
					return element;
				}
			}
		}
		return null;
	}
	
	protected abstract void initializeCollection();
	
	protected abstract K getKey(T item);
	
	protected abstract boolean matchKey(K k1, K k2);
}