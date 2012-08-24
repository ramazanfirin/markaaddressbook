package model;

import java.util.List;

import javax.jws.WebMethod;

import model.interfaces.AbsractInterface;
import model.model.Driver;
import model.model.User;

public interface DataProvider {
	public List<AbsractInterface> loadAllDriver2();
	public List<AbsractInterface> loadAllBus2();
	public List<AbsractInterface> loadUsers();
	public User getUser(Long id);
	public List<AbsractInterface> loadHosts();
	public List<AbsractInterface> loadAllMuavin();
	public List<AbsractInterface> loadBusOwners();
	public List<AbsractInterface> loadAuthorith();
	public User checkPassword(String username,String password);
	public List<AbsractInterface> searchEntiy(Class clazz,String name,String surname,String phone);
	public List<AbsractInterface> searchBus(String plate,String phone,String driverName,String driverSurname,
			 String hostName,String hostSurname,String busOwnerName,String busOwnerSurname);
	public List<AbsractInterface> loadAllOutOffice();
	public List<AbsractInterface> loadAllServiceArea();
	
	//public static DataProvider getInstance();
	
	public void saveOrUpdate(Object o)  throws Exception;
	public  void delete(Object o);
	public void deleteBusOwner(Object object);
	public void deleteHost(Object object);
	public void deleteMuavin(Object object);
	public void deleteDriver(Object object);
	
	
}
