package midterm;

/**
 *Program that allows for the user to view profiles and add to them
 * to create a profile
 * 
 *@author: Ibrahim Mansour
 *@author: Yousef Helal
 * CIS22C

 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;

public class AddFriends extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JCheckBox chckbxNewCheckBox;
	private String username = "";
	Database database = new Database();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFriends frame = new AddFriends();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddFriends() {
		
	    List<JCheckBox> cbList = new ArrayList<>();
	    

		makeProfiles();
		assignFriends();
		getContentPane().setLayout(null);
		
		int len = database.getLength();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Profile friends = new Profile();
		JButton btnNewButton_1 = new JButton("Save changes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = 0;
				try {
					FileWriter foute = new FileWriter("friends.txt");
					for(JCheckBox checkbox : cbList)
					{
						if(checkbox.isSelected() == true)
						{
							String key = database.getKey(x);
							String name = database.getProfile(key).getName();
							
							//adds key to the linked bag friends if checkbox is selcted
							friends.addFriend(key);
							foute.write(name + '\n');
							
							
						}
						x++;
					}
					foute.close();
				}
				catch (IOException s) {
					s.printStackTrace();
				}
				Home menu = new Home();
				menu.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(327, 22, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Suggestions:");
		lblNewLabel.setBounds(280, 63, 81, 16);
		contentPane.add(lblNewLabel);
		
;
		

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"userinfo.txt"));
			this.username = reader.readLine();
			String status = reader.readLine();
			String path = reader.readLine();		
			database.addProfile(new Profile(this.username, status, path));
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int ypos = 47;
		Profile friends1 = new Profile();
		try {
			reader = new BufferedReader(new FileReader("friends.txt"));
			String line = reader.readLine();
			while(line!= null)
			{
				//adds friends to linked bag, in order 
				//to be compared later in the program
				friends1.addFriend(line);
				line = reader.readLine();		
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int x=0; x!=len;x++)
		{
			
			String key = database.getKey(x);
			
			JLabel l = new JLabel(database.getProfile(key).getName());
			l.setBounds(36, ypos, 150, 16);
			contentPane.add(l);
			
			JButton view = new JButton("View");
			view.setBounds(200, ypos, 79, 29);
			contentPane.add(view);
			view.addActionListener(new ActionListener() {
				//Displays the user, with pohto, name and status
				public void actionPerformed(ActionEvent e) {
					
					String name = database.getProfile(key).getName();
					String status = database.getProfile(key).getStatus();
					String path = database.getProfile(key).getPath();
					BagInterface<String> friends = database.getFriends(key);
					Object[] listarray = friends.toArray();
					String[] stringArray = new String[listarray.length];
			
					try {
						int x;
						FileWriter foute = new FileWriter("view.txt");
						foute.write(name + "\n" + status + "\n" + path);
						for(x=0;x!=listarray.length;x++)
						{
							stringArray[x] = (String)listarray[x];
							foute.write("\n" + database.getProfile(stringArray[x]).getName() );
							
						}
						
						foute.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					view vw = new view();
					vw.setVisible(true);
				}
				
			});
			
			
			JCheckBox chckbxNewCheckBox = new JCheckBox();
			chckbxNewCheckBox.setBounds(10, ypos, 128, 23);
			len = database.getLength();

			//if user clicks addfriends again then it preselects the friends chosen by the user
			if(friends1.contains(database.getProfile(key).getName()) == true)
			{
				chckbxNewCheckBox.setSelected(true);
				database.addFriendToProfile(database.getKey(len-1), key);
				

			}
			contentPane.add(chckbxNewCheckBox);
			
			cbList.add(chckbxNewCheckBox);
			;
			ypos = ypos +28;
		}
		
		len = database.getLength();
		
		BagInterface<String> recFriends = database.getRecFriends(database.getKey(10));
		Object[] listarray = recFriends.toArray();
		String[] stringArray = new String[listarray.length];
		int x;
		int y = 63;
		for(x = 0;(x!=listarray.length);x++)
		{
			stringArray[x] = (String)listarray[x];
			JLabel lblNewLabel_1 = new JLabel(database.getProfile(stringArray[x]).getName());
			lblNewLabel_1.setBounds(362, y, 88, 16);
			contentPane.add(lblNewLabel_1);
			y = y +28;
		}
		

	}

	
	/*
	 * Program that predefines phony profile friends for the user to 
	 * add and adds profiles that are created by the user
	 * 
	 * NOTICE: Paths need to be changed to be tailored specific to you computer
	 */
	public void makeProfiles()
	{
		
		database.addProfile(new Profile("Obama", "Online", "/Users/ibrahimmansour/Desktop/MidtermPhotos/obama.jpg"));
		database.addProfile(new Profile("Trump","Online", "/Users/ibrahimmansour/Desktop/MidtermPhotos/trump.jpg"));
		database.addProfile(new Profile("Mo Salah", "Busy", "/Users/ibrahimmansour/Desktop/MidtermPhotos/salah.jpg"));
		database.addProfile(new Profile("Mane", "Online", "/Users/ibrahimmansour/Desktop/MidtermPhotos/Mane.jpg"));
		database.addProfile(new Profile("Firmino", "Online", "/Users/ibrahimmansour/Desktop/MidtermPhotos/firmino.jpg"));
		database.addProfile(new Profile("Mark Zuckerburg", "Busy", "/Users/ibrahimmansour/Desktop/MidtermPhotos/mark.jpg"));
		database.addProfile(new Profile("Jeff Bezos", "Busy", "/Users/ibrahimmansour/Desktop/MidtermPhotos/jeff.jpg"));
		database.addProfile(new Profile("Bill Gates", "Online", "/Users/ibrahimmansour/Desktop/MidtermPhotos/Bill.jpg"));
		database.addProfile(new Profile("Lionel Messi", "Offline", "/Users/ibrahimmansour/Desktop/MidtermPhotos/messi.jpg"));
		database.addProfile(new Profile("Cristiano Ronaldo", "Online", "/Users/ibrahimmansour/Desktop/MidtermPhotos/ronaldo.jpg"));
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("usernames.txt"));
			String line = reader.readLine();
			while(line!= null)
			{
				if(line.contentEquals("NEW"))
				{
					String name = reader.readLine();
					String stat = reader.readLine();
					String path = reader.readLine();
					database.addProfile(new Profile(name, stat, path));

				}
				line = reader.readLine();
			
			}

		}
		catch(IOException e5)
		{
			e5.printStackTrace();
		}
		
	}
	public void assignFriends()
	{
		database.addFriendToProfile(database.getKey(0), database.getKey(1));
		database.addFriendToProfile(database.getKey(1), database.getKey(0));
		
		database.addFriendToProfile(database.getKey(2), database.getKey(3));
		database.addFriendToProfile(database.getKey(2), database.getKey(4));
		database.addFriendToProfile(database.getKey(2), database.getKey(8));
		database.addFriendToProfile(database.getKey(2), database.getKey(9));
		
		database.addFriendToProfile(database.getKey(3), database.getKey(2));
		database.addFriendToProfile(database.getKey(3), database.getKey(4));
		database.addFriendToProfile(database.getKey(3), database.getKey(8));
		database.addFriendToProfile(database.getKey(3), database.getKey(9));
		
		database.addFriendToProfile(database.getKey(4), database.getKey(2));
		database.addFriendToProfile(database.getKey(4), database.getKey(3));
		database.addFriendToProfile(database.getKey(4), database.getKey(8));
		database.addFriendToProfile(database.getKey(4), database.getKey(9));
		
		database.addFriendToProfile(database.getKey(5), database.getKey(6));
		database.addFriendToProfile(database.getKey(5), database.getKey(7));
		
		database.addFriendToProfile(database.getKey(6), database.getKey(5));
		database.addFriendToProfile(database.getKey(6), database.getKey(7));
		
		database.addFriendToProfile(database.getKey(7), database.getKey(5));
		database.addFriendToProfile(database.getKey(7), database.getKey(6));
		
		database.addFriendToProfile(database.getKey(8), database.getKey(2));
		database.addFriendToProfile(database.getKey(8), database.getKey(3));
		database.addFriendToProfile(database.getKey(8), database.getKey(4));
		database.addFriendToProfile(database.getKey(8), database.getKey(9));

		database.addFriendToProfile(database.getKey(9), database.getKey(2));
		database.addFriendToProfile(database.getKey(9), database.getKey(3));
		database.addFriendToProfile(database.getKey(9), database.getKey(4));
		database.addFriendToProfile(database.getKey(9), database.getKey(8));

	}

}
