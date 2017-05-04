package termibooking.client.remote;

import termibooking.server.remote.IBus;
import termibooking.server.remote.ITermiBooking;

public class RMIServiceLocator {
	
	private IBus busserv;
	private ITermiBooking termiserv;


	 public RMIServiceLocator(){ 
	     
	    }

	    public void setService(String[] args) {
	    	// Add your code to get the TARGET reference HERE
	    	if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			try {
				String nameTermiBooking = "//" + args[0] + ":" + args[1] + "/" + args[2];
				String nameBus = "//" + args[0] + ":" + args[1] + "/" + args[3];
				busserv = (IBus) java.rmi.Naming.lookup(nameBus);
				termiserv = (ITermiBooking) java.rmi.Naming.lookup(nameTermiBooking);
			} catch (Exception e) {
				System.err.println("- Exception running the client: " + e.getMessage());
				e.printStackTrace();
			}
	    	
	    }
	    public IBus getBusService() {
	    	return busserv;
	    }
	    
	    public ITermiBooking getTermiBookingtService() {
	    	return termiserv;
	    }

}
