package widgets;

import java.util.Iterator;
import java.util.Set;

import model.DBManager;
import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.DriverEntityWizard;

public class DriverTabItem extends BasicTabItem{

	private static final String[] columnNames = {
		Util.getString("driver.name"),
		Util.getString("driver.surname"),
		Util.getString("driver.phoneNumber"),
		Util.getString("bus.plate"),
		Util.getString("bus.phoneNumber")};
	
			
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
		return new DriverEntityWizard(entity);
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
	void saveData() {
		DBManager.getInstance().saveOrUpdate(entity);
		
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
	    case 3:
	      String result="";	
		  Set list = ae.getBusList();
		  if(list!=null && list.size()>0){
			  for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Bus object2 = (Bus) iterator.next();
				result=result+"-"+object2.getPlate();
			}
		  }
	    	
	    	return  result;
	    case 4:
	    	String result2="";	
			  Set list2 = ae.getBusList();
			  if(list2!=null && list2.size()>0){
				  for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
					Bus object2 = (Bus) iterator.next();
					result2=result2+"-"+object2.getPhone();
				}
			  }
		    	
		    	return  result2;
	    }
	    return "";
		
	}



	@Override
	String getName() {
		return Util.getString("driver.list");
	}



}


