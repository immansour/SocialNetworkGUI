package midterm;
/**
 *Program that allows for the user to edit
 *their name status and picture
 * 
 *@author: Ibrahim Mansour
 *@author: Yousef Helal
 * CIS22C

 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class editprof extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editprof frame = new editprof();
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
	public editprof() {
		String name = null;
		String status = null;
		String path = null;
		Profile user = new Profile();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("userinfo.txt"));
			name = reader.readLine();
			status = reader.readLine();
			path = reader.readLine();
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Edit Profile");
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
		
		if(status.contentEquals("Online"))
		{
			onlineButton.setSelected(true);
		}
		if(status.contentEquals("Offline"))
		{
			offlineButton.setSelected(true);
		}
		if(status.contentEquals("Busy"))
		{
			busyButton.setSelected(true);
		}
		
		JLabel lblNewLabel_3 = new JLabel("Profile Picture:");
		lblNewLabel_3.setBounds(6, 184, 104, 16);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField(name);
		textField.setBounds(58, 36, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_4= new JLabel();
		lblNewLabel_4.setBounds(241, 41, 130, 16);
		contentPane.add(lblNewLabel_4);

		

		
		JButton btnNewButton_1 = new JButton("Save Changes");
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
				contentPane.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(304, 228, 117, 29);
		contentPane.add(btnNewButton_1);
		




		
		
		
	}
	

}
