package wizards;

import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;

import com.AddressBookNew;


public class DriverEntityWizard extends Wizard {
  private BasicDriverPage page1;

  private ISelection selection;
  
  private Driver driver;

  public DriverEntityWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new BasicDriverPage(selection);
    //page2 = new AddressContactPage(selection);
    addPage(page1);
    //addPage(page2);
    
    System.out.println("test");
    
  }

  public boolean performFinish() {

	 Driver driver = (Driver)AddressBookNew.getInstance().getTabItemDriver().getEntity();
	 driver.setName(page1.getNameVariable().getText());
	 driver.setSurname(page1.getSurname().getText());
	 driver.setPhone(page1.getPhone().getText());
	  
//	 IStructuredSelection selection = (IStructuredSelection)page1.getViewer().getSelection();
//	 if(selection.isEmpty())
//		 driver.setBus(null);
//	 else
//		 driver.setBus((Bus)selection.getFirstElement());
//	// DBManager.getInstance().saveOrUpdate(driver);
	  
    return true;
   // return false;
  }

public Driver getDriver() {
	return driver;
}

public void setDriver(Driver driver) {
	this.driver = driver;
}
}