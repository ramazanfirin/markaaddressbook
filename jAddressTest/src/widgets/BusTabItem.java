package widgets;

import model.DBManager;
import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.BusEntityWizard;

public class BusTabItem extends BasicTabItem{

	private static final String[] columnNames = {
		 Util.getString("bus.plate"),
		 Util.getString("bus.phoneNumber")};
	
			
	public BusTabItem(CTabFolder parent, String name) {
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
	void prepareComponents(Composite grpLocationBus) {
			
	    Label labelBusPlate=new Label(grpLocationBus,SWT.NONE);
	    labelBusPlate.setText(Util.getString("bus.plate"));
	    labelBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		Text textBusPlate=new Text(grpLocationBus,SWT.BORDER);
		textBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelBusPhone=new Label(grpLocationBus,SWT.NONE);
		labelBusPhone.setText(Util.getString("bus.phone"));
		labelBusPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		Text textBusPhone=new Text(grpLocationBus,SWT.BORDER);
		textBusPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
	}

	@Override
	String[] getColumNames() {
		return columnNames;
	}


	@Override
	Wizard getNewWizard() {
		return new BusEntityWizard();
	}

	@Override
	void createNewEntity() {
		entity = new Bus();
		
	}

	@Override
	void loadData() {
		try {
			entityList = DBManager.getInstance().loadAllBus2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	String getTableColumValues(Object object, int columnIndex) {
		Bus ae = (Bus) object;
	    switch (columnIndex) {
	    case 0:
	      return ae.getPlate();
	    case 1:
	      return ae.getPhone();
	    }
	    return "";
		
	}

}


