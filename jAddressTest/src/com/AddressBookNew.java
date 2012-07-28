package com;

/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/



/* Imports */
import java.util.Locale;
import java.util.ResourceBundle;

import model.model.User;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.mihalis.opal.login.LoginDialog;

import util.MenuUtil;
import util.Util;
import widgets.BasicCTabFolder;
import widgets.BusTabItem;
import widgets.DriverTabItem;
import widgets.UserTabItem;
/**
 * AddressBookExample is an example that uses <code>org.eclipse.swt 
 * libraries to implement a simple address book.  This application has 
 * saveR, load, sorting, and searching functions common
 * to basic address books.
 */
public class AddressBookNew {

	public static ResourceBundle resAddressBook = ResourceBundle.getBundle("examples_addressbook",new Locale("tr", "TR"));
	private Shell shell;
	
	 private BasicCTabFolder cTabFolder;
	 private DriverTabItem tabItemDriver;
	 private BusTabItem tabItemBus;
	 private UserTabItem tabItemUser;
	 private User loginUser;
	
	
	 
	 private static AddressBookNew instance = new AddressBookNew();
	 public static AddressBookNew getInstance() {
			return instance;
		}

	 
	 
public static void main(String[] args) throws Exception{
	Display display = new Display();
	Shell shell = instance.open(display);
	while(!shell.isDisposed()){
		if(!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}
public Shell open(Display display) throws Exception{
	shell = new Shell(display);
	shell.setLayout(new FillLayout());
	 
//    if(checkLogin())
//    	shell.setText(loginUser.getUsername());
//    else{
//    	shell.dispose();
//    	return shell;
//    }
  
    createMenuBar();
 	
    cTabFolder = new BasicCTabFolder(shell, SWT.BORDER);
    cTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
    
    cTabFolder.setSimple(false);
    cTabFolder.setUnselectedImageVisible(false);
    cTabFolder.setUnselectedCloseVisible(false);
	
    tabItemDriver = new DriverTabItem(cTabFolder,Util.getString("driver.list"));
    tabItemBus = new BusTabItem(cTabFolder,Util.getString("bus.list"));
    tabItemUser = new UserTabItem(cTabFolder,Util.getString("user.list"));

    shell.open();
	return shell;
}



public boolean checkLogin(){
	final LoginDialog dialog = new LoginDialog();
    dialog.setVerifier(new LoginVerifier());
    
    return dialog.open();
    
}

private Menu createMenuBar() {
	Menu menuBar = new Menu(shell, SWT.BAR);
	shell.setMenuBar(menuBar);
	
	MenuUtil.createFileMenu(menuBar);
	MenuUtil.createHelpMenu(menuBar);
	
	return menuBar;
}


public DriverTabItem getTabItemDriver() {
	return tabItemDriver;
}

public void setTabItemDriver(DriverTabItem tabItemDriver) {
	this.tabItemDriver = tabItemDriver;
}

public BusTabItem getTabItemBus() {
	return tabItemBus;
}

public void setTabItemBus(BusTabItem tabItemBus) {
	this.tabItemBus = tabItemBus;
}

public User getLoginUser() {
	return loginUser;
}

public void setLoginUser(User loginUser) {
	this.loginUser = loginUser;
}



public UserTabItem getTabItemUser() {
	return tabItemUser;
}



public void setTabItemUser(UserTabItem tabItemUser) {
	this.tabItemUser = tabItemUser;
}

}