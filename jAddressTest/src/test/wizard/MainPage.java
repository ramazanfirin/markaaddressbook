package test.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

public abstract class MainPage extends WizardPage{

	protected MainPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	TextModifyListener modifyListener = new TextModifyListener();
	
	@Override
	public void createControl(Composite arg0) {
		// TODO Auto-generated method stub
		
	}
	
	class TextModifyListener implements ModifyListener{

		@Override
		public void modifyText(ModifyEvent e) {
			System.out.println("text modify "+" name=");
			dialogChanged();
			
		}
	}
	
	abstract void dialogChanged();
	
	  public void updateStatus(String message) {
		    setErrorMessage(message);
		    //setPageComplete(message == null);
		  }

}
