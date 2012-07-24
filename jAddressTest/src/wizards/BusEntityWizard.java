package wizards;

import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;


public class BusEntityWizard extends Wizard {
  
  private BasicBusPage page1;

  private AddressContactPage page2;

  private ISelection selection;
  
  private Bus bus;

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

	  bus.setPlate(page1.getPlate().getText());
	  bus.setPhone(page1.getPhone().getText());
	  
	  
    return true;
  }


}