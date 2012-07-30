package widgets;

import model.DBManager;
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

public class UserTabItem extends PersonTabItem{

	private static final String[] columnNames = {
		Util.getString("name"),
		Util.getString("surname"),
		Util.getString("phone"),
		Util.getString("user.name"),
		Util.getString("user.authority")};
	
	
	public UserTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void prepareComponents(Composite grpLocation) {
		super.prepareComponents(grpLocation);
		
		Label labelAuthority=new Label(grpLocation,SWT.NONE);
		labelAuthority.setText(Util.getString("user.authority"));
		labelAuthority.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		Text textAuthority=new Text(grpLocation,SWT.BORDER);
		textAuthority.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
	}

	@Override
	String[] getColumNames() {
		return columnNames;
	}

	@Override
	Wizard getNewWizard() {
		return new UserEntityWizard(entity);
	}

	@Override
	void createNewEntity() {
		entity = new User();
		
	}

	@Override
	void loadData() {
		entityList = DBManager.getInstance().loadUsers();
		
	}

	@Override
	String getTableColumValues(Object object, int columnIndex) {
		User ae = (User) object;
	    switch (columnIndex) {
	    case 0:
	    	return ae.getName();
	    case 1:
	    	return ae.getSurname();
	    case 2:
	    	return ae.getPhone();
	    case 3:
	      return ae.getUsername();
	    case 4:
		  return ae.getAuthority().getAuthority();   
	    }
	    return "";
	}

	@Override
	void saveData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	String getName() {
		return Util.getString("user.list");
	}

}
