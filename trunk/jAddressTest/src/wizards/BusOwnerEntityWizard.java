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

public BusOwnerPage page1;

  public void addPages() {
    page1 = new BusOwnerPage(selection,entity,title);
    addPage(page1);
  }

  public boolean performFinish() {


	 	 BusOwner person = (BusOwner)entity;
		 person.setName(page1.getNameVariable().getText());
		 person.setSurname(page1.getSurname().getText());
		 person.setPhone(page1.getPhone().getText());
		 person.setShortCode(page1.getShortCode().getText()); 
		 
		 return true;

	 
  }




}