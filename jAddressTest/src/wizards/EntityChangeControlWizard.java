package wizards;

import model.interfaces.AbsractInterface;

import org.eclipse.jface.wizard.Wizard;

public class EntityChangeControlWizard extends PersonEntityWizard{

	EntityChangeControlPage page1;
	
	public EntityChangeControlWizard(AbsractInterface _entity, String _title) {
		super(_entity, _title);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void addPages() {
		page1 = new EntityChangeControlPage(selection, "", title);
	    addPage(page1);
	}
	
	@Override
	public boolean performFinish() {
		
		return false;
	}

}
