package midterm;

/**
 * LinkedDictionary: An implementation of LinedDictionary that
 * 					 uses a Linked Chain as its implementation. 
 * 					 It also allows for the return of Iterators
 * 					 that can iterate through keys and values.
 * 
 * 
 * @implements DictionaryInterface
 * 
 * @author Yousef Helal
 * 
 * @verison 1.0
 * 
 * @date 5/11/2020
 * 
 */
import java.util.*;

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V> {
	
	private Node firstNode;       // Reference to first node of chain
	private int numberOfEntries;  // Number of entries in chain

	public LinkedDictionary() {
		
		initializeDataFields();
	
	} // end default constructor

	/**
	 * Initializes the Dictionary to one that is empty
	 */
	public void initializeDataFields() {
		
		this.firstNode = null;
		this.numberOfEntries = 0;
	
	}

	/**
	 * Adds a new entry to this dictionary. If the given search key already exists
	 * in the dictionary, replaces the corresponding value.
	 * 
	 * @param key   An object search key of the new entry.
	 * 
	 * @param value An object associated with the search key.
	 * 
	 * @return Either null if the new entry was added to the dictionary or the value
	 *         that was associated with key if that value was replaced.
	 */
	public V add(K key, V value) {
		
		V data = null; //To hold the data if a key is already present
		
		Node iter = this.firstNode;
		
		while ((iter != null) && (!key.equals(iter.key))) {
			
			iter = iter.next;
			
		}
		
		if (iter == null) {
			
			Node newNode = new Node(key, value);
			newNode.next = this.firstNode;
			
			this.firstNode = newNode;
			this.numberOfEntries++;
			
		} else {
			
			data = iter.value;
			iter.value = value;
			
		}
		
		return data;
		
	}

	/**
	 * Removes a specific entry from this dictionary.
	 * 
	 * @param key An object search key of the entry to be removed.
	 * 
	 * @return Either the value that was associated with the search key or null if
	 *         no such object exists.
	 */
	public V remove(K key) {
		
		V data = null; //To hold the data of the key being removed
		
		Node iterNode = this.firstNode;
		Node beforeNode = null;
		
		while ((iterNode != null) && (!key.equals(iterNode.key))) {
			
			beforeNode = iterNode;
			iterNode = iterNode.next;
			
		}
		
		if (iterNode != null) {
			
			
			if (beforeNode == null) {
				
				this.firstNode = iterNode.next;
				
			} else {  
				
				beforeNode.next = iterNode.next;
				
			}
			
			data = iterNode.value;
			
			this.numberOfEntries--;
			
		}
		
		return data;
		
	}

	/**
	 * Retrieves from this dictionary the value associated with a given search key.
	 * 
	 * @param key An object search key of the entry to be retrieved.
	 * 
	 * @return Either the value that is associated with the search key or null if no
	 *         such object exists.
	 */
	public V getValue(K key) {
		
		V data = null;
		
		Node iterNode = this.firstNode;
		
		while ((iterNode != null) && (!key.equals(iterNode.key))) {
			
			iterNode = iterNode.next;
			
		}
		
		if (iterNode != null) {
			
			data = iterNode.value;
			
		}
		
		return data;
		
	}

	/**
	 * Sees whether a specific entry is in this dictionary.
	 * 
	 * @param key An object search key of the desired entry.
	 * @return True if key is associated with an entry in the dictionary.
	 */
	public boolean contains(K key) {
		
		return (this.getValue(key) != null);
		
	}

	/**
	 * Creates an iterator that traverses all search keys in this dictionary.
	 * 
	 * @return An iterator that provides sequential access to the search keys in the
	 *         dictionary.
	 */
	public Iterator<K> getKeyIterator() {
		
		return new KeyIterator();
		
	}

	/**
	 * Creates an iterator that traverses all values in this dictionary.
	 * 
	 * @return An iterator that provides sequential access to the values in this
	 *         dictionary.
	 */
	public Iterator<V> getValueIterator() {
		
		return new ValueIterator();
		
	}

	/**
	 * Sees whether this dictionary is empty.
	 * 
	 * @return True if the dictionary is empty.
	 */
	public boolean isEmpty() {
		
		return (this.numberOfEntries == 0);
		
	}

	/**
	 * Gets the size of this dictionary.
	 * 
	 * @return The number of entries (key-value pairs) currently in the dictionary.
	 */
	public int getSize() {
		
		return this.numberOfEntries;
		
	}

	/**
	 *  Removes all entries from this dictionary.
	 */
	public void clear() {
		
		this.initializeDataFields();
		
	}

	
	/**
	 * Same as in SortedLinkedDictionary.
	 * Since iterators implement Iterator, methods must be public.
	 */
	private class KeyIterator implements Iterator<K> {
		private Node nextNode;

		private KeyIterator() {
			nextNode = firstNode;
		} // end default constructor

		public boolean hasNext() {
			return nextNode != null;
		} // end hasNext

		public K next() {
			K result;

			if (hasNext()) {
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			} // end if

			return result;
		} // end next

		public void remove() {
			throw new UnsupportedOperationException();
		} // end remove
	} // end KeyIterator

	
	/**
	 * Same as in SortedLinkedDictionary.
	 * Since iterators implement Iterator, methods must be public.
	 */
	private class ValueIterator implements Iterator<V> {
		private Node nextNode;

		private ValueIterator() {
			nextNode = firstNode;
		} // end default constructor

		public boolean hasNext() {
			return nextNode != null;
		} // end hasNext

		public V next() {
			V result;

			if (hasNext()) {
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			} // end if

			return result;
		} // end next

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		} // end remove
	} // end getValueIterator

	
    /**
	 * Same as what is used in LinkedList, but with an extra field of "key"
	 */
	private class Node {
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
			next = null;
		} // end constructor

		private Node(K searchKey, V dataValue, Node nextNode) {
			key = searchKey;
			value = dataValue;
			next = nextNode;
		} // end constructor

		private K getKey() {
			return key;
		} // end getKey

		private V getValue() {
			return value;
		} // end getValue

		private void setValue(V newValue) {
			value = newValue;
		} // end setValue

		private Node getNextNode() {
			return next;
		} // end getNextNode

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node

} // end LinkedDictionary