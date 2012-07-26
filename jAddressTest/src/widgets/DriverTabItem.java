package widgets;

import java.util.ArrayList;
import java.util.List;

import model.DBManager;
import model.interfaces.AbsractInterface;
import model.model.Driver;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.DriverEntityWizard;

public class DriverTabItem extends BasicTabItem{

	private static final String[] columnNames = {Util.getString("Last_name"),
		Util.getString("First_name"),
		Util.getString("Business_phone"),
		Util.getString("Home_phone"),
		Util.getString("Email"),
		Util.getString("Fax")};
	
			
	public DriverTabItem(CTabFolder parent, String name) {
		super(parent, name);
	}

	
	@Override
	void search() {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	void prepareComponents(Composite grpLocation) {
			
		Label labelDriverName=new Label(grpLocation,SWT.NONE);
		labelDriverName.setText(Util.getString("driver.name"));
		labelDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		Text textDriverName=new Text(grpLocation,SWT.BORDER);
		textDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelDriverSurname=new Label(grpLocation,SWT.NONE);
		labelDriverSurname.setText(Util.getString("driver.surname"));
		labelDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		Text textDriverSurname=new Text(grpLocation,SWT.BORDER);
		textDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelDriverPhone=new Label(grpLocation,SWT.NONE);
		labelDriverPhone.setText(Util.getString("driver.phoneNumber"));
		labelDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		Text textDriverPhone=new Text(grpLocation,SWT.BORDER);
		textDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
	}

	@Override
	String[] getColumNames() {
		return columnNames;
	}


	@Override
	Wizard getNewWizard() {
		return new DriverEntityWizard();
	}

	@Override
	void createNewEntity() {
		entity = new Driver();
		
	}

	@Override
	void loadData() {
		try {
			entityList = DBManager.getInstance().loadAllDriver2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	String getTableColumValues(Object object, int columnIndex) {
		Driver ae = (Driver) object;
	    switch (columnIndex) {
	    case 0:
	      return ae.getName();
	    case 1:
	      return ae.getSurname();
	    case 2:
	      return ae.getPhone();
	    }
	    return "";
		
	}

}


