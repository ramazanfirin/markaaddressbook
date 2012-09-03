package test;

import junit.framework.TestCase;
import model.HttpDataProvider;
import server.Server;

public class TestSaveUser extends TestCase{
	
	
public void testSoapFault(){
		
		new Server();
		HttpDataProvider httpDataProvider = new HttpDataProvider();
		httpDataProvider.prepareEndPoint("http://localhost:8090/OtobusFihrist");
		
		try {
			httpDataProvider.testException();
			System.out.println("hatasiz bitti");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("hata var= "+ e.getMessage());
		}
		
	}

//	public void testDriverCreate(){
//		
//		Driver user = new Driver();
//		user.setName("ramazan");
//		user.setSurname("firin");
//		user.setPhone("123456");
//		user.setInsertDate(new Date());
//		
//		Bus bus = new Bus();
//		bus.setPlate("plateramazan");
//		bus.setPhone("phoneramazan");
//		
//		City city  =new City();
//		city.setName("KAYseri");
//		DBDataProvider.getInstance().saveOrUpdate(city);
//		
//		Address address = new Address();
//		address.setCity(city);
//		address.setDescription("kıranardı");
//		
//		user.setAddress(address);
//		
//		DBDataProvider.getInstance().saveOrUpdate(user);
//		
//		DBDataProvider.getInstance().loadAllDriver2();
//		System.out.println("bitti");
//		
//		
//	}
	
	/*
	public void testDriverall2(){
		
		new Server();
		List<AbsractInterface> list=HttpDataProvider.getInstance().loadAllDriver2();
		System.out.println("bitti");
	}

	
	public void testDriverall2(){
		
			
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			List<AbsractInterface> list=DBDataProvider.getInstance().loadAllDriver2();
			Driver driver=(Driver)list.get(0);
			System.out.println(driver.getName());
			//System.out.println(driver.get);
			
			
			XStream xstream = new XStream();
			String xml = xstream.toXML(list);
			
			System.out.println(xml);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	/*
	
	
	public void testOutLocation(){
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			OutOffice outOffice = new OutOffice();
			outOffice.getFirstAuthorizedPerson().setName("ramazan");
			outOffice.getFirstAuthorizedPerson().setSurname("firin");
			outOffice.getFirstAuthorizedPerson().setPhone("5555555555555");
			outOffice.getFirstAuthorizedPerson().setLocationType(AuthorizedPerson.EnumLocationType.OutOffice.toString());
			
			
			session.save(outOffice);
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
   }

	public void testOutLocationPerson(){
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			String queryString = "from OutOffice p where 1=1";
			Query query = session.createQuery(queryString);
			List list = query.list();
			System.out.println(list.size()+"bitti");
			
			tx.commit();
		}catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
   }
	
	
	
//	public void testSearchBus(){
//       List list = DBManager.getInstance().searchBus("","", "ömer","", "", "", "", "");
//       System.out.println(list.size());
//       System.out.println("bitti");
//	}
	
	
//	public void testHost(){
//		Session session=null;
//		Transaction tx=null;
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			String queryString = "from Bus p where 1=1 and ( (p.firstDriver.name like '%ömer%' or p.firstDriver.name like '%ÖMER%') or (p.secondDriver.name like '%ömer%' or p.secondDriver.name like '%ÖMER%')or (p.thirdDriver.name like '%ömer%' or p.thirdDriver.name like '%ÖMER%' ))";
//			queryString = "from Bus p where p.firstDriver.name like '%ömer%' or p.secondDriver.name like '%ömer%'";
//			Query query = session.createQuery(queryString);
//			List list = query.list();
//			System.out.println(list.size());
//		}catch (HibernateException e) {
//			e.printStackTrace();
//			throw e;
//		}finally {
//			session.close();
//		}
//	}

//	public void testUppercase(){
//		Session session=null;
//		Transaction tx=null;
//		
//		List list=null;
//		String aa= "Şa";
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			tx = session.beginTransaction();
//			
//			String queryString = "select p from model.model.Driver p where 1=1 and upper(p.name) like '%"+aa.toUpperCase()+"%'";
//			Query query = session.createQuery(queryString);
//			list = query.list();
//			tx.commit();
//		} catch (HibernateException e) {
//			tx.rollback();
//			e.printStackTrace();
//		}finally {
//	}
//	
//	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//		Object object = (Object) iterator.next();
//		System.out.println(object);
//	}
//	}
//
//	public void testUppercase2(){
//		Session session=null;
//		Transaction tx=null;
//		
//		List list=null;
//		String aa= "şa";
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			tx = session.beginTransaction();
//			
//			String queryString = "select p.name from model.model.Driver p where 1=1 and p.name like '%"+aa+"%'";
//			Query query = session.createQuery(queryString);
//			list = query.list();
//			tx.commit();
//		} catch (HibernateException e) {
//			tx.rollback();
//			e.printStackTrace();
//		}finally {
//	}
//	
//	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//		Object object = (Object) iterator.next();
//		System.out.println(object);
//	}
//	}
	
	
//	public void testSearch(){
//		List<AbsractInterface>  list = DBManager.getInstance().searchEntiy(Driver.class,"Rama", "", "");
//		System.out.println(list.size());
//	    for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Driver driver = (Driver) iterator
//					.next();
//			
//			
//			System.out.println(driver.getNameSurname());
//		}
//				
//	}
	
//	public void testDriver(){
//		
//         List<Driver> list = DBManager.getInstance().loadAllDriver();
//         System.out.println("bitti");
//         
//         for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Driver driver = (Driver) iterator.next();
//			if(driver.getName().equals("sofor1name")){
//				System.out.println(driver.getFirstDriverBusList().size());
//				driver.getFirstDriverBusList().addAll(driver.getSecondDriverBusList());
//				System.out.println(driver.getFirstDriverBusList().size());
//			}
//			
//		}
//		
//	}
//	
	
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
