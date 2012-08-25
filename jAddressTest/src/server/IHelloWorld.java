package server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import model.model.User;

@WebService
public interface IHelloWorld {
   //@WebParam is optional
	 String loadAllDriver2();
	 String loadAllBus2();
	 String loadHosts();
	 String loadAllMuavin();
	 String loadBusOwners();
	 String checkPassword(String username,String password);
	 String searchEntiy(Class clazz,String name,String surname,String phone);
	 String searchBus(String plate,String phone,String driverName,String driverSurname,
			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname);
	 String loadAllOutOffice();
	 String loadAllServiceArea();
	 
	 String getUser(Long id);
	 
	 String loadAuthorith();
	 String loadUsers();
	 
	 String saveOrUpdate(String string)  throws Exception;
	 
	 String loadCities();
}