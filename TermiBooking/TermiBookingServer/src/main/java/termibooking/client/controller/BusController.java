package termibooking.client.controller;

import termibooking.client.remote.RMIServiceLocator;



public class BusController {
	
	private RMIServiceLocator rmi;

	public BusController(RMIServiceLocator rmi) {
		this.rmi=rmi;
	}
	
	public void sendMessage(){
		try {
			rmi.getBusService().sendMessage("My name is Jorge");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
