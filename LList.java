package midterm;
/**
 * LList: A List implementation that uses a Linked Chain
 * 		  as it's base
 * 
 * @author Yousef Helal with help from Ibrahim Mansour
 * 
 * @implements ListInterface<T>
 * 
 */

public class LList<T> implements ListInterface<T>

{

	private Node<T> firstNode; // Reference to first node of chain
	private int numberOfEntries; //Number of entries in the chain

	/**
	 * Node
	 * 
	 * Holds data, as well as a reference to the next node in the chain
	 * 
	 */
	@SuppressWarnings("hiding")
	private class Node<T> {
		
		// Fields
		private T data;			//Data of Node
		private Node<T> next;	//Reference to next Node

		
		// Constructors
		public Node(T dataSet) {
			this(dataSet, null);
		}
		public Node(T dataSet, Node<T> nextSet) {
			this.data = dataSet;
			this.next = nextSet;
		}

		
		// Getters
		public T getData() {
			return this.data;
		}
		public Node<T> getNextNode() {
			return this.next;
		}

		
		// Setters
		public void setData(T dataSet) {
			this.data = dataSet;
		}
		public void setNextNode(Node<T> nextSet) {
			this.next = nextSet;
		}
	}

	/**
	 * LList Constructor(only one)
	 * 
	 * Calls initializeDataFields()
	 */
	public LList() {
		initializeDataFields();
	}

	/**
	 * Adds a new entry to the end of this list. Entries currently in the list are
	 * unaffected. The list's size is increased by 1.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 */
	public void add(T newEntry) {
		
		this.add(this.numberOfEntries + 1, newEntry);
	
	}

	/**
	 * Adds a new entry at a specified position within this list. Entries originally
	 * at and above the specified position are at the next higher position within
	 * the list. The list's size is increased by 1.
	 * 
	 * @param newPosition An integer that specifies the desired position of the new
	 *                    entry.
	 * @param newEntry    The object to be added as a new entry.
	 * @throws IndexOutOfBoundsException if either newPosition less than 1, or
	 *                                   newPosition greater than getLength()+1.
	 */
	public void add(int newPosition, T newEntry) {
		
		if ((newPosition > 0) && (newPosition <= this.numberOfEntries + 1)) {
		
			Node<T> newNode = new Node<T>(newEntry);
			if (newPosition == 1) {
				
				newNode.setNextNode(this.firstNode);
				firstNode = newNode;
			
			} else {
			
				Node<T> nodeBefore = this.getNodeAt(newPosition - 1);				
				Node<T> nodeAfter = nodeBefore.getNextNode();
				
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);

			
			}
			
			numberOfEntries++;
		
		} else {
		
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault while trying to add()");
		
		}
	}

	/**
	 * Removes the entry at a given position from this list. Entries originally at
	 * positions higher than the given position are at the next lower position
	 * within the list, and the list's size is decreased by 1.
	 * 
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be removed.
	 * @return A reference to the removed entry.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T remove(int givenPosition) {
		
		T data;

		if ((givenPosition > 0) && (givenPosition <= this.numberOfEntries)) {
		
			if (givenPosition == 1) {
			
				data = this.firstNode.getData();
				this.firstNode = this.firstNode.getNextNode();
			
			} else {
				
				Node<T> nodeBefore = this.getNodeAt(givenPosition - 1);
				Node<T> nodeRemove = nodeBefore.getNextNode();
				data = nodeRemove.getData();
				nodeBefore.setNextNode(nodeRemove.getNextNode());
			
			}
			
			this.numberOfEntries--;
			
			return data;
		
		} else {
			
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault while trying to remove()");
		
		}
	}

	/** 
	 * Removes all entries from this list. 
	 */
	public void clear() {
		initializeDataFields();
	}

	/**
	 * Replaces the entry at a given position in this list.
	 * 
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be replaced.
	 * @param newEntry      The object that will replace the entry at the position
	 *                      givenPosition.
	 * @return The original entry that was replaced.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T replace(int givenPosition, T newEntry) {
		
		if ((givenPosition > 0) && (givenPosition <= this.numberOfEntries)) {
		
			T data = this.getNodeAt(givenPosition).getData();
			this.getNodeAt(givenPosition).setData(newEntry);
			
			return data;
		
		} else {
		
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault while trying to replace()");
		
		}
	}

	/**
	 * Retrieves the entry at a given position in this list.
	 * 
	 * @param givenPosition An integer that indicates the position of the desired
	 *                      entry.
	 * @return A reference to the indicated entry.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T getEntry(int givenPosition) {
		
		if ((givenPosition > 0) && (givenPosition <= this.numberOfEntries)) {
		
			return this.getNodeAt(givenPosition).getData();
		
		} else {
		
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault while trying to getEntry()");
		
		}
	}

	/**
	 * Sees whether this list contains a given entry.
	 * 
	 * @param anEntry The object that is the desired entry.
	 * @return True if the list contains anEntry, or false if not.
	 */
	public boolean contains(T anEntry) {
		
		boolean isContain = false;
		Node<T> tempNode = this.firstNode;

		if (tempNode != null) {
		
			for (int i = 0; i < this.numberOfEntries; i++) {
			
				if (tempNode.getData().equals(anEntry)) {
					
					isContain = true;
					i = this.numberOfEntries;
				
				} else {
				
					tempNode = tempNode.getNextNode();
				
				} 
				
			
			}
		
		}
		return isContain;
	}

	/**
	 * Gets the length of this list.
	 * 
	 * @return The integer number of entries currently in the list.
	 */
	public int getLength() {
		
		return this.numberOfEntries;
	
	}

	/**
	 * Sees whether this list is empty.
	 * 
	 * @return True if the list is empty, or false if not.
	 */
	public boolean isEmpty() {

		boolean isEmpty = false;

		if (this.firstNode == null) {
			
			isEmpty = true;
		
		}

		return isEmpty;
	}

	/**
	 * Retrieves all entries that are in this list in the order in which they occur
	 * in the list.
	 * 
	 * @return A newly allocated array of all the entries in the list.
	 */
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		
		T[] array = (T[]) (new Object[this.numberOfEntries]);
		Node<T> tempNode = this.firstNode;
		if (tempNode != null) {
		
			for (int i = 0; i < this.numberOfEntries; i++) {
			
				array[i] = tempNode.getData();
				tempNode = tempNode.getNextNode();
			
			}
		}
		return array;
	}
	
	/**
	 * Initializes the class's data fields to indicate an empty list.
	 * 
	 * @post List will be a default empty list
	 */
	private void initializeDataFields() {
		
		this.firstNode = null;
		this.numberOfEntries = 0;
	
	} // end initializeDataFields


	/**
	 * Returns a reference to the node at a given position.
	 * 
	 * @pre Chain is not empty
	 * @pre 1 <= givenPosition <= numberOfEntries
	 * 
	 * @param The position of the node to receive
	 * 
	 * @return The node at givenPosition
	 */
	private Node<T> getNodeAt(int givenPosition) {
		
		assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= this.numberOfEntries);
		Node<T> currentNode = this.firstNode;

		// Traverse the chain to locate the desired node
		// (skipped if givenPosition is 1)
		for (int counter = 1; counter < givenPosition; counter++) {
					
			currentNode = currentNode.getNextNode();

		}
		assert currentNode != null;

		return currentNode;
		
	} // end getNodeAt


}