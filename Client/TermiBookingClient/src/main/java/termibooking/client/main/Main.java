package termibooking.client.main;

import termibooking.client.controller.BusController;
import termibooking.client.remote.RMIServiceLocator;


public class Main {

	public static void main(String[] args) {
		RMIServiceLocator rmi=new RMIServiceLocator();
		rmi.setService(args);
		BusController bc=new BusController(rmi);
		bc.sendMessage();	
	}

}
