package midterm;
/**
 * Program that allows for the user to login 
 * with any old username that the user initially used to create profile
 * 
 * @Author: Ibrahim and Yousef
 * CIS22C
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class returninguser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					returninguser frame = new returninguser();
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
	public returninguser() {
		
		//Array lists to store data from files
		ListInterface <String> usernames = new AList();
		ListInterface <String> paths = new AList();
		ListInterface <String> status = new AList();
		ListInterface <String> friends = new AList();


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
					
					//Stores data into array lists
					usernames.add(name);
					status.add(stat);
					paths.add(path);
				}
				line = reader.readLine();			
			}

		}
		catch(IOException e5)
		{
			e5.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(109, 157, 212, 16);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			private String line;

			public void actionPerformed(ActionEvent e) {
				String user = textField.getText();
				
				//checks to see if username input exists
				if(!usernames.contains(user))
				{
					lblNewLabel.setText("NAME NOT FOUND TRY AGAIN");
				}
				else
				{
					int len = usernames.getLength();
					int x;
					//gets location
					for(x=1;x!=len+1;x++)
					{
						String entry = usernames.getEntry(x);
						if(entry.contentEquals(user))
						{
							break;
						}
					}
					//gets values given the location from the previous loop
					String statuss = status.getEntry(x);
					String pathh = paths.getEntry(x);
					try {
						FileWriter fout = new FileWriter("userinfo.txt");
						fout.write(user + "\n" + statuss + "\n" + pathh);
						fout.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						BufferedReader read;
						read = new BufferedReader(new FileReader("usernames.txt"));
						String line = read.readLine();
						while(line!=null)
						{
							if(line.contentEquals(pathh))
							{
								while(line!= null)
								{
									if(line.contentEquals("NEW"))
										break;
									//Strores line in array list friends
									friends.add(line);
									line = read.readLine();

									
								}
								break;
							}
							line = read.readLine();
						}
					} catch(IOException e4)
					{
						e4.printStackTrace();
					}
					//removes the first entry since it is a path. 
					//will create a AWT exception if remvoed it in previous while loop
					friends.remove(1);
					try {
						FileWriter fout = new FileWriter("friends.txt");
						len = friends.getLength();
						for(x= 1;x!=len+1;x++)
							fout.write(friends.getEntry(x) + "\n");
						fout.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Home newhome = new Home();
					newhome.setVisible(true);

				
				}

					
			}	
	
		});
		btnNewButton.setBounds(266, 112, 117, 29);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(121, 112, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				newUserinterface newus = new newUserinterface();
				newus.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(6, 6, 81, 29);
		contentPane.add(btnNewButton_1);
		

		
		
	}
}

