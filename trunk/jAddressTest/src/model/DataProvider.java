package model;

import java.util.List;

import javax.jws.WebMethod;

import model.interfaces.AbsractInterface;
import model.model.Driver;
import model.model.User;

public interface DataProvider {
	public List<AbsractInterface> loadAllDriver2() throws Exception;
	public List<AbsractInterface> loadAllBus2()  throws Exception;
	public List<AbsractInterface> loadUsers()  throws Exception;
	public User getUser(Long id)  throws Exception;
	public List<AbsractInterface> loadHosts()  throws Exception;
	public List<AbsractInterface> loadAllMuavin()  throws Exception;
	public List<AbsractInterface> loadBusOwners()  throws Exception;
	public List<AbsractInterface> loadAuthorith()  throws Exception;
	public User checkPassword(String username,String password)  throws Exception;
	public List<AbsractInterface> searchEntiy(Class clazz,String name,String surname,String phone)  throws Exception;
	public List<AbsractInterface> searchBus(String plate,String shortCode,String phone,String driverName,String driverSurname,
			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname)  throws Exception;
	public List<AbsractInterface> loadAllOutOffice()  throws Exception;
	public List<AbsractInterface> loadAllServiceArea() throws Exception;
	public List<AbsractInterface> loadCities() throws Exception;
	
	//public static DataProvider getInstance();
	
	public void saveOrUpdate(Object o)  throws Exception;
	public  void delete(Object o)  throws Exception;
	public void deleteBusOwner(Object object)  throws Exception;
	public void deleteHost(Object object) throws Exception;
	public void deleteMuavin(Object object)  throws Exception;
	public void deleteDriver(Object object)  throws Exception;
	
	
	public List<AbsractInterface> searchOutOffice(String name,String city) throws Exception;
	public List<AbsractInterface> searchServiceArea(String name,String city) throws Exception;
	public List<AbsractInterface> searchGeneral(String name,String surname,String busPlate,String busShortCode,
			                                    String outOfficeName,String outOfficeCityId,String serviceAreaName,String serviceAreaCityId) throws Exception;
	
	public void testException() throws Exception;
}
