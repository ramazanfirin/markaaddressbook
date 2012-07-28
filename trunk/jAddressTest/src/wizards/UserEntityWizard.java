package wizards;

import model.model.Authority;
import model.model.Bus;
import model.model.User;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;

import util.Util;

import com.AddressBookNew;


public class UserEntityWizard extends Wizard {
  private BasicUserPage page1;

  private ISelection selection;
  
 

  public UserEntityWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new BasicUserPage(selection);
    //page2 = new AddressContactPage(selection);
    addPage(page1);
    //addPage(page2);
    
    System.out.println("test");
    
  }

  public boolean performFinish() {

	 User user = (User)AddressBookNew.getInstance().getTabItemUser().getEntity();
	 user.setUsername(page1.getUsername().getText());
	 user.setPassword(Util.encrypt(page1.getPassword().getText()));
	 
	  
	 IStructuredSelection selection = (IStructuredSelection)page1.getViewer().getSelection();
	 user.setAuthority((Authority)selection.getFirstElement());
	// DBManager.getInstance().saveOrUpdate(driver);
	  
    return true;
   // return false;
  }


}