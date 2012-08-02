package util;

import org.apache.commons.codec.binary.Base64;


import com.AddressBookNew;

public class Util {

	
	public static String getString(String name){
		return AddressBookNew.getInstance().resAddressBook.getString(name);
	}
	
	public static String encrypt(String string){
		byte[] encoded = Base64.encodeBase64(string.getBytes()); 
		return new String(encoded);
	}

	public static void main(String[] args) {
		System.out.println(encrypt("ramazan"));
	}

	
	public static boolean isAdmin(){
		if(AddressBookNew.getInstance().getLoginUser().getAuthority().getAuthority().equals(Util.getString("authority.admin")))
			return true;
		else
			return false;
	}

}
