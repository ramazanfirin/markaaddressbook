package wizards;

import java.util.List;
import java.util.Set;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.LogClass;
import util.Util;

class BusPage extends BasicPage{
  
  private Text plate;

   private Text phone;
   
   private Text shortCode;

   private ISelection selection;

   ComboViewer viewer;
   ComboViewer viewer2;
   ComboViewer viewer3;
   ComboViewer viewer4;
   ComboViewer viewer5;
   ComboViewer viewer6;
   ComboViewer viewer7;
   
   AbsractInterface entity;
   
  public BusPage(ISelection selection,AbsractInterface _entity,String title) {
    super(selection,"wizardPage",title);
    entity = _entity;
  }

  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    layout.verticalSpacing = 9;

    Bus bus = (Bus)entity;
    
    Label label = new Label(container, SWT.NULL);
    label.setText(Util.getString("bus.plate"));
    plate = new Text(container, SWT.BORDER | SWT.SINGLE);
    plate.setText(bus.getPlate());
    plate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    plate.addModifyListener(textModifyListener);
    plate.setEditable(Util.isAdmin());
    plate.addVerifyListener(whiteSpaceVerifyListener);
    

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("bus.phoneNumber"));
    phone = new Text(container, SWT.BORDER | SWT.SINGLE);
    phone.setText(bus.getPhone());
    phone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    phone.addModifyListener(textModifyListener);
    phone.setEditable(Util.isAdmin());
    phone.addVerifyListener(digitVerifyListener);
    phone.setTextLimit(10);

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("bus.shortCode"));
    shortCode = new Text(container, SWT.BORDER | SWT.SINGLE);
    shortCode.setText(bus.getShortCode());
    shortCode.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    shortCode.addModifyListener(textModifyListener);
    shortCode.setEditable(Util.isAdmin());
      
    createLine(container, layout.numColumns);
    
    List driverList=null;
	try {
		driverList = Util.getApplicationInstance().getDataProvider().loadAllDriver2();
		 driverList.add(0,Util.getString("select"));
	} catch (Exception e1) {
		MessageDialog.openError(this.getShell(), "Hata", e1.getMessage());
		e1.printStackTrace();
		LogClass.logger.error("Error", e1);
	}
   
    
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.first"));
    viewer = new ComboViewer(container, SWT.READ_ONLY);
    viewer.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer.getCombo().setEnabled(Util.isAdmin());
    viewer.setContentProvider(new ArrayContentProvider());
    viewer.setLabelProvider(personLabelProvider);
    viewer.setInput(driverList);
    viewer.addSelectionChangedListener(comboSelectionChangeProvider); 
    if(bus.getFirstDriver()!=null)
    	viewer.getCombo().setText(bus.getFirstDriver().getNameSurname());
    else
    	viewer.getCombo().setText(Util.getString("select")); 
    
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.second"));
    viewer2 = new ComboViewer(container, SWT.READ_ONLY);
    viewer2.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer2.getCombo().setEnabled(Util.isAdmin());
    viewer2.setContentProvider(new ArrayContentProvider());
    viewer2.setLabelProvider(personLabelProvider);
    viewer2.setInput(driverList);
    viewer2.addSelectionChangedListener(comboSelectionChangeProvider); 
    if(bus.getSecondDriver()!=null)
    	viewer2.getCombo().setText(bus.getSecondDriver().getNameSurname());
    else
    	viewer2.getCombo().setText(Util.getString("select")); 
    
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.third"));
    
    viewer3 = new ComboViewer(container, SWT.READ_ONLY);
    viewer3.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer3.getCombo().setEnabled(Util.isAdmin());
    viewer3.setContentProvider(new ArrayContentProvider());
    viewer3.setLabelProvider(personLabelProvider);
    viewer3.setInput(driverList);
    viewer3.addSelectionChangedListener(comboSelectionChangeProvider); 
    if(bus.getThirdDriver()!=null)
    	viewer3.getCombo().setText(bus.getThirdDriver().getNameSurname());
    else
    	viewer3.getCombo().setText(Util.getString("select")); 
    
    createLine(container, layout.numColumns);
    
    List hostList=null;
	try {
		hostList = Util.getApplicationInstance().getDataProvider().loadHosts();
		hostList.add(0,Util.getString("select"));
	} catch (Exception e1) {
		MessageDialog.openError(this.getShell(), "Hata", e1.getMessage());
		e1.printStackTrace();
		LogClass.logger.error("Error", e1);
	}
    
    
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("host"));
    viewer4 = new ComboViewer(container, SWT.READ_ONLY);
    viewer4.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer4.getCombo().setEnabled(Util.isAdmin());
    viewer4.setContentProvider(new ArrayContentProvider());
    viewer4.setLabelProvider(personLabelProvider);
    viewer4.setInput(hostList);
    viewer4.addSelectionChangedListener(comboSelectionChangeProvider); 
    if(bus.getHost()!=null)
    	viewer4.getCombo().setText(bus.getHost().getNameSurname());
    else
    	viewer4.getCombo().setText(Util.getString("select")); 
    
    
    List muavinList=null;
	try {
		muavinList = Util.getApplicationInstance().getDataProvider().loadAllMuavin();
		muavinList.add(0,Util.getString("select"));
	} catch (Exception e1) {
		MessageDialog.openError(this.getShell(), "Hata", e1.getMessage());
		e1.printStackTrace();
		LogClass.logger.error("Error", e1);
	}
    
    
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("muavin"));
    viewer7 = new ComboViewer(container, SWT.READ_ONLY);
    viewer7.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer7.getCombo().setEnabled(Util.isAdmin());
    viewer7.setContentProvider(new ArrayContentProvider());
    viewer7.setLabelProvider(personLabelProvider);
    viewer7.setInput(muavinList);
    viewer7.addSelectionChangedListener(comboSelectionChangeProvider); 
    if(bus.getMuavin()!=null)
    	viewer7.getCombo().setText(bus.getMuavin().getNameSurname());
    else
    	viewer7.getCombo().setText(Util.getString("select")); 
    
    
  
    createLine(container, layout.numColumns);
    
    List ownerList=null;
	try {
		ownerList = Util.getApplicationInstance().getDataProvider().loadBusOwners();
		ownerList.add(0,Util.getString("select"));
	} catch (Exception e1) {
		MessageDialog.openError(this.getShell(), "Hata", e1.getMessage());
		e1.printStackTrace();
		LogClass.logger.error("Error", e1);
	}
    
    
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("busOwner.first"));
    viewer5 = new ComboViewer(container, SWT.READ_ONLY);
    viewer5.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer5.getCombo().setEnabled(Util.isAdmin());
    viewer5.setContentProvider(new ArrayContentProvider());
    viewer5.setLabelProvider(personLabelProvider);
    viewer5.setInput(ownerList);
    viewer5.addSelectionChangedListener(comboSelectionChangeProvider); 
    if(bus.getFirstOwner()!=null)
    	viewer5.getCombo().setText(bus.getFirstOwner().getNameSurname());
    else
    	viewer5.getCombo().setText(Util.getString("select")); 
    
   
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("busOwner.second"));
    viewer6 = new ComboViewer(container, SWT.READ_ONLY);
    viewer6.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer6.getCombo().setEnabled(Util.isAdmin());
    viewer6.setContentProvider(new ArrayContentProvider());
    viewer6.setLabelProvider(personLabelProvider);
    viewer6.setInput(ownerList);
    viewer6.addSelectionChangedListener(comboSelectionChangeProvider); 
    if(bus.getSecondOwner()!=null)
    	viewer6.getCombo().setText(bus.getSecondOwner().getNameSurname());
    else
    	viewer6.getCombo().setText(Util.getString("select")); 
    
    
    
    
    setControl(container);
    setPageComplete(false);
    dialogChanged();
    
  }

  public String getDriverName(Set<Driver> driverList){
	  if (driverList == null || driverList.size()==0)
		  return "";
	  
      if(driverList.size()>0){
    	  Driver driver = (Driver)driverList.toArray()[0];
    	  return driver.getNameSurname();
      }	  
    
      return "";
  }
  
  public boolean dialogChanged() {
    if (this.plate.getText().length() == 0) {
      updateStatus("Plaka alaninin girilmesi gereklidir.");
      return false;
    }

    if (this.phone.getText().length() == 0) {
      updateStatus("Telefon alanin girilmesi gereklidir.");
      return false;
    }

    updateStatus(null);
    setPageComplete(true);
    return true;
  }  
  
  
  public void updateStatus(String message) {
	    setErrorMessage(message);
	    //setPageComplete(message == null);
	  }

public Text getNameVariable() {
	return plate;
}

public void setNameVariable(Text name) {
	this.plate = name;
}


public Text getPhone() {
	return phone;
}

public void setPhone(Text phone) {
	this.phone = phone;
}

public ISelection getSelection() {
	return selection;
}

public void setSelection(ISelection selection) {
	this.selection = selection;
}

public Text getPlate() {
	return plate;
}

public void setPlate(Text plate) {
	this.plate = plate;
}

public ComboViewer getViewer() {
	return viewer;
}

public void setViewer(ComboViewer viewer) {
	this.viewer = viewer;
}

public ComboViewer getViewer2() {
	return viewer2;
}

public void setViewer2(ComboViewer viewer2) {
	this.viewer2 = viewer2;
}

public ComboViewer getViewer3() {
	return viewer3;
}

public void setViewer3(ComboViewer viewer3) {
	this.viewer3 = viewer3;
}

public ComboViewer getViewer4() {
	return viewer4;
}

public void setViewer4(ComboViewer viewer4) {
	this.viewer4 = viewer4;
}

public ComboViewer getViewer5() {
	return viewer5;
}

public void setViewer5(ComboViewer viewer5) {
	this.viewer5 = viewer5;
}

public ComboViewer getViewer6() {
	return viewer6;
}

public void setViewer6(ComboViewer viewer6) {
	this.viewer6 = viewer6;
}

public ComboViewer getViewer7() {
	return viewer7;
}

public void setViewer7(ComboViewer viewer7) {
	this.viewer7 = viewer7;
}

public Text getShortCode() {
	return shortCode;
}

public void setShortCode(Text shortCode) {
	this.shortCode = shortCode;
}



}
  
