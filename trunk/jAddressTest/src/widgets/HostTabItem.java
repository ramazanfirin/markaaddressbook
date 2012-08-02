package widgets;

import model.DBManager;
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

public class HostTabItem extends PersonTabItem{

	public HostTabItem(CTabFolder parent, String name) {
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
	void createNewEntity() {
		entity = new Host();
		
	}

	@Override
	void loadData() {
		entityList = DBManager.getInstance().loadHosts();
		
	}

	@Override
	String getName() {
		return Util.getString("bus.list");
	}

	@Override
	String getWizardTitle() {
		return Util.getString("host");
	}

	@Override
	void deleteEntity(Object object) {
		DBManager.getInstance().deleteHost(object);
		
		
	}

	

}
