package test.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class EmailPage extends MainPage {
	  private String email = "";
	  Text ea;
	  public EmailPage() {
	    super("E-mail");
	    setDescription("Enter the e-mail address");
	    setPageComplete(false);
	  }
	  public void createControl(Composite parent) {
	    Composite composite = new Composite(parent, SWT.NONE);
	    composite.setLayout(new GridLayout(2, false));

	    new Label(composite, SWT.LEFT).setText("E-mail Address:");
	    ea = new Text(composite, SWT.BORDER);
	    ea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	    ea.addModifyListener(modifyListener);

	    setControl(composite);
	  }
	@Override
	void dialogChanged() {
		System.out.println();
		if(ea.getText().equals("")){
			 updateStatus("email alaninin girilmesi gereklidir.");
	      return ;
		}
		
		updateStatus(null);
	    setPageComplete(true);
		
	}
}