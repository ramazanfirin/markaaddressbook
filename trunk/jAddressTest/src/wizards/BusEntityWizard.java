package wizards;

import java.util.HashSet;

import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;

import com.AddressBook;
import com.AddressBookNew;


public class BusEntityWizard extends Wizard {
  
  private BasicBusPage page1;

  private ISelection selection;
  
  private Driver tempDriver;

  public BusEntityWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new BasicBusPage(selection);
    //page2 = new AddressContactPage(selection);
    addPage(page1);
    //addPage(page2);
    
    System.out.println("test");
  }

  public boolean performFinish() {

	  Bus bus = (Bus)AddressBookNew.getInstance().getTabItemBus().getEntity();
	  bus.setPlate(page1.getPlate().getText());
	  bus.setPhone(page1.getPhone().getText());

	 // bus.getDriverList().clear();
	  
	  IStructuredSelection selection = (IStructuredSelection)page1.getViewer().getSelection();
      //bus.getDriverList().clear();
	  if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  //bus.getDriverList().size();
		 if(bus.getDriverList()!=null && bus.getDriverList().size()>0){
		 Driver d =(Driver)bus.getDriverList().toArray()[0];
		 d.setBus(null);
		 }
	  }
	  else{
		 //tempDriver = bus.getDriverList()
		 Driver d = (Driver)selection.getFirstElement();
		 d.setBus(bus);
		 bus.getDriverList().add(d);
	  }
    
	  return true;
  }

public Driver getTempDriver() {
	return tempDriver;
}

public void setTempDriver(Driver tempDriver) {
	this.tempDriver = tempDriver;
}


}