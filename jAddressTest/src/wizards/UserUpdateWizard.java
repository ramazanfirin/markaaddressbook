package wizards;

import model.interfaces.AbsractInterface;
import model.model.User;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;

import util.Util;


public class UserUpdateWizard extends PersonEntityWizard {
  private UserUpdatePage page1;

  private ISelection selection;
  
 

  public UserUpdateWizard(AbsractInterface entity,String title) {
    super(entity,title);
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new UserUpdatePage(selection,getEntity(),title);
    addPage(page1);
   
   }

  public boolean performFinish() {

	 User user = (User)getEntity();
	 user.setName(page1.get_name().getText());
	 user.setSurname(page1.getSurname().getText());
	 user.setPhone(page1.getPhone().getText());
	 
	 
	 if(page1.updatePassword.getSelection()){
		 if(!Util.encrypt(page1.getCurrentPassword().getText()).equals(user.getPassword())){	
			 MessageDialog.openError(this.getShell(), "Hata", "Sifre yanlis");
			 return false;
		 }else{
			 user.setUsername(page1.getUsername().getText());
			 user.setPassword(Util.encrypt(page1.getPassword().getText()));
		 }
	 }
    return true;
  }


}