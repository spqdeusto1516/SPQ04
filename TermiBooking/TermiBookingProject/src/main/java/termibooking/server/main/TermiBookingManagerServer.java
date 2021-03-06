package termibooking.server.main;

import java.rmi.Naming;

import termibooking.server.data.Bus;
import termibooking.server.data.Company;
import termibooking.server.data.Station;
import termibooking.server.remote.BusFacade;
import termibooking.server.remote.IBus;
import termibooking.server.remote.ITermiBooking;
import termibooking.server.remote.TermiBookingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TermiBookingManagerServer {
	final static Logger logger = LoggerFactory.getLogger(TermiBookingManagerServer.class);
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
			TermiBookingServer termiserv = new TermiBookingServer();
			ITermiBooking termibookingser = new TermiBookingFacade(termiserv);			
			Naming.rebind(nameTermiBooking, termibookingser);
			logger.info("* TermiBooking Service '"+nameTermiBooking+"'  active and waiting...");
			
			IBus busser = new BusFacade(termiserv);
			Naming.rebind(nameBus, busser);
			logger.info("* TermiBooking Bus Service '"+nameBus+"' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			@SuppressWarnings("unused")
			String line  = stdin.readLine();
		} catch (Exception e) {
			logger.error("$ TermiBookingManagerServer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
