package util;

import java.text.MessageFormat;
import java.util.MissingResourceException;

import org.apache.commons.codec.binary.Base64;


import com.AddressBookNew;
import com.AddressBookNewClient;

public class Util {

	
	public static String getString(String name){
		return AddressBookNew.getInstance().resAddressBook.getString(name);
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
