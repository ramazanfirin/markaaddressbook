package model;

import java.util.List;

import model.interfaces.AbsractInterface;
import model.model.Driver;
import model.model.User;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import server.IHelloWorld;
import util.Util;

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
	    //factory.setAddress("http://localhost:8090/OtobusFihrist");
	    //factory.setAddress(Util.getParameter("serverAddress"));
	    
	    iHelloWorld = (IHelloWorld) factory.create();

	}
	
	public void prepareEndPoint(String endPoint){
		factory.setAddress(endPoint);
		iHelloWorld = (IHelloWorld) factory.create();
	}

	private static HttpDataProvider instance = new HttpDataProvider();
	public static HttpDataProvider getInstance() {
			return instance;
	}
	
	
	@Override
	public List<AbsractInterface> loadAllDriver2() throws Exception{
		String result = iHelloWorld.loadAllDriver2();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAllBus2() throws Exception{
		String result = iHelloWorld.loadAllBus2();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadUsers() throws Exception{
		String result = iHelloWorld.loadUsers();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public User getUser(Long id) throws Exception{
		String result = iHelloWorld.getUser(id);
		XStream xstream = new XStream();
		User user = (User)xstream.fromXML(result);
		return user;
	}

	@Override
	public List<AbsractInterface> loadHosts() throws Exception{
		String result = iHelloWorld.loadHosts();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAllMuavin()throws Exception {
		String result = iHelloWorld.loadAllMuavin();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadBusOwners() throws Exception{
		String result = iHelloWorld.loadBusOwners();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAuthorith()throws Exception {
		String result = iHelloWorld.loadAuthorith();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public User checkPassword(String username, String password)throws Exception {
		String result = iHelloWorld.checkPassword(username, password);
		if(result==null || result.equals("") )
			return null;
		XStream xstream = new XStream();
		User user = (User)xstream.fromXML(result);		
		return user;
	}

	@Override
	public List<AbsractInterface> searchEntiy(Class clazz, String name,
			String surname, String phone)throws Exception {
		String result = iHelloWorld.searchEntiy(clazz, name, surname, phone);
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> searchBus(String plate, String shortCode,String phone,
			String driverName, String driverSurname, String hostName,
			String hostSurname, String busOwnerName, String busOwnerSurname)throws Exception {
		
		String result = iHelloWorld.searchBus(plate,shortCode, phone, driverName, driverSurname, hostName, hostSurname, busOwnerName, busOwnerSurname);
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;

	}

	@Override
	public List<AbsractInterface> loadAllOutOffice() throws Exception{
		String result = iHelloWorld.loadAllOutOffice();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}

	@Override
	public List<AbsractInterface> loadAllServiceArea() throws Exception{
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
	public void delete(Object o) throws Exception{
		XStream xstream = new XStream();
		String string = xstream.toXML(o);
		iHelloWorld.delete(string);
		
	}


	@Override
	public void deleteBusOwner(Object object)throws Exception {
		XStream xstream = new XStream();
		String string = xstream.toXML(object);
		iHelloWorld.deleteBusOwner(string);
		
	}


	@Override
	public void deleteHost(Object object) throws Exception{
		XStream xstream = new XStream();
		String string = xstream.toXML(object);
		iHelloWorld.deleteHost(string);
		
	}


	@Override
	public void deleteMuavin(Object object)throws Exception {
		XStream xstream = new XStream();
		String string = xstream.toXML(object);
		iHelloWorld.deleteMuavin(string);
		
	}


	@Override
	public void deleteDriver(Object object) throws Exception{
		XStream xstream = new XStream();
		String string = xstream.toXML(object);
		iHelloWorld.deleteDriver(string);
	}


	@Override
	public List<AbsractInterface> loadCities() throws Exception{
		String result = iHelloWorld.loadCities();
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}


	@Override
	public List<AbsractInterface> searchOutOffice(String name,String city) throws Exception{
		String result = iHelloWorld.searchOutOffice(name,city);
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}


	@Override
	public List<AbsractInterface> searchServiceArea(String name,String city) throws Exception{
		String result = iHelloWorld.searchServiceArea(name,city);
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}


	@Override
	public List<AbsractInterface> searchGeneral(String name, String surname,
			String busPlate, String busShortCode,String outOfficeName,String outOfficeCityId,String serviceAreaName,String serviceAreaCityId) throws Exception{
		String result = iHelloWorld.searchGeneral(name, surname, busPlate, busShortCode, outOfficeName, outOfficeCityId, serviceAreaName, serviceAreaCityId);
		XStream xstream = new XStream();
		List<AbsractInterface> list = (List<AbsractInterface>)xstream.fromXML(result);
		
		return list;
	}


	public JaxWsProxyFactoryBean getFactory() {
		return factory;
	}


	public void setFactory(JaxWsProxyFactoryBean factory) {
		this.factory = factory;
	}

	@Override
	public void testException() throws Exception {
		iHelloWorld.checkException();
		
	}

}
