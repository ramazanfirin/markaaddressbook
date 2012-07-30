package widgets;

import model.DBManager;
import model.model.BusOwner;
import model.model.Host;
import model.model.User;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.UserEntityWizard;

public class BusOwnerTabItem extends PersonTabItem{

	public BusOwnerTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void prepareComponents(Composite grpLocation) {
		super.prepareComponents(grpLocation);
		
	}

	@Override
	void createNewEntity() {
		entity = new BusOwner();
		
	}

	@Override
	void loadData() {
		entityList = DBManager.getInstance().loadBusOwners();
		
	}

	@Override
	String getName() {
		return Util.getString("busOwner.list");
	}

	

}
