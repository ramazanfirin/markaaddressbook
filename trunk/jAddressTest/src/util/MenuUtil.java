package util;

import model.DBManager;
import model.model.User;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import wizards.UserUpdateWizard;

import com.AddressBookNew;

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
	
	public static void createUserOperationMenu(Menu menuBar) {
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
				User user = AddressBookNew.getInstance().getLoginUser();
				UserUpdateWizard updateWizard = new UserUpdateWizard(user,"Kullanici Bilgileri"); 
				WizardDialog wizardDialog = new WizardDialog(shell.getShell(), updateWizard);
				if(wizardDialog.open()==Window.OK){
					DBManager.getInstance().saveOrUpdate(user);
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
					AddressBookNew.getInstance().getTabItemDriver().newEntity();
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
					AddressBookNew.getInstance().getTabItemBus().newEntity();
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
				AddressBookNew.getInstance().getTabItemHost().newEntity();
			}
		});
		
		MenuItem subItemBusOwner = new MenuItem(menu, SWT.NONE);
		subItemBusOwner.setEnabled(Util.isAdmin());
		subItemBusOwner.setText(Util.getString("newEntityMenu.newBusOwner"));
		subItemBusOwner.setAccelerator(SWT.MOD1 + 'N');
		subItemBusOwner.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddressBookNew.getInstance().getTabItemBusOwner().newEntity();
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
		
	    ToolItem itemDriver = new ToolItem(toolBar, SWT.PUSH);
	    itemDriver.setEnabled(Util.isAdmin());
	    itemDriver.setText(Util.getString("toolbar.new.driver"));
	    Image icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");    itemDriver.setImage(icon);
	    itemDriver.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddressBookNew.getInstance().getTabItemDriver().newEntity();
			}
		});
	    
	    ToolItem itemBus = new ToolItem(toolBar, SWT.PUSH);
	    itemBus.setEnabled(Util.isAdmin());
	    itemBus.setText(Util.getString("toolbar.new.bus"));
	    icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemBus.setImage(icon);
	    itemBus.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddressBookNew.getInstance().getTabItemBus().newEntity();
			}
		});
	    
	    ToolItem itemHost = new ToolItem(toolBar, SWT.PUSH);
	    itemHost.setEnabled(Util.isAdmin());
	    itemHost.setText(Util.getString("toolbar.new.host"));
	    icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemHost.setImage(icon);
	    itemHost.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddressBookNew.getInstance().getTabItemHost().newEntity();
			}
		});

	    ToolItem itemBusOwner = new ToolItem(toolBar, SWT.PUSH);
	    itemBusOwner.setEnabled(Util.isAdmin());
	    itemBusOwner.setText(Util.getString("toolbar.new.busOwner"));
	    icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemBusOwner.setImage(icon);
	    itemBusOwner.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddressBookNew.getInstance().getTabItemBusOwner().newEntity();
			}
		});
	    
	    ToolItem itemUser = new ToolItem(toolBar, SWT.PUSH);
	    itemUser.setEnabled(Util.isAdmin());
	    itemUser.setText(Util.getString("toolbar.new.user"));
	    icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemUser.setImage(icon);
	    itemUser.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddressBookNew.getInstance().getTabItemUser().newEntity();
			}
		});
	    
	    ToolItem itemUserUpdate = new ToolItem(toolBar, SWT.PUSH);
	    itemUserUpdate.setText(Util.getString("userOperationMenu.update"));
	    icon = new Image(parent.getShell().getDisplay(), "./img/artwork/toolbar/user_list.png");
	    itemUserUpdate.setImage(icon);
	    itemUserUpdate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				User user = AddressBookNew.getInstance().getLoginUser();
				UserUpdateWizard updateWizard = new UserUpdateWizard(user,"Kullanici Bilgileri"); 
				WizardDialog wizardDialog = new WizardDialog(_parent.getShell(), updateWizard);
				if(wizardDialog.open()==Window.OK){
					DBManager.getInstance().saveOrUpdate(user);
					
				}
			}
		});
	
	    
	    
	    return toolBar;
	}
}
