package StackAndQueuePackage;

/**
 * LinkedStack: An implementation of the ADT stack that uses a LinkedList
 * 				to implement the Stack;
 * 
 * @implements Implements that StackInterface, and all of its methods;
 * 
 * @author Yousef Helal
 *
 * @version 1.0
 * 
 * @date 5/6/2020
 */
public final class LinkedQueue<T> implements QueueInterface<T>
{
	
	private Node<T> firstNode; 		// References the first node in the chain
	private Node<T> lastNode; 		// References the last node in the chain
	private boolean isInitialized;  // Whether the object has been initialized properly.
	private int numEntries;         // Number of Entries in Stack
	 
	
	public LinkedQueue() {
		
		this.firstNode = null;
		this.lastNode = null;
		this.isInitialized = true;
		this.numEntries = 0;
		
	}	
	
	/**
	 * Checks whether the queue has been initialized. If it has not,
	 * 		throws an error.
	 */
	private void checkInitialization() {
		
		if (!this.isInitialized) {
		
			throw new SecurityException("LinkedStack object is not this.initialized properly.");
		
		}
	} 
	
	/** 
	* Adds a new entry to the back of this queue.
	* @param newEntry  An object to be added. 
	*/
	public void enqueue(T newEntry) {
		
		this.checkInitialization();
		
		Node<T> newNode = new Node<T>(newEntry, null);
		
		if (this.isEmpty()) {
	
			this.firstNode = newNode; 
			lastNode = newNode;
	
		} else {
			
			lastNode.next = newNode;
			lastNode = newNode;
			
		}
		this.numEntries++;
	
	} // end push

	/** 
	* Removes and returns the entry at the front of this queue.
    * @return  The object at the front of the queue. 
    * @throws  EmptyQueueException if the queue is empty before the operation.
    */
	public T dequeue() {
		
		this.checkInitialization();
		
		if (this.isEmpty()) {
			
			throw new EmptyQueueException("ERROR: Cannot getFron() an empty queue.");
		
		} 

		T result = this.getFront();
		
		firstNode = firstNode.next;		
		
		if (firstNode == null) {
			lastNode = null;
		}
		
		this.numEntries--;
		
		return result;
	
	} 
	
	/** 
	* Retrieves the entry at the front of this queue.
    * @return  The object at the front of the queue.
    * @throws  EmptyQueueException if the queue is empty.
    */
	public T getFront() {
		
		this.checkInitialization();
		
		T result = null;
		
		if (this.isEmpty()) {
			
			throw new EmptyQueueException("ERROR: Cannot getFron() an empty queue.");
		
		} else {
		
			result = this.firstNode.data;
		}
		
		return result;
		
	}
	
	/** 
	* Detects whether this queue is empty.
    * @return  True if the queue is empty, or false otherwise.
    */
	public boolean isEmpty()
	{
		
		return (this.firstNode == null) && (this.lastNode == null) && (this.numEntries == 0);
	
	}
	
	/** 
	* Removes all entries from this Queue. 
	*/
	public void clear() {
		
		this.checkInitialization();
		
		this.firstNode = null;
		this.lastNode = null;
		this.numEntries = 0;
	
	}
	
	/**
	 * A Node to hold both data, and a link to the next node
	 */
	@SuppressWarnings("hiding")
	private class Node<T>
	{
		private T data;
		private Node<T> next;
		
		//Constructors
		public Node(T dataSet) {
			this(dataSet, null);
		}
		
		public Node(T dataSet, Node<T> nextSet) {
			this.data = dataSet;
			this.next = nextSet;
		}
		
		//Getters
		public T getData() {
			return this.data;
		}
		
		public Node<T> getNextNode() {
			return this.next;
		}

		//Setters
		public void setData(T dataSet) {
			this.data = dataSet;
		}
		
		public void setNextNode(Node<T> nextSet) {
			this.next = nextSet;
		}
	
	}
	
}// end LinkedStack

 