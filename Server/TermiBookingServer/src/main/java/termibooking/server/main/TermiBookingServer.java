package termibooking.server.main;

import java.util.ArrayList;

import termibooking.server.dao.DataDAO;
import termibooking.server.dao.IDataDAO;
import termibooking.server.data.Bus;
import termibooking.server.data.Company;
import termibooking.server.data.Passenger;
import termibooking.server.data.Reservation;
import termibooking.server.data.Station;
import termibooking.server.data.User;



public class TermiBookingServer {
	
	private IDataDAO dao;

	public TermiBookingServer() {
		dao=new DataDAO();
	}
	
	public void storeReservation(){
		System.out.println("Guardar reserva");
		Company comp=new Company("ALSA", "1", 2);
		Station dep=new Station("TermiBus", "1", "Bilbao");
		Bus bus=new Bus("1", 20, 50, 50, 50, dep, dep, comp);
		ArrayList<Passenger> pass=new ArrayList<Passenger>();
		pass.add(new Passenger("Jon", "Iturmendi", 20));
		pass.add(new Passenger("Joseba", "Eskubi", 21));
		User user=new User("joseba.eskubi@opendeusto.es", "1234", dep);
		Reservation res=new Reservation(bus, 2, pass, user);
		dao.storeReservation(res);
	}
	
	public synchronized void recieveMessage(String message){
		System.out.println(message);
	}
	
}
