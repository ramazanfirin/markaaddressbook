package widgets;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class CustomWizardDialog extends WizardDialog{

	public CustomWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
		// TODO Auto-generated constructor stub
	}

	
	public Button getButton(int id){
		
		return super.getButton(id);
	}
	
public Control getButtonBar(){
		
		return super.getButtonBar();
	}
}
