package test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import model.DBManager;
import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.Driver;
import model.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class TestSaveUser extends TestCase{

	public void testDriver(){
		
         List<Driver> list = DBManager.getInstance().loadAllDriver();
         System.out.println("bitti");
         
         for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Driver driver = (Driver) iterator.next();
			if(driver.getName().equals("sofor1name")){
				System.out.println(driver.getFirstDriverBusList().size());
				driver.getFirstDriverBusList().addAll(driver.getSecondDriverBusList());
				System.out.println(driver.getFirstDriverBusList().size());
			}
			
		}
		
	}
	
	
	/*
	public void testSave(){
		Driver user = new Driver();
		user.setName("ramazan");
		user.setSurname("firin");
		user.setPhone("123456");
		user.setInsertDate(new Date());
		
		Bus bus = new Bus();
		bus.setPlate("plateramazan");
		bus.setPhone("phoneramazan");
		
		user.setBus(bus);
		
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(bus);
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		List<Bus> list = DBManager.getInstance().loadAllBus();
		Bus bus2 = list.get(0);
		System.out.println("Driver Listesi ="+ bus2.getDriverList().size());
		
		bus2.getDriverList().clear();
		
		HibernateUtil.shutdown();
	}
	
	public void testCascade(){
		
		System.out.println("bus sayisi ="+ DBManager.getInstance().loadAllBus().size());
		
		Bus bus=new Bus();
		
		Driver driver = new Driver();
		driver.setName("name");
		driver.setSurname("name");
		driver.setPhone("name");
		
		//DBManager.getInstance().saveOrUpdate(driver);
		
		
		
		bus.getDriverList().add(driver);
		driver.setBus(bus);
		DBManager.getInstance().saveOrUpdate(bus);
		
		System.out.println("bus sayisi ="+ DBManager.getInstance().loadAllBus().size());
		bus = DBManager.getInstance().getBus(new Long(1));
		System.out.println("Driver Listesi ="+ bus.getDriverList().size());
		
		
		bus.getDriverList().clear();
		DBManager.getInstance().saveOrUpdate(bus);
		
		bus = DBManager.getInstance().getBus(new Long(1));
		System.out.println("Driver Listesi ="+ bus.getDriverList().size());
		
		
		
	}
	*/

	/*	
	
	public void testBus(){
		Bus bus = new Bus();
		bus.setPlate("plaka");
		bus.setPhone("telefon");
		
		Driver firstDriver = new Driver();
		firstDriver.setName("junit");
		firstDriver.setSurname("junit");
		
		Driver secondDriver = new Driver();
		secondDriver.setName("junit2");
		secondDriver.setSurname("junit2");
		
		bus.setFirstDriver(firstDriver);
		bus.setSecondDriver(secondDriver);
		
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(bus);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		HibernateUtil.shutdown();
	}
	
	
	public void testUser(){
		User user = new User();
		user.setUsername("ramazan");
		user.setPassword("cmFtYXphbg==");
		
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		HibernateUtil.shutdown();
	}
	
	public void testDriverall2(){
		
			
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			List<AbsractInterface> list=DBManager.getInstance().loadAllBus2();
			Bus bus=(Bus)list.get(1);
			System.out.println(bus.getDriverList().size());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	*/
}