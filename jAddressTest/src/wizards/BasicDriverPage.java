package wizards;

import model.model.Driver;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

import com.AddressBook;
import com.AddressBookNew;

class BasicDriverPage extends WizardPage {
  
  private Text _name;

  private Text surname;

  private Text phone;

  private Text emailText;
 
  private ISelection selection;

  

  
  public BasicDriverPage(ISelection selection) {
    super("wizardPage");
    setTitle(Util.getString("driver"));
    setDescription("This wizard creates a new contact.");
    this.selection = selection;
    setPageComplete(false);
  }

  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    layout.verticalSpacing = 9;

    
    Driver driver = (Driver)AddressBookNew.getInstance().getTabItemDriver().getEntity();
    
    Label label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.name"));

    _name = new Text(container, SWT.BORDER | SWT.SINGLE);
    _name.setText(driver.getName());

    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    _name.setLayoutData(gd);
    _name.addKeyListener(new KeyListener() {

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void keyReleased(KeyEvent e) {
			if (!_name.getText().isEmpty()) {
				dialogChanged();
			}
		}

	});

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.surname"));

    surname = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    surname.setLayoutData(gd);
    surname.setText(driver.getSurname());
    
    surname.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.phoneNumber"));

    phone = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    phone.setLayoutData(gd);
    phone.setText(driver.getPhone());
    createLine(container, layout.numColumns);

    label = new Label(container, SWT.NULL);
    label.setText("&E-Mail Address:");

    emailText = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    emailText.setLayoutData(gd);
    emailText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });

    dialogChanged();
    setControl(container);
  // super.
  }



  
  public boolean dialogChanged() {
    if (this._name.getText().length() == 0) {
      updateStatus("Sofor ismi zorunludur");
      return false;
    }else
      updateStatus(null);	 
    
    
    if (this.surname.getText().length() == 0) {
      updateStatus("Sofor soyisim zorunludur.");
      return false;
    } else 
    	updateStatus(null);	
    	
    if (this.emailText.getText().length() > 0) {
      if (this.emailText.getText().indexOf("@") < 0) {
        updateStatus("Please enter a complete email address in the form yourname@yourdomain.com");
        return false;
      }
    }

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
  
  
  
  private void createLine(Composite parent, int ncol) {
	    Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
	        | SWT.BOLD);
	    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
	    gridData.horizontalSpan = ncol;
	    line.setLayoutData(gridData);
	  }
  private void updateStatus(String message) {
	    setErrorMessage(message);
	    //setPageComplete(message == null);
	  }

public Text getNameVariable() {
	return _name;
}

public void setNameVariable(Text name) {
	this._name = name;
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

public Text getEmailText() {
	return emailText;
}

public void setEmailText(Text emailText) {
	this.emailText = emailText;
}

public ISelection getSelection() {
	return selection;
}

public void setSelection(ISelection selection) {
	this.selection = selection;
}


  
}