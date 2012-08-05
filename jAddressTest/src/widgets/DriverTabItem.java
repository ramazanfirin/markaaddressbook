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

public class DriverTabItem extends PersonTabItem{

	public DriverTabItem(CTabFolder parent, String name) {
		super(parent, name);
	}

    
	
	@Override
	void search() {
		super.search();
		
	}

	@Override
	String[] getColumNames() {
		return super.getColumNames();
	}


	@Override
	Wizard getNewWizard() {
		return new DriverEntityWizard(entity,getWizardTitle());
	}

	@Override
	void createNewEntity() {
		entity = new Driver();
		
	}

	@Override
	void loadAllItems() {
			entityList = DBManager.getInstance().loadAllDriver2();
	}
	
//	@Override
//	void saveData(){
//		DBManager.getInstance().saveOrUpdate(entity);
//		
//	}

	@Override
	String getName() {
		return Util.getString("driver.list");
	}



	@Override
	String getWizardTitle() {
		return Util.getString("driver");
	}

	@Override
	void deleteEntity(Object object) {
		DBManager.getInstance().deleteDriver(object);
		
	}

}


