package midterm;
/**
 * 
 * Initial Jframe that starts the program
 * 
 * @Author: Ibrahim Mansour and Yousef Helal
 * CIS22C
 * 
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Choice;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class userInterface {

	private JFrame frame;
	private JTextField textField;
	private JPanel Createprofile;
	private JPanel Home;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userInterface window = new userInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public userInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to FakeBook");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lblNewLabel.setBounds(32, 6, 279, 45);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Create Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter foute = new FileWriter("usernames.txt");
					foute.close();
				}
				catch(IOException e4)
				{
					e4.printStackTrace();
				}
				
				frame.dispose();
				createprof CP = new createprof();
				CP.setVisible(true);
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 17));
		btnNewButton.setBounds(106, 100, 230, 63);
		frame.getContentPane().add(btnNewButton);
		
	}
}
