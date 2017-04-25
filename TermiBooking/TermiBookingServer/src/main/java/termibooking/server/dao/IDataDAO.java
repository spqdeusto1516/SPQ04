package termibooking.server.dao;

import java.util.List;


import termibooking.server.data.*;

public interface IDataDAO {
	public void storeUser(User user);
	public List<User> getUsers();
	public List<Bus> getBuses();
	public User getUser(String name, String pass);
	public void deleteUser(User user);
	public void storeReservation(Reservation reserv);
	public void updateBus(Bus bus);
}
