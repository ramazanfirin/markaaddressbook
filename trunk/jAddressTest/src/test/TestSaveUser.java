package test;

import java.util.Date;
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
	
	public void testBus(){
		Bus bus = new Bus();
		bus.setPlate("plaka");
		bus.setPhone("telefon");
		
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(bus);
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
	
}
