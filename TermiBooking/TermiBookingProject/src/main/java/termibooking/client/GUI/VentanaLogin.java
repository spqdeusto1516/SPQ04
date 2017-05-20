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

import termibooking.client.controller.BusController;
import termibooking.client.controller.TermiBookingController;



public class VentanaLogin extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	int role;
	public VentanaLogin(final int role, final TermiBookingController tc, final BusController bc) {
		this.role = role;
		
		getContentPane().setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblLogIn.setBounds(171, 32, 104, 33);
		getContentPane().add(lblLogIn);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(137, 79, 46, 14);
		getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(193, 76, 117, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(115, 115, 63, 14);
		getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 112, 117, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNeedAnAccount = new JLabel("Need an account?");
		lblNeedAnAccount.setBounds(174, 191, 104, 14);
		getContentPane().add(lblNeedAnAccount);
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.setBounds(147, 216, 140, 23);
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new HiloVentanaRegister(tc).start();
			}
		});
		getContentPane().add(btnCreateAccount);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.setBounds(171, 145, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean logged=tc.login(textField.getText(), textField_1.getText());
				if(logged){
					if (role == 1){
						new HiloVentanaUser(tc, bc).start();
					} else if (role == 2) {
						new HiloVentanaAdmin(tc).start();
					}
				}
			}
		});
		getContentPane().add(btnLogin);
		
		int anchVentana = 450;
		int altVentana = 300;
		this.setSize(anchVentana, altVentana);
		this.setTitle("Log In");
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Centrar la ventana a partir de la resoluci�n actual de la pantalla
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Rectangle resolucion = gs[0].getDefaultConfiguration().getBounds();
		int x = (int) (resolucion.getWidth())/2 - anchVentana/2;
		int y = (int) (resolucion.getHeight())/2 - altVentana/2;
		setLocation(x, y);
		
	}
	
	static class HiloVentanaRegister extends Thread {
		TermiBookingController tc;
		public HiloVentanaRegister(TermiBookingController tc){
			this.tc = tc;
		}
		
		public void run(){
			new VentanaRegister(tc).setVisible(true);
		}
	}
	
	static class HiloVentanaUser extends Thread {
		TermiBookingController tc;
		BusController bc;
		public HiloVentanaUser(TermiBookingController tc, BusController bc){
			this.tc = tc;
			this.bc = bc;
		}
		
		public void run(){
			new VentanaUser(tc, bc).setVisible(true);
		}
	}
	
	static class HiloVentanaAdmin extends Thread {
		TermiBookingController tc;
		public HiloVentanaAdmin(TermiBookingController tc){
			this.tc=tc;
		}
		
		public void run(){
			new adminPage(tc).getFrame().setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//VentanaLogin v = new VentanaLogin(1);
		//v.setVisible(true);
	}

}
