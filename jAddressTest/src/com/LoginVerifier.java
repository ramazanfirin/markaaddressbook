package com;

import model.DBManager;
import model.model.User;

import org.eclipse.swt.custom.BusyIndicator;
import org.mihalis.opal.login.LoginDialogVerifier;

import util.Util;

public class LoginVerifier implements LoginDialogVerifier{

	@Override
    public void authenticate(final String login, final String password) throws Exception {
            if ("".equals(login)) {
                    throw new Exception("Kullanici ismini giriniz");
            }

            if ("".equals(password)) {
                    throw new Exception("Sifrenizi giriniz");
            }

//            if (!login.equalsIgnoreCase("laurent")) {
//                    throw new Exception("Login unknown.");
//            }
//
//            if (!password.equalsIgnoreCase("laurent")) {
//                    throw new Exception("Authentication failed, please check your password.");
//            }
            
           	 User user=DBManager.getInstance().checkPassword(login, Util.encrypt(password));
		            if(user==null)
		            	 throw new Exception("Sifre yanlis");
		            
		           AddressBookNew.getInstance().setLoginUser(user); 

				
			
           
    }
}
