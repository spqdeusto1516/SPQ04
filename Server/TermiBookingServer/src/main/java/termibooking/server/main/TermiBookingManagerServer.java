package termibooking.server.main;

import java.rmi.Naming;

public class TermiBookingManagerServer {
	
	public static void main(String[] args) {
		if (args.length != 4) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String nameTermiBooking = "//" + args[0] + ":" + args[1] + "/" + args[3];
		String nameBus = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			System.out.println("Reserva");
			TermiBookingServer termiserv = new TermiBookingServer();
			
			termiserv.storeReservation();
			
			//IEasyBooking easybookingser = new EasyBookingFacade(ebser);			
			//Naming.rebind(nameEasyBooking, easybookingser);
			System.out.println("* TermiBooking Service  active and waiting...");
			
			//IFlight flightser = new FlightFacade(ebser);
			//Naming.rebind(nameFlight, flightser);
			System.out.println("* TermiBooking Bus Service active and waiting...");
		} catch (Exception e) {
			System.err.println("$ TermiBookingManagerServer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
