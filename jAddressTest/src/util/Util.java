package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;

import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;

import model.HttpDataProvider;

import org.apache.commons.codec.binary.Base64;
import com.AddressBookNew;
import com.AddressBookNewClient;

public class Util {

	
	public static String getString(String name){
		return AddressBookNew.getInstance().resAddressBook.getString(name);
	}
	
	public static String getParameter(String name){
		return AddressBookNew.getInstance().parameters.getString(name);
	}
	
	public static String getParameterAsStream(String name){
		Properties props = new Properties();
		String message ="";
		try {
			ClassLoader classLoader = props.getClass().getClassLoader();
			InputStream a =classLoader.getResourceAsStream("parameters.properties");
			props.load(classLoader.getResourceAsStream("parameters.properties"));
			message = props.getProperty(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static String getParameterProperties(String name){
		
		Properties props = new Properties();
		String message ="";
		try {
			props.load(new FileInputStream("config/parameters.properties"));
			message = props.getProperty(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	
	public static String saveServerParameter(String name){
		Properties props = new Properties();
		String message ="";
		try {
			props.load(new FileInputStream("config/parameters.properties"));
			props.setProperty("serverAddress", name);
			props.store(new FileOutputStream("config/parameters.properties"),"no comments");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public static List<String> getServerAddresses(){
		String[] serverAddresses=null;
		if(Util.isFromJNPL()){
			serverAddresses= getParameter("serverConnectionAddressForClient").split(",");
		}else
			serverAddresses = getParameterProperties("serverConnectionAddressForClient").split(",");
		List<String> serverAddress = new ArrayList<String>();
		for (int i = 0; i < serverAddresses.length; i++) {
			serverAddress.add(serverAddresses[i]);
		}
		return serverAddress;
	}
	
	public static Boolean isFromJNPL(){
		try { 
	        // Lookup the javax.jnlp.BasicService object 
	        BasicService bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService"); 
	        // Invoke the showDocument method 
	       return true;
	    } catch(UnavailableServiceException ue) { 
	        // Service is not supported 
	       return false;
	    } 
	}
	
	public static void saveEndPoint(){
		if(isFromJNPL() || Util.getApplicationInstance().userLocalDB)
			return ;
		List<String> serverAddresses = getServerAddresses();
		String address = Util.getApplicationInstance().getDialog().getServer();
		if(!serverAddresses.contains(address)){
			saveServerParameter(address);
		}
		
	}
	
	public static void setServerEndPoint(){
        if(!Util.getApplicationInstance().userLocalDB){
         	String address = Util.getApplicationInstance().getDialog().getServer();
        	HttpDataProvider dataProvider = (HttpDataProvider)Util.getApplicationInstance().getDataProvider();
        	dataProvider.prepareEndPoint(address);
        }
	}
	
	public static String encrypt(String string){
		byte[] encoded = Base64.encodeBase64(string.getBytes()); 
		return new String(encoded);
	}

	public static void main(String[] args) {
		System.out.println(encrypt("admin"));
		System.out.println(encrypt("user"));
		System.out.println(getFormattedPhone("5551234578"));
	}

	
	public static boolean isAdmin(){
		if(Util.getApplicationInstance().getLoginUser().getAuthority().getAuthority().equals(Util.getString("authority.admin")))
			return true;
		else
			return false;
	}

	 public static String getString(String key, Object... params  ) {
	        try {
	            return MessageFormat.format(getString(key), params);
	        } catch (MissingResourceException e) {
	            return '!' + key + '!';
	        }
	    }
	 
	 public static String getFormattedPhone(String phone ) {
		 String value="";
			if(phone!=null && phone.length()==10){
				value = phone.substring(0,3)+" "+phone.substring(3,6)+"-"+phone.substring(6,8)+"-"+phone.substring(8,10);
			}
			return value;
	    }

	 public static boolean isEmpty(String s){
		 if(s==null | s.equals("") | s.length()==0)
			return  true;
		 else
			return false;     
	 }

	 public static AddressBookNew getApplicationInstance(){
		 if(AddressBookNew.getInstance().isRunning)
			 return AddressBookNew.getInstance();
		 else
			 return AddressBookNewClient.getInstance();
	 }
}
