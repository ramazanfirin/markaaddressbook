package wizards;

import model.DBManager;
import model.model.Driver;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;

import com.AddressBook;


public class DriverEntityWizard extends Wizard {
  private BasicDriverPage page1;

  private AddressContactPage page2;

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

	 Driver driver = AddressBook.getInstance().getDriver();
	 driver.setName(page1.getNameVariable().getText());
	 driver.setSurname(page1.getSurname().getText());
	 driver.setPhone(page1.getPhone().getText());
	  
	// DBManager.getInstance().saveOrUpdate(driver);
	  
    return true;
  }

public Driver getDriver() {
	return driver;
}

public void setDriver(Driver driver) {
	this.driver = driver;
}
}