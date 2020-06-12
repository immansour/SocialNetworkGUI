package StackAndQueuePackage;

/**
 * LinkedStack: An implementation of the ADT stack that uses a LinkedList
 * 				to implement the Stack;
 * 
 * @implements Implements that StackInterface, and all of its methods;
 * 
 * @author Yousef Helal.
 * 
 * @date 5/4/2020
 * 
 * @version 1.0
 *
 */
public final class LinkedStack<T> implements StackInterface<T>
{
	
	private Node<T> topNode; 		// References the first node in the chain
	private boolean isInitialized;  // Is the stack initialized
	private int numEntries;			// Number of entries in the stack
	
	
	/**
	 * Constructor for LinkedStack()
	 */
	public LinkedStack() {
		
		this.topNode = null;
		this.isInitialized = true;
		this.numEntries = 0;
		
	}	
	
	/**
	 * Checks if the LinkedStack object is initialized properly.
	 */
	private void checkInitialization() {
		
		if (!this.isInitialized) {
		
			throw new SecurityException("LinkedStack object is not this.initialized properly.");
		
		}
	} 
	
	/**
	* Adds a new entry to the top of this stack.
    * @param newEntry  An object to be added to the stack. 
    */
	public void push(T newEntry) {
		
		this.checkInitialization();
		
		Node<T> newNode = new Node<T>(newEntry, this.topNode);
		this.topNode = newNode; 
		this.numEntries++;
	
	}

	/** 
	* Removes and returns this stack's top entry.
    * @return  The object at the top of the stack. 
    * @throws  EmptyStackException if the stack is empty before the operation. 
    */
	public T pop() {
		
		this.checkInitialization();
		
		T result = null;
		
		if (this.isEmpty()) {
		
			throw new EmptyStackException("ERROR: Cannot pop() from an empty list");
			
		}
		else {
			
			result = peek(); 
			this.topNode = this.topNode.getNextNode();
			this.numEntries--;
			return result;
		
		}
	} 
	
	/** 
	* Retrieves this stack's top entry.
    * @return  The object at the top of the stack.
    * @throws  EmptyStackException if the stack is empty. 
    */
	public T peek() {
		
		this.checkInitialization();
		
		T result = null;
		
		if (this.isEmpty()) {
			
			throw new EmptyStackException("ERROR: Cannot peek an empty list.");
		
		} else {
		
			result = this.topNode.data;
		}
		
		return result;
		
	}
	
	/** 
	* Detects whether this stack is empty.
    * @return  True if the stack is empty.
    */
	public boolean isEmpty()
	{
		
		return (this.topNode == null);
	
	}
	
	/** 
	* Removes all entries from this stack. 
	*/
	public void clear() {
		
		this.checkInitialization();
		
		this.topNode = null;
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

 