package util;

import org.apache.commons.codec.binary.Base64;

import com.AddressBook;

public class Util {

	
	public static String getString(String name){
		return AddressBook.getInstance().resAddressBook.getString(name);
	}
	
	public static String encrypt(String string){
		byte[] encoded = Base64.encodeBase64(string.getBytes()); 
		return new String(encoded);
	}

	public static void main(String[] args) {
		System.out.println(encrypt("ramazan"));
	}
}
