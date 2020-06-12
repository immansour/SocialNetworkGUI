package midterm;

/**
 *Program that displays the 
 *main menu. Gives the user
 *the option of editing their current
 *profile, or adding friends. Has a sign out feature as 
 *well that appends the created profile
 * 
 *@author: Ibrahim Mansour
 *@author: Yousef Helal
 * CIS22C

 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private String path = null;
	private String name = null;
	private String status = null;
	ListInterface <String> friends = new AList <>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {

		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"userinfo.txt"));
			name = reader.readLine();
			status = reader.readLine();
			path = reader.readLine();
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Main menu");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 6, 236, 16);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon(path);
		Image scaleImage = icon.getImage().getScaledInstance(110, 110,Image.SCALE_DEFAULT);
		ImageIcon modIcon = new ImageIcon(scaleImage);
		
		JLabel img = new JLabel(modIcon);
		img.setBounds(20,34,110,110);
		contentPane.add(img);
		
		JButton btnNewButton = new JButton("Friends");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriends add = new AddFriends();
				add.setVisible(true);
				
				
				
				
			}
		});
		btnNewButton.setBounds(306, 34, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Edit Profile");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editprof newuser = new editprof();
				newuser.setVisible(true);
				contentPane.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(306, 69, 117, 29);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(6, 175, 49, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Status:");
		lblNewLabel_2.setBounds(6, 206, 49, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setText(name);
		lblNewLabel_3.setBounds(50, 175, 183, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setText(status);
		lblNewLabel_4.setBounds(56, 206, 95, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Friends:");
		lblNewLabel_5.setBounds(221, 116, 50, 16);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Log out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("usernames.txt", true)); 
			        out.write("\n" + "NEW" + "\n" + name + "\n" + status + "\n" + path + "\n"); 
					int len = friends.getLength();
					for(int x = 1; x!=len+1;x++)
					{
						out.write(friends.getEntry(x) + "\n");
					}
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				
				}
				newUserinterface newprof = new newUserinterface();
				newprof.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(355, 2, 95, 29);
		contentPane.add(btnNewButton_1);
		
		try {
			int ypos = 117;
			reader = new BufferedReader(new FileReader("friends.txt"));
			String line = reader.readLine();
			while(line != null)
			{
				friends.add(line);
				JLabel lblNewLabel_6 = new JLabel(line);
				lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
				lblNewLabel_6.setBounds(283, ypos, 120, 16);
				contentPane.add(lblNewLabel_6);
				line = reader.readLine();
				ypos+=13;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
