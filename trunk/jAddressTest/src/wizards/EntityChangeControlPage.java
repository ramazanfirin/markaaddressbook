package wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

public class EntityChangeControlPage extends BasicPage{
	
	 private Text plate;

	   private Text phone;

	protected EntityChangeControlPage(ISelection selection, String pageName,
			String title) {
		super(selection, pageName, title);
		// TODO Auto-generated constructor stub
	}

	 public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;
		    
		Label label = new Label(container, SWT.NULL);
		label.setText(Util.getString("bus.plate"));
		plate = new Text(container, SWT.BORDER | SWT.SINGLE);
		//plate.setText(bus.getPlate());
		plate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		plate.addModifyListener(textModifyListener);
		plate.setEditable(Util.isAdmin());

		label = new Label(container, SWT.NULL);
		label.setText(Util.getString("bus.phoneNumber"));
		phone = new Text(container, SWT.BORDER | SWT.SINGLE);
		//phone.setText(bus.getPhone());
		phone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		phone.addModifyListener(textModifyListener);
		phone.setEditable(Util.isAdmin());
		    
	 }
	
	@Override
	boolean dialogChanged() {
		// TODO Auto-generated method stub
		return false;
	}

}
