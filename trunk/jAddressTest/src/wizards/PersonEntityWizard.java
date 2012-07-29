package wizards;

import model.interfaces.AbsractInterface;
import model.model.Driver;
import model.model.Person;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;

import com.AddressBookNew;


public class PersonEntityWizard extends Wizard {
  public PersonPage page1;

  private ISelection selection;
  
  private AbsractInterface entity;
  
  public PersonEntityWizard(AbsractInterface _entity) {
    super();
    entity = _entity;
    setNeedsProgressMonitor(true);
  }

  public void addPages() {
    page1 = new PersonPage(selection,entity);
    addPage(page1);
  }

  public boolean performFinish() {

 	 Person person = (Person)entity;
	 person.setName(page1.getNameVariable().getText());
	 person.setSurname(page1.getSurname().getText());
	 person.setPhone(page1.getPhone().getText());
	  
	 return true;

  }

public AbsractInterface getEntity() {
	return entity;
}

public void setEntity(AbsractInterface entity) {
	this.entity = entity;
}


}