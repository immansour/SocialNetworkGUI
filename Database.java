package midterm;

import java.util.Iterator;
import java.util.Random;

import GraphPackage.*;
public class Database {
	
	private DictionaryInterface<String, Profile> profiles; //To hold all the profiles stored in the databse
	private ListInterface<String> profileKeys;             //To hold the keys to access the profiles stored in the database
	private boolean isInitialized;
	private UndirectedGraph<String> friendGraph; //Holds the relationships between the different friends
	

	Database() {
		
		profiles = new LinkedDictionary<String, Profile>();
		friendGraph = new UndirectedGraph<String>();
		profileKeys = new LList<String>();
		isInitialized = true;
		
	}
	/**
	 * Checks whether the object has been initialized properly
	 * 
	 * @throws SecurityException if object is not initialized properly
	 */
	void checkInitialization() {
		
		if (!isInitialized) {
			
			throw new SecurityException("Database object was not initialized properly");
			
		}
		
	}
	/**
	 * Adds the profile passed to it to the database
	 * 
	 * @param profile The profile to add to the database
	 * 
	 */
	
	void addProfile(Profile profile) {
		checkInitialization();
		
		String uniqeId = this.generateRandString(20, profileKeys);
		
		profileKeys.add(uniqeId); 		//Adding key to list of keys
		
		profiles.add(uniqeId, profile); //Adding Profile to dictionary of profiles
		friendGraph.addVertex(uniqeId); 
		
		
	}
	/**
	 * Gets the profile of the key passed to it
	 * 
	 * @param key The key to get the associated profile
	 * 
	 * @return The profile associated with the key
	 */
	
	Profile getProfile(String key) {
		
		if (profileKeys.contains(key)) {
			
			Profile profileReturn = profiles.getValue(key);
			return profileReturn;
			
		} else {
		
			throw new IndexOutOfBoundsException("Cannot find a profile with that key");
	
		}
	}
	
	/**
	 * Gets all the friends of the specified profile key
	 * 
	 * @param key The key of the profile to get the friends of
	 * 
	 * @return A BagInterface containing all the friends
	 */
	public BagInterface<String> getFriends(String key) {
	
		if (profileKeys.contains(key)) {
			
			BagInterface<String> friends = new LinkedBag<String>();
			
			Iterator<VertexInterface<String>> iter = friendGraph.getVertex(key).getNeighborIterator();
			
			while (iter.hasNext()) {
				
				friends.add(iter.next().getLabel());
				
			}
			
			return friends;

		} else {
		
			throw new IndexOutOfBoundsException("Cannot find a profile with that key");
	
		}
		
		
	}
	/**
	 * Gets the recommended friends of a specified key
	 *  
	 * @param key The key to get the recommended friends of
	 * 
	 * @return A Bag containing the keys of the recommended friends
	 */
	public BagInterface<String> getRecFriends(String key) {
		
		if (profileKeys.contains(key)) {
			
			BagInterface<String> friendRec = new LinkedBag<String>();
			
			Iterator<VertexInterface<String>> iter = friendGraph.getVertex(key).getNeighborIterator();
			Iterator<VertexInterface<String>> friendIter = null;
			
			
			while (iter.hasNext()) {
				
				friendIter = iter.next().getNeighborIterator();
				
				while (friendIter.hasNext()) {
					
					String toCheck = friendIter.next().getLabel();
					if (!friendRec.contains(toCheck) && !(toCheck.equals(key))) {
						
						friendRec.add(toCheck);
						
					}
					
					
				}
				
			}
			
			return friendRec;
			
		} else {
		
			throw new IndexOutOfBoundsException("Cannot find a profile with that key");
	
		}
		
	}
	
	/**
	 * Removed a friend from a profile
	 * 
	 * @param profileKey The key of the profile to remove the friend from
	 * 
	 * @param friendKey The key of the friend to remove
	 * 
	 * @throws IndexOutOfBoundsException if Friend list does not contain key or key doesn't exits
	 * 
	 */
	public void addFriendToProfile(String profileKey, String friendKey) {
		checkInitialization(); //Could throw SecurityException
		
		if (profileKeys.contains(profileKey) && profileKeys.contains(friendKey)) {
			
			friendGraph.addEdge(profileKey, friendKey);
		
		} else {
		
			throw new IndexOutOfBoundsException("Cannot find a profile with that key");

		}
		
		
	}
	/**
	 * A static method that generates a random alpha-numeric string that contains characters from
	 * 		a-z, A-Z, and 0-9
	 * 
	 * @param sizeString The size of the string to generate
	 * 
	 * @param checkAgainstCollision A list of other keys to make sure that their are no identicals
	 * 
	 * @return A unique and random String ID
	 */
	
	private String generateRandString(int sizeString, ListInterface<String> checkAgainstCollision) {
		
		final String possibilities = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		Random random = new Random();
		
		StringBuilder randIdBuilder = new StringBuilder();
		
		for (int i = 0; i < sizeString; i++) {
			
			randIdBuilder.append(possibilities.charAt(random.nextInt(possibilities.length())));
			
		}
		
		String randId = randIdBuilder.toString();
		
		if (checkAgainstCollision.contains(randId)) {
			
			randId = this.generateRandString(sizeString, checkAgainstCollision);
			
		}
		
		return randId;
	}
	public int getLength()
	{
		int i = profiles.getSize();
		return i;
	}
	public String getKey(int i)
	{
		Object[] listarray = profileKeys.toArray();
		String[] stringArray = new String[listarray.length];
		stringArray[i] = (String)listarray[i];
		
		
		return stringArray[i];
	}
	/**
	 * Checks if the database is empty
	 * 
	 * @return True if database is empty, and false if it holds anything
	 */
	boolean isEmpty() {
		checkInitialization();
		
		return profileKeys.isEmpty();
		
	}
	public String getKeyValue(String name)
	{
		String value = null;
		String key;
		int x;
		for(x=1;x!=(profileKeys.getLength());x++)
		{
			key = profileKeys.getEntry(x);
			if(name.contentEquals(profiles.getValue(key).getName()))
			{
				value = key;
				break;
			}
			
			
		
		}
		return value;
	}

	
	

}