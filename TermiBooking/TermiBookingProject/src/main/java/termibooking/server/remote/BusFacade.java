package termibooking.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import termibooking.server.data.Bus;
import termibooking.server.dto.BusAssembler;
import termibooking.server.dto.BusDTO;
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


	public List<BusDTO> findBus(String arrival, String departure)
			throws RemoteException {
		BusAssembler ba=new BusAssembler();
		List<Bus> buses=teser.findBus(arrival, departure);
		List<BusDTO> busesdto=ba.assemble(buses);
		return busesdto;
	}
}
