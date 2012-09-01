package com;

import model.model.User;
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
            
            Util.setServerEndPoint();
           	User user=Util.getApplicationInstance().getDataProvider().checkPassword(login, Util.encrypt(password));
		            if(user==null)
		            	 throw new Exception("Sifre yanlis");
		            
		    Util.getApplicationInstance().setLoginUser(user); 
		    Util.saveEndPoint();
 }
}
