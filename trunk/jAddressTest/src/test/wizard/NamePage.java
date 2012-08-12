package test.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;



public class NamePage extends MainPage {
	  private String firstName = "";

	  private String lastName = "";
	  
	  Text first;

	  Text last;
	  
	  public NamePage() {
	    super("Name");
	    setDescription("Enter the first and last names");
	    setPageComplete(false);
	  }

	  public void createControl(Composite parent) {
	    Composite composite = new Composite(parent, SWT.NONE);
	    composite.setLayout(new GridLayout(2, false));

	    new Label(composite, SWT.LEFT).setText("First Name:");
	    first = new Text(composite, SWT.BORDER);
	    first.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	    new Label(composite, SWT.LEFT).setText("Last Name:");
	    last = new Text(composite, SWT.BORDER);
	    last.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	    first.addModifyListener(modifyListener);

	    last.addModifyListener(modifyListener);

	    setControl(composite);
	  }

	@Override
	void dialogChanged() {
		System.out.println(first.getText()+ " " + last.getText());
		if(first.getText().equals("")){
			 updateStatus("isim alaninin girilmesi gereklidir.");
	      return ;
		}
		
		if(last.getText().equals("")){
			 updateStatus("soyisim alaninin girilmesi gereklidir.");
	      return ;
		}
		
		updateStatus(null);
	    setPageComplete(true);
	}
}	  
