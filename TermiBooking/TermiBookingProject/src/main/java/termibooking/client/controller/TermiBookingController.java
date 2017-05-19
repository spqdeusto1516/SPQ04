package termibooking.client.controller;

import termibooking.client.remote.RMIServiceLocator;
import termibooking.server.dao.DataDAO;
import termibooking.server.dto.BusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TermiBookingController {
	final static Logger logger = LoggerFactory.getLogger(TermiBookingController.class);
	private RMIServiceLocator rmi;

	public TermiBookingController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}
	
	public boolean removeUser(String email){
		boolean removed=false;
		try {
			removed=rmi.getTermiBookingtService().removeUser(email);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return removed;
	}
	public boolean signIn(String email, String password){
		boolean stored=false;
		try {
			stored=rmi.getTermiBookingtService().signIn(email, password);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return stored;
	}
	
	public boolean reservation(BusDTO bus, int seats){
		boolean reserved=false;
		try {
			reserved=rmi.getTermiBookingtService().newReservation(bus, seats);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reserved;
	}
	
	public void addPassenger(String name, String surname, int age, int seats){
		try {
			rmi.getTermiBookingtService().addPassenger(name, surname, age, seats);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
