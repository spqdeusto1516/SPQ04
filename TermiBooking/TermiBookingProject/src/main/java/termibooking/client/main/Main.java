package termibooking.client.main;

import termibooking.client.controller.BusController;
import termibooking.client.controller.TermiBookingController;
import termibooking.client.remote.RMIServiceLocator;


public class Main {

	public static void main(String[] args) {
		RMIServiceLocator rmi=new RMIServiceLocator();
		rmi.setService(args);
		BusController bc=new BusController(rmi);
		TermiBookingController tc=new TermiBookingController(rmi);
		tc.removeUser("joseesku@gmail.com");
	}

}
