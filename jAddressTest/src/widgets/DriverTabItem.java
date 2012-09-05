package widgets;

import model.model.Driver;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.custom.CTabFolder;

import util.Util;
import wizards.DriverEntityWizard;

public class DriverTabItem extends PersonTabItem{

	public DriverTabItem(CTabFolder parent, String name) {
		super(parent, name);
	}

    
	
	@Override
	void search() throws Exception {
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
	void loadAllItems() throws Exception{
			entityList = Util.getApplicationInstance().getDataProvider().loadAllDriver2();
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
	void deleteEntity(Object object) throws Exception{
		Util.getApplicationInstance().getDataProvider().deleteDriver(object);
		
	}

}


