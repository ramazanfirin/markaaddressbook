package widgets;

import model.DBManager;
import model.model.Person;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.PersonEntityWizard;

public abstract class PersonTabItem extends BasicTabItem{

	private static final String[] columnNames = {
		Util.getString("driver.name"),
		Util.getString("driver.surname"),
		Util.getString("driver.phoneNumber"),
		};
	
	Text textDriverName;
	Text textDriverSurname;
	Text textDriverPhone;
			
	public PersonTabItem(CTabFolder parent, String name) {
		super(parent, name);
	}

	
	@Override
	void search() {
		if(entity == null)
			createNewEntity();
		entityList = DBManager.getInstance().searchEntiy(entity.getClass(), textDriverName.getText(), textDriverSurname.getText(), textDriverPhone.getText());		
		refresh();
	}

	@Override
	void prepareComponents(Composite grpLocation) {
			
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
	String[] getColumNames() {
		return columnNames;
	}


	@Override
	Wizard getNewWizard() {
		return new PersonEntityWizard(entity,getWizardTitle());
	}

	
	

	@Override
	void saveData() {
		DBManager.getInstance().saveOrUpdate(entity);
		
	}
	
	@Override
	String getTableColumValues(Object object, int columnIndex) {
		Person ae = (Person) object;
	    switch (columnIndex) {
	    case 0:
	      return ae.getName();
	    case 1:
	      return ae.getSurname();
	    case 2:
	      return ae.getFormattedPhone();
	    
			
	    }
	    return "";
		
	}


	@Override
	void createNewEntity() {
		// TODO Auto-generated method stub
		
	}


	@Override
	void loadData() {
		// TODO Auto-generated method stub
		
	}




}


