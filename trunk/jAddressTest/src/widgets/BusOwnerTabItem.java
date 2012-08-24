package widgets;

import model.model.BusOwner;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.BusOwnerEntityWizard;

public class BusOwnerTabItem extends PersonTabItem{

	Text shortCode;
	
	public BusOwnerTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void prepareComponents(Composite grpLocation) {
		super.prepareComponents(grpLocation);
		
		Label labelDriverPhone=new Label(grpLocation,SWT.NONE);
		labelDriverPhone.setText(Util.getString("busOwner.shortCode"));
		labelDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		shortCode=new Text(grpLocation,SWT.BORDER);
		shortCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		shortCode.setTextLimit(10);
	}

	@Override
	void createNewEntity() {
		entity = new BusOwner();
		
	}

	@Override
	Wizard getNewWizard() {
		return new BusOwnerEntityWizard(entity,getWizardTitle());
	}
	
	@Override
	void loadAllItems() {
		entityList = Util.getApplicationInstance().getDataProvider().loadBusOwners();
		
	}
	
	@Override
	String getName() {
		return Util.getString("busOwner.list");
	}

	@Override
	String getWizardTitle() {
		return Util.getString("busOwner");
	}

	@Override
	void deleteEntity(Object object) {
		Util.getApplicationInstance().getDataProvider().deleteBusOwner(object);
	}

	

}
