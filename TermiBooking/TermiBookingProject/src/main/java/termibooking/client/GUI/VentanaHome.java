package termibooking.client.GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import termibooking.client.controller.BusController;
import termibooking.client.controller.TermiBookingController;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaHome extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaHome(final TermiBookingController tc, final BusController bc) {
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("User");
		btnNewButton.setBounds(100, 75, 136, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new HiloVentanaLogin(1, tc, bc).start();
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Admin");
		btnNewButton_1.setBounds(257, 75, 136, 51);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new HiloVentanaLogin(2, tc, bc).start();
			}
		});
		getContentPane().add(btnNewButton_1);
		
		JLabel lblTermibooking = new JLabel("TermiBooking");
		lblTermibooking.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTermibooking.setBounds(156, 11, 184, 35);
		getContentPane().add(lblTermibooking);
		
		int anchVentana = 500;
		int altVentana = 200;
		this.setSize(500, 200);
		this.setTitle("TermiBooking Home");
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Centrar la ventana a partir de la resoluci�n actual de la pantalla
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Rectangle resolucion = gs[0].getDefaultConfiguration().getBounds();
		int x = (int) (resolucion.getWidth())/2 - anchVentana/2;
		int y = (int) (resolucion.getHeight())/2 - altVentana/2;
		setLocation(x, y);
	}
	
	static class HiloVentanaLogin extends Thread {
		int role;
		TermiBookingController tc;
		BusController bc;
		public HiloVentanaLogin(int role, TermiBookingController tc, BusController bc){
			this.role = role;
			this.tc = tc;
			this.bc = bc;
		}
		
		public void run(){
			new VentanaLogin(role, tc, bc).setVisible(true);
		}
	}
	
	
	public static void main(String[] args) {
		//VentanaHome v = new VentanaHome();
		//v.setVisible(true);

	}
}


