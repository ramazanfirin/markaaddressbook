package widgets;

import model.DBManager;
import model.model.Muavin;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;

import util.Util;

public class MuavinTabItem extends PersonTabItem{

	public MuavinTabItem(CTabFolder parent, String name) {
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
		entity = new Muavin();
		
	}

	@Override
	void loadAllItems() {
		entityList = DBManager.getInstance().loadAllMuavin();
       	
	}

	@Override
	String getName() {
		return Util.getString("muavin.list");
	}

	@Override
	String getWizardTitle() {
		return Util.getString("muavin");
	}

	@Override
	void deleteEntity(Object object) {
		DBManager.getInstance().deleteHost(object);
		
	}
}
