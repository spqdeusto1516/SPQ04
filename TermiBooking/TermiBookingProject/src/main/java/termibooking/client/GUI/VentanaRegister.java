package termibooking.client.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import termibooking.client.GUI.VentanaPassenger.MiActionListener;
import termibooking.client.controller.TermiBookingController;



public class VentanaRegister extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	public VentanaRegister(final TermiBookingController tc) {
		getContentPane().setLayout(null);
		
		JLabel lblCreateAccount = new JLabel("Create account");
		lblCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblCreateAccount.setBounds(119, 33, 199, 35);
		getContentPane().add(lblCreateAccount);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(141, 99, 46, 14);
		getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(197, 96, 109, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(119, 141, 56, 14);
		lblPassword.setSize(lblPassword.getPreferredSize());
		getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(197, 138, 109, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(152, 195, 89, 23);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tc.signIn(textField.getText(), textField_1.getText());				
			}
		});
		getContentPane().add(btnCreate);
		
		int anchVentana = 450;
		int altVentana = 300;
		this.setSize(anchVentana, altVentana);
		this.setTitle("Create account");
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Centrar la ventana a partir de la resoluci�n actual de la pantalla
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Rectangle resolucion = gs[0].getDefaultConfiguration().getBounds();
		int x = (int) (resolucion.getWidth())/2 - anchVentana/2;
		int y = (int) (resolucion.getHeight())/2 - altVentana/2;
		setLocation(x, y);
		
		ActionListener salir = new MiActionListener(this);
		btnCreate.addActionListener(salir);
	}

	class MiActionListener implements ActionListener {
		VentanaRegister v;
		public MiActionListener(VentanaRegister v){
			this.v = v;
		}
		
		public void actionPerformed(ActionEvent arg0){
			v.dispose();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//VentanaRegister v = new VentanaRegister();
		//v.setVisible(true);
	}

}
