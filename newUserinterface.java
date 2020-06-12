package midterm;
/**
 *Program that displays the new user interface that 
 *allows for them to login or create a new profile
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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newUserinterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newUserinterface frame = new newUserinterface();
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
	public newUserinterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Fakebook");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		lblNewLabel.setBounds(33, 17, 376, 51);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("NEW USER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				createprof newprof = new createprof();
				newprof.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.MAGENTA);
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 29));
		btnNewButton.setBounds(20, 102, 162, 110);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("RETURNING USER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				returninguser returning = new returninguser();
				returning.setVisible(true);
			}
			
			
		});
		btnNewButton_1.setForeground(Color.CYAN);
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		btnNewButton_1.setBounds(213, 100, 196, 112);
		contentPane.add(btnNewButton_1);
		
		
	}

}
