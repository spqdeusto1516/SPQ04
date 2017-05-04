package termibooking.server;

import static org.junit.Assert.*;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import junit.framework.JUnit4TestAdapter;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import termibooking.server.data.Bus;
import termibooking.server.data.Company;
import termibooking.server.data.Station;
import termibooking.server.data.User;
import termibooking.server.main.TermiBookingServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerPerfTest {
	
	TermiBookingServer teser=new TermiBookingServer();
	
	final static Logger logger = LoggerFactory.getLogger(ServerPerfTest.class);
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	/*
	public static junit.framework.Test suite(){
		return new JUnit4TestAdapter(ServerPerfTest.class);
	}
	*/
	@Test
	public void signInTest() {
		boolean signed;
		signed=teser.signIn("joseesku", "joseesku");
		assertTrue(signed);
	}
	
	@Test
	@PerfTest(invocations = 30)
	public void reservationTest(){
		boolean reserved;
		teser.signIn("jon.iturmendi@opendeusto.es", "1234");
		Bus b= new Bus("A3", 1, 50, 50, 40, new Station("Bilbao", "Bilbao", "Bilbao"), new Station("Madrid", "Madrid", "Madrid"), new Company("Alsa", "Alsa", 4));
		reserved=teser.newReservation(b, 3);
		assertTrue(reserved);
	}
	
	@Test
	public void removingTest(){
		boolean removed;
		teser.signIn("jon", "jon");
		removed=teser.removeUser("jon");
		assertTrue(removed);
	}

	@After public  void deleteDatabase() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try
        {
            tx.begin();
	
            logger.info("Deleting test users from persistence. Cleaning up.");
            Query<User> q1 = pm.newQuery(User.class);
            long numberInstancesDeleted = q1.deletePersistentAll();
            logger.info("Deleted " + numberInstancesDeleted + " user");
			
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

}
