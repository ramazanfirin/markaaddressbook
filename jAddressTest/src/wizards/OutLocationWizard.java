package wizards;

import model.interfaces.AbsractInterface;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;


public class OutLocationWizard extends Wizard{

	OutLocationAuthorizedPersonPage authorizedPersonPage;
	OutLocationLocationPage locationLocationPage;;
	
	AbsractInterface entity;
	String title ;
	
	private ISelection selection;
	
	public OutLocationWizard(AbsractInterface _entity,String _title) {
		super();
		entity=_entity; 
		title = _title;
	}

	@Override
    public void addPages() {
  
       authorizedPersonPage = new OutLocationAuthorizedPersonPage(title);
		addPage(authorizedPersonPage);
		System.out.println("dikkat");
	
		addPage(authorizedPersonPage);
		
//        locationLocationPage = new OutLocationLocationPage(title);
//        addPage(authorizedPersonPage);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

	public AbsractInterface getEntity() {
		return entity;
	}

	public void setEntity(AbsractInterface entity) {
		this.entity = entity;
	}

}


