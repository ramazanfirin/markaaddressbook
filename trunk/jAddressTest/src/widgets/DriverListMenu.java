package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.AddressBook;

import util.Util;

public class DriverListMenu {

	
	
	
	public static Menu createPopUpMenu(Table _driverTable) {
		final Table driverTable = _driverTable;
		//private String[] copyBuffer=null;
		Menu popUpMenu = new Menu(driverTable.getShell(), SWT.POP_UP);

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
				items[3].setEnabled(count != 0); // copy
				//items[4].setEnabled(copyBuffer != null); // paste
				items[5].setEnabled(count != 0); // delete
				items[7].setEnabled(driverTable.getItemCount() != 0); // find
			}
		});

		//New
		MenuItem item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_new"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddressBook.getInstance().newDriverEntry();
			}
		});
		
		new MenuItem(popUpMenu, SWT.SEPARATOR);	
		
		//Edit
		item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_edit"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = driverTable.getSelection();
				if (items.length == 0) return;
				AddressBook.getInstance().editEntry(items[0]);
			}
		});

		//Copy
		item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_copy"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = driverTable.getSelection();
				if (items.length == 0) return;
				//copyBuffer = new String[driverTable.getColumnCount()];
				//for (int i = 0; i < copyBuffer.length; i++) {
					//copyBuffer[i] = items[0].getText(i);
				//}
			}
		});
		
		//Paste
		item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_paste"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//if (copyBuffer == null) return;
				TableItem item = new TableItem(driverTable, SWT.NONE);
				//item.setText(copyBuffer);
				AddressBook.getInstance().setModified( true);
			}
		});
		
		//Delete
		item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_delete"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = driverTable.getSelection();
				if (items.length == 0) return;
				items[0].dispose();
				AddressBook.getInstance().setModified( true);
			}
		});
		
		new MenuItem(popUpMenu, SWT.SEPARATOR);	
		
		//Find...
		item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_find"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//AddressBook.getInstance().getSearchDialog().open();
			}
		});

		return popUpMenu;
	}

	
}
