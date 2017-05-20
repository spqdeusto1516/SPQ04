package termibooking.client.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import termibooking.client.controller.TermiBookingController;


public class adminPage {

	private JFrame frame;
	private JTextField textField;
	List<String> users = new ArrayList<String>();
	TermiBookingController tc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public adminPage(TermiBookingController tc) {
		this.tc=tc;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 380);
		frame.setTitle("TermiBooking Admin");
		frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblListOfUsers = new JLabel("List of Users");
		lblListOfUsers.setBounds(94, 22, 68, 23);
		lblListOfUsers.setSize(lblListOfUsers.getPreferredSize());
		panel.add(lblListOfUsers);
		
		JLabel lblNameOfThe = new JLabel("Name of the user");
		lblNameOfThe.setBounds(339, 26, 106, 14);
		panel.add(lblNameOfThe);
		
		textField = new JTextField();
		textField.setBounds(319, 56, 126, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tc.removeUser(textField.getText());
			}
			
		});
		btnDelete.setBounds(339, 112, 89, 23);
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 47, 174, 191);
		
		JList<String> list = new JList<String>();
		list.setBounds(32, 47, 174, 191);
		//list.setPreferredSize(new Dimension(209, 137));
		users=tc.getUsers();
		String[] user = new String[users.size()];
		for(int i = 0; i < users.size(); i++){
			user[i] = users.get(i);
		}
		//String[] buses = busesString.toArray(new String[0]);
		list.setListData(user);
		scrollPane.setViewportView(list);
		panel.add(scrollPane);
		initializeList();
		
		int anchVentana = 535;
		int altVentana = 380;
		frame.setSize(535, 320);
		
		// Centrar la ventana a partir de la resoluci�n actual de la pantalla
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Rectangle resolucion = gs[0].getDefaultConfiguration().getBounds();
		int x = (int) (resolucion.getWidth())/2 - anchVentana/2;
		int y = (int) (resolucion.getHeight())/2 - altVentana/2;
		frame.setLocation(x, y);
	}

	private void initializeList() {
		// TODO implement initialize list
		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
}
