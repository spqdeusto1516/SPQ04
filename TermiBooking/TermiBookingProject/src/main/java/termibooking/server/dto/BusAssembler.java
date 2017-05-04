package termibooking.server.dto;

import java.util.ArrayList;
import java.util.List;

import termibooking.server.data.Bus;
import termibooking.server.data.Company;
import termibooking.server.data.Station;

public class BusAssembler {
	
	public List<BusDTO> assemble(List<Bus> buses){
		List<BusDTO> busesDTO = new ArrayList<BusDTO>();

		for (Bus b : buses) {
			busesDTO.add(new BusDTO(b.getCode(), b.getNumber(), b.getTotal_seats(), b.getRemaining_seats(), b.getPrice(), b.getDepartureSta().getName(), b.getArrivalSta().getName(), b.getCompany().getName()));
		}

		System.out.println("* Assembling buses ...");
		
		return busesDTO;	
	}
	
	public Bus assemble(BusDTO b){
		Bus bus= new Bus(b.getCode(), b.getNumber(), b.getTotal_seats(), b.getRemaining_seats(), b.getPrice(), new Station(b.getDepartureSta(), "BIO", b.getDepartureSta()), new Station(b.getArrivalSta(), "BIO", b.getArrivalSta()), new Company(b.getCompany(), b.getCompany(), 5));
		System.out.println(" Assembling bus...");
		return bus;
	}
}
