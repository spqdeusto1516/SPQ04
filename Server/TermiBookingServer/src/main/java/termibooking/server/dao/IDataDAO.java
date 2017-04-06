package termibooking.server.dao;

import java.util.List;


import termibooking.server.data.*;

public interface IDataDAO {
	public void storeUser(User user);
	public List<Bus> getBuses();
	public User getUser(String name, String pass);
	public void storeReservation(Reservation reserv);
	public void updateBus(Bus bus);
}
