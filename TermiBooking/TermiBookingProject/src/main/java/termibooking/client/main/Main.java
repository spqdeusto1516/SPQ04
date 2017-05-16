package termibooking.client.main;

import termibooking.client.controller.BusController;
import termibooking.client.controller.TermiBookingController;
import termibooking.client.remote.RMIServiceLocator;
import termibooking.server.dto.BusDTO;


public class Main {

	public static void main(String[] args) {
		RMIServiceLocator rmi=new RMIServiceLocator();
		rmi.setService(args);
		BusController bc=new BusController(rmi);
		TermiBookingController tc=new TermiBookingController(rmi);
		tc.signIn("joseesku", "1234");
		BusDTO bus = new BusDTO("6", 1, 50, 50, 20, "Bilbao", "Madrid", "Alsa");
		tc.reservation(bus, 3);
		tc.removeUser("joseesku");
	}

}
