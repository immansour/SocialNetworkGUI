/**
 * Profile: A class that holds the name, status, and 
 * 			friends of a profile. It has all the appropriate setters
 * 			and getters. It uses a LinkedBag to organize the keys
 * 			of the friends of the profile
 * 
 * @author Yousef Helal
 * @author Ibrahim Mansour
 * 
 * @version 1.0
 * 
 * @date 5/18/2020
 * 
 */
package midterm;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;
public class Profile {
	private String name; //Name of Profile
	private String status;//Status of profile
	public String path;
	BagInterface<String> friendlist = new LinkedBag<>();  //Friends of profile
	
	/**
	 * Default Constructor for a Profile object
	 */

	Profile() {
		
		name = "unknown";
		status = "Offline";
		path = "unknown";
		friendlist = new LinkedBag<String>();
		
	}
	/**
	 * Overloaded Constructor for a Profile Object
	 * 
	 * @param n The name of the profile
	 * 
	 * @param s The status of the profile
	 * 
	 */
	
	Profile(String n, String s, String p) {
		
		name = n;
		status = s;
		path = p;
		friendlist = new LinkedBag<String>();
		
	}
	/**
	 * Adds a friend to the bag of friends
	 * 
	 * @param key The key of the friend to add
	 */
	public void addFriend(String name)
	{
		if(!friendlist.contains(name))
			friendlist.add(name);
		else
			System.out.println(name +" already exists in list of friends");
		
	}
//======================Appropriate Setters and Getters======================
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return status;
	}
	public String getPath() {
		return path;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/*
	 * portions of the code are used from:
	 * https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
	 */
	public void getFile()
	{
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	 this.path = chooser.getSelectedFile().getAbsolutePath();
        }
	}
	/**
	 * Gets all friend of the profile
	 * 
	 * @return All of the friends of the profile in array form
	 */
	public String[] getFriends() {
		
		return friendlist.toArray();
	
	}
	public enum Status {
		
		Online, Offline, Busy;
		
	}
	public boolean contains(String name)
	{
		
		boolean val = friendlist.contains(name);
		return val;
	}



}
