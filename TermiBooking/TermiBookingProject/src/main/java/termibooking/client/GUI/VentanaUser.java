package termibooking.client.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JList;

import termibooking.client.controller.BusController;
import termibooking.client.controller.TermiBookingController;
import termibooking.server.dto.BusDTO;


public class VentanaUser extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	List<BusDTO> buses=new ArrayList<BusDTO>();
	public VentanaUser(final TermiBookingController tc, final BusController bc) {
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(128, 11, 33, 14);
		getContentPane().add(lblFrom);
		
		textField = new JTextField();
		textField.setBounds(171, 8, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(333, 11, 24, 14);
		getContentPane().add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(367, 8, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(255, 39, 89, 23);
		
		getContentPane().add(btnSearch);
		
		JLabel lblListOfBuses = new JLabel("List of buses:");
		lblListOfBuses.setBounds(32, 90, 78, 14);
		getContentPane().add(lblListOfBuses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 115, 275, 137);
		//scrollPane.setPreferredSize(new Dimension(209, 137));
		final JList<String> list = new JList<String>();
		final ArrayList<String> busesString = new ArrayList<String>();
		list.setBounds(32, 115, 275, 137);
		//list.setPreferredSize(new Dimension(209, 137));
		
		//list.setS
		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);
		
		JLabel lblBusCode = new JLabel("Bus code:");
		lblBusCode.setBounds(394, 118, 54, 14);
		lblBusCode.setSize(lblBusCode.getPreferredSize());
		getContentPane().add(lblBusCode);
		
		JLabel lblNOfPassengers = new JLabel("N. of passengers:");
		lblNOfPassengers.setBounds(355, 165, 97, 14);
		lblNOfPassengers.setSize(lblNOfPassengers.getPreferredSize());
		getContentPane().add(lblNOfPassengers);
		
		textField_2 = new JTextField();
		textField_2.setBounds(480, 115, 86, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(480, 162, 86, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnMakeReservation = new JButton("Make reservation...");
		btnMakeReservation.setBounds(399, 219, 127, 23);
		btnMakeReservation.setSize(btnMakeReservation.getPreferredSize());
		btnMakeReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BusDTO bus = null;
				for(BusDTO b:buses){
					if(b.getCode().equals(textField_2.getText())){
						bus=b;
					}
				}
				
				tc.reservation(bus, Integer.parseInt(textField_3.getText()));
				new HiloVentanaPassenger(Integer.parseInt(textField_3.getText()), tc, 1).start();
				
				//tc.reservation(tc.getBus(Integer.parseInt(textField_2.getText())), Integer.parseInt(textField_3.getText()));
				
			}
		});
		getContentPane().add(btnMakeReservation);
		
		int anchVentana = 600;
		int altVentana = 300;
		this.setSize(anchVentana, altVentana);
		this.setTitle("TermiBooking User");
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Centrar la ventana a partir de la resoluci�n actual de la pantalla
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Rectangle resolucion = gs[0].getDefaultConfiguration().getBounds();
		int x = (int) (resolucion.getWidth())/2 - anchVentana/2;
		int y = (int) (resolucion.getHeight())/2 - altVentana/2;
		setLocation(x, y);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buses= bc.findBus(textField_1.getText(), textField.getText());
				for(BusDTO bus : buses){
					busesString.add(bus.getDepartureSta()+" - "+bus.getArrivalSta()+" ("+bus.getPrice()+") => "+bus.getCode());
				}
				String[] buses = new String[busesString.size()];
				for(int i = 0; i < busesString.size(); i++){
					buses[i] = busesString.get(i);
				}
				//String[] buses = busesString.toArray(new String[0]);
				list.setListData(buses);
			}
		});
	}
	
	static class HiloVentanaPassenger extends Thread {
		int passengers;
		TermiBookingController tc;
		int seats;
		public HiloVentanaPassenger(int pass, TermiBookingController tc, int seats){
			this.passengers = pass;
			this.tc = tc;
			this.seats=seats;
		}
		
		public void run(){
			for (int i = passengers; i > 0; i-- ){
				if (i == passengers){
					seats = 0;
				}else {
					seats = 1;
				}
				new VentanaPassenger(i, tc, seats).setVisible(true);
			}
			
		}
	}
	
	
	
	public static void main(String[] args) {
		
		//VentanaUser v = new VentanaUser();
		//v.setVisible(true);
		
		
		
		
	}
}
