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
		 Util.getString("bus.phoneNumber"),
 	     Util.getString("driver.first.nameSurname"),
 	     Util.getString("driver.second.nameSurname"),
	     Util.getString("driver.third.nameSurname")
	   	};
	
	
	Text textBusPlate;
	Text textHostName;
	Text textBusPhone;
	Text textHostSurname;
	Text textDriverName;
	Text textOwnerName;
	Text textDriverSurname;
	Text textOwnerSurname;
	
			
	public BusTabItem(CTabFolder parent, String name) {
		super(parent, name);
	}

	
	@Override
	void search() {
		entityList =DBManager.getInstance().searchBus(textBusPlate.getText(), textBusPhone.getText(), textDriverName.getText(), textDriverSurname.getText(), 
				textHostName.getText(), textHostSurname.getText(), textOwnerName.getText(), textOwnerSurname.getText());
		refresh();
	}

	@Override
	void prepareComponents(Composite grpLocationBus) {
		

		 GridLayout grpLayout = new GridLayout(4, false);
		    grpLayout.verticalSpacing = 0;
		    grpLocation.setLayout(grpLayout);
		    
		
		Label labelBusPlate=new Label(grpLocationBus,SWT.NONE);
	    labelBusPlate.setText(Util.getString("bus.plate"));
	    labelBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textBusPlate=new Text(grpLocationBus,SWT.BORDER);
		textBusPlate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelHostName=new Label(grpLocationBus,SWT.NONE);
		labelHostName.setText(Util.getString("host.name"));
		labelHostName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textHostName=new Text(grpLocationBus,SWT.BORDER);
		textHostName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelBusPhone=new Label(grpLocationBus,SWT.NONE);
		labelBusPhone.setText(Util.getString("bus.phone"));
		labelBusPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textBusPhone=new Text(grpLocationBus,SWT.BORDER);
		textBusPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelHostSurname=new Label(grpLocationBus,SWT.NONE);
		labelHostSurname.setText(Util.getString("host.surname"));
		labelHostSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textHostSurname=new Text(grpLocationBus,SWT.BORDER);
		textHostSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		
		Label labelDriverName=new Label(grpLocationBus,SWT.NONE);
		labelDriverName.setText(Util.getString("driver.name"));
		labelDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textDriverName=new Text(grpLocationBus,SWT.BORDER);
		textDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelOwnerName=new Label(grpLocationBus,SWT.NONE);
		labelOwnerName.setText(Util.getString("busOwner.name"));
		labelOwnerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textOwnerName=new Text(grpLocationBus,SWT.BORDER);
		textOwnerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelDriverSurname=new Label(grpLocationBus,SWT.NONE);
		labelDriverSurname.setText(Util.getString("driver.surname"));
		labelDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textDriverSurname=new Text(grpLocationBus,SWT.BORDER);
		textDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label labelOwnerSurname=new Label(grpLocationBus,SWT.NONE);
		labelOwnerSurname.setText(Util.getString("busOwner.surname"));
		labelOwnerSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		
		textOwnerSurname=new Text(grpLocationBus,SWT.BORDER);
		textOwnerSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
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
		entityList = DBManager.getInstance().loadAllBus2();
	}
	
	@Override
	String getTableColumValues(Object object, int columnIndex) {
		Bus ae = (Bus) object;
	    switch (columnIndex) {
	    case 0:
	      return ae.getPlate();
	    case 1:
	      return ae.getFormattedPhone();
	    case 2:
	       if(ae.getFirstDriver()!=null)
	    	   return ae.getFirstDriver().getNameSurname();
	       else
	    	   return "";
	    
	    case 3:
		       if(ae.getSecondDriver()!=null)
		    	   return ae.getSecondDriver().getNameSurname();
		       else
		    	   return "";
		
		case 4:
		       if(ae.getThirdDriver()!=null)
		    	   return ae.getThirdDriver().getNameSurname();
		       else
		    	   return "";
		
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
		DBManager.getInstance().delete(object);
		
	}

}


