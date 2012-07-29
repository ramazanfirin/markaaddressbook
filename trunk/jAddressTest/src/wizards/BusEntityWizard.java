package wizards;

import model.model.Bus;
import model.model.Driver;
import model.model.Host;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;

import com.AddressBookNew;


public class BusEntityWizard extends Wizard {
  
  private BusPage page1;

  private ISelection selection;
  
  private Driver tempDriver;

  public BusEntityWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new BusPage(selection);
    //page2 = new AddressContactPage(selection);
    addPage(page1);
    //addPage(page2);
    
    System.out.println("test");
  }

  public boolean performFinish() {

	  Bus bus = (Bus)AddressBookNew.getInstance().getTabItemBus().getEntity();
	  bus.setPlate(page1.getPlate().getText());
	  bus.setPhone(page1.getPhone().getText());

	  IStructuredSelection selection = (IStructuredSelection)page1.getViewer().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setFirstDriver(null);
	  }else{
		  Driver driver = (Driver)selection.getFirstElement();
		  bus.setFirstDriver(driver);
	  }
	  
      
      selection = (IStructuredSelection)page1.getViewer2().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setSecondDriver(null);
	  }else{
		  Driver driver = (Driver)selection.getFirstElement();
		  bus.setSecondDriver(driver);
	  }
	  
      
      selection = (IStructuredSelection)page1.getViewer3().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setThirdDriver(null);
	  }else{
		  Driver driver = (Driver)selection.getFirstElement();
		  bus.setThirdDriver(driver);
	  }
      
      selection = (IStructuredSelection)page1.getViewer4().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setHost(null);
	  }else{
		  Host host = (Host)selection.getFirstElement();
		  bus.setHost(host);
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