package termibooking.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import termibooking.server.main.TermiBookingServer;

public class BusFacade extends UnicastRemoteObject implements IBus{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TermiBookingServer teser;
	
	
	public BusFacade(TermiBookingServer teser) throws RemoteException {
		super();
		this.teser = teser;
	}


	@Override
	public void sendMessage(String message) {
		teser.recieveMessage(message);
		
	}
}
