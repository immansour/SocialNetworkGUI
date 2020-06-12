package midterm;

/**
 * A class of bags whose entries are stored in a chain of linked nodes. The bag
 * is never full.
 * 
 * @author: Ibrahim Mansour
 */
public final class LinkedBag<T> implements BagInterface<T> {

	private Node firstNode; // Feference to first node
	private int numberOfEntries;

	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	} // end default constructor

	/**
	 * Sees whether this bag is empty.
	 * 
	 * @return true if the bag is empty, or false if not
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	/**
	 * Gets the current number of entries in this bag.
	 * 
	 * @return the integer number of entries currently in the bag
	 */
	public int getCurrentSize() {
		return numberOfEntries;
	} // end getCurrentSize

	/**
	 * Adds a new entry to this bag.
	 * 
	 * @param newEntry
	 *            The object to be added as a new entry.
	 * @return True.
	 */
	public boolean add(T newEntry) // OutOfMemoryError possible
	{
		// Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode; // Make new node reference rest of chain
		// (firstNode is null if chain is empty)
		firstNode = newNode; // New node is at beginning of chain
		numberOfEntries++;
		return true;
	} // end add

	/**
	 * Retrieves all entries that are in this bag.
	 * 
	 * @return A newly allocated array of all the entries in the bag.
	 */
	public T[] toArray() {
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		} // end while
		return result;
	} // end toArray

	/**
	 * Counts the number of times a given entry appears in this bag.
	 * 
	 * @param anEntry
	 *            The entry to be counted.
	 * @return The number of times anEntry appears in the bag.
	 */
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int loopCounter = 0;
		Node currentNode = firstNode;
		while ((loopCounter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				frequency++;
			}
			loopCounter++;
			currentNode = currentNode.next;
		} // end while
		return frequency;
	} // end getFrequencyOf

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while
		return found;
	} // end contains

	/**
	 * Removes one occurrence of a given entry from this bag, if possible.
	 * 
	 * @param anEntry
	 *            The entry to be removed.
	 * @return True if the removal was successful, or false otherwise.
	 */
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		if (nodeN != null) {
			nodeN.data = firstNode.data; // Teplace located entry with entry
			// in first node
			firstNode = firstNode.next; // Remove first node
			numberOfEntries--;
			result = true;
		} // end if
		return result;
	} // end remove

	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				found = true;
			} else {
				currentNode = currentNode.next;
			}
		} // end while
		return currentNode;
	} // end getReferenceTo

	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	} // end clear

	private class Node {

		private T data; // Entry in bag
		private Node next; // link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructor
	} // end Node

	/**
	 * Removes one unspecified entry from this bag, if possible.
	 * 
	 * @return Either the removed entry, if the removal was successful, or null.
	 */
	public T remove() {
		T result = null;

		// MODIFY THIS METHOD TO REMOVE A RANDOM ITEM FROM THE BAG

		if (firstNode != null) {
			result = firstNode.data;
			firstNode = firstNode.next; // Remove first node from chain
			numberOfEntries--;
		} // end if

		return result;
	} // end remove

	/**
	 * Override the toString method so that we can inspect the contents of the bag.
	 * 
	 * @return A string representation of the contents of the bag.
	 */
	public String toString() {

		String result = "Bag{Size:" + numberOfEntries + " ";

		Node scout = firstNode;

		for (int index = 0; index < numberOfEntries; index++) {
			result += "[" + scout.data + "] ";
			scout = scout.next;
		} // end for

		result += "}";
		return result;
	} // end toString

	/*********************************************************************
	 * 
	 * METHODS TO BE COMPLETED
	 * 
	 * 
	 ************************************************************************/

	/**
	 * Check to see if two bags are equals.
	 * 
	 * @param aBag
	 *            Another object to check this bag against.
	 * @return True if the two bags contain the same objects with the same
	 *         frequencies.
	 */
	public boolean equals(LinkedBag<T> aBag) {
		boolean result = false; // result of comparison of bags

		// COMPLETE THIS METHOD
		
		Node thisBag = firstNode;
		
		
		if (numberOfEntries == aBag.getCurrentSize()) {
			result = true;
		}
		
		while(thisBag != null){
			int countThis = getFrequencyOf(thisBag.data);
			
			int countOther = aBag.getFrequencyOf(thisBag.data);
			
			if(countThis != countOther) {
				result = false;
			}
			
			thisBag = thisBag.next;
		}
		return result;
	} // end equals

	/**
	 * Duplicate all the items in a bag.
	 * 
	 * @return True if the duplication is possible.
	 */
	public boolean duplicateAll() {
		boolean success = true; // should always return true
								// if there is a problem allocating nodes
								// an exception will be thrown

		// COMPLETE THIS METHOD

		Node start = firstNode;
		

		while (start != null) {
			
			Node temp = new Node(start.data);
			temp.next = firstNode;
			
			firstNode = temp;
			
			numberOfEntries++;

			start = start.next;
		}

		return success;
	} // end duplicateAll

	/**
	 * Remove all duplicate items from a bag
	 */
	public void removeDuplicates() {

		// COMPLETE THIS METHOD

		Node og = firstNode;
		Node newChain = null;
		
		int newNum = 0;
		
		while(og != null) {
			T value = og.data;
			Node ogNew = newChain;
			boolean isDup = false;
			
			while(ogNew != null && !isDup) {
				if(value.equals(ogNew.data)) {
					isDup = true;
				} else {
					ogNew = ogNew.next;
				}
			}
			
			if(!isDup) {
				newNum++;
				Node newNode = new Node(value);
				
				newNode.next = newChain;
				newChain = newNode;
			}
			
			og = og.next;
			
		}
		
		numberOfEntries = newNum;
		firstNode = newChain;
		return;
	} // end removeDuplicates
}
