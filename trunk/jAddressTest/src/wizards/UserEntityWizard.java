package wizards;

import model.interfaces.AbsractInterface;
import model.model.Authority;
import model.model.User;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import util.Util;


public class UserEntityWizard extends PersonEntityWizard {
  private UserPage page1;

  private ISelection selection;
  
 

  public UserEntityWizard(AbsractInterface entity,String title) {
    super(entity,title);
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new UserPage(selection,getEntity(),title);
    addPage(page1);
   }

  public boolean performFinish() {

	 User user = (User)getEntity();
	 user.setName(page1.get_name().getText());
	 user.setSurname(page1.getSurname().getText());
	 user.setPhone(page1.getPhone().getText());
	 
	 user.setUsername(page1.getUsername().getText());
	 user.setPassword(Util.encrypt(page1.getPassword().getText()));
	 
	  
	 IStructuredSelection selection = (IStructuredSelection)page1.getViewer().getSelection();
	 user.setAuthority((Authority)selection.getFirstElement());
	  
    return true;
  }


}