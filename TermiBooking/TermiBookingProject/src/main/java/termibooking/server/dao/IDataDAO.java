package termibooking.server.dao;

import java.util.List;


import termibooking.server.data.*;

public interface IDataDAO {
	public boolean storeUser(User user);
	public List<User> getUsers();
	public List<Bus> getBuses();
	public User getUser(String name);
	public boolean deleteUser(String name);
	public void deleteBus(String code);
	public boolean storeReservation(Reservation reserv);
	public boolean updateBus(Bus bus);
}
