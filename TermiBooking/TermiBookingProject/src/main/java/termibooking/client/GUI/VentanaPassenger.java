package termibooking.client.GUI;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import termibooking.client.controller.TermiBookingController;



public class VentanaPassenger extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int seats;
	public VentanaPassenger(final int number, final TermiBookingController tc, final int seats) {
		getContentPane().setLayout(null);
		this.seats = seats;
		String message = "Name of passenger " + number + ":";
		JLabel lblNameOfPassenger = new JLabel(message);
		lblNameOfPassenger.setBounds(22, 23, 113, 14);
		lblNameOfPassenger.setSize(lblNameOfPassenger.getPreferredSize());
		getContentPane().add(lblNameOfPassenger);
		
		textField = new JTextField();
		textField.setBounds(185, 20, 139, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(124, 123, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tc.addPassenger(textField.getText(), textField_1.getText(), Integer.parseInt(textField_2.getText()), seats);
				
			}
		});
		getContentPane().add(btnOk);
		
		JLabel lblSurnameOfPassenger = new JLabel("Surname of passenger "+number+":");
		lblSurnameOfPassenger.setBounds(22, 48, 118, 14);
		lblSurnameOfPassenger.setSize(lblSurnameOfPassenger.getPreferredSize());
		getContentPane().add(lblSurnameOfPassenger);
		
		textField_1 = new JTextField();
		textField_1.setBounds(185, 45, 139, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAgeOfPassenger = new JLabel("Age of passenger "+number+":");
		lblAgeOfPassenger.setBounds(22, 77, 118, 14);
		getContentPane().add(lblAgeOfPassenger);
		
		textField_2 = new JTextField();
		textField_2.setBounds(185, 74, 139, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		int anchVentana = 350;
		int altVentana = 200;
		this.setSize(350, 200);
		this.setTitle("Introduce passenger data");
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Centrar la ventana a partir de la resoluci�n actual de la pantalla
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Rectangle resolucion = gs[0].getDefaultConfiguration().getBounds();
		int x = (int) (resolucion.getWidth())/2 - anchVentana/2;
		int y = (int) (resolucion.getHeight())/2 - altVentana/2;
		setLocation(x, y);
		
		ActionListener salir = new MiActionListener(this);
		btnOk.addActionListener(salir);
	}
	
	class MiActionListener implements ActionListener {
		VentanaPassenger v;
		public MiActionListener(VentanaPassenger v){
			this.v = v;
		}
		
		public void actionPerformed(ActionEvent arg0){
			v.dispose();
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//VentanaPassenger v = new VentanaPassenger(1);
		//v.setVisible(true);
	}
}