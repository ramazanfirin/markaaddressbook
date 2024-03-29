package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.BusOwner;
import model.model.Driver;
import model.model.Host;
import model.model.Muavin;
import model.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class DBDataProvider implements DataProvider{

	 private static DBDataProvider instance = new DBDataProvider();
	 public static DBDataProvider getInstance() {
			return instance;
		}
	 
	 public void saveOrUpdate(Object o) throws Exception{
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
	 
	 public void delete(Object o) throws Exception{
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
	 
//	 public List<Driver> loadAllDriver(){
//		 Session session=null;
//			Transaction tx=null;
//			try {
//				session = HibernateUtil.getSessionFactory().openSession();
//				String hql = "from Driver p where 1=1 order by p.name";
//		        Query query = session.createQuery(hql);
//
//				return  query.list();
//
//			} catch (HibernateException e) {
//				e.printStackTrace();
//				throw e;
//			}finally {
//				session.close();
//			}
//			
//	 }
	 
	 public List<AbsractInterface> loadAllDriver2() throws Exception{
		 //testException();
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
	 
//	 public List<Bus> loadAllBus() {
//		 Session session=null;
//			Transaction tx=null;
//			try {
//				session = HibernateUtil.getSessionFactory().openSession();
//				String hql = "from Bus p where 1=1 order by p.plate";
//		        Query query = session.createQuery(hql);
//
//				return  query.list();
//
//			} catch (HibernateException e) {
//				e.printStackTrace();
//				throw e;
//			}finally {
//				session.close();
//			}
//			
//	 }
	 
//	 public Bus getBus(Long id) {
//		 Session session=null;
//			Transaction tx=null;
//			try {
//				session = HibernateUtil.getSessionFactory().openSession();
//				String hql = "from Bus p where id="+id;
//		        Query query = session.createQuery(hql);
//
//				return  (Bus)query.uniqueResult();
//
//			} catch (HibernateException e) {
//				e.printStackTrace();
//				throw e;
//			}finally {
//				session.close();
//			}
//			
//	 }
	 
	 public User getUser(Long id) throws Exception{
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				String hql = "from User p where id="+id;
		        Query query = session.createQuery(hql);
		        if (!tx.wasCommitted())
		            tx.commit();
				return  (User)query.uniqueResult();

			} catch (HibernateException e) {
				tx.rollback();
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
			
	 }
	 
	 public List<AbsractInterface> loadAllBus2() throws Exception {
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
	 
	 
	 public List<AbsractInterface> loadUsers()throws Exception{
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
	 
	 public List<AbsractInterface> loadHosts()throws Exception{
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
	 
	 public List<AbsractInterface> loadAllMuavin()throws Exception{
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from Muavin p where 1=1";
		        Query query = session.createQuery(hql);
		        return  query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }
	 
	 public List<AbsractInterface> loadBusOwners()throws Exception{
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
	 
	 public List<AbsractInterface> loadAuthorith()throws Exception{
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
	 
	 public User checkPassword(String username,String password)throws Exception{
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
	 
	 public List<AbsractInterface> searchEntiy(Class clazz,String name,String surname,String phone) throws Exception{
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

	 public List<AbsractInterface> searchBus(String plate,String shortCode,String phone,String driverName,String driverSurname,
			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname) throws Exception{
		 Session session=null;
			Transaction tx=null;
			boolean driverNameExist=false;
			boolean busOwnerExist=false;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
								
				String queryString1="from Bus p where 1=1 ";
				String queryString2="from Bus p where 1=1 ";
				String queryString3="from Bus p where 1=1 ";
				//String queryString4;
				if(driverName!=null && !driverName.equals("")){
					driverNameExist= true;
					queryString1 = queryString1+" and  (p.firstDriver.name like '%"+driverName.toLowerCase()+"%' or p.firstDriver.name like '%"+driverName.toUpperCase()+"%')"; 
					queryString2 = queryString2+" and  (p.secondDriver.name like '%"+driverName.toLowerCase()+"%' or p.secondDriver.name like '%"+driverName.toUpperCase()+"%')"; 
					queryString3 = queryString3+" and  (p.thirdDriver.name like '%"+driverName.toLowerCase()+"%' or p.thirdDriver.name like '%"+driverName.toUpperCase()+"%')";
				}
				
				if(driverSurname!=null && !driverSurname.equals("")){
					driverNameExist= true;
					queryString1 = queryString1+" and  (p.firstDriver.surname like '%"+driverSurname.toLowerCase()+"%' or p.firstDriver.surname like '%"+driverSurname.toUpperCase()+"%')"; 
					queryString2 = queryString2+" and  (p.secondDriver.surname like '%"+driverSurname.toLowerCase()+"%' or p.secondDriver.surname like '%"+driverSurname.toUpperCase()+"%')"; 
					queryString3 = queryString3+" and  (p.thirdDriver.surname like '%"+driverSurname.toLowerCase()+"%' or p.thirdDriver.surname like '%"+driverSurname.toUpperCase()+"%')";
				}
				
//				if(busOwnerName!=null && !busOwnerName.equals("")){
//					busOwnerExist= true;
//					queryString4 = queryString1+" and  (p.firstDriver.surname like '%"+driverSurname.toLowerCase()+"%' or p.firstDriver.surname like '%"+driverSurname.toUpperCase()+"%')"; 
//					queryString4 = queryString2+" and  (p.secondDriver.surname like '%"+driverSurname.toLowerCase()+"%' or p.secondDriver.surname like '%"+driverSurname.toUpperCase()+"%')"; 
//					queryString3 = queryString3+" and  (p.thirdDriver.surname like '%"+driverSurname.toLowerCase()+"%' or p.thirdDriver.surname like '%"+driverSurname.toUpperCase()+"%')";
//				}
				
				if(plate!=null && !plate.equals("")){
					queryString1 = queryString1+ " and p.plate like '%"+plate+"%'";	
					if(driverNameExist){
						queryString2 = queryString2+ " and p.plate like '%"+plate+"%'";	
						queryString3 = queryString3+ " and p.plate like '%"+plate+"%'";	
					}	
				}
				
				if(phone!=null && !phone.equals("")){
					queryString1 = queryString1+ " and p.phone like '%"+phone+"%'";	
					if(driverNameExist){
						queryString2 = queryString2+ " and p.phone like '%"+phone+"%'";	
						queryString3 = queryString3+ " and p.phone like '%"+phone+"%'";	
					}	
				}
				
				if(shortCode!=null && !shortCode.equals("")){
					queryString1 = queryString1+ " and p.shortCode like '%"+shortCode+"%'";	
					if(driverNameExist){
						queryString2 = queryString2+ " and p.shortCode like '%"+shortCode+"%'";	
						queryString3 = queryString3+ " and p.shortCode like '%"+shortCode+"%'";	
					}	
				}
				
				Query query1 = session.createQuery(queryString1);
				Query query2 = session.createQuery(queryString2);
				Query query3 = session.createQuery(queryString3);
				
				
				Set<AbsractInterface> result= new HashSet<AbsractInterface>();
				Set<AbsractInterface> set1= new HashSet(query1.list());;
				
				result.addAll(set1);
				
				
				if(driverNameExist){
					Set<AbsractInterface> set2= new HashSet(query2.list());;
					result.addAll(set2);
					Set<AbsractInterface> set3= new HashSet(query3.list());;
					result.addAll(set3);
				}
	
				List<AbsractInterface> list = new ArrayList<AbsractInterface>(); 
				list.addAll(result);

				return  list;
			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }
	 
	 public void deleteBusOwner(Object object) throws Exception{
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
	
	 
	 public void deleteHost(Object object) throws Exception{
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
	 
	 public void deleteMuavin(Object object) throws Exception{
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
		 
	 
	 public void deleteDriver(Object object) throws Exception{
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



	 public List<AbsractInterface> loadAllOutOffice() throws Exception{
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from OutOffice p where 1=1";
		        Query query = session.createQuery(hql);
		        return  query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }
	 
	 public List<AbsractInterface> loadAllServiceArea() throws Exception{
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from ServiceArea p where 1=1";
		        Query query = session.createQuery(hql);
		        return  query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	 }

	@Override
	public List<AbsractInterface> loadCities() {
		 Session session=null;
			Transaction tx=null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				String hql = "from City p where 1=1";
		        Query query = session.createQuery(hql);
		        return  query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
				throw e;
			}finally {
				session.close();
			}
	}


	public List<AbsractInterface> searchOutOffice(String name,String cityId) throws Exception{
		Session session=null;
		Transaction tx=null;
		boolean driverNameExist=false;
		boolean busOwnerExist=false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String queryString="from OutOffice p where 1=1 ";
			if(name!=null && !name.equals(""))
				queryString = queryString+ " and (p.name like '%"+name.toLowerCase()+"%' or p.name like '%"+name.toUpperCase()+"%')";
			
			if(cityId!=null && !cityId.equals(""))
				queryString = queryString+ " and (p.address.city.id="+cityId+")";
			
			Query query = session.createQuery(queryString);
			return  query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
	}

	@Override
	public List<AbsractInterface> searchServiceArea(String name,String cityId) throws Exception {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String queryString="from ServiceArea p where 1=1 ";
			if(name!=null && !name.equals(""))
				queryString = queryString+ " and (p.name like '%"+name.toLowerCase()+"%' or p.name like '%"+name.toUpperCase()+"%')";
			
			if(cityId!=null && !cityId.equals(""))
				queryString = queryString+ " and (p.address.city.id="+cityId+")";
			
			Query query = session.createQuery(queryString);
			return  query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
	}

	@Override
	public List<AbsractInterface> searchGeneral(String name, String surname,
			String busPlate, String busShortCode,String outOfficeName,String outOfficeCityId,String serviceAreaName,String serviceAreaCityId) throws Exception{
		
		Set<AbsractInterface> result= new HashSet<AbsractInterface>();
		
		if((name!=null && !name.equals("")) || (surname!=null && !surname.equals(""))){
		
			List<AbsractInterface> list= searchEntiy(Driver.class, name, surname, "");
			result.addAll(list);
			
			list= searchEntiy(Host.class, name, surname, "");
			result.addAll(list);
	
			list= searchEntiy(Muavin.class, name, surname, "");
			result.addAll(list);
		
			list= searchEntiy(BusOwner.class, name, surname, "");
			result.addAll(list);
		
		}if((busPlate!=null && !busPlate.equals("")) || (busShortCode!=null && !busShortCode.equals(""))){
			List<AbsractInterface> list= searchBus(busPlate, busShortCode, "", "", "", "", "", "", "");
			result.addAll(list);
		}
		
		if((outOfficeName!=null && !outOfficeName.equals("")) || (outOfficeCityId!=null && !outOfficeCityId.equals(""))){
			List<AbsractInterface> list= searchOutOffice(outOfficeName, outOfficeCityId);
			result.addAll(list);
		}
		
		if((serviceAreaName!=null && !serviceAreaName.equals("")) || (serviceAreaCityId!=null && !serviceAreaCityId.equals(""))){
			List<AbsractInterface> list= searchServiceArea(serviceAreaName, serviceAreaCityId);
			result.addAll(list);
		}
		
		List<AbsractInterface> list = new ArrayList<AbsractInterface>(); 
		list.addAll(result);

		return  list;
	}

	@Override
	public void testException() throws Exception {
		throw new Exception("deneme2");
		
	}
	 




}



	 
