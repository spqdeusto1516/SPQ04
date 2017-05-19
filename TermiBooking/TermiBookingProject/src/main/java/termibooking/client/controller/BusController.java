package termibooking.client.controller;

import java.util.ArrayList;
import java.util.List;

import termibooking.client.remote.RMIServiceLocator;
import termibooking.server.dto.BusDTO;



public class BusController {
	
	private RMIServiceLocator rmi;

	public BusController(RMIServiceLocator rmi) {
		this.rmi=rmi;
	}
	public List<BusDTO> findBus(String arrival, String departure){
		List<BusDTO> buses = new ArrayList<BusDTO>();
		try{
			buses=rmi.getBusService().findBus(arrival, departure);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return buses;
	}
}
