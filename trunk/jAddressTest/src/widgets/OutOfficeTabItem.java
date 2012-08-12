package widgets;

import model.DBManager;
import model.model.Muavin;
import model.model.OutLocation;
import model.model.OutOffice;
import model.model.Person;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;

import util.Util;
import wizards.OutLocationWizard;
import wizards.PersonEntityWizard;

public class OutOfficeTabItem extends PersonTabItem{

	public OutOfficeTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void search() {
		super.search();
		
	}

	@Override
	void prepareComponents(Composite grpLocation) {
		super.prepareComponents(grpLocation);
		
	}
	
	@Override
	Wizard getNewWizard() {
		return new OutLocationWizard(entity,getWizardTitle());
	}

	@Override
	void createNewEntity() {
		entity = new OutOffice();
		
	}

	@Override
	void loadAllItems() {
		entityList = DBManager.getInstance().loadAllOutOffice();
       	
	}

	@Override
	String getName() {
		return Util.getString("outOffice.list");
	}

	@Override
	String getWizardTitle() {
		return Util.getString("outOffice");
	}

	@Override
	void deleteEntity(Object object) {
		//DBManager.getInstance().deleteHost(object);
		
	}
	
	@Override
	String getTableColumValues(Object object, int columnIndex) {
		OutLocation ae = (OutLocation) object;
	    switch (columnIndex) {
	    case 0:
	      return ae.getFirstAuthorizedPerson().getNameSurnamePhone();
	    case 1:
	      return ae.getSecondAuthorizedPerson().getNameSurnamePhone();
	    case 2:
	      return ae.getNote();
	    
			
	    }
	    return "";
		
	}
}
