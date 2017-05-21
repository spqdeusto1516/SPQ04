package termibooking.server.main;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.SyncResolver;

import termibooking.server.dao.DataDAO;
import termibooking.server.dao.IDataDAO;
import termibooking.server.data.Bus;
import termibooking.server.data.Company;
import termibooking.server.data.Passenger;
import termibooking.server.data.Pay;
import termibooking.server.data.Reservation;
import termibooking.server.data.Station;
import termibooking.server.data.User;
import termibooking.server.dto.BusDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TermiBookingServer {
	final static Logger logger = LoggerFactory.getLogger(TermiBookingServer.class);
	private IDataDAO dao;
	private User user;
	private Reservation reser;
	private List<User> users = new ArrayList<User>();

	public TermiBookingServer() {
		dao=new DataDAO();
	}
	
	public synchronized boolean signIn(String email, String password){
		User u=new User(email, password);
		boolean stored=dao.storeUser(u);
		return stored;
	}
	public synchronized boolean login(String email, String password){
		boolean logged = false;
		users = dao.getUsers();
		for(User u:users){
			if(u.getEmail().equals(email)&&u.getPassword().equals(password)){
				user=u;
				logged=true;
			}
		}
		return logged;
	}
	public synchronized boolean newReservation(Bus bus, int seats){
		int remaining_seats=bus.getRemaining_seats();
		bus.setRemaining_seats(remaining_seats-seats);
		dao.deleteBus(bus.getCode());
		Pay payment=new Pay();
		payment.PayCreditCard(300, "5563645636473", user);
		reser=new Reservation(bus, seats, user, payment);
		return true;
	}
	public IDataDAO getDao() {
		return dao;
	}

	public void setDao(IDataDAO dao) {
		this.dao = dao;
	}

	public synchronized void addPassenger(String name, String surname, int age, int seats){
		Passenger pas= new Passenger(name, surname, age);
		reser.addPassenger(pas);
		logger.info("New passenger");
		if (seats==0){
			dao.storeReservation(reser);
		}
	}
	public synchronized List<Bus> findBus(String arrival, String departure){
		List<Bus> busDAO = new ArrayList<Bus>();
		busDAO = dao.getBuses();
		for(Bus b:busDAO){
			logger.info(b.getCode());
		}
		List<Bus> buses=new ArrayList<Bus>();
		for(Bus b : busDAO){
			if(b.getArrivalSta().getName().equals(arrival) && b.getDepartureSta().getName().equals(departure)){
				buses.add(b);
			}
		}
		return buses;
		
	}
	public synchronized boolean removeUser(String email){
		boolean deleted=dao.deleteUser(email);
		return deleted;
	}
	
	public synchronized List<String> getUsers(){
		List<String> names = new ArrayList<String>();
		for(User u:users){
			names.add(u.getEmail());
		}
		return names;
	}
	
}
