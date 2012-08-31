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

import model.DBDataProvider;
import model.DataProvider;
import model.HttpDataProvider;
import model.model.City;
import model.model.User;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.mihalis.opal.login.LoginDialog;

import server.Server;
import util.MenuUtil;
import util.Util;
import widgets.BasicCTabFolder;
import widgets.BusOwnerTabItem;
import widgets.BusTabItem;
import widgets.DriverTabItem;
import widgets.HostTabItem;
import widgets.MuavinTabItem;
import widgets.OutOfficeTabItem;
import widgets.SearchTabItem;
import widgets.ServiceAreaTabItem;
import widgets.UserTabItem;
/**
 * AddressBookExample is an example that uses <code>org.eclipse.swt 
 * libraries to implement a simple address book.  This application has 
 * saveR, load, sorting, and searching functions common
 * to basic address books.
 */
public class AddressBookNew {

	boolean checkLogin=true;
	public static ResourceBundle resAddressBook = ResourceBundle.getBundle("addressbook",new Locale("tr", "TR"));
	private Shell shell;
	
	 private BasicCTabFolder cTabFolder;
	 private DriverTabItem tabItemDriver;
	 private BusTabItem tabItemBus;
	 private UserTabItem tabItemUser;
	 private HostTabItem tabItemHost;
	 private BusOwnerTabItem tabItemBusOwner;
	 private MuavinTabItem tabItemMuavin;
	 private OutOfficeTabItem tabItemOutOffice;
	 private ServiceAreaTabItem tabItemServiceArea;
	 private SearchTabItem tabItemSearch;
	 
	 
	 private User loginUser;
	
	 public static Display display;
	
	 DataProvider dataProvider;
	 
	 public Boolean userLocalDB=true;
	 public Boolean isRunning=false;
	 
	 public CityLabelProvider cityLabelProvider = new CityLabelProvider();
	 
	 protected static AddressBookNew instance = new AddressBookNew();
	 public static AddressBookNew getInstance() {
			return instance;
	 }

	 
	 
public static void main(String[] args){
	System.out.println("basliyoruz");
	try {
		Server server = new Server();
		instance.userLocalDB=true;
		instance.isRunning=true;
		display = new Display();
		Shell shell = instance.open(display);
		
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		System.exit(0);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public Shell open(Display display) throws Exception{
	prepareDataProvider();
	
//	Window.setExceptionHandler(new Window.IExceptionHandler() {
//	    public void handleException(Throwable error) {
//	        MessageDialog.openError(null, "Error", "Error: " + error.getMessage());
//	    }
//	});
	
	shell = new Shell(display);
	FillLayout layout = new FillLayout();
	layout.type=SWT.VERTICAL;
	shell.setLayout(layout);
	 
    if(checkLogin())
    	shell.setText(Util.getString("firm.name")+"-"+Util.getString("welcome.message",loginUser.getNameSurname())+" useLocalDB="+userLocalDB);
    else{
    	shell.dispose();
    	return shell;
    }
     
    Composite cContent=new Composite(shell,SWT.NONE);
    cContent.setLayout(new GridLayout(1,false));
    
    Composite cMenu=new Composite(cContent,SWT.NONE);
    cMenu.setLayout(new FillLayout());
    cMenu.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    createToolBar(cMenu);
    cMenu.pack();
    this.getLoginUser();
    createMenuBar();
    
    cTabFolder = new BasicCTabFolder(cContent, SWT.BORDER);
    cTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
    
    cTabFolder.setSimple(false);
    cTabFolder.setUnselectedImageVisible(false);
    cTabFolder.setUnselectedCloseVisible(false);
	
    tabItemSearch = new SearchTabItem(cTabFolder,Util.getString("search"));
    tabItemBus = new BusTabItem(cTabFolder,Util.getString("bus.list"));
    tabItemDriver = new DriverTabItem(cTabFolder,Util.getString("driver.list"));
    tabItemHost = new HostTabItem(cTabFolder,Util.getString("host.list"));
    tabItemBusOwner = new BusOwnerTabItem(cTabFolder,Util.getString("busOwner.list"));
    tabItemMuavin = new MuavinTabItem(cTabFolder,Util.getString("muavin.list"));
    tabItemOutOffice = new OutOfficeTabItem(cTabFolder,Util.getString("outOffice.list"));
    tabItemServiceArea = new ServiceAreaTabItem(cTabFolder,Util.getString("serviceArea.list"));
    if(Util.isAdmin())
    tabItemUser = new UserTabItem(cTabFolder,Util.getString("user.list"));
    
    cTabFolder.forceFocus();
    shell.open();
	return shell;
}

public void prepareDataProvider(){
	if(userLocalDB)
		dataProvider = new DBDataProvider();
	else
		dataProvider = new HttpDataProvider();
}

public boolean checkLogin(){
	final LoginDialog dialog = new LoginDialog();
	dialog.setDescription(Util.getString("login.message"));
	dialog.setDisplayRememberPassword(false);
	dialog.setVerifier(new LoginVerifier());
    
	if(checkLogin)
		return dialog.open();
	else{
		if(userLocalDB)
			loginUser = getDataProvider().getUser(new Long(1));
		else
			loginUser = getDataProvider().getUser(new Long(2));
		return true;
	}
}

private Menu createMenuBar() {
	Menu menuBar = new Menu(shell, SWT.BAR);
	shell.setMenuBar(menuBar);
	
	MenuUtil.createFileMenu(menuBar);
	MenuUtil.createNewEntity(menuBar);
	MenuUtil.createUserOperationMenu(menuBar);
	MenuUtil.createHelpMenu(menuBar);
	
	return menuBar;
}

private ToolBar createToolBar(Composite parent){
	return MenuUtil.createToolBar(parent);

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



public HostTabItem getTabItemHost() {
	return tabItemHost;
}



public void setTabItemHost(HostTabItem tabItemHost) {
	this.tabItemHost = tabItemHost;
}



public BusOwnerTabItem getTabItemBusOwner() {
	return tabItemBusOwner;
}



public void setTabItemBusOwner(BusOwnerTabItem tabItemBusOwner) {
	this.tabItemBusOwner = tabItemBusOwner;
}



public MuavinTabItem getTabItemMuavin() {
	return tabItemMuavin;
}



public void setTabItemMuavin(MuavinTabItem tabItemMuavin) {
	this.tabItemMuavin = tabItemMuavin;
}



public OutOfficeTabItem getTabItemOutOffice() {
	return tabItemOutOffice;
}



public void setTabItemOutOffice(OutOfficeTabItem tabItemOutOffice) {
	this.tabItemOutOffice = tabItemOutOffice;
}



public DataProvider getDataProvider() {
	return dataProvider;
}



public void setDataProvider(DataProvider dataProvider) {
	this.dataProvider = dataProvider;
}


class CityLabelProvider extends LabelProvider{
	@Override
	public String getText(Object element) {
		if (element instanceof City) {
			City driver = (City) element;
			return driver.getName();
		}
		return super.getText(element);
	}
	
}


public SearchTabItem getTabItemSearch() {
	return tabItemSearch;
}



public void setTabItemSearch(SearchTabItem tabItemSearch) {
	this.tabItemSearch = tabItemSearch;
}



public ServiceAreaTabItem getTabItemServiceArea() {
	return tabItemServiceArea;
}



public void setTabItemServiceArea(ServiceAreaTabItem tabItemServiceArea) {
	this.tabItemServiceArea = tabItemServiceArea;
}	


}