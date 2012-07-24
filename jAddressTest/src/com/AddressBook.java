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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import model.DBManager;
import model.model.Driver;
import model.model.User;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.mihalis.opal.login.LoginDialog;
import org.mihalis.opal.login.LoginDialogVerifier;

import util.Util;
import widgets.DriverSearchGroup;
import wizards.BusEntityWizard;
import wizards.DriverEntityWizard;

import com.providers.DriverContentProviders;
import com.providers.DriverLabelProviders;
/**
 * AddressBookExample is an example that uses <code>org.eclipse.swt 
 * libraries to implement a simple address book.  This application has 
 * saveR, load, sorting, and searching functions common
 * to basic address books.
 */
public class AddressBook {

	public static ResourceBundle resAddressBook = ResourceBundle.getBundle("examples_addressbook",new Locale("tr", "TR"));
	private Shell shell;
	
	private Table driverTable;
	private Table busTable;


	
	private File file;
	private boolean isModified;
	
	private String[] copyBuffer;

	private int lastSortColumn= -1;
	
	private static final String DELIMITER = "\t";
	private static final String[] columnNames = {resAddressBook.getString("Last_name"),
												 resAddressBook.getString("First_name"),
												 resAddressBook.getString("Business_phone"),
												 resAddressBook.getString("Home_phone"),
												 resAddressBook.getString("Email"),
												 resAddressBook.getString("Fax")};
	
	private static final String[] columnNamesBus = {
		 resAddressBook.getString("bus.plate"),
		 resAddressBook.getString("bus.phoneNumber")};
		
	
	 private CTabFolder cTabFolder;
	 private CTabItem tabItemSearch;
	 private CTabItem tabItemDriver;
	 private CTabItem tabItemBus;
	
	 
	 private ScrolledComposite cSUserData;
	 
	 DriverSearchGroup driverSearchGroup;
	 
	 SashForm sashForm;
	 
	 WizardDialog wizardDriver;
	 WizardDialog wizardBus;
	 
	 Driver driver;
	 User user;
	 
	 private TableViewer viewerDriver;
	 List<Driver> driverList= new ArrayList<Driver>();
	 
	 
	 private static AddressBook instance = new AddressBook();
	 public static AddressBook getInstance() {
			return instance;
		}

	 
	 
public static void main(String[] args) {
	Display display = new Display();
	Shell shell = instance.open(display);
	while(!shell.isDisposed()){
		if(!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}
public Shell open(Display display) {
	shell = new Shell(display);
	shell.setLayout(new FillLayout());
	 
//	final LoginDialog dialog = new LoginDialog();
//    dialog.setVerifier(verifier);
//    final boolean result=dialog.open();
//    if(result){
//    	shell.setText(user.getUsername());
//    }else{
//    	shell.dispose();
//    	return shell;
//    }
    
    createMenuBar();
     
	wizardDriver = new WizardDialog(shell,
	        new DriverEntityWizard());
	
	wizardBus = new WizardDialog(shell,
	        new BusEntityWizard());

	/*
     * Setup TabFolder data
     */
    cTabFolder = new CTabFolder(shell, SWT.BORDER);
    cTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
    
    cTabFolder.setSimple(false);
    cTabFolder.setUnselectedImageVisible(false);
    cTabFolder.setUnselectedCloseVisible(false);
	
    tabItemDriver = new CTabItem(cTabFolder, SWT.NONE);
    tabItemDriver.setText("User List");
    Composite compositeDriver=new Composite(cTabFolder,SWT.NONE);
    GridLayout layout = new GridLayout(1, false);
    compositeDriver.setLayout(layout);
    tabItemDriver.setControl(compositeDriver);
    cTabFolder.setSelection(tabItemDriver);
    tabItemDriver.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/user_list.png"));
    
    tabItemBus = new CTabItem(cTabFolder, SWT.NONE);
    tabItemBus.setText("Bus List");
    Composite compositeBus=new Composite(cTabFolder,SWT.NONE);
    compositeBus.setLayout(layout);
    tabItemBus.setControl(compositeBus);
    cTabFolder.setSelection(tabItemDriver);
    tabItemBus.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/user_list.png"));
    
    tabItemSearch = new CTabItem(cTabFolder, SWT.NONE);
    tabItemSearch.setText("User Search");
    Composite compositeSearch=new Composite(cTabFolder,SWT.NONE);
    tabItemSearch.setControl(compositeSearch);
    tabItemSearch.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/search.png"));
    compositeSearch.setLayout(new FormLayout());
     
    /*
     * Setup TabFolder data
     */
   // driverSearchGroup = new DriverSearchGroup(compositeDriver);
    
    //Driver Search
    final Group grpLocation = new Group(compositeDriver, SWT.NONE);
    grpLocation.setText("arama");
    grpLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    GridLayout grpLayout = new GridLayout(2, false);
    grpLayout.verticalSpacing = 0;
    grpLocation.setLayout(grpLayout);
	
	
    Label labelDriverName=new Label(grpLocation,SWT.NONE);
	labelDriverName.setText(resAddressBook.getString("driver.name"));
	labelDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
	
	Text textDriverName=new Text(grpLocation,SWT.BORDER);
	textDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	
	Label labelDriverSurname=new Label(grpLocation,SWT.NONE);
	labelDriverSurname.setText(resAddressBook.getString("driver.surname"));
	labelDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
	
	Text textDriverSurname=new Text(grpLocation,SWT.BORDER);
	textDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	
	Label labelDriverPhone=new Label(grpLocation,SWT.NONE);
	labelDriverPhone.setText(resAddressBook.getString("driver.phoneNumber"));
	labelDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
	
	Text textDriverPhone=new Text(grpLocation,SWT.BORDER);
	textDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
   
	
	Button searchDriverButton = new Button(grpLocation, SWT.NONE);
	searchDriverButton.setText(resAddressBook.getString("search"));
	searchDriverButton.addSelectionListener(new SelectionListener() {

	      public void widgetSelected(SelectionEvent event) {
	    	  //MailDialog dialog=new MailDialog(shell, null);
	    	  //dialog.open();
	    	 
	      }

	      public void widgetDefaultSelected(SelectionEvent event) {
	        //text.setText("No worries!");
	      }
	 });
	
	//Driver Search-- End
    
	//Driver Table
	final Group grpDriverList = new Group(compositeDriver, SWT.NONE);
	grpDriverList.setText(resAddressBook.getString("driver.list"));
	grpDriverList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout gridDriverList = new GridLayout(1, false);
    gridDriverList.verticalSpacing = 0;
    grpDriverList.setLayout(gridDriverList);
	
    viewerDriver = new TableViewer(grpDriverList,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
    viewerDriver.setContentProvider(new DriverContentProviders());
    viewerDriver.setLabelProvider(new DriverLabelProviders());
    viewerDriver.setInput(driverList);
    //driverTable = new Table(grpDriverList, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
    driverTable = viewerDriver.getTable();
    driverTable.setHeaderVisible(true);	
	driverTable.setLinesVisible(true);
	driverTable.setMenu(createPopUpMenu());	
	driverTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
	driverTable.addSelectionListener(new SelectionAdapter() {
		public void widgetDefaultSelected(SelectionEvent e) {
			TableItem[] items = driverTable.getSelection();
			driverTable.getSelectionIndex();
			if (items.length > 0) 
				editEntry(items[0]);
		}
	});
	
	
	
	for(int i = 0; i < columnNames.length; i++) {
		TableColumn column = new TableColumn(driverTable, SWT.NONE);
		column.setText(columnNames[i]);
		column.setWidth(150);
		final int columnIndex = i;
		column.addSelectionListener(new SelectionAdapter() {		
			public void widgetSelected(SelectionEvent e) {
				sort(columnIndex);
			}
		});
	}
	
	
	
	//Driver Table -end
	
	
	//Bus Table
	//Driver Search
    final Group grpLocationBus = new Group(compositeBus, SWT.NONE);
    grpLocationBus.setText("arama");
    grpLocationBus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    GridLayout grpLayoutBus = new GridLayout(2, false);
    grpLayoutBus.verticalSpacing = 0;
    grpLocationBus.setLayout(grpLayoutBus);
	
	
    Label labelBusPlate=new Label(grpLocationBus,SWT.NONE);
    labelBusPlate.setText(resAddressBook.getString("driver.name"));
    labelBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
	
	Text textBusPlate=new Text(grpLocationBus,SWT.BORDER);
	textBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	
	Label labelBusPhone=new Label(grpLocationBus,SWT.NONE);
	labelBusPhone.setText(resAddressBook.getString("driver.surname"));
	labelBusPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
	
	Text textBusPhone=new Text(grpLocationBus,SWT.BORDER);
	textBusPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

   
	
	Button searchBusButton = new Button(grpLocationBus, SWT.NONE);
	searchBusButton.setText(resAddressBook.getString("search"));
	searchBusButton.addSelectionListener(new SelectionListener() {

	      public void widgetSelected(SelectionEvent event) {
	    	  if(wizardBus.open() == Window.OK){
	    		  
	    	  }
	      }

	      public void widgetDefaultSelected(SelectionEvent event) {
	        //text.setText("No worries!");
	      }
	 });
	
	//Driver Table
	final Group grpBusList = new Group(compositeBus, SWT.NONE);
	grpBusList.setText(resAddressBook.getString("bus.list"));
	grpBusList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    GridLayout gridBusList = new GridLayout(1, false);
    gridBusList.verticalSpacing = 0;
    grpBusList.setLayout(gridBusList);
	
	//BusTable -
	 busTable = new Table(grpBusList, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
	 busTable.setHeaderVisible(true);	
	 busTable.setLinesVisible(true);
	 busTable.setMenu(createPopUpMenu());	
	 busTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
	 busTable.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem[] items = driverTable.getSelection();
				
				if (items.length > 0) 
					editEntry(items[0]);
			}
		});
		for(int i = 0; i < columnNamesBus.length; i++) {
			TableColumn column = new TableColumn(busTable, SWT.NONE);
			column.setText(columnNamesBus[i]);
			column.setWidth(150);
			final int columnIndex = i;
			column.addSelectionListener(new SelectionAdapter() {		
				public void widgetSelected(SelectionEvent e) {
					sort(columnIndex);
				}
			});
		}
	
		//BusTable -

	newAddressBook();

	loadData();
	viewerDriver.refresh();
	viewerDriver.setInput(driverList);
	// Pack the columns
    
	//shell.setSize(driverTable.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, 300);
	shell.open();
	return shell;
}

public void loadData(){
	driverList = DBManager.getInstance().loadAllDriver();
	System.out.println("driver sayisi="+driverList.size());
	viewerDriver.refresh();
	viewerDriver.setInput(driverList);
	driver = new Driver();
	
}



/**
 * Creates the menu at the top of the shell where most
 * of the programs functionality is accessed.
 *
 * @return		The <code>Menu widget that was created
 */
private Menu createMenuBar() {
	Menu menuBar = new Menu(shell, SWT.BAR);
	shell.setMenuBar(menuBar);
	
	//create each header and subMenu for the menuBar
	createFileMenu(menuBar);
	createEditMenu(menuBar);
	createSearchMenu(menuBar);
	createHelpMenu(menuBar);
	
	return menuBar;
}





private void newAddressBook() {	
	//shell.setText(resAddressBook.getString("Title_bar") + resAddressBook.getString("New_title"));
	file = null;
	isModified = false;
}

public void editEntry(TableItem item) {
	driver = (Driver)item.getData();
	wizardDriver.open();
	DBManager.getInstance().saveOrUpdate(driver);
    loadData();
}

public void newDriverEntry() {
	driver = new Driver();
	int result=wizardDriver.open();
    DBManager.getInstance().saveOrUpdate(driver);
    loadData();
}

public void deleteEntry(TableItem item) {
	driver = (Driver)item.getData();
	boolean answer = MessageDialog.openConfirm(shell, "Onaylama", "Deneme");
    if(answer){
    	DBManager.getInstance().delete(driver);
        loadData();
    }
	
   
}




private void sort(int column) {
	if(driverTable.getItemCount() <= 1) return;

	TableItem[] items = driverTable.getItems();
	String[][] data = new String[items.length][driverTable.getColumnCount()];
	for(int i = 0; i < items.length; i++) {
		for(int j = 0; j < driverTable.getColumnCount(); j++) {
			data[i][j] = items[i].getText(j);
		}
	}
	
	Arrays.sort(data, new RowComparator(column));
	
	if (lastSortColumn != column) {
		driverTable.setSortColumn(driverTable.getColumn(column));
		driverTable.setSortDirection(SWT.DOWN);
		for (int i = 0; i < data.length; i++) {
			items[i].setText(data[i]);
		}
		lastSortColumn = column;
	} else {
		// reverse order if the current column is selected again
		driverTable.setSortDirection(SWT.UP);
		int j = data.length -1;
		for (int i = 0; i < data.length; i++) {
			items[i].setText(data[j--]);
		}
		lastSortColumn = -1;
	}
	
}















//////////////////////////////?***********************************************************************



/**
 * Creates all the items located in the File submenu and
 * associate all the menu items with their appropriate
 * functions.
 *
 * @param	menuBar Menu
 *				the <code>Menu that file contain
 *				the File submenu.
 */
private void createFileMenu(Menu menuBar) {
	//File menu.
	MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
	item.setText(resAddressBook.getString("File_menu_title"));
	Menu menu = new Menu(shell, SWT.DROP_DOWN);
	item.setMenu(menu);
	/** 
	 * Adds a listener to handle enabling and disabling 
	 * some items in the Edit submenu.
	 */
	menu.addMenuListener(new MenuAdapter() {
		public void menuShown(MenuEvent e) {
			Menu menu = (Menu)e.widget;
			MenuItem[] items = menu.getItems();
			items[1].setEnabled(driverTable.getSelectionCount() != 0); // edit contact
			items[5].setEnabled((file != null) && isModified); // save
			items[6].setEnabled(driverTable.getItemCount() != 0); // save as
		}
	});


	//File -> New Contact
	MenuItem subItem = new MenuItem(menu, SWT.NONE);
	subItem.setText(resAddressBook.getString("New_contact"));
	subItem.setAccelerator(SWT.MOD1 + 'N');
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			newDriverEntry();
		}
	});
	
	subItem = new MenuItem(menu, SWT.NONE);
	subItem.setText(resAddressBook.getString("Edit_contact"));
	subItem.setAccelerator(SWT.MOD1 + 'E');
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			TableItem[] items = driverTable.getSelection();
			if (items.length == 0) return;
			editEntry(items[0]);
		}
	});

	
	new MenuItem(menu, SWT.SEPARATOR);
	
	

	//File -> Open
	

	
		
	new MenuItem(menu, SWT.SEPARATOR);
	
	//File -> Exit.
	subItem = new MenuItem(menu, SWT.NONE);
	subItem.setText(resAddressBook.getString("Exit"));
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			shell.close();
		}
	});
}

/**
 * Creates all the items located in the Edit submenu and
 * associate all the menu items with their appropriate
 * functions.
 *
 * @param	menuBar Menu
 *				the <code>Menu that file contain
 *				the Edit submenu.
 *
 * @see	#createSortMenu()
 */
private MenuItem createEditMenu(Menu menuBar) {
	//Edit menu.
	MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
	item.setText(resAddressBook.getString("Edit_menu_title"));
	Menu menu = new Menu(shell, SWT.DROP_DOWN);
	item.setMenu(menu);
	
	/** 
	 * Add a listener to handle enabling and disabling 
	 * some items in the Edit submenu.
	 */
	menu.addMenuListener(new MenuAdapter() {
		public void menuShown(MenuEvent e) {
			Menu menu = (Menu)e.widget;
			MenuItem[] items = menu.getItems();
			int count = driverTable.getSelectionCount();
			items[0].setEnabled(count != 0); // edit
			items[1].setEnabled(count != 0); // copy
			items[2].setEnabled(copyBuffer != null); // paste
			items[3].setEnabled(count != 0); // delete
			items[5].setEnabled(driverTable.getItemCount() != 0); // sort
		}
	});
	
	//Edit -> Edit
	MenuItem subItem = new MenuItem(menu, SWT.PUSH);
	subItem.setText(resAddressBook.getString("Edit"));
	subItem.setAccelerator(SWT.MOD1 + 'E');
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			TableItem[] items = driverTable.getSelection();
			if (items.length == 0) return;
			editEntry(items[0]);
		}
	});

	//Edit -> Copy
	subItem = new MenuItem(menu, SWT.NONE);
	subItem.setText(resAddressBook.getString("Copy"));
	subItem.setAccelerator(SWT.MOD1 + 'C');
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			TableItem[] items = driverTable.getSelection();
			if (items.length == 0) return;
			copyBuffer = new String[driverTable.getColumnCount()];
			for (int i = 0; i < copyBuffer.length; i++) {
				copyBuffer[i] = items[0].getText(i);
			}
		}
	});
	
	//Edit -> Paste
	subItem = new MenuItem(menu, SWT.NONE);
	subItem.setText(resAddressBook.getString("Paste"));
	subItem.setAccelerator(SWT.MOD1 + 'V');
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			if (copyBuffer == null) return;
			TableItem item = new TableItem(driverTable, SWT.NONE);
			item.setText(copyBuffer);
			isModified = true;
		}
	});
	
	//Edit -> Delete
	subItem = new MenuItem(menu, SWT.NONE);
	subItem.setText(resAddressBook.getString("Delete"));
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			TableItem[] items = driverTable.getSelection();
			if (items.length == 0) return;
			items[0].dispose();
			isModified = true;		}
	});
	
	new MenuItem(menu, SWT.SEPARATOR);
	
	//Edit -> Sort(Cascade)
	subItem = new MenuItem(menu, SWT.CASCADE);
	subItem.setText(resAddressBook.getString("Sort"));
	Menu submenu = createSortMenu();
	subItem.setMenu(submenu);
	
	return item;
	
}

/**
 * Creates all the items located in the Sort cascading submenu and
 * associate all the menu items with their appropriate
 * functions.
 *
 * @return	Menu
 *			The cascading menu with all the sort menu items on it.
 */
private Menu createSortMenu() {
	Menu submenu = new Menu(shell, SWT.DROP_DOWN);
	MenuItem subitem;
	for(int i = 0; i < columnNames.length; i++) {
		subitem = new MenuItem (submenu, SWT.NONE);
		subitem.setText(columnNames [i]);
		final int column = i;
		subitem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				sort(column);
			}
		});

	}
	
	return submenu;
}

/**
 * Creates all the items located in the Search submenu and
 * associate all the menu items with their appropriate
 * functions.
 *
 * @param	menuBar	Menu
 *				the <code>Menu that file contain
 *				the Search submenu.
 */
private void createSearchMenu(Menu menuBar) {
	//Search menu.
	MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
	item.setText(resAddressBook.getString("Search_menu_title"));
	Menu searchMenu = new Menu(shell, SWT.DROP_DOWN);
	item.setMenu(searchMenu);

	

	//Search -> Find Next
	item = new MenuItem(searchMenu, SWT.NONE);
	item.setText(resAddressBook.getString("Find_next"));
	item.setAccelerator(SWT.F3);
	item.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			//searchDialog.open();
		}
	});
}

/** 
 * Creates all items located in the popup menu and associates
 * all the menu items with their appropriate functions.
 *
 * @return	Menu
 *			The created popup menu.
 */
private Menu createPopUpMenu() {
	Menu popUpMenu = new Menu(shell, SWT.POP_UP);

	/** 
	 * Adds a listener to handle enabling and disabling 
	 * some items in the Edit submenu.
	 */
	popUpMenu.addMenuListener(new MenuAdapter() {
		public void menuShown(MenuEvent e) {
			Menu menu = (Menu)e.widget;
			MenuItem[] items = menu.getItems();
			int count = driverTable.getSelectionCount();
			items[2].setEnabled(count != 0); // edit
			//items[3].setEnabled(count != 0); // copy
			items[4].setEnabled(count != 0); // paste
			//items[5].setEnabled(count != 0); // delete
			//items[7].setEnabled(driverTable.getItemCount() != 0); // find
		}
	});

	//New
	MenuItem item = new MenuItem(popUpMenu, SWT.PUSH);
	item.setText(resAddressBook.getString("Pop_up_new"));
	item.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			newDriverEntry();
		}
	});
	
	new MenuItem(popUpMenu, SWT.SEPARATOR);	
	
	//Edit
	item = new MenuItem(popUpMenu, SWT.PUSH);
	item.setText(resAddressBook.getString("Pop_up_edit"));
	item.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			e.getSource();
			TableItem[] items = driverTable.getSelection();
			driverTable.getSelectionIndex();
			if (items.length == 0) return;
			editEntry(items[0]);
		}
	});

	
	new MenuItem(popUpMenu, SWT.SEPARATOR);	
	
	//Delete
	item = new MenuItem(popUpMenu, SWT.PUSH);
	item.setText(resAddressBook.getString("Pop_up_delete"));
	item.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			TableItem[] items = driverTable.getSelection();
			if (items.length == 0) return;
			deleteEntry(items[0]);
		}
	});
	
	
	
	
	return popUpMenu;
}

/**
 * Creates all the items located in the Help submenu and
 * associate all the menu items with their appropriate
 * functions.
 *
 * @param	menuBar	Menu
 *				the <code>Menu that file contain
 *				the Help submenu.
 */
private void createHelpMenu(Menu menuBar) {
	
	//Help Menu
	MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
	item.setText(resAddressBook.getString("Help_menu_title"));	
	Menu menu = new Menu(shell, SWT.DROP_DOWN);
	item.setMenu(menu);
	
	//Help -> About Text Editor
	MenuItem subItem = new MenuItem(menu, SWT.NONE);
	subItem.setText(resAddressBook.getString("About"));
	subItem.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			MessageBox box = new MessageBox(shell, SWT.NONE);
			box.setText(resAddressBook.getString("About_1") + shell.getText());
			box.setMessage(shell.getText() + resAddressBook.getString("About_2"));
			box.open();		
		}
	});
}

final LoginDialogVerifier verifier = new LoginDialogVerifier() {

    @Override
    public void authenticate(final String login, final String password) throws Exception {
            if ("".equals(login)) {
                    throw new Exception("Please enter a login.");
            }

            if ("".equals(password)) {
                    throw new Exception("Please enter a password.");
            }

//            if (!login.equalsIgnoreCase("laurent")) {
//                    throw new Exception("Login unknown.");
//            }
//
//            if (!password.equalsIgnoreCase("laurent")) {
//                    throw new Exception("Authentication failed, please check your password.");
//            }
            
            user=DBManager.getInstance().checkPassword(login, Util.encrypt(password));
            if(user==null)
            	 throw new Exception("Sifre yanlis");

    }
};



/**
 * To compare entries (rows) by the given column
 */
private class RowComparator implements Comparator {
	private int column;
	
	/**
	 * Constructs a RowComparator given the column index
	 * @param col The index (starting at zero) of the column
	 */
	public RowComparator(int col) {
		column = col;
	}
	
	/**
	 * Compares two rows (type String[]) using the specified
	 * column entry.
	 * @param obj1 First row to compare
	 * @param obj2 Second row to compare
	 * @return negative if obj1 less than obj2, positive if
	 * 			obj1 greater than obj2, and zero if equal.
	 */
	public int compare(Object obj1, Object obj2) {
		String[] row1 = (String[])obj1;
		String[] row2 = (String[])obj2;
		
		return row1[column].compareTo(row2[column]);
	}
}



public String[] getCopyBuffer() {
	return copyBuffer;
}



public void setCopyBuffer(String[] copyBuffer) {
	this.copyBuffer = copyBuffer;
}



public boolean isModified() {
	return isModified;
}



public void setModified(boolean isModified) {
	this.isModified = isModified;
}



public Driver getDriver() {
	return driver;
}



public void setDriver(Driver driver) {
	this.driver = driver;
}
}