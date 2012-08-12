package wizards;

import model.model.OutLocation;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

public class OutLocationLocationPage extends BasicPage{

	protected OutLocationLocationPage(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite main) {
		Composite container = new Composite(main, SWT.NULL);
	      GridLayout layout = new GridLayout();
	      container.setLayout(layout);
	      layout.numColumns = 2;
	      layout.verticalSpacing = 9;
	      
	      OutLocationWizard parentWizard = (OutLocationWizard)getWizard();
	      OutLocation outLocation = (OutLocation)parentWizard.getEntity();
	      
	      Label label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("phone.first"));
	      Text phone1 = new Text(container, SWT.BORDER | SWT.SINGLE);
	      phone1.setText(outLocation.getFirstPhone());
	      phone1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone1.addModifyListener(textModifyListener);
	      phone1.setEditable(Util.isAdmin());	
	      
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("phone.second"));
	      Text phone2 = new Text(container, SWT.BORDER | SWT.SINGLE);
	      phone2.setText(outLocation.getSecondPhone());
	      phone2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone2.addModifyListener(textModifyListener);
	      phone2.setEditable(Util.isAdmin());	
	      
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("phone.third"));
	      Text phone3 = new Text(container, SWT.BORDER | SWT.SINGLE);
	      phone3.setText(outLocation.getSecondPhone());
	      phone3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone3.addModifyListener(textModifyListener);
	      phone3.setEditable(Util.isAdmin());	
		
	      createLine(container, layout.numColumns);
	      
	      setControl(container);
	      setPageComplete(false);
	      dialogChanged();
	
	}

	@Override
	protected boolean dialogChanged() {
		System.out.println("protexctd geldi");
		return false;
	}

}
