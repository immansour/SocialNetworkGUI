package midterm;
/**
 * progam that displays the pre determined methods
 * in order to arraylist
 */
import java.util.Arrays;

public class AList<T> implements ListInterface<T>

{

	private T[] list; // Array of this.list entries; ignore this.list[0]
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	
	public AList() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public AList(int iniCapacity) {
		if (iniCapacity < DEFAULT_CAPACITY) {
			iniCapacity = DEFAULT_CAPACITY;
		}
		
		if (iniCapacity > MAX_CAPACITY) {
			iniCapacity = MAX_CAPACITY;
		}
		
		this.list = (T[])(new Object[iniCapacity + 1]);
		this.numberOfEntries = 0;
		this.initialized = true;
		
	}
	
	// Doubles the capacity of the array this.list if it is full.
	// Precondition: checkInitialization has been called.
	private void ensureCapacity() {
		int capacity = this.list.length - 1;
		if (this.numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity); // Is capacity too big?
			this.list = Arrays.copyOf(this.list, newCapacity + 1);
		} // end if
	} // end ensureCapacity

	
	// Makes room for a new entry at newPosition.
	// Precondition: 1 <= newPosition <= this.numberOfEntries + 1;
	// this.numberOfEntries is this.list's length before addition;
	// checkInitialization has been called.
	private void makeRoom(int newPosition) {
		assert (newPosition >= 1) && (newPosition <= this.numberOfEntries + 1);

		int newIndex = newPosition;
		int lastIndex = this.numberOfEntries;

		// Move each entry to next higher index, starting at end of
		// this.list and continuing until the entry at newIndex is moved
		for (int index = lastIndex; index >= newIndex; index--)
			this.list[index + 1] = this.list[index];
	} // end makeRoom

	// Shifts entries that are beyond the entry to be removed to the
	// next lower position.
	// Precondition: 1 <= givenPosition < this.numberOfEntries;
	// this.numberOfEntries is this.list's length before removal;
	// checkInitialization has been called.
	private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < this.numberOfEntries);

		int removedIndex = givenPosition;
		int lastIndex = this.numberOfEntries;

		for (int index = removedIndex; index < lastIndex; index++)
			this.list[index] = this.list[index + 1];
	} // end removeGap

	// Throws an exception if this object is not this.initialized.
	private void checkInitialization() {
		if (!this.initialized)
			throw new SecurityException("Athis.list object is not this.initialized properly.");
	} // end checkInitialization

	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create a this.list " + "whose capacity exceeds " + "allowed maximum.");
	} // end checkCapacity

	
	/** Adds a new entry to the end of this this.list.
	Entries currently in the this.list are unaffected.
	The this.list's size is increased by 1.
	* @param newEntry The object to be added as a new entry.
	*/
	public void add(T newEntry) {
		checkInitialization();
		this.list[this.numberOfEntries + 1] = newEntry;
		this.numberOfEntries++;
		ensureCapacity();
	}

	/**
	 * Adds a new entry at a specified position within this this.list. Entries originally
	 * at and above the specified position are at the next higher position within
	 * the this.list. The this.list's size is increased by 1.
	 * 
	 * @param newPosition An integer that specifies the desired position of the new
	 *                    entry.
	 * @param newEntry    The object to be added as a new entry.
	 * @throws IndexOutOfBoundsException if either newPosition less than 1, or
	 *                                   newPosition greater than getLength()+1.
	 */
	public void add(int newPos, T newEntry) {
		checkInitialization();
		if ((newPos > 0) && (newPos <= this.numberOfEntries + 1)) {
			if (newPos <= this.numberOfEntries) {
				makeRoom(newPos);
			}
			this.list[newPos] = newEntry;
			this.numberOfEntries++;
			ensureCapacity();
		} else {
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault");
		}
	}

	/**
	 * Removes the entry at a given position from this this.list. Entries originally at
	 * positions higher than the given position are at the next lower position
	 * within the this.list, and the this.list's size is decreased by 1.
	 * 
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be removed.
	 * @return A reference to the removed entry.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T remove(int positionRem) {
		checkInitialization();
		if ((positionRem > 0) && (positionRem <= this.numberOfEntries)) {
			assert !isEmpty();
			T dataRem = this.list[positionRem];
			removeGap(positionRem);
			this.numberOfEntries--;
			return dataRem;
		} else {
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault");
		}
	}

	/** Removes all entries from this this.list. */
	@SuppressWarnings("unchecked")
	public void clear() {
		
		this.list = (T[])(new Object[DEFAULT_CAPACITY + 1]);
		this.numberOfEntries = 0;
		this.initialized = true;
		
	}

	/**
	 * Replaces the entry at a given position in this this.list.
	 * 
	 * @param givenPosition An integer that indicates the position of the entry to
	 *                      be replaced.
	 * @param newEntry      The object that will replace the entry at the position
	 *                      givenPosition.
	 * @return The original entry that was replaced.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T replace(int positionRep, T newEntry) {
		checkInitialization();
		
		if ((positionRep > 0) && (positionRep <= this.numberOfEntries)) {
			T dataRep = this.list[positionRep];
			this.list[positionRep] = newEntry;
			return dataRep;
		} else {
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault");
		}
	}

	/**
	 * Retrieves the entry at a given position in this this.list.
	 * 
	 * @param givenPosition An integer that indicates the position of the desired
	 *                      entry.
	 * @return A reference to the indicated entry.
	 * @throws IndexOutOfBoundsException if either givenPosition less than 1, or
	 *                                   givenPosition greater than getLength()+1.
	 */
	public T getEntry(int positionGet) {
		checkInitialization();

		if ((positionGet > 0) && (positionGet <= this.numberOfEntries)) {
			return this.list[positionGet];
		} else {
			throw new IndexOutOfBoundsException("ERROR: Segmentation Fault");
		}
	}

	/**
	 * Sees whether this this.list contains a given entry.
	 * 
	 * @param anEntry The object that is the desired entry.
	 * @return True if the this.list contains anEntry, or false if not.
	 */
	public boolean contains(T dataFind) {
		checkInitialization();
		boolean isContain = false;
		for (int i = 1; i <= this.numberOfEntries; i++) {
			if (dataFind.equals(this.list[i])) {
				isContain = true;
				i = this.numberOfEntries;
			}
		}
		return isContain;
	}
	public int getPoint(T dataFind)
	{
		checkInitialization();
		int i = 0;
		if(this.numberOfEntries != 0) {
			for(i = 1; i!=this.numberOfEntries; i++) {
				if(dataFind.equals(this.list[i]))
					break;
			}
		}
		return i;
	}

	/**
	 * Gets the length of this this.list.
	 * 
	 * @return The integer number of entries currently in the this.list.
	 */
	public int getLength() {
		checkInitialization();
			
		return this.numberOfEntries;
	}

	/**
	 * Sees whether this this.list is empty.
	 * 
	 * @return True if the this.list is empty, or false if not.
	 */
	public boolean isEmpty() {
		checkInitialization();
		boolean isEmpty = false;
		
		if (this.numberOfEntries == 0) {
			isEmpty = true; 
		}
		
		return isEmpty;
	}

	/**
	 * Retrieves all entries that are in this this.list in the order in which they occur
	 * in the this.list.
	 * 
	 * @return A newly allocated array of all the entries in the this.list.
	 */
	public T[] toArray() {
		T[] tempArray = (T[])(new Object[numberOfEntries]);
		for (int i  = 0; i < numberOfEntries; i++) {
			tempArray[i] = this.list[i + 1];
		}
		return tempArray;
	}

}
