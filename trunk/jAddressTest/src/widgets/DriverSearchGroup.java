package widgets;

import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.AddressBook;

public class DriverSearchGroup extends Composite{

	

	private static ResourceBundle resAddressBook = ResourceBundle.getBundle("examples_addressbook",new Locale("tr", "TR"));
	
	Label labelDriverName;
	Text textDriverName;
	Label labelDriverSurname;
	Text textDriverSurname;
	Label labelDriverPhone;
	Text textDriverPhone;
	Button searchDriverButton;
	
	
	public DriverSearchGroup(Composite parent) {
		super(parent,  SWT.NONE);
		
		
		 final Group grpLocation = new Group(parent, SWT.NONE);
		    grpLocation.setText("arama");
		    grpLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		    GridLayout grpLayout = new GridLayout(2, false);
		    grpLayout.verticalSpacing = 0;
		    grpLocation.setLayout(grpLayout);
			
			
		    labelDriverName=new Label(grpLocation,SWT.NONE);
			labelDriverName.setText(resAddressBook.getString("driver.name"));
			labelDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			
			Text textDriverName=new Text(grpLocation,SWT.BORDER);
			textDriverName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			
			labelDriverSurname=new Label(grpLocation,SWT.NONE);
			labelDriverSurname.setText(resAddressBook.getString("driver.surname"));
			labelDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			
			textDriverSurname=new Text(grpLocation,SWT.BORDER);
			textDriverSurname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			
			labelDriverPhone=new Label(grpLocation,SWT.NONE);
			labelDriverPhone.setText(resAddressBook.getString("driver.phoneNumber"));
			labelDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			
			textDriverPhone=new Text(grpLocation,SWT.BORDER);
			textDriverPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		   
			
			searchDriverButton = new Button(grpLocation, SWT.NONE);
			searchDriverButton.setText(resAddressBook.getString("search"));
			

	}


	public Button getSearchDriverButton() {
		return searchDriverButton;
	}


	public void setSearchDriverButton(Button searchDriverButton) {
		this.searchDriverButton = searchDriverButton;
	}

	
	
	
}
