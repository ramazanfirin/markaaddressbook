package wizards;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.Driver;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;

import com.AddressBookNew;


public class DriverEntityWizard extends PersonEntityWizard {
  //private DriverPage page1;

  private ISelection selection;
  
  public DriverEntityWizard(AbsractInterface entity) {
    super(entity);
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new DriverPage(selection,getEntity());
    addPage(page1);
  }

}