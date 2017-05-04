package termibooking.server.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Reservation {
	
	@Persistent(defaultFetchGroup="true")
	private Bus bus;
	private int seats;
	@Join
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	@Persistent(defaultFetchGroup="true")
	private User user;
	@Persistent(defaultFetchGroup="true")
	private Pay payment;
	
	public Reservation(Bus bus, int seats, User user, Pay payment) {
		super();
		this.bus = bus;
		this.seats = seats;
		this.user = user;
		this.payment = payment;
	}

	public Pay getPayment() {
		return payment;
	}

	public void setPayment(Pay payment) {
		this.payment = payment;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}
	public void addPassenger(Passenger pas){
		this.passengers.add(pas);
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
