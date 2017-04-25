package termibooking.server.main;

import java.rmi.Naming;

import termibooking.server.remote.BusFacade;
import termibooking.server.remote.IBus;
import termibooking.server.remote.ITermiBooking;
import termibooking.server.remote.TermiBookingFacade;

public class TermiBookingManagerServer {
	
	public static void main(String[] args) {
		if (args.length != 4) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String nameBus = "//" + args[0] + ":" + args[1] + "/" + args[3];
		String nameTermiBooking = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			System.out.println("Reserva");
			TermiBookingServer termiserv = new TermiBookingServer();
			
			termiserv.storeReservation();
			
			ITermiBooking termibookingser = new TermiBookingFacade();			
			Naming.rebind(nameTermiBooking, termibookingser);
			System.out.println("* TermiBooking Service '"+nameTermiBooking+"'  active and waiting...");
			
			IBus busser = new BusFacade(termiserv);
			Naming.rebind(nameBus, busser);
			System.out.println("* TermiBooking Bus Service '"+nameBus+"' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			@SuppressWarnings("unused")
			String line  = stdin.readLine();
		} catch (Exception e) {
			System.err.println("$ TermiBookingManagerServer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
