package widgets;

import java.util.List;

import model.model.City;
import model.model.OutLocation;
import model.model.OutOffice;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.LogClass;
import util.Util;
import wizards.OutLocationWizard;

public class OutOfficeTabItem extends BasicTabItem{

	private static final String[] columnNames = {
		Util.getString("name"),
		Util.getString("outOffice.firstAuthorization.person"),
		Util.getString("outOffice.secondAuthorization.person"),
		Util.getString("phone"),
		};
	
	Text textDriverName;
	 ComboViewer city;
			
	
	public OutOfficeTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void search() throws Exception{
		IStructuredSelection selection = (IStructuredSelection)city.getSelection();
	    String cityId="";
		if(selection.isEmpty() || selection.getFirstElement() instanceof String){
			cityId="";
		  }else{
			  City city = (City)selection.getFirstElement();
			  cityId=city.getId().toString();
		  }
		
		entityList =Util.getApplicationInstance().getDataProvider().searchOutOffice(textDriverName.getText(), cityId);
		refresh();
	}

	@Override
	void prepareComponents(Composite grpLocation) {
		//super.prepareComponents(grpLocation);
		Label labelDriverName=new Label(grpLocation,SWT.NONE);
		labelDriverName.setText(Util.getString("name"));
		labelDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textDriverName=new Text(grpLocation,SWT.BORDER);
		textDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		List cityList= null;
		try {
			cityList = Util.getApplicationInstance().getDataProvider()
					.loadCities();
			cityList.add(0, Util.getString("select"));
		} catch (Exception e1) {
			MessageDialog.openError(shell, "Hata", e1.getMessage());
			e1.printStackTrace();
			LogClass.logger.error("Error", e1);
		}
		

		Label label = null;
		label = new Label(grpLocation, SWT.NULL);
		label.setText(Util.getString("city"));
		city = new ComboViewer(grpLocation, SWT.READ_ONLY);
		city.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		city.setContentProvider(new ArrayContentProvider());
		city.setLabelProvider(Util.getApplicationInstance().cityLabelProvider);
		city.setInput(cityList);
		city.getCombo().setText(Util.getString("select"));
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
	void loadAllItems() throws Exception{
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
		      return ae.getName();
	    case 1:
	      return ae.getFirstAuthorizedPerson().getNameSurnamePhone();
	    case 2:
	      return ae.getSecondAuthorizedPerson().getNameSurnamePhone();
	    case 3:
	      return ae.getAllPhone();
	    
			
	    }
	    return "";
		
	}

	@Override
	String[] getColumNames() {
		return columnNames;
	}
}
