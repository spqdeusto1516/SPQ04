package termibooking.client.controller;

import termibooking.client.remote.RMIServiceLocator;



public class BusController {
	
	private RMIServiceLocator rmi;

	public BusController(RMIServiceLocator rmi) {
		this.rmi=rmi;
	}
	
}
