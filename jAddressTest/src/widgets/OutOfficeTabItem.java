package widgets;

import model.model.OutLocation;
import model.model.OutOffice;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.OutLocationWizard;

public class OutOfficeTabItem extends BasicTabItem{

	private static final String[] columnNames = {
		Util.getString("outOffice.firstAuthorization.person"),
		Util.getString("outOffice.secondAuthorization.person"),
		Util.getString("phone"),
		};
	
	Text textDriverName;
	Text textDriverSurname;
	Text textDriverPhone;
			
	
	public OutOfficeTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void search() {
		//super.search();
		
	}

	@Override
	void prepareComponents(Composite grpLocation) {
		//super.prepareComponents(grpLocation);
		Label labelDriverName=new Label(grpLocation,SWT.NONE);
		labelDriverName.setText(Util.getString("name"));
		labelDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textDriverName=new Text(grpLocation,SWT.BORDER);
		textDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelDriverSurname=new Label(grpLocation,SWT.NONE);
		labelDriverSurname.setText(Util.getString("surname"));
		labelDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textDriverSurname=new Text(grpLocation,SWT.BORDER);
		textDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelDriverPhone=new Label(grpLocation,SWT.NONE);
		labelDriverPhone.setText(Util.getString("phone"));
		labelDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textDriverPhone=new Text(grpLocation,SWT.BORDER);
		textDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		textDriverPhone.setTextLimit(10);
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
		entityList = Util.getApplicationInstance().getDataProvider().loadAllOutOffice();
       	
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
	      return ae.getAllPhone();
	    
			
	    }
	    return "";
		
	}

	@Override
	String[] getColumNames() {
		return columnNames;
	}
}
