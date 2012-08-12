package test.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class AddEntryWizard extends Wizard{

	
		  private WelcomePage welcomePage= new WelcomePage();

		  private NamePage namePage= new NamePage();

		  private EmailPage emailPage= new EmailPage();

		  public AddEntryWizard() {
		    addPage(welcomePage);
		    addPage(namePage);
		    addPage(emailPage);

		    setWindowTitle("Address Book Entry Wizard");
		  }
		  public boolean performFinish() {
//		    AddressEntry entry = new AddressEntry();
//		    entry.setFirstName(namePage.getFirstName());
//		    entry.setLastName(namePage.getLastName());
//		    entry.setEmail(emailPage.getEmail());
//
//		    MainClass.mainWindow.add(entry);

		    return true;
		  }


}

