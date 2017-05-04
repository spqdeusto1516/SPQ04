package termibooking.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import termibooking.server.data.Bus;
import termibooking.server.dto.BusAssembler;
import termibooking.server.dto.BusDTO;
import termibooking.server.main.TermiBookingServer;

public class TermiBookingFacade extends UnicastRemoteObject implements ITermiBooking{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TermiBookingServer teser;

	public TermiBookingFacade(TermiBookingServer teser) throws RemoteException {
		super();
		this.teser=teser;
	}

	public boolean signIn(String email, String password)
			throws RemoteException {
		boolean signed=teser.signIn(email, password);
		return signed;
	}

	public boolean login(String email, String password) throws RemoteException {
		teser.login(email, password);
		return false;
	}

	public boolean newReservation(BusDTO bus, int seats) throws RemoteException {
		BusAssembler ba=new BusAssembler();
		Bus busn=ba.assemble(bus);
		boolean reserved=teser.newReservation(busn, seats);
		return reserved;
	}

	public void addPassenger(String name, String surname, int age, int seats)
			throws RemoteException {
		teser.addPassenger(name, surname, age, seats);
		// TODO Auto-generated method stub
		
	}

	public boolean removeUser(String email) throws RemoteException {
		// TODO Auto-generated method stub
		boolean removed=teser.removeUser(email);
		return removed;
	}

}
