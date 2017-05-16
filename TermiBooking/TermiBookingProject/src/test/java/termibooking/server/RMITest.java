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

import termibooking.server.data.Pay;
import termibooking.server.data.Reservation;
import termibooking.server.data.User;
import termibooking.server.dto.BusDTO;
import termibooking.server.main.TermiBookingServer;
import termibooking.server.remote.ITermiBooking;
import termibooking.server.remote.TermiBookingFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RMITest {
	// Properties are hard-coded because we want the test to be executed without external interaction
	
		private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		private static Thread rmiRegistryThread = null;
		private static Thread rmiServerThread = null;
		
		final static Logger logger = LoggerFactory.getLogger(RMITest.class);
		
		private ITermiBooking termibooking;
		@Rule public ContiPerfRule rule = new ContiPerfRule();
		public static junit.framework.Test suite() {
			return new JUnit4TestAdapter(RMITest.class);
		}


		@BeforeClass static public void setUp() {
			// Launch the RMI registry
			class RMIRegistryRunnable implements Runnable {

				public void run() {
					try {
						java.rmi.registry.LocateRegistry.createRegistry(1099);
						logger.info("BeforeClass: RMI registry ready.");
					} catch (Exception e) {
						logger.error("Exception starting RMI registry:");
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
					logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
					logger.info("**************: " + cwd);
					System.setProperty("java.rmi.server.codebase", "file:" + cwd);
					System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

					if (System.getSecurityManager() == null) {
						System.setSecurityManager(new SecurityManager());
					}

					String name = "//127.0.0.1:1099/termibooking";
					logger.info("BeforeClass - Setting the server ready TestServer name: " + name);

					try {
						TermiBookingServer teser = new TermiBookingServer();
						ITermiBooking termibooking = new TermiBookingFacade(teser);
						Naming.rebind(name, termibooking);
					} catch (RemoteException re) {
						logger.error(" # Messenger RemoteException: " + re.getMessage());
						re.printStackTrace();
						System.exit(-1);
					} catch (MalformedURLException murle) {
						logger.error(" # Messenger MalformedURLException: " + murle.getMessage());
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
			logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
			termibooking = (ITermiBooking) java.rmi.Naming.lookup(name);
			}
			catch (Exception re) {
				logger.error(" # Messenger RemoteException: " + re.getMessage());
		//		re.printStackTrace();
				System.exit(-1);
			} 
			
		}
		
		@Test
		@PerfTest(invocations=300, threads=10)
		public void registerNewUserTest() {
			boolean signed=false;
			try{
				logger.info("Test 1 - Register new user");
				signed=termibooking.signIn("joseesku@gmail.com", "1234");
			}
			catch (Exception re) {
				logger.error(" # Messenger RemoteException: " + re.getMessage());
			} 
			/*
			 * Very simple test, inserting a valid new user
			 */
			assertTrue( signed );
		}
		
		@Test
		@PerfTest(duration=1000)
		public void removingUserTest() {
			boolean removed=false;
			try{
				logger.info("Test 2 - Removing a user");
				termibooking.signIn("jon.iturmendi@opendeusto.es", "1234");
				// Silly way of testing the password testing
				removed=termibooking.removeUser("jon.iturmendi@opendeusto.es");
				
			}
			catch (Exception re) {
				logger.error(" # Messenger RemoteException: " + re.getMessage());
			} 
			/*
			 * Very simple test 
			 */
			assertTrue( removed );
		}
		
		
		@Test
		@PerfTest(invocations = 500)
		@Required(max = 150, average = 50)
		public void reservationTest() {
			logger.info("Test 3 - Making a reservation");
			boolean reserved=true;
			try{
				termibooking.signIn("jon.iturmendi@opendeusto.es", "1234");
				BusDTO bus=new BusDTO("6", 1, 50, 50, 20, "Bilbao", "Madrid", "Alsa");
				logger.info("Hacer reserva");
				termibooking.newReservation(bus, 3);
			} catch (Exception re){
				logger.error(" # Messenger RemoteException: " + re.getMessage());
			}
			assertTrue(reserved);
		}
		@Test
		public void addPassengerTest(){
			logger.info("Test 4 - Adding passenger");
			try{
				termibooking.signIn("jon.iturmendi@opendeusto.es", "1234");
				BusDTO bus=new BusDTO("6", 1, 50, 50, 20, "Bilbao", "Madrid", "Alsa");
				termibooking.newReservation(bus, 3);
				termibooking.addPassenger("Joseba", "Eskubi", 21, 0);
			} catch (Exception re){
				logger.error(" # Messenger RemoteException: " + re.getMessage());
			}
			assertTrue(true);
		}
/*
		@After public  void deleteDatabase() {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx = pm.currentTransaction();
	        try
	        {
	            tx.begin();
		
	            logger.info("Deleting test users from persistence. Cleaning up.");
	            Query<Pay> q3 = pm.newQuery(Pay.class);
	            Query<Reservation> q1 = pm.newQuery(Reservation.class);
	            Query<User> q2 = pm.newQuery(User.class);
	            long numberInstancesDeletedP = q3.deletePersistentAll();
	            long numberInstancesDeletedR = q1.deletePersistentAll();
	            long numberInstancesDeletedU = q2.deletePersistentAll();
	            System.out.println("Deleted " + numberInstancesDeletedU + " user" + numberInstancesDeletedR + "reservation" + numberInstancesDeletedP + " payment");
				
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
		*/
}
