package widgets;

import model.model.Host;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;

import util.Util;

public class HostTabItem extends PersonTabItem{

	public HostTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void search() throws Exception{
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
	void loadAllItems() throws Exception{
		entityList = Util.getApplicationInstance().getDataProvider().loadHosts();
       	
	}

	@Override
	String getName() {
		return Util.getString("host.list");
	}

	@Override
	String getWizardTitle() {
		return Util.getString("host");
	}

	@Override
	void deleteEntity(Object object) throws Exception {
		Util.getApplicationInstance().getDataProvider().deleteHost(object);
		
		
	}

	

}
