package test.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class WelcomePage extends WizardPage{
	  protected WelcomePage() {
		    super("Welcome", "Welcome", ImageDescriptor.createFromFile(WelcomePage.class,
		        "welcome.gif"));
		    setDescription("Welcome to the Address Book Entry Wizard");
		  }

		  public void createControl(Composite parent) {
		    Composite composite = new Composite(parent, SWT.NONE);
		    composite.setLayout(new FillLayout(SWT.VERTICAL));
		    new Label(composite, SWT.CENTER).setText("Welcome to the Address Book Entry Wizard!");
		    new Label(composite, SWT.LEFT)
		        .setText("This wizard guides you through creating an Address Book entry.");
		    new Label(composite, SWT.LEFT).setText("Click Next to continue.");
		    setControl(composite);
		  }
}
