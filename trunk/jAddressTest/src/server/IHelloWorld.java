package server;

import javax.jws.WebService;

@WebService
public interface IHelloWorld{
   //@WebParam is optional
	 String loadAllDriver2();
	 String loadAllBus2();
	 String loadHosts();
	 String loadAllMuavin();
	 String loadBusOwners();
	 String checkPassword(String username,String password);
	 String searchEntiy(Class clazz,String name,String surname,String phone);
	 String searchBus(String plate,String shortCode,String phone,String driverName,String driverSurname,
			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname);
	 String loadAllOutOffice();
	 String loadAllServiceArea();
	 
	 String getUser(Long id);
	 
	 String loadAuthorith();
	 String loadUsers();
	 
	 String saveOrUpdate(String string)  throws Exception;
	 
	 String loadCities();

	 void delete(String string) ;
	 void deleteBusOwner(String string) ;
	 void deleteHost(String string) ;
	 void deleteMuavin(String string) ;
	 void deleteDriver(String string) ;
	 
	 String searchOutOffice(String name,String cityId);
	 String searchServiceArea(String name,String cityId);
	 String searchGeneral(String name, String surname,
				String busPlate, String busShortCode,String outOfficeName,String outOfficeCityId,String serviceAreaName,String serviceAreaCityId);
	 
	 void checkException() throws Exception;
}