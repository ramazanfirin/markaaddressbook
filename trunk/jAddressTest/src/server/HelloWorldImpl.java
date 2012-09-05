package server;

import java.util.List;

import javax.jws.WebService;

import model.DBDataProvider;
import model.interfaces.AbsractInterface;
import model.model.User;

import com.thoughtworks.xstream.XStream;

@WebService
public class HelloWorldImpl implements IHelloWorld {
	public String loadAllDriver2() throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllDriver2();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadAllBus2()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllBus2();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadHosts()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadHosts();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	public String loadAllMuavin()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllMuavin();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadBusOwners()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadBusOwners();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

//	@Override
//	public String checkPassword(String username, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String searchEntiy(Class clazz,String name,String surname,String phone) {
//		List<AbsractInterface> list=DBDataProvider.getInstance().searchEntiy(clazz, name, surname, phone);
//		XStream xstream = new XStream();
//		String xml = xstream.toXML(list);
//		return xml;
//	}
//
//	@Override
//	public String searchBus(String plate,String phone,String driverName,String driverSurname,
//			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname) {
//		List<AbsractInterface> list=DBDataProvider.getInstance().searchBus(plate, phone, driverName, driverSurname, hostName, hostSurname, busOwnerName, busOwnerSurname);
//		XStream xstream = new XStream();
//		String xml = xstream.toXML(list);
//		return xml;
//	}

	
	public String loadAllOutOffice()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllOutOffice();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadAllServiceArea()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllServiceArea();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String checkPassword(String username, String password)  throws Exception{
		User user=DBDataProvider.getInstance().checkPassword(username, password);
		if(user!=null){
			XStream xstream = new XStream();
			String xml = xstream.toXML(user);
			return xml;
		}else
			return "";
	}


	@Override
	public String searchEntiy(Class clazz, String name, String surname,
			String phone)  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().searchEntiy(clazz, name, surname, phone);
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String searchBus(String plate,String shortCode, String phone, String driverName,
			String driverSurname, String hostName, String hostSurname,
			String busOwnerName, String busOwnerSurname) throws Exception {
		List<AbsractInterface> list=DBDataProvider.getInstance().searchBus(plate,shortCode, phone, driverName, driverSurname, hostName, hostSurname, busOwnerName, busOwnerSurname);
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String getUser(Long id) throws Exception {
		User user=DBDataProvider.getInstance().getUser(id);
		XStream xstream = new XStream();
		String xml = xstream.toXML(user);
		return xml;
	}


	@Override
	public String loadAuthorith()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAuthorith();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String loadUsers()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadUsers();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String saveOrUpdate(String string) throws Exception{
		XStream xstream = new XStream();
		Object object = xstream.fromXML(string);
		DBDataProvider.getInstance().saveOrUpdate(object);
		return "success";
	}


	@Override
	public String loadCities()  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().loadCities();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public void delete(String string)  throws Exception {
		XStream xstream = new XStream();
		Object object = xstream.fromXML(string);
		DBDataProvider.getInstance().delete(object);
		
	}


	@Override
	public void deleteBusOwner(String string)  throws Exception{
		XStream xstream = new XStream();
		Object object = xstream.fromXML(string);
		DBDataProvider.getInstance().deleteBusOwner(object);
		
	}


	@Override
	public void deleteHost(String string)  throws Exception{
		XStream xstream = new XStream();
		Object object = xstream.fromXML(string);
		DBDataProvider.getInstance().deleteHost(object);
	}


	@Override
	public void deleteMuavin(String string)  throws Exception{
		XStream xstream = new XStream();
		Object object = xstream.fromXML(string);
		DBDataProvider.getInstance().deleteMuavin(object);
		
	}


	@Override
	public void deleteDriver(String string) throws Exception {
		XStream xstream = new XStream();
		Object object = xstream.fromXML(string);
		DBDataProvider.getInstance().deleteDriver(object);		
	}


	@Override
	public String searchOutOffice(String name, String cityId)  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().searchOutOffice(name, cityId);
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String searchServiceArea(String name, String cityId) throws Exception {
		List<AbsractInterface> list=DBDataProvider.getInstance().searchServiceArea(name, cityId);
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String searchGeneral(String name, String surname, String busPlate,
			String busShortCode, String outOfficeName, String outOfficeCityId,
			String serviceAreaName, String serviceAreaCityId)  throws Exception{
		List<AbsractInterface> list=DBDataProvider.getInstance().
			searchGeneral(name, surname, busPlate, busShortCode, outOfficeName, outOfficeCityId, serviceAreaName, serviceAreaCityId);
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;

	}


	@Override
	public void checkException() throws Exception {
		System.out.println(" on checkException ");
		throw new Exception("deneme");
		
	}

	
}