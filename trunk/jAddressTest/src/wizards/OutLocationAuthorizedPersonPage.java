package wizards;

import model.model.AuthorizedPerson;
import model.model.OutLocation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;
import wizards.BasicPage;

public class OutLocationAuthorizedPersonPage extends BasicPage{

	protected Text _name;
	protected Text surname;
	protected Text phone;
	
	protected Text _name2;
	protected Text surname2;
	protected Text phone2;

	protected OutLocationAuthorizedPersonPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite main) {
		Composite container = new Composite(main, SWT.NULL);
	      GridLayout layout = new GridLayout();
	      container.setLayout(layout);
	      layout.numColumns = 1;
	      layout.verticalSpacing = 9;
	     
	      Group grpLocation=null;
	      grpLocation = new Group(container, SWT.NONE);
		    grpLocation.setText(Util.getString("outOffice.firstAuthorization.person"));
		    grpLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		    GridLayout grpLayout = new GridLayout(2, false);
		    grpLayout.verticalSpacing = 9;
		    grpLocation.setLayout(grpLayout);
	      

	      OutLocationWizard parentWizard = (OutLocationWizard)getWizard();
	      OutLocation outLocation = (OutLocation)parentWizard.getEntity();
	      
	      AuthorizedPerson firstAuthorizedPerson = outLocation.getFirstAuthorizedPerson();
	      
	      Label label = new Label(grpLocation, SWT.NULL);
	      label.setText(Util.getString("name"));
	      _name = new Text(grpLocation, SWT.BORDER | SWT.SINGLE);
	      _name.setText(firstAuthorizedPerson.getName());
	      _name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      _name.addModifyListener(textModifyListener);
	      _name.setEditable(Util.isAdmin());	

	      label = new Label(grpLocation, SWT.NULL);
	      label.setText(Util.getString("surname"));
	      surname = new Text(grpLocation, SWT.BORDER | SWT.SINGLE);
	      surname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      surname.setText(firstAuthorizedPerson.getSurname());
	      surname.addModifyListener(textModifyListener);
	      surname.setEditable(Util.isAdmin());	
	      
	      label = new Label(grpLocation, SWT.NULL);
	      label.setText(Util.getString("phone"));
	      phone = new Text(grpLocation, SWT.BORDER | SWT.SINGLE);
	      phone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone.setText(firstAuthorizedPerson.getPhone());
	      phone.addModifyListener(textModifyListener);
	      phone.setEditable(Util.isAdmin()); 
	      phone.addVerifyListener(digitVerifyListener);
	      phone.setTextLimit(10);
	      createLine(container, layout.numColumns);
	      
	      
	      Group grpLocation2=null;
	      grpLocation2 = new Group(container, SWT.NONE);
		    grpLocation2.setText(Util.getString("outOffice.firstAuthorization.person"));
		    grpLocation2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		    GridLayout grpLayout2 = new GridLayout(2, false);
		    grpLayout2.verticalSpacing = 9;
		    grpLocation2.setLayout(grpLayout2);
	      

	      
	      AuthorizedPerson secondAuthorizedPerson = outLocation.getSecondAuthorizedPerson();
	      
	      label = new Label(grpLocation2, SWT.NULL);
	      label.setText(Util.getString("name"));
	      _name2 = new Text(grpLocation2, SWT.BORDER | SWT.SINGLE);
	      _name2.setText(firstAuthorizedPerson.getName());
	      _name2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      _name2.addModifyListener(textModifyListener);
	      _name2.setEditable(Util.isAdmin());	

	      label = new Label(grpLocation2, SWT.NULL);
	      label.setText(Util.getString("surname"));
	      surname2 = new Text(grpLocation2, SWT.BORDER | SWT.SINGLE);
	      surname2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      surname2.setText(firstAuthorizedPerson.getSurname());
	      surname2.addModifyListener(textModifyListener);
	      surname2.setEditable(Util.isAdmin());	
	      
	      label = new Label(grpLocation2, SWT.NULL);
	      label.setText(Util.getString("phone"));
	      phone2 = new Text(grpLocation2, SWT.BORDER | SWT.SINGLE);
	      phone2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone2.setText(firstAuthorizedPerson.getPhone());
	      phone2.addModifyListener(textModifyListener);
	      phone2.setEditable(Util.isAdmin()); 
	      phone2.addVerifyListener(digitVerifyListener);
	      phone2.setTextLimit(10);
	      
	      
	      
	      
	      
	      
	      
	      setControl(container);
	      setPageComplete(false);
	      dialogChanged();
		
	}
	
	protected boolean dialogChanged() {
		
		 
		if (this.get_name().getText().length() == 0) {
      updateStatus("Isim alani zorunludur");
      return false;
    }else
      updateStatus(null);	 
    
    
    if (this.surname.getText().length() == 0) {
      updateStatus("Soyisim alani zorunludur.");
      return false;
    } else 
    	updateStatus(null);	
    	
    if (this.phone.getText().length() == 0) {
        updateStatus("Telefon alani zorunludur.");
        return false;
      } else 
      	updateStatus(null);	
    
    if (this.phone.getText().length() < 10) {
        updateStatus("Telefon alani 10 hane olmalidir.Ornek : 5551234567");
        return false;
      } else 
      	updateStatus(null);	
    
	if (this._name2.getText().length() == 0) {
	      updateStatus("Isim alani zorunludur");
	      return false;
	    }else
	      updateStatus(null);	 
	    
	    
	    if (this.surname2.getText().length() == 0) {
	      updateStatus("Soyisim alani zorunludur.");
	      return false;
	    } else 
	    	updateStatus(null);	
	    	
	    if (this.phone2.getText().length() == 0) {
	        updateStatus("Telefon alani zorunludur.");
	        return false;
	      } else 
	      	updateStatus(null);	
	    
	    if (this.phone2.getText().length() < 10) {
	        updateStatus("Telefon alani 10 hane olmalidir.Ornek : 5551234567");
	        return false;
	      } else 
	      	updateStatus(null);	    
    

    updateStatus(null);
    setPageComplete(true);
    
    return true;
    
   }

	public Text get_name() {
		return _name;
	}

	public void set_name(Text _name) {
		this._name = _name;
	}

	public Text getSurname() {
		return surname;
	}

	public void setSurname(Text surname) {
		this.surname = surname;
	}

	public Text getPhone() {
		return phone;
	}

	public void setPhone(Text phone) {
		this.phone = phone;
	}

	public Text get_name2() {
		return _name2;
	}

	public void set_name2(Text _name2) {
		this._name2 = _name2;
	}

	public Text getSurname2() {
		return surname2;
	}

	public void setSurname2(Text surname2) {
		this.surname2 = surname2;
	}

	public Text getPhone2() {
		return phone2;
	}

	public void setPhone2(Text phone2) {
		this.phone2 = phone2;
	}  

}
