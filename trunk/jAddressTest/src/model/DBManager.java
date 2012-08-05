package model;

import java.util.Iterator;
import java.util.List;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.BusOwner;
import model.model.Driver;
import model.model.Host;
import model.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DBManager {

	 private static DBManager instance = new DBManager();
	 public static DBManager getInstance() {
			return instance;
		}
	 
	 
	 public void saveOrUpdate(Object o) {
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				session.saveOrUpdate(o);
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }
	 
	 public void delete(Object o){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				session.delete(o);
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }
	 
	 public List<Driver> loadAllDriver(){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Driver p where 1=1 order by p.name";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public List<AbsractInterface> loadAllDriver2(){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Driver p where 1=1 order by p.name";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public List<Bus> loadAllBus() {
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Bus p where 1=1 order by p.plate";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public Bus getBus(Long id) {
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Bus p where id="+id;
		        Query query = session.createQuery(hql);

				return  (Bus)query.uniqueResult();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public List<AbsractInterface> loadAllBus2(){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Bus p where 1=1 order by p.plate";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 
	 public List<AbsractInterface> loadUsers(){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from User p where 1=1";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public List<AbsractInterface> loadHosts(){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Host p where 1=1";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public List<AbsractInterface> loadBusOwners(){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from BusOwner p where 1=1";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public List<AbsractInterface> loadAuthorith(){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Authority p where 1=1 order by p.authority";
		        Query query = session.createQuery(hql);

				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public User checkPassword(String username,String password){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from User p where username=:username and password=:password";
		        Query query = session.createQuery(hql);
		        query.setParameter("username", username);
		        query.setParameter("password", password);
				return  (User)query.uniqueResult();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
		
	 }
	 
	 public List<AbsractInterface> searchEntiy(Class clazz,String name,String surname,String phone){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String queryString = "from "+clazz.getCanonicalName() +" p where 1=1";
				if(name!=null && !name.equals(""))
					queryString = queryString+ " and (p.name like '%"+name.toLowerCase()+"%' or p.name like '%"+name.toUpperCase()+"%')";				
				if(surname!=null && !surname.equals(""))
					queryString = queryString+ " and (p.surname like '%"+surname.toLowerCase()+"%' or p.name like '%"+surname.toUpperCase()+"%')";	
				if(phone!=null && !phone.equals(""))
					queryString = queryString+ " and (p.phone like '%"+phone.toLowerCase()+"%' or p.name like '%"+phone.toUpperCase()+"%')";
				
				Query query = session.createQuery(queryString);
				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }

	 public List<AbsractInterface> searchBus(String plate,String phone,String driverName,String driverSurname,
			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname){
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String queryString = "from Bus p where 1=1";
				if(plate!=null && !plate.equals(""))
					queryString = queryString+ " and p.plate like '%"+plate+"%'";				
				if(phone!=null && !phone.equals(""))
					queryString = queryString+ " and p.phone like '%"+phone+"%'";		
				if(driverName!=null && !driverName.equals(""))
					queryString = queryString+ " and ( (p.firstDriver.name like '%"+driverName.toLowerCase()+"%' or p.firstDriver.name like '%"+driverName.toUpperCase()+"%') or (p.secondDriver.name like '%"+driverName.toLowerCase()+"%' or p.secondDriver.name like '%"+driverName.toUpperCase()+"%')or (p.thirdDriver.name like '%"+driverName.toLowerCase()+"%' or p.thirdDriver.name like '%"+driverName.toUpperCase()+"%' ))";	
				if(driverSurname!=null && !driverSurname.equals(""))
					queryString = queryString+ " and (p.firstDriver.surname like '%"+driverName+"%' or p.secondDriver.surname like '%"+driverName+"%' or p.thirdDriver.surname like '%"+driverName+"%')";	
				if(hostName!=null && !hostName.equals(""))
					queryString = queryString+ " and p.host.name like '%"+hostName+"%'";	
				if(hostSurname!=null && !hostSurname.equals(""))
					queryString = queryString+ " and p.host.surname like '%"+hostSurname+"%'";	
				if(busOwnerName!=null && !busOwnerName.equals(""))
					queryString = queryString+ " and (p.firstOwner.name like '%"+busOwnerName+"%' or p.secondOwner.name like '%"+busOwnerName+"%')";	
				if(busOwnerSurname!=null && !busOwnerSurname.equals(""))
					queryString = queryString+ " and (p.bussecondOwner.surname like '%"+busOwnerSurname+"%' or p.bussecondOwner.surname like '%"+busOwnerSurname+"%')";	
				
				Query query = session.createQuery(queryString);
				return  query.list();

			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }
	 
	 public void deleteBusOwner(Object object){
		BusOwner busOwner = (BusOwner)object;
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			for (Iterator iterator = busOwner.getFirstBusOwnerList().iterator(); iterator.hasNext();) {
				Bus bus = (Bus) iterator.next();
				bus.setFirstOwner(null);
				session.update(bus);
			}
			
			for (Iterator iterator = busOwner.getSecondBusOwnerList().iterator(); iterator.hasNext();) {
				Bus bus = (Bus) iterator.next();
				bus.setSecondOwner(null);
				session.update(bus);
			}
			
			session.delete(busOwner);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
	}
	
	 
	 public void deleteHost(Object object){
			Host obj = (Host)object;
			Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				for (Iterator iterator = obj.getBusList().iterator(); iterator.hasNext();) {
					Bus bus = (Bus) iterator.next();
					bus.setHost(null);
					session.update(bus);
				}
				
				session.delete(object);
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
		}
		 
	 
	 public void deleteDriver(Object object){
			Driver driver = (Driver)object;
			Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				for (Iterator iterator = driver.getFirstDriverBusList().iterator(); iterator.hasNext();) {
					Bus bus = (Bus) iterator.next();
					bus.setFirstDriver(null);
					session.update(bus);
				}
				
				for (Iterator iterator = driver.getSecondDriverBusList().iterator(); iterator.hasNext();) {
					Bus bus = (Bus) iterator.next();
					bus.setSecondDriver(null);
					session.update(bus);
				}
				
				for (Iterator iterator = driver.getThirdDriverBusList().iterator(); iterator.hasNext();) {
					Bus bus = (Bus) iterator.next();
					bus.setThirdDriver(null);
					session.update(bus);
				}
				
				session.delete(driver);
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
		}







}



	 
