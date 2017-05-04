package termibooking.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


import termibooking.server.dto.BusDTO;

public interface ITermiBooking extends Remote {
	public boolean signIn(String email, String password) throws RemoteException;
	public boolean login(String email, String password) throws RemoteException;
	public boolean newReservation(BusDTO bus, int seats) throws RemoteException;
	public void addPassenger(String name, String surname, int age, int seats) throws RemoteException;
	public boolean removeUser(String email) throws RemoteException;
}
