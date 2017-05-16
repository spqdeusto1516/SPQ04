package termibooking.server;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;  

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;  

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import termibooking.server.dao.DataDAO;
import termibooking.server.dao.IDataDAO;
import termibooking.server.data.Bus;
import termibooking.server.data.Company;
import termibooking.server.data.Pay;
import termibooking.server.data.Reservation;
import termibooking.server.data.Station;
import termibooking.server.data.User;
import termibooking.server.main.TermiBookingServer;

public class DAOTest {

	IDataDAO dao;
	final static Logger logger = LoggerFactory.getLogger(DAOTest.class);
		
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DAOTest.class);
	}
	
	@Before
	public void setUp() throws Exception {	
		dao=new DataDAO();

	}

	@Test
	public void testStoreUser() {
		User u=new User("jon", "jon");
		boolean stored=dao.storeUser(u);
		assertTrue(stored);
		
	}
	
	@Test
	public void testGetUser() {
		User u=new User("joseba", "joseba");
		dao.storeUser(u);
		User u2=dao.getUser("joseba");
		assertEquals(u2.getEmail(), u.getEmail());
	}

	@Test
	public void testStoreReservation(){
		Bus b=new Bus("A3", 1, 50, 50, 40, new Station("Bilbao", "Bilbao", "Bilbao"), new Station("Madrid", "Madrid", "Madrid"), new Company("Alsa", "Alsa", 4));
		User u=new User("asier", "asier");
		Pay payment=new Pay();
		Reservation reserv=new Reservation(b, 4, u, payment);
		boolean reserved=dao.storeReservation(reserv);
		assertTrue(reserved);
			
	}
	
	@Test
	public void testListUsers(){
		List<User> users=new ArrayList<User>();
		users=dao.getUsers();
		for(User u : users){
			System.out.println(u.getEmail());
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
	
            System.out.println("Deleting test users from persistence. Cleaning up.");
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
	*/
}
