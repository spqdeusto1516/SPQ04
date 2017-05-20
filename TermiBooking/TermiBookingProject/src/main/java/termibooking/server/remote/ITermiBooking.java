package termibooking.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


import termibooking.server.dto.BusDTO;

public interface ITermiBooking extends Remote {
	/**
	 * Creates a new user in database that has a n email and a password 
	 */
	public boolean signIn(String email, String password) throws RemoteException;
	/**
	 * Gets a user already created from database, looks if there is any user with the email and password provided
	 */
	public boolean login(String email, String password) throws RemoteException;
	/**
	 * Makes a new reservation for one bus with a number of selected seats
	 */
	public boolean newReservation(BusDTO bus, int seats) throws RemoteException;
	/**
	 * Add the passenger list to the reservation previously done, after that stores reservation into database
	 */
	public void addPassenger(String name, String surname, int age, int seats) throws RemoteException;
	/**
	 * Removes a user from database give it's email 
	 */
	public boolean removeUser(String email) throws RemoteException;
	/**
	 * Gets the emails of all users in database
	 */
	public List<String> getUsers() throws RemoteException;
}
