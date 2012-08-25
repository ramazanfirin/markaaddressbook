package wizards;

import java.util.List;

import model.model.OutLocation;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

public class OutLocationLocationPage extends BasicPage{

	Text phone1;
	Text phone2;
	Text phone3;
	Text ipPhone;
	Text note;
	
	ComboViewer city;
    Text address;

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
	      phone1 = new Text(container, SWT.BORDER | SWT.SINGLE);
	      phone1.setText(outLocation.getFirstPhone());
	      phone1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone1.addModifyListener(textModifyListener);
	      phone1.setEditable(Util.isAdmin());	
	      phone1.addVerifyListener(digitVerifyListener);
	      phone1.setTextLimit(10);
	      
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("phone.second"));
	      phone2 = new Text(container, SWT.BORDER | SWT.SINGLE);
	      phone2.setText(outLocation.getSecondPhone());
	      phone2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone2.addModifyListener(textModifyListener);
	      phone2.setEditable(Util.isAdmin());	
	      phone2.addVerifyListener(digitVerifyListener);
	      phone2.setTextLimit(10);
	      
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("phone.third"));
	      phone3 = new Text(container, SWT.BORDER | SWT.SINGLE);
	      phone3.setText(outLocation.getSecondPhone());
	      phone3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      phone3.addModifyListener(textModifyListener);
	      phone3.setEditable(Util.isAdmin());
	      phone3.addVerifyListener(digitVerifyListener);
	      phone3.setTextLimit(10);
		
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("ipPhone"));
	      ipPhone = new Text(container, SWT.BORDER | SWT.SINGLE);
	      ipPhone.setText(outLocation.getSecondPhone());
	      ipPhone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      ipPhone.addModifyListener(textModifyListener);
	      ipPhone.setEditable(Util.isAdmin());	
	      
	      createLine(container, layout.numColumns);
	      
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("note"));
	      note = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	      note.setLayoutData(new GridData(GridData.FILL_BOTH));
	      note.setText(outLocation.getNote());
	      note.addModifyListener(textModifyListener);
	      note.setEditable(Util.isAdmin());	
	      
	      createLine(container, layout.numColumns);
	      
	      List cityList=Util.getApplicationInstance().getDataProvider().loadCities();
	      cityList.add(0,Util.getString("select"));
	      
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("city"));
	      city = new ComboViewer(container, SWT.READ_ONLY );
	      city.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	      city.getCombo().setEnabled(Util.isAdmin());
	      city.setContentProvider(new ArrayContentProvider());
	      city.setLabelProvider(cityLabelProvider);
	      city.setInput(cityList);
	      city.addSelectionChangedListener(comboSelectionChangeProvider); 
	      if(outLocation.getAddress().getCity()!=null)
	    	  city.getCombo().setText(outLocation.getAddress().getCity().getName());
	      else
	    	  city.getCombo().setText(Util.getString("select")); 
	      
	      label = new Label(container, SWT.NULL);
	      label.setText(Util.getString("address"));
	      address = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	      GridData ad= new GridData(GridData.FILL_BOTH);
	      ad.heightHint=75;	
	      address.setLayoutData(ad);
	      address.setText(outLocation.getAddress().getDescription());
	      address.addModifyListener(textModifyListener);
	      address.setEditable(Util.isAdmin()); 
	      
	      setControl(container);
	      setPageComplete(false);
	      dialogChanged();
	
	}

	@Override
	protected boolean dialogChanged() {
		if (this.getPhone1().getText().length() == 0) {
		      updateStatus("ilk Telefon alani zorunludur");
		      return false;
		    }else
		      updateStatus(null);	 

		updateStatus(null);
	    setPageComplete(true);
	    
	    return true;
	}

	public Text getPhone1() {
		return phone1;
	}

	public void setPhone1(Text phone1) {
		this.phone1 = phone1;
	}

	public Text getPhone2() {
		return phone2;
	}

	public void setPhone2(Text phone2) {
		this.phone2 = phone2;
	}

	public Text getPhone3() {
		return phone3;
	}

	public void setPhone3(Text phone3) {
		this.phone3 = phone3;
	}

	public Text getIpPhone() {
		return ipPhone;
	}

	public void setIpPhone(Text ipPhone) {
		this.ipPhone = ipPhone;
	}

	public Text getNote() {
		return note;
	}

	public void setNote(Text note) {
		this.note = note;
	}

	public ComboViewer getCity() {
		return city;
	}

	public void setCity(ComboViewer city) {
		this.city = city;
	}

	public Text getAddress() {
		return address;
	}

	public void setAddress(Text address) {
		this.address = address;
	}

}
