package wizards;

import model.interfaces.AbsractInterface;
import model.model.City;
import model.model.OutLocation;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;

import test.wizard.EmailPage;
import test.wizard.NamePage;


public class OutLocationWizard extends Wizard{

	OutLocationAuthorizedPersonPage authorizedPersonPage;
	OutLocationLocationPage locationLocationPage;;
	
	AbsractInterface entity;
	String title ;
	
	public OutLocationWizard(AbsractInterface _entity,String _title) {
		super();
		entity=_entity; 
		title = _title;
	}

	@Override
    public void addPages() {
      authorizedPersonPage = new OutLocationAuthorizedPersonPage(title);
      addPage(authorizedPersonPage);
      locationLocationPage = new OutLocationLocationPage(title);
      addPage(locationLocationPage);

	}
	
	@Override
	public boolean performFinish() {
		OutLocation outLocation = (OutLocation)entity;
		outLocation.getFirstAuthorizedPerson().setName(authorizedPersonPage.get_name().getText());
		outLocation.getFirstAuthorizedPerson().setSurname(authorizedPersonPage.getSurname().getText());
		outLocation.getFirstAuthorizedPerson().setPhone(authorizedPersonPage.getPhone().getText());
		
		outLocation.getSecondAuthorizedPerson().setName(authorizedPersonPage.get_name2().getText());
		outLocation.getSecondAuthorizedPerson().setSurname(authorizedPersonPage.getSurname2().getText());
		outLocation.getSecondAuthorizedPerson().setPhone(authorizedPersonPage.getPhone2().getText());
		
		outLocation.setFirstPhone(locationLocationPage.getPhone1().getText());
		outLocation.setSecondPhone(locationLocationPage.getPhone2().getText());
		outLocation.setThirdPhone(locationLocationPage.getPhone3().getText());
		
		outLocation.setIpPhone(locationLocationPage.getIpPhone().getText());
		
		outLocation.setNote(locationLocationPage.getNote().getText());
		
		IStructuredSelection selection = (IStructuredSelection)locationLocationPage.getCity().getSelection();
	     if(selection.isEmpty() || selection.getFirstElement() instanceof String){
	    	 outLocation.getAddress().setCity(null);
		  }else{
			  City city = (City)selection.getFirstElement();
			  outLocation.getAddress().setCity(city);
		  }
	     
	     outLocation.getAddress().setDescription(locationLocationPage.getAddress().getText());
		
		return true;
	}

	public AbsractInterface getEntity() {
		return entity;
	}

	public void setEntity(AbsractInterface entity) {
		this.entity = entity;
	}

}


