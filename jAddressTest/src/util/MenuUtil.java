package util;

import model.model.User;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import wizards.UserUpdateWizard;

public class MenuUtil {

	public static void createFileMenu(Menu menuBar) {
		final Shell shell = menuBar.getShell();
		MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
		item.setText(Util.getString("File_menu_title"));
		Menu menu = new Menu(menuBar.getShell(), SWT.DROP_DOWN);
		item.setMenu(menu);
		
		//File -> Exit.
		MenuItem subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(Util.getString("Exit"));
		subItem.setAccelerator(SWT.MOD1 + 'N');
		subItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.getShell().close();
			}
		});
}
	
	public static void createUserOperationMenu(Menu menuBar){
		final Shell shell = menuBar.getShell();
		MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
		item.setText(Util.getString("userOperationMenu.title"));
		Menu menu = new Menu(menuBar.getShell(), SWT.DROP_DOWN);
		item.setMenu(menu);
		
		
		//File -> Exit.
		MenuItem subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(Util.getString("userOperationMenu.update"));
		subItem.setAccelerator(SWT.MOD1 + 'N');
		subItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				User user = Util.getApplicationInstance().getLoginUser();
				UserUpdateWizard updateWizard = new UserUpdateWizard(user,"Kullanici Bilgileri"); 
				WizardDialog wizardDialog = new WizardDialog(shell.getShell(), updateWizard);
				if(wizardDialog.open()==Window.OK){
					try {
						Util.getApplicationInstance().getDataProvider().saveOrUpdate(user);
					} catch (Exception e1) {
						MessageDialog.openError(wizardDialog.getShell(), "Error", e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});
}
	
	public static void createNewEntity(Menu menuBar) {
		final Shell shell= menuBar.getShell();
		MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
		item.setText(Util.getString("newEntityMenu.title"));	
		Menu menu = new Menu(shell, SWT.DROP_DOWN);
		item.setMenu(menu);
		
		
		//File -> New Contact
		MenuItem subItem = new MenuItem(menu, SWT.NONE);
		subItem.setEnabled(Util.isAdmin());
		subItem.setText(Util.getString("newEntityMenu.newDriver"));
		subItem.setAccelerator(SWT.MOD1 + 'N');
		subItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					Util.getApplicationInstance().getTabItemDriver().newEntity();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		MenuItem subItemBus = new MenuItem(menu, SWT.NONE);
		subItemBus.setText(Util.getString("newEntityMenu.newBus"));
		subItemBus.setAccelerator(SWT.MOD1 + 'N');
		subItemBus.setEnabled(Util.isAdmin());
		subItemBus.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					Util.getApplicationInstance().getTabItemBus().newEntity();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		MenuItem subItemHost = new MenuItem(menu, SWT.NONE);
		subItemHost.setEnabled(Util.isAdmin());
		subItemHost.setText(Util.getString("newEntityMenu.newHost"));
		subItemHost.setAccelerator(SWT.MOD1 + 'N');
		subItemHost.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemHost().newEntity();
			}
		});
		
		MenuItem subItemBusOwner = new MenuItem(menu, SWT.NONE);
		subItemBusOwner.setEnabled(Util.isAdmin());
		subItemBusOwner.setText(Util.getString("newEntityMenu.newBusOwner"));
		subItemBusOwner.setAccelerator(SWT.MOD1 + 'N');
		subItemBusOwner.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemBusOwner().newEntity();
			}
		});
		
		MenuItem subItemMuavin = new MenuItem(menu, SWT.NONE);
		subItemMuavin.setEnabled(Util.isAdmin());
		subItemMuavin.setText(Util.getString("newEntityMenu.newMuavin"));
		subItemMuavin.setAccelerator(SWT.MOD1 + 'N');
		subItemMuavin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemMuavin().newEntity();
			}
		});
		
		MenuItem subItemOutOffice = new MenuItem(menu, SWT.NONE);
		subItemOutOffice.setEnabled(Util.isAdmin());
		subItemOutOffice.setText(Util.getString("newEntityMenu.newOutOffice"));
		subItemOutOffice.setAccelerator(SWT.MOD1 + 'N');
		subItemOutOffice.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemOutOffice().newEntity();
			}
		});
		
		MenuItem subItemServiceArea = new MenuItem(menu, SWT.NONE);
		subItemServiceArea.setEnabled(Util.isAdmin());
		subItemServiceArea.setText(Util.getString("newEntityMenu.newServiceArea"));
		subItemServiceArea.setAccelerator(SWT.MOD1 + 'N');
		subItemServiceArea.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemServiceArea().newEntity();
			}
		});
}

	
	
	
	public static void createHelpMenu(Menu menuBar) {
		final Shell shell= menuBar.getShell();
		//Help Menu
		MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
		item.setText(Util.getString("Help_menu_title"));	
		Menu menu = new Menu(shell, SWT.DROP_DOWN);
		item.setMenu(menu);
		
		//Help -> About Text Editor
		MenuItem subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(Util.getString("About"));
		subItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell, SWT.NONE);
				box.setText(Util.getString("About_1") + shell.getText());
				box.setMessage(shell.getText() + Util.getString("About_2"));
				box.open();		
			}
		});
	}
	
	public static ToolBar createToolBar(Composite parent){
		final Composite _parent=parent;
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		toolBar.setSize(300,70);
	    
		ToolItem itemUserUpdate = new ToolItem(toolBar, SWT.PUSH);
	    itemUserUpdate.setText(Util.getString("userOperationMenu.update"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    ClassLoader classLoader = parent.getClass().getClassLoader();
	    Image icon = new Image(parent.getShell().getDisplay(), classLoader.getResourceAsStream("resources/user_list.png"));
	    itemUserUpdate.setImage(icon);
	    itemUserUpdate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				User user = Util.getApplicationInstance().getLoginUser();
				UserUpdateWizard updateWizard = new UserUpdateWizard(user,"Kullanici Bilgileri"); 
				WizardDialog wizardDialog = new WizardDialog(_parent.getShell(), updateWizard);
				if(wizardDialog.open()==Window.OK){
					try {
						Util.getApplicationInstance().getDataProvider().saveOrUpdate(user);
					} catch (Exception e1) {
						MessageDialog.openError(wizardDialog.getShell(), "Error", e1.getMessage());
						e1.printStackTrace();
					}
					
				}
			}
		});

		
		ToolItem itemDriver = new ToolItem(toolBar, SWT.PUSH);
	    itemDriver.setEnabled(Util.isAdmin());
	    itemDriver.setText(Util.getString("toolbar.new.driver"));
	    //Image icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");    
	    itemDriver.setImage(icon);
	    itemDriver.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemDriver().newEntity();
			}
		});
	    
	    	    
	    ToolItem itemBus = new ToolItem(toolBar, SWT.PUSH);
	    itemBus.setEnabled(Util.isAdmin());
	    itemBus.setText(Util.getString("toolbar.new.bus"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemBus.setImage(icon);
	    itemBus.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemBus().newEntity();
			}
		});
	    
	    ToolItem itemHost = new ToolItem(toolBar, SWT.PUSH);
	    itemHost.setEnabled(Util.isAdmin());
	    itemHost.setText(Util.getString("toolbar.new.host"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemHost.setImage(icon);
	    itemHost.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemHost().newEntity();
			}
		});

	    ToolItem itemBusOwner = new ToolItem(toolBar, SWT.PUSH);
	    itemBusOwner.setEnabled(Util.isAdmin());
	    itemBusOwner.setText(Util.getString("toolbar.new.busOwner"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemBusOwner.setImage(icon);
	    itemBusOwner.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemBusOwner().newEntity();
			}
		});
	    
	    ToolItem itemMuavin = new ToolItem(toolBar, SWT.PUSH);
	    itemMuavin.setEnabled(Util.isAdmin());
	    itemMuavin.setText(Util.getString("toolbar.new.muavin"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemMuavin.setImage(icon);
	    itemMuavin.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemMuavin().newEntity();
			}
		});
	    
	    ToolItem itemOutOffice = new ToolItem(toolBar, SWT.PUSH);
	    itemOutOffice.setEnabled(Util.isAdmin());
	    itemOutOffice.setText(Util.getString("toolbar.new.outOffice"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemOutOffice.setImage(icon);
	    itemOutOffice.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemOutOffice().newEntity();
			}
		});
	    
	    ToolItem itemServiceArea = new ToolItem(toolBar, SWT.PUSH);
	    itemServiceArea.setEnabled(Util.isAdmin());
	    itemServiceArea.setText(Util.getString("toolbar.new.serviceArea"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemServiceArea.setImage(icon);
	    itemServiceArea.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemServiceArea().newEntity();
			}
		});
	    
	    
	    ToolItem itemUser = new ToolItem(toolBar, SWT.PUSH);
	    itemUser.setEnabled(Util.isAdmin());
	    itemUser.setText(Util.getString("toolbar.new.user"));
	    //icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemUser.setImage(icon);
	    itemUser.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Util.getApplicationInstance().getTabItemUser().newEntity();
			}
		});
	    
	    return toolBar;
	}
}
