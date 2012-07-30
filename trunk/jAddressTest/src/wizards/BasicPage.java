package wizards;

import java.util.Set;

import model.model.Person;

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
import org.eclipse.swt.widgets.Composite;

public abstract class BasicPage extends WizardPage{
	
	
	abstract boolean dialogChanged();
	
	   ComboSelectionChangeProvider comboSelectionChangeProvider = new ComboSelectionChangeProvider();
	   PersonLabelProvider personLabelProvider = new PersonLabelProvider();
	   TextModifyListener textModifyListener = new TextModifyListener();
	   DigitVerifyListener digitVerifyListener = new DigitVerifyListener();
	
	
	
	
	

	protected BasicPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
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
}
