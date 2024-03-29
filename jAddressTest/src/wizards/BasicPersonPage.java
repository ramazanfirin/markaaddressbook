package wizards;

import java.util.List;

import model.model.Authority;
import model.model.Bus;
import model.model.User;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

import com.AddressBookNew;

abstract class  BasicPersonPage extends BasicPage {
  
	 protected Text _name;

	 protected Text surname;

	 protected Text phone;

	 protected ComboViewer city;
	 
	 protected Text address; 
	 
	 private ISelection selection;
 
    protected String title;
  
  ComboViewer viewer;

  protected Text phoneSecond;

  
  public BasicPersonPage(ISelection selection,String title) {
    super(selection,"wizardPage",title);
  }

  
  @Override
	public void createControl(Composite parent) {
		Composite main = new Composite(parent, SWT.NULL);
		FillLayout fillLayout = new FillLayout();
	    fillLayout.type = SWT.VERTICAL;
	  	main.setLayout(fillLayout);
  	

//	  	RowLayout layout2 = new RowLayout(SWT.VERTICAL);
//		layout2.wrap = false;
//		layout2.fill = true;
//		layout2.justify = false;
//		main.setLayout(layout2);
//		
	  	createMainComponent(main);
		createTable(main);
		createOtherComponent(main);
	
	    dialogChanged();
	    setControl(main);
	}

	
	public abstract void createMainComponent(Composite composite);
	public abstract void createTable(Composite composite);
	public abstract void createOtherComponent(Composite composite);




  
	public boolean dialogChanged() {
		
		 
		if (this._name.getText().length() == 0) {
      updateStatus("Isim alani zorunludur");
      return false;
    }else
      updateStatus(null);	 
    
    
    if (this.surname.getText().length() == 0) {
      updateStatus("Soyisim alani zorunludur.");
      return false;
    } else 
    	updateStatus(null);	
    	
    if (this.phone.getText().length() == 0) {
        updateStatus("Telefon alani zorunludur.");
        return false;
      } else 
      	updateStatus(null);	
    
    if (this.phone.getText().length() < 10) {
        updateStatus("Telefon alani 10 hane olmalidir.Ornek : 5551234567");
        return false;
      } else 
      	updateStatus(null);	
    
    
    if (this.phoneSecond.getText().length() < 10 && this.phoneSecond.getText().length() > 0) {
        updateStatus("ikinci Telefon alani 10 hane olmalidir.Ornek : 5551234567");
        return false;
      } else 
      	updateStatus(null);	

    updateStatus(null);
    setPageComplete(true);
    
    return true;
    
   }  
  


  
  public boolean canFlipToNextPage(){
	  System.out.println("geldi"); 
	  if (getErrorMessage() != null) 
		   return false;
	  
	  return dialogChanged();
	}
  
  
 public void updateStatus(String message) {
	    setErrorMessage(message);
	    setPageComplete(message == null);
	  }


public ISelection getSelection() {
	return selection;
}

public void setSelection(ISelection selection) {
	this.selection = selection;
}

public ComboViewer getViewer() {
	return viewer;
}

public void setViewer(ComboViewer viewer) {
	this.viewer = viewer;
}

public Text get_name() {
	return _name;
}

public void set_name(Text _name) {
	this._name = _name;
}

public Text getSurname() {
	return surname;
}

public void setSurname(Text surname) {
	this.surname = surname;
}

public Text getPhone() {
	return phone;
}

public void setPhone(Text phone) {
	this.phone = phone;
}


public ComboViewer getCity() {
	return city;
}


public void setCity(ComboViewer city) {
	this.city = city;
}


public Text getAddress() {
	return address;
}


public void setAddress(Text address) {
	this.address = address;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public Text getPhoneSecond() {
	return phoneSecond;
}


public void setPhoneSecond(Text phoneSecond) {
	this.phoneSecond = phoneSecond;
}



  
}