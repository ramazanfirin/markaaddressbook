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
import model.model.User;

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

import util.MenuUtil;
import util.Util;
import widgets.BasicCTabFolder;
import widgets.BusOwnerTabItem;
import widgets.BusTabItem;
import widgets.DriverTabItem;
import widgets.HostTabItem;
import widgets.MuavinTabItem;
import widgets.OutOfficeTabItem;
import widgets.ServiceAreaTabItem;
import widgets.UserTabItem;
/**
 * AddressBookExample is an example that uses <code>org.eclipse.swt 
 * libraries to implement a simple address book.  This application has 
 * saveR, load, sorting, and searching functions common
 * to basic address books.
 */
public class AddressBookNewClient extends AddressBookNew{


	protected static AddressBookNewClient instance = new AddressBookNewClient();
	 public static AddressBookNewClient getInstance() {
			return instance;
	 }
	
	public static void main(String[] args) {
		
		System.out.println("basliyoruz2");
		try {
			display = new Display();
			instance.userLocalDB=false;
			instance.isRunning=true;
			Shell shell = instance.open(display);
			while(!shell.isDisposed()){
				if(!display.readAndDispatch())
					display.sleep();
			}
			display.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}