package server;

import javax.jws.WebService;

@WebService
public interface IHelloWorld{
   //@WebParam is optional
	 String loadAllDriver2() throws Exception;
	 String loadAllBus2() throws Exception;
	 String loadHosts() throws Exception;
	 String loadAllMuavin()throws Exception;
	 String loadBusOwners() throws Exception;
	 String checkPassword(String username,String password) throws Exception;
	 String searchEntiy(Class clazz,String name,String surname,String phone) throws Exception;
	 String searchBus(String plate,String shortCode,String phone,String driverName,String driverSurname,
			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname) throws Exception;
	 String loadAllOutOffice() throws Exception;
	 String loadAllServiceArea() throws Exception;
	 
	 String getUser(Long id) throws Exception;
	 
	 String loadAuthorith() throws Exception;
	 String loadUsers() throws Exception;
	 
	 String saveOrUpdate(String string)  throws Exception;
	 
	 String loadCities() throws Exception;

	 void delete(String string) throws Exception;
	 void deleteBusOwner(String string) throws Exception;
	 void deleteHost(String string) throws Exception;
	 void deleteMuavin(String string) throws Exception;
	 void deleteDriver(String string) throws Exception;
	 
	 String searchOutOffice(String name,String cityId)throws Exception;
	 String searchServiceArea(String name,String cityId)throws Exception;
	 String searchGeneral(String name, String surname,
				String busPlate, String busShortCode,String outOfficeName,String outOfficeCityId,String serviceAreaName,String serviceAreaCityId)throws Exception;
	 
	 void checkException() throws Exception;
}