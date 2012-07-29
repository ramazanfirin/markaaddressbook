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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public abstract class BasicPage extends WizardPage{
	
	
	abstract boolean dialogChanged();
	
	   ComboSelectionChangeProvider comboSelectionChangeProvider = new ComboSelectionChangeProvider();
	   PersonLabelProvider personLabelProvider = new PersonLabelProvider();
	   TextModifyListener textModifyListener = new TextModifyListener();
	
	
	
	
	

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
}
