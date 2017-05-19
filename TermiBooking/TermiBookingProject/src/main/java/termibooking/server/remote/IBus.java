package termibooking.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import termibooking.server.dto.BusDTO;

public interface IBus extends Remote {
	/**
	 * Returns the list of buses from the database selected by arrival and departure stations.
	 */
	public List<BusDTO> findBus(String arrival, String departure) throws RemoteException;
}
