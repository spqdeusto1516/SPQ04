package termibooking.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TermiBookingFacade extends UnicastRemoteObject implements ITermiBooking{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TermiBookingFacade() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

}
