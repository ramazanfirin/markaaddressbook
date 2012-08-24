package model;

import java.util.List;

import model.interfaces.AbsractInterface;
import model.model.Driver;
import model.model.User;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import server.IHelloWorld;

import com.thoughtworks.xstream.XStream;

public class HttpDataProvider implements DataProvider{

	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	IHelloWorld iHelloWorld;
	
	
	public HttpDataProvider() {
		super();
		//create WebService client proxy factory
	    factory = new JaxWsProxyFactoryBean();
	    //register WebService interface
	    factory.setServiceClass(IHelloWorld.class);
	    //set webservice publish address to factory.
	    factory.setAddress("http://localhost:8090/OtobusFihrist");
	    
	    iHelloWorld = (IHelloWorld) factory.create();

	}

	private static HttpDataProvider instance = new HttpDataProvider();
	public static HttpDataProvider getInstance() {
			return instance;
	}
	
	
	@Override
	public List<AbsractInterface> loadAllDriver2() {
		String result = iHelloWorld.loadAllDriver2();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAllBus2() {
		String result = iHelloWorld.loadAllBus2();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadUsers() {
		String result = iHelloWorld.loadUsers();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public User getUser(Long id) {
		String result = iHelloWorld.getUser(id);
		XStream xstream = new XStream();
		User user = (User)xstream.fromXML(result);
		return user;
	}

	@Override
	public List<AbsractInterface> loadHosts() {
		String result = iHelloWorld.loadHosts();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAllMuavin() {
		String result = iHelloWorld.loadAllMuavin();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadBusOwners() {
		String result = iHelloWorld.loadBusOwners();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAuthorith() {
		String result = iHelloWorld.loadAuthorith();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public User checkPassword(String username, String password) {
		String result = iHelloWorld.checkPassword(username, password);
		if(result==null || result.equals("") )
			return null;
		XStream xstream = new XStream();
		User user = (User)xstream.fromXML(result);		
		return user;
	}

	@Override
	public List<AbsractInterface> searchEntiy(Class clazz, String name,
			String surname, String phone) {
		String result = iHelloWorld.searchEntiy(clazz, name, surname, phone);
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> searchBus(String plate, String phone,
			String driverName, String driverSurname, String hostName,
			String hostSurname, String busOwnerName, String busOwnerSurname) {
		
		String result = iHelloWorld.searchBus(plate, phone, driverName, driverSurname, hostName, hostSurname, busOwnerName, busOwnerSurname);
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;

	}

	@Override
	public List<AbsractInterface> loadAllOutOffice() {
		String result = iHelloWorld.loadAllOutOffice();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAllServiceArea() {
		String result = iHelloWorld.loadAllServiceArea();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}


	@Override
	public void saveOrUpdate(Object o) throws Exception{
		XStream xstream = new XStream();
		String string = xstream.toXML(o);
		String result = iHelloWorld.saveOrUpdate(string);
		if("success".equals(result))
			return;
		else
			throw new Exception();
		
	}


	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteBusOwner(Object object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteHost(Object object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteMuavin(Object object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteDriver(Object object) {
		// TODO Auto-generated method stub
		
	}

}
