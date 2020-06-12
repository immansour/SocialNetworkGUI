package midterm;
/**
 * Program that allows for the user to view the 
 * data for the different profile names.
 * @author ibrahimmansour and yousef hela
 * CIS22C

 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class view extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
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
	public view() {
		
		String path = null;
		String name = null;
		String status = null;
		String line = null;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"view.txt"));
			name = reader.readLine();
			status = reader.readLine();
			path = reader.readLine();
			line = reader.readLine();
			int y = 144;
			while(line!= null)
			{
				JLabel lblNewLabel_5 = new JLabel(line);
				lblNewLabel_5.setBounds(271, y, 150, 16);
				contentPane.add(lblNewLabel_5);
				y+=28;
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(path);
		Image scaleImage = icon.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);
		ImageIcon modIcon = new ImageIcon(scaleImage);
		contentPane.setLayout(null);
		
		JLabel img = new JLabel(modIcon);
		img.setBounds(5,58,150,150);
		contentPane.add(img);
		
		JLabel lblNewLabel_1 = new JLabel("Status: ");
		lblNewLabel_1.setBounds(215, 100, 47, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Name: ");
		lblNewLabel.setBounds(215, 58, 44, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setText(name);
		lblNewLabel_2.setBounds(271, 58, 173, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setText(status);
		lblNewLabel_3.setBounds(271, 100, 173, 16);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriends add = new AddFriends();
				add.setVisible(true);
				contentPane.setVisible(false);
			}
		});
		btnNewButton.setBounds(5, 6, 75, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Friends:");
		lblNewLabel_4.setBounds(215, 144, 61, 16);
		contentPane.add(lblNewLabel_4);
		

		
		
		
		
	}

}
