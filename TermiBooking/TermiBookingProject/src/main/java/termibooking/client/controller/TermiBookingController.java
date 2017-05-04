package termibooking.client.controller;

import termibooking.client.remote.RMIServiceLocator;
import termibooking.server.dto.BusDTO;

public class TermiBookingController {
	
	private RMIServiceLocator rmi;

	public TermiBookingController(RMIServiceLocator rmi) {
		this.rmi = rmi;
	}
	
	public boolean removeUser(String email){
		boolean removed=false;
		try {
			removed=rmi.getTermiBookingtService().removeUser(email);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return removed;
	}
	public boolean signIn(String email, String password){
		boolean stored=false;
		try {
			stored=rmi.getTermiBookingtService().signIn(email, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return stored;
	}
	
	public boolean reservation(BusDTO bus, int seats){
		boolean reserved=false;
		try {
			reserved=rmi.getTermiBookingtService().newReservation(bus, seats);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return reserved;
	}
}
