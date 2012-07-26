package util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.AddressBookNew;

public class MenuUtil {

	public static void createFileMenu(Menu menuBar) {
		final Shell shell = menuBar.getShell();
		//File menu.
		MenuItem item = new MenuItem(menuBar, SWT.CASCADE);
		item.setText(Util.getString("File_menu_title"));
		Menu menu = new Menu(menuBar.getShell(), SWT.DROP_DOWN);
		item.setMenu(menu);
		
		//File -> New Contact
		MenuItem subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(Util.getString("New_contact"));
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
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		//File -> New Contact
		MenuItem subItemBus = new MenuItem(menu, SWT.NONE);
		subItemBus.setText(Util.getString("New_contact"));
		subItemBus.setAccelerator(SWT.MOD1 + 'N');
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
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		//File -> Exit.
		subItem = new MenuItem(menu, SWT.NONE);
		subItem.setText(Util.getString("Exit"));
		subItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.getShell().close();
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
}
