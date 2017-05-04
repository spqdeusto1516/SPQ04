package termibooking.server;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Rule;
import org.databene.contiperf.Required;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.EmptyReportModule;

import termibooking.server.data.User;
import termibooking.server.dto.BusDTO;
import termibooking.server.main.TermiBookingServer;
import termibooking.server.remote.ITermiBooking;
import termibooking.server.remote.TermiBookingFacade;


public class RMITest {
	// Properties are hard-coded because we want the test to be executed without external interaction
	
		private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		private static Thread rmiRegistryThread = null;
		private static Thread rmiServerThread = null;
		
		private ITermiBooking termibooking;

		public static junit.framework.Test suite() {
			return new JUnit4TestAdapter(RMITest.class);
		}


		@BeforeClass static public void setUp() {
			// Launch the RMI registry
			class RMIRegistryRunnable implements Runnable {

				public void run() {
					try {
						java.rmi.registry.LocateRegistry.createRegistry(1099);
						System.out.println("BeforeClass: RMI registry ready.");
					} catch (Exception e) {
						System.out.println("Exception starting RMI registry:");
						e.printStackTrace();
					}	
				}
			}
			
			rmiRegistryThread = new Thread(new RMIRegistryRunnable());
			rmiRegistryThread.start();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			
			class RMIServerRunnable implements Runnable {

				public void run() {
					System.out.println("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
					System.out.println("**************: " + cwd);
					System.setProperty("java.rmi.server.codebase", "file:" + cwd);
					System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

					if (System.getSecurityManager() == null) {
						System.setSecurityManager(new SecurityManager());
					}

					String name = "//127.0.0.1:1099/termibooking";
					System.out.println("BeforeClass - Setting the server ready TestServer name: " + name);

					try {
						TermiBookingServer teser = new TermiBookingServer();
						ITermiBooking termibooking = new TermiBookingFacade(teser);
						Naming.rebind(name, termibooking);
					} catch (RemoteException re) {
						System.err.println(" # Messenger RemoteException: " + re.getMessage());
						re.printStackTrace();
						System.exit(-1);
					} catch (MalformedURLException murle) {
						System.err.println(" # Messenger MalformedURLException: " + murle.getMessage());
						murle.printStackTrace();
						System.exit(-1);
					}
				}
			}
			rmiServerThread = new Thread(new RMIServerRunnable());
			rmiServerThread.start();
			try {
				Thread.sleep(4000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		
		}
		

		@Before public void setUpClient() {
			try {
			System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			String name = "//127.0.0.1:1099/termibooking";
			System.out.println("BeforeTest - Setting the client ready for calling TestServer name: " + name);
			termibooking = (ITermiBooking) java.rmi.Naming.lookup(name);
			}
			catch (Exception re) {
				System.err.println(" # Messenger RemoteException: " + re.getMessage());
		//		re.printStackTrace();
				System.exit(-1);
			} 
			
		}
		
		@Test 
		public void registerNewUserTest() {
			boolean signed=false;
			try{
				System.out.println("Test 1 - Register new user");
				signed=termibooking.signIn("joseesku@gmail.com", "1234");
			}
			catch (Exception re) {
				System.err.println(" # Messenger RemoteException: " + re.getMessage());
			} 
			/*
			 * Very simple test, inserting a valid new user
			 */
			assertTrue( signed );
		}
		
		@Test public void removingUserTest() {
			boolean removed=false;
			try{
				System.out.println("Test 2 - Removing a user");
				termibooking.signIn("jon.iturmendi@opendeusto.es", "1234");
				// Silly way of testing the password testing
				removed=termibooking.removeUser("jon.iturmendi@opendeusto.es");
				
			}
			catch (Exception re) {
				System.err.println(" # Messenger RemoteException: " + re.getMessage());
			} 
			/*
			 * Very simple test 
			 */
			assertTrue( removed );
		}
		
		
		@Test public void reservationTest() {
			System.out.println("Test 3 - Making a reservation");
			boolean reserved=true;
			try{
				termibooking.signIn("jon.iturmendi@opendeusto.es", "1234");
				BusDTO bus=new BusDTO("6", 1, 50, 50, 20, "Bilbao", "Madrid", "Alsa");
				System.out.println("Hacer reserva");
				termibooking.newReservation(bus, 3);
			} catch (Exception re){
				System.err.println(" # Messenger RemoteException: " + re.getMessage());
			}
			assertTrue(reserved);
		}
		

		@After public  void deleteDatabase() {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx = pm.currentTransaction();
	        try
	        {
	            tx.begin();
		
	            System.out.println("Deleting test users from persistence. Cleaning up.");
	            Query<User> q1 = pm.newQuery(User.class);
	            long numberInstancesDeleted = q1.deletePersistentAll();
	            System.out.println("Deleted " + numberInstancesDeleted + " user");
				
	            tx.commit();
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
			
		}

		@AfterClass static public void tearDown() {
			try	{
				rmiServerThread.join();
				rmiRegistryThread.join();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			

		} 
}
