package wizards;

import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.AddressBookNew;

import util.Util;

class BasicBusPage extends WizardPage {
  
  private Text plate;

   private Text phone;

   private ISelection selection;

  

  
  public BasicBusPage(ISelection selection) {
    super("wizardPage");
    setTitle(Util.getString("bus"));
    setDescription("This wizard creates a new bus.");
    this.selection = selection;
  }

  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    layout.verticalSpacing = 9;

    Bus bus = (Bus)AddressBookNew.getInstance().getTabItemBus().getEntity();
    
    Label label = new Label(container, SWT.NULL);
    label.setText(Util.getString("bus.plate"));

    plate = new Text(container, SWT.BORDER | SWT.SINGLE);
    plate.setText(bus.getPlate());
    plate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    plate.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("bus.phoneNumber"));

    phone = new Text(container, SWT.BORDER | SWT.SINGLE);
    phone.setText(bus.getPhone());
    phone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    phone.addModifyListener(new ModifyListener() {
        public void modifyText(ModifyEvent e) {
          dialogChanged();
        }
      });

    createLine(container, layout.numColumns);
    setPageComplete(false);
    dialogChanged();
    setControl(container);
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


  
}