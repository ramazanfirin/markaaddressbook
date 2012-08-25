package server;

import java.util.List;

import javax.jws.WebService;

import model.DBDataProvider;
import model.interfaces.AbsractInterface;
import model.model.User;

import com.thoughtworks.xstream.XStream;

@WebService
public class HelloWorldImpl implements IHelloWorld {
	public String loadAllDriver2() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllDriver2();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadAllBus2() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllBus2();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadHosts() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadHosts();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	public String loadAllMuavin() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllMuavin();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadBusOwners() {
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

	
	public String loadAllOutOffice() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllOutOffice();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
	public String loadAllServiceArea() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAllServiceArea();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String checkPassword(String username, String password) {
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
			String phone) {
		List<AbsractInterface> list=DBDataProvider.getInstance().searchEntiy(clazz, name, surname, phone);
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String searchBus(String plate, String phone, String driverName,
			String driverSurname, String hostName, String hostSurname,
			String busOwnerName, String busOwnerSurname) {
		List<AbsractInterface> list=DBDataProvider.getInstance().searchBus(plate, phone, driverName, driverSurname, hostName, hostSurname, busOwnerName, busOwnerSurname);
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String getUser(Long id) {
		User user=DBDataProvider.getInstance().getUser(id);
		XStream xstream = new XStream();
		String xml = xstream.toXML(user);
		return xml;
	}


	@Override
	public String loadAuthorith() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadAuthorith();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}


	@Override
	public String loadUsers() {
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
	public String loadCities() {
		List<AbsractInterface> list=DBDataProvider.getInstance().loadCities();
		XStream xstream = new XStream();
		String xml = xstream.toXML(list);
		return xml;
	}

	
}