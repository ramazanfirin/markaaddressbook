package widgets;

import java.util.List;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.City;
import model.model.OutLocation;
import model.model.Person;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.BusEntityWizard;

public class SearchTabItem extends BasicTabItem{

	private static final String[] columnNames = {
		 Util.getString("name"),
		 Util.getString("surname"),
		 Util.getString("phone"),
		 Util.getString("mission"),
	};
	
	Text name;
	Text surname;
	Text textBusPlate;
	Text textShortCode;
	
	Text outOfficeNameText;
	Text serviceAreaNameText;
	
	ComboViewer outOfficeCity;
	ComboViewer serviceAreaCity;
	
	String outOfficecityId;
	String serviceAreaCityId="";
	
	
	public SearchTabItem(CTabFolder parent, String name) {
		super(parent, name);
	}

	
	@Override
	void search() {
//		entityList =DBManager.getInstance().searchBus(textBusPlate.getText(), textBusPhone.getText(), textDriverName.getText(), textDriverSurname.getText(), 
//				textHostName.getText(), textHostSurname.getText(), textOwnerName.getText(), textOwnerSurname.getText());
	  
		IStructuredSelection selection = (IStructuredSelection)outOfficeCity.getSelection();
	  	if(selection.isEmpty() || selection.getFirstElement() instanceof String){
			outOfficecityId="";
		  }else{
			  City city = (City)selection.getFirstElement();
			  outOfficecityId=city.getId().toString();
		  }
		
		selection = (IStructuredSelection)serviceAreaCity.getSelection();
	 	if(selection.isEmpty() || selection.getFirstElement() instanceof String){
			serviceAreaCityId="";
		  }else{
			  City city = (City)selection.getFirstElement();
			  serviceAreaCityId=city.getId().toString();
		  }
	   
		
		
		
		if(Util.isEmpty(name.getText()) && 
			   Util.isEmpty(surname.getText()) && 
			   Util.isEmpty(textBusPlate.getText()) && 
			   Util.isEmpty(textShortCode.getText()) && 
		       Util.isEmpty(outOfficeNameText.getText()) &&
		       Util.isEmpty(outOfficecityId) &&
		       Util.isEmpty(serviceAreaNameText.getText()) &&
		       Util.isEmpty(serviceAreaCityId)
			   
	      ){
		   MessageDialog.openError(this.shell, "Hata", "En az bir kriter doldurunuz");
		   return;
	   }	
		
	   
	   
	   
	   
	   try {
			entityList =Util.getApplicationInstance().getDataProvider().searchGeneral(name.getText(), surname.getText(), textBusPlate.getText(), textShortCode.getText(),
					outOfficeNameText.getText(),outOfficecityId,serviceAreaNameText.getText(),serviceAreaCityId);
							} catch (Exception e) {
			 
			e.printStackTrace();
			MessageDialog.openError(this.shell, "Hata", e.getMessage());
		}
		refresh();
	}

	@Override
	void prepareComponents(Composite grpLocationBus) {
		

		 GridLayout grpLayout = new GridLayout(4, false);
		    grpLayout.verticalSpacing = 0;
		    grpLocation.setLayout(grpLayout);
		    
		
		
		
		Label nameText=new Label(grpLocationBus,SWT.NONE);
		nameText.setText(Util.getString("person") +" "+ Util.getString("name"));
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		name=new Text(grpLocationBus,SWT.BORDER);
		name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		//************************
		
		
		Label outOfficeName=new Label(grpLocationBus,SWT.NONE);
		outOfficeName.setText(Util.getString("outOffice") +" "+ Util.getString("name") );
		outOfficeName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		outOfficeNameText=new Text(grpLocationBus,SWT.BORDER);
		outOfficeNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		//***********************
		
		Label labelSurname=new Label(grpLocationBus,SWT.NONE);
		labelSurname.setText(Util.getString("person") +" "+Util.getString("surname"));
		labelSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		surname=new Text(grpLocationBus,SWT.BORDER);
		surname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		//*********************
		Label outOfficeCityLabel=new Label(grpLocationBus,SWT.NONE);
		outOfficeCityLabel.setText(Util.getString("outOffice") +" "+ Util.getString("city") );
		outOfficeCityLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		List cityList = Util.getApplicationInstance().getDataProvider().loadCities();
		cityList.add(0, Util.getString("select"));
		
		outOfficeCity = new ComboViewer(grpLocation, SWT.READ_ONLY);
		outOfficeCity.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		outOfficeCity.getCombo().setEnabled(Util.isAdmin());
		outOfficeCity.setContentProvider(new ArrayContentProvider());
		outOfficeCity.setLabelProvider(Util.getApplicationInstance().cityLabelProvider);
		outOfficeCity.setInput(cityList);
		outOfficeCity.getCombo().setText(Util.getString("select"));
		
		Label labelBusPlate=new Label(grpLocationBus,SWT.NONE);
	    labelBusPlate.setText(Util.getString("bus.plate"));
	    labelBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textBusPlate=new Text(grpLocationBus,SWT.BORDER);
		textBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label serviceAreaName=new Label(grpLocationBus,SWT.NONE);
		serviceAreaName.setText(Util.getString("serviceArea") +" "+ Util.getString("name") );
		serviceAreaName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		serviceAreaNameText=new Text(grpLocationBus,SWT.BORDER);
		serviceAreaNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		
		Label labelBusShortCode=new Label(grpLocationBus,SWT.NONE);
		labelBusShortCode.setText(Util.getString("bus.shortCode"));
		labelBusShortCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textShortCode=new Text(grpLocationBus,SWT.BORDER);
		textShortCode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		
		
		
		
		
		
		
		
		
		
		Label serviceAreaCityLabel=new Label(grpLocationBus,SWT.NONE);
		serviceAreaCityLabel.setText(Util.getString("serviceArea") +" "+ Util.getString("city") );
		serviceAreaCityLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		serviceAreaCity = new ComboViewer(grpLocation, SWT.READ_ONLY);
		serviceAreaCity.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		serviceAreaCity.getCombo().setEnabled(Util.isAdmin());
		serviceAreaCity.setContentProvider(new ArrayContentProvider());
		serviceAreaCity.setLabelProvider(Util.getApplicationInstance().cityLabelProvider);
		serviceAreaCity.setInput(cityList);
		serviceAreaCity.getCombo().setText(Util.getString("select"));
			
	}

	@Override
	String[] getColumNames() {
		return columnNames;
	}


	@Override
	Wizard getNewWizard() {
		return new BusEntityWizard(entity,getWizardTitle());
	}

	@Override
	void createNewEntity() {
		entity = new Bus();
		
	}

	@Override
	void loadAllItems() {
		entityList = Util.getApplicationInstance().getDataProvider().loadAllBus2();
	}
	
	@Override
	String getTableColumValues(Object object, int columnIndex) {
		switch (columnIndex) {
	    case 0:
	        if(object instanceof Person)
	        	return ((Person)object).getName();
	        if(object instanceof Bus)
	        	return ((Bus)object).getPlate();
	        if(object instanceof OutLocation)
	        	return ((OutLocation)object).getName();
	    case 1:
	        if(object instanceof Person)
	        	return ((Person)object).getSurname();
	        if(object instanceof Bus)
	        	return ((Bus)object).getShortCode();
	        if(object instanceof OutLocation)
	        	return "";
	    case 2:
	        if(object instanceof Person)
	        	return ((Person)object).getFormattedPhone();
	        if(object instanceof Bus)
	        	return ((Bus)object).getFormattedPhone();
	        if(object instanceof OutLocation)
	        	return ((OutLocation)object).getFormattedPhone();
	   
		case 3:
			AbsractInterface s = (AbsractInterface)object;
			return s.getMission();
	    }
	    return "";
		
	}

	@Override
	String getName() {
		return Util.getString("bus.list");
	}


	@Override
	String getWizardTitle() {
		return Util.getString("bus");
	}


	@Override
	void deleteEntity(Object object) {
		Util.getApplicationInstance().getDataProvider().delete(object);
		
	}

}


