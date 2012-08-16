package wizards;

import java.util.Set;

import model.model.Person;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public abstract class BasicPage extends WizardPage{
	
	private ISelection selection;
	
	abstract protected boolean dialogChanged();
	
	protected   ComboSelectionChangeProvider comboSelectionChangeProvider = new ComboSelectionChangeProvider();
	protected   PersonLabelProvider personLabelProvider = new PersonLabelProvider();
	protected   TextModifyListener textModifyListener = new TextModifyListener();
	protected   DigitVerifyListener digitVerifyListener = new DigitVerifyListener();
	protected   WhiteSpaceVerifyListener whiteSpaceVerifyListener = new WhiteSpaceVerifyListener();	
	
	
	
	

	protected BasicPage(ISelection selection,String pageName,String title) {
		super(pageName);
		setTitle(title);
		this.selection = selection;
	    setPageComplete(false);
		
	}
	
	protected BasicPage(String title) {
		super(title);
		setTitle(title);
		    setPageComplete(false);
		
	}
	

	@Override
	public void createControl(Composite parent) {
		
	}

	

	

	class ComboSelectionChangeProvider implements ISelectionChangedListener{

		@Override
		public void selectionChanged(SelectionChangedEvent arg0) {
			dialogChanged();
			
		}
	}
	
	

	class PersonLabelProvider extends LabelProvider{
		@Override
		public String getText(Object element) {
			if (element instanceof Person) {
				Person driver = (Person) element;
				return driver.getNameSurname();
			}
			return super.getText(element);
		}
		
	}	
	
	
	class TextModifyListener implements ModifyListener{

		@Override
		public void modifyText(ModifyEvent e) {
			dialogChanged();
			
		}
	}	
	
	class DigitVerifyListener implements VerifyListener{

		@Override
		public void verifyText(VerifyEvent event) {
			switch (event.keyCode) {  
            case SWT.BS:           // Backspace  
            case SWT.DEL:          // Delete  
            case SWT.HOME:         // Home  
            case SWT.END:          // End  
            case SWT.ARROW_LEFT:   // Left arrow  
            case SWT.ARROW_RIGHT:  // Right arrow  
                return;  
        }  
  
        if (!Character.isDigit(event.character)) {  
            event.doit = false;  // disallow the action  
        }  
			
		}
		
	}
	
	class WhiteSpaceVerifyListener implements VerifyListener{

		@Override
		public void verifyText(VerifyEvent event) {
			 
  
        if (event.keyCode==32) {  
            event.doit = false;  // disallow the action  
        }  
			
		}
		
	}
	
	protected void createLine(Composite parent, int ncol) {
	    Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
	        | SWT.BOLD);
	    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
	    gridData.horizontalSpan = ncol;
	    line.setLayoutData(gridData);
	  }
	
	 public void updateStatus(String message) {
		    setErrorMessage(message);
		    setPageComplete(message == null);
		  }

}
