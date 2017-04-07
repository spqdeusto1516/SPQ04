package termibooking.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBus extends Remote {
	public void sendMessage(String message) throws RemoteException;
}
