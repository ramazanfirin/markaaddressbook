package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import util.Util;

import com.AddressBook;

public class DriverTableComposite extends Composite{

	private Table driverTable;
	
	
	
	private static final String[] columnNames = {
		 Util.getString("Last_name"),
		 Util.getString("First_name"),
		 Util.getString("Business_phone"),
		 Util.getString("Home_phone"),
		 Util.getString("Email"),
		 Util.getString("Fax")};
	
	
	public DriverTableComposite(Composite parent) {
		super(parent, SWT.NONE);
		
		
		driverTable = new Table(parent, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		driverTable.setHeaderVisible(true);	
		driverTable.setLinesVisible(true);
		driverTable.setMenu(DriverListMenu.createPopUpMenu(driverTable));	
		driverTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
		driverTable.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem[] items = driverTable.getSelection();
				//if (items.length > 0) editEntry(items[0]);
			}
		});
		
		for(int i = 0; i < columnNames.length; i++) {
			TableColumn column = new TableColumn(driverTable, SWT.NONE);
			column.setText(columnNames[i]);
			column.setWidth(150);
			final int columnIndex = i;
			column.addSelectionListener(new SelectionAdapter() {		
				public void widgetSelected(SelectionEvent e) {
				//	sort(columnIndex);
				}
			});
		}
		//Driver Table -end
	}


	public Table getDriverTable() {
		return driverTable;
	}


	public void setDriverTable(Table driverTable) {
		this.driverTable = driverTable;
	}

}
