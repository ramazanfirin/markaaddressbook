package test;

import java.util.Date;

import junit.framework.TestCase;
import model.model.Driver;
import model.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class TestSaveUser extends TestCase{

	public void testSave(){
		Driver user = new Driver();
		user.setName("apple");
		user.setSurname("surname");
		user.setPhone("123456");
		user.setInsertDate(new Date());
		
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
}
