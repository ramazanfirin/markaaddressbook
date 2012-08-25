package wizards;

import model.interfaces.AbsractInterface;
import model.model.City;
import model.model.Person;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;


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
	 person.setPhoneSecond(page1.getPhoneSecond().getText());
	  
	 IStructuredSelection selection = (IStructuredSelection)page1.getCity().getSelection();
     if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  person.getAddress().setCity(null);
	  }else{
		  City city = (City)selection.getFirstElement();
		  person.getAddress().setCity(city);
	  }
     
     person.getAddress().setDescription(page1.getAddress().getText());
	 
	 return true;

  }

public AbsractInterface getEntity() {
	return entity;
}

public void setEntity(AbsractInterface entity) {
	this.entity = entity;
}


}