package model;

import java.util.List;

import model.model.Driver;
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
	 
	 
	 public void saveOrUpdate(Object o){
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
			}finally {
				session.close();
			}
			return null;
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
			}finally {
				session.close();
			}
			return null;
	 }
	 
}
