package termibooking.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import termibooking.server.dto.BusDTO;

public interface IBus extends Remote {
	public List<BusDTO> findBus(String arrival, String departure) throws RemoteException;
}
