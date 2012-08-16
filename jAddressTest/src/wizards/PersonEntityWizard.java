package wizards;

import model.interfaces.AbsractInterface;
import model.model.Driver;
import model.model.Person;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;


import com.AddressBookNew;


public class PersonEntityWizard extends Wizard {
  public PersonPage page1;

  public ISelection selection;
  
  protected AbsractInterface entity;
  
  protected String title;
  
  public PersonEntityWizard(AbsractInterface _entity,String _title) {
    super();
    entity = _entity;
    setNeedsProgressMonitor(true);
    title=_title;
  }

  public void addPages() {
    //page1 = new PersonPage(selection,entity,title);
   page1 = new PersonPage(selection,entity,title) {
	
	@Override
	public void createCustomPersonComponent(Composite container) {
		// TODO Auto-generated method stub
		
	}
};
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