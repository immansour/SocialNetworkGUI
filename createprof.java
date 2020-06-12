package midterm;


/**
 *Program that displays the create profile class, and gives the user the option 
 * to create a profile
 * 
 *@author: Ibrahim Mansour
 *@author: Yousef Helal
 * CIS22C

 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class createprof extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public static String name;
	public static String status;
	public static String path;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					createprof frame = new createprof();
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
	public createprof() {
		Profile user = new Profile();
		try {
			FileWriter foute = new FileWriter("friends.txt");
			foute.write(name + '\n');
			foute.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create a Profile");
		lblNewLabel.setBounds(19, 6, 142, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(6, 41, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setBounds(6, 73, 61, 16);
		contentPane.add(lblNewLabel_2);
		

		


		
		
		JButton btnNewButton = new JButton("Select File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.getFile();

			}
		});
		btnNewButton.setBounds(122, 179, 117, 29);
		contentPane.add(btnNewButton);
		
		
		
		JRadioButton onlineButton = new JRadioButton("Online");
		onlineButton.setBounds(79, 69, 141, 23);
		contentPane.add(onlineButton);
		
		JRadioButton offlineButton = new JRadioButton("Offline");
		offlineButton.setBounds(79, 94, 141, 23);
		contentPane.add(offlineButton);
		
		JRadioButton busyButton = new JRadioButton("Busy");
		busyButton.setBounds(79, 118, 141, 23);
		contentPane.add(busyButton);
		
		ButtonGroup g = new ButtonGroup();
		g.add(onlineButton);
		g.add(offlineButton);
		g.add(busyButton);
		
		JLabel lblNewLabel_3 = new JLabel("Profile Picture:");
		lblNewLabel_3.setBounds(6, 184, 104, 16);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(58, 36, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_4= new JLabel();
		lblNewLabel_4.setBounds(241, 41, 130, 16);
		contentPane.add(lblNewLabel_4);

		

		
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 user.setName(textField.getText());
			
				if(onlineButton.isSelected())
					user.setStatus("Online");
				if(offlineButton.isSelected())
					user.setStatus("Offline");
				if(busyButton.isSelected())
					user.setStatus("Busy");
				String name = user.getName();
				String status = user.getStatus();
				String path = user.path;

				try {
					FileWriter fout = new FileWriter("userinfo.txt");
					fout.write(name + "\n" + status + "\n" + path);
					fout.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
				
				Home menu = new Home();
				menu.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(304, 228, 117, 29);
		contentPane.add(btnNewButton_1);
		




		
		
		
	}

}
