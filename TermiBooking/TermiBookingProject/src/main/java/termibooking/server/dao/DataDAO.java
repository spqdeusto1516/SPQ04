package termibooking.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import termibooking.server.data.Bus;
import termibooking.server.data.Reservation;
import termibooking.server.data.Station;
import termibooking.server.data.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataDAO implements IDataDAO {
	final static Logger logger = LoggerFactory.getLogger(DataDAO.class);
private PersistenceManagerFactory pmf;
	
	public DataDAO(){
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	public boolean storeUser(User user) {
		return this.storeObject(user);
	}
	
	private boolean storeObject(User user) {
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    boolean stored;
	    try {
	       tx.begin();
	       logger.info("   * Storing an object: " + user);
	       pm.makePersistent(user);
	       tx.commit();
	      stored=true;
	    } catch (Exception ex) {
	    	logger.error("   $ Error storing an object: " + ex.getMessage());
	    	stored=false;
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
				
    		pm.close();
    		
	    }
	    return stored;
	}
	
	public boolean storeReservation(Reservation reserv) {
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    boolean stored;
	    try {
	       tx.begin();
	       logger.info("   * Storing an object: " + reserv);
	       pm.makePersistent(reserv);
	       tx.commit();
	       stored=true;
	    } catch (Exception ex) {
	    	logger.error("   $ Error storing an object: " + ex.getMessage());
	    	stored=false;
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
				
    		pm.close();
	    }
		return stored;
	}
	
	public List<Bus> getBuses() {
		PersistenceManager pm = pmf.getPersistenceManager();
		/* By default only 1 level is retrieved from the db
		 * so if we wish to fetch more than one level, we must indicate it
		 */
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		List<Bus> buses = new ArrayList<Bus>();
		
		try {
			logger.info("   * Retrieving an Extent for Products.");
			
			tx.begin();			
			Extent<Bus> extent = pm.getExtent(Bus.class, true);
			
			for (Bus bus : extent) {
				buses.add(bus);
			}

			tx.commit();			
		} catch (Exception ex) {
	    	logger.error("   $ Error retrieving an extent: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}

    		pm.close();    		
	    }
	    				
		return buses;
	}
	
	public User getUser(String name){
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		User user = null;
	    
		try {
			logger.info ("   * Querying a Product: " + name);
			
	    	tx.begin();
	    	Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + name +"'");
	    	query.setUnique(true);
	    	user = (User)query.execute();	    
 	    	tx.commit();
   	    
	     } catch (Exception ex) {
		   	logger.error("   $ Error retreiving an extent: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
				
	   		pm.close();
	     }

	    return user;
	}	
	
	
	public boolean updateBus(Bus bus) {
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    boolean updated;
	    try {
	    	tx.begin();
	    	pm.makePersistent(bus);
	    	tx.commit();
	    	updated=true;
	     } catch (Exception ex) {
		   	logger.error("   $ Error retreiving an extent: " + ex.getMessage());
		   	updated=false;
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
				
	   		pm.close();
	     }
	    return updated;
	}

	public List<User> getUsers() {
		PersistenceManager pm = pmf.getPersistenceManager();
		/* By default only 1 level is retrieved from the db
		 * so if we wish to fetch more than one level, we must indicate it
		 */
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		List<User> users = new ArrayList<User>();
		
		try {
			logger.info("   * Retrieving an Extent for Products.");
			
			tx.begin();			
			Extent<User> extent = pm.getExtent(User.class, true);
			
			for (User user : extent) {
				users.add(user);
			}

			tx.commit();			
		} catch (Exception ex) {
	    	logger.error("   $ Error retrieving an extent: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}

    		pm.close();    		
	    }
	    				
		return users;
	}

	public boolean deleteUser(String name) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();
	    User user=null;
	    boolean deleted;
	    	logger.info("Deleting a user from database");
		try {

	    	tx.begin();
	    	Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + name +"'");
	    	query.setUnique(true);
	    	user = (User)query.execute();
	    	pm.deletePersistent(user);
 	    	tx.commit();
 	    	deleted=true;
	     } catch (Exception ex) {
		   	logger.error("   $ Error retreiving an extent: " + ex.getMessage());
		   	deleted=false;
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
				
	   		pm.close();
	     }

		return deleted;
	}

	public void deleteBus(String code) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();
	    Bus bus=null;
	    	logger.info("Deleting a bus from database");
		try {

	    	tx.begin();
	    	Query<?> query = pm.newQuery("SELECT FROM " + Bus.class.getName() + " WHERE code == '" + code +"'");
	    	query.setUnique(true);
	    	bus = (Bus)query.execute();
	    	pm.deletePersistent(bus);
 	    	tx.commit();
	     } catch (Exception ex) {
		   	logger.error("   $ Error retreiving an extent: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
				
	   		pm.close();
	     }
		
	}
}
