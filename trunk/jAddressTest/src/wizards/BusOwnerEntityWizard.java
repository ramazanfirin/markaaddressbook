package wizards;

import model.interfaces.AbsractInterface;
import model.model.BusOwner;
import model.model.Driver;
import model.model.Person;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;


import com.AddressBookNew;


public class BusOwnerEntityWizard extends PersonEntityWizard {
 
	
public BusOwnerEntityWizard(AbsractInterface _entity, String _title) {
		super(_entity, _title);
		// TODO Auto-generated constructor stub
	}

public BusOwnerPage page2;

  public void addPages() {
    page2 = new BusOwnerPage(selection,entity,title);
    addPage(page2);
  }

  public boolean performFinish() {


	 	 BusOwner person = (BusOwner)entity;
		 person.setShortCode(page2.getShortCode().getText()); 
		 
		 page1=page2;
		
		 super.performFinish();
		 return true;

	 
  }




}