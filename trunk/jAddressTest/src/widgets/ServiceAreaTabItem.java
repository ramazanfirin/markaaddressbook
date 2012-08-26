package widgets;

import java.util.List;

import model.model.City;
import model.model.OutLocation;
import model.model.OutOffice;
import model.model.ServiceArea;

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

import util.Util;
import wizards.OutLocationWizard;

public class ServiceAreaTabItem extends BasicTabItem{

	private static final String[] columnNames = {
		Util.getString("name"),
		Util.getString("outOffice.firstAuthorization.person"),
		Util.getString("outOffice.secondAuthorization.person"),
		Util.getString("phone"),
		};
	
	Text textDriverName;
	 ComboViewer city;
			
	
	public ServiceAreaTabItem(CTabFolder parent, String name) {
		super(parent, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void search() {
		IStructuredSelection selection = (IStructuredSelection)city.getSelection();
	    String cityId="";
		if(selection.isEmpty() || selection.getFirstElement() instanceof String){
			cityId="";
		  }else{
			  City city = (City)selection.getFirstElement();
			  cityId=city.getId().toString();
		  }
		
		entityList =Util.getApplicationInstance().getDataProvider().searchServiceArea(textDriverName.getText(), cityId);
		refresh();
	}

	@Override
	void prepareComponents(Composite grpLocation) {
		//super.prepareComponents(grpLocation);
		//super.prepareComponents(grpLocation);
		Label labelDriverName=new Label(grpLocation,SWT.NONE);
		labelDriverName.setText(Util.getString("name"));
		labelDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textDriverName=new Text(grpLocation,SWT.BORDER);
		textDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		List cityList = Util.getApplicationInstance().getDataProvider()
				.loadCities();
		cityList.add(0, Util.getString("select"));

		Label label = null;
		label = new Label(grpLocation, SWT.NULL);
		label.setText(Util.getString("city"));
		city = new ComboViewer(grpLocation, SWT.READ_ONLY);
		city.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		city.getCombo().setEnabled(Util.isAdmin());
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
		entity = new ServiceArea();
		
	}

	@Override
	void loadAllItems() {
		entityList = Util.getApplicationInstance().getDataProvider().loadAllServiceArea();
       	
	}

	@Override
	String getName() {
		return Util.getString("serviceArea.list");
	}

	@Override
	String getWizardTitle() {
		return Util.getString("serviceArea");
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
