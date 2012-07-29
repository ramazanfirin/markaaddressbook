package wizards;

import model.interfaces.AbsractInterface;
import model.model.Driver;
import model.model.Person;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import util.Util;

import com.AddressBookNew;

class PersonPage extends BasicPage {
  
  private Text _name;

  private Text surname;

  private Text phone;

  private ISelection selection;
  
  ComboViewer viewer;

  private AbsractInterface entity;

  
  public PersonPage(ISelection selection,AbsractInterface _entity) {
    super("wizardPage");
    setTitle(Util.getString("driver"));
    setDescription("This wizard creates a new contact.");
    this.selection = selection;
    setPageComplete(false);
    entity =  _entity;
  }

  public void createControl(Composite parent) {
    
	Composite main = new Composite(parent, SWT.NULL);
	FillLayout fillLayout = new FillLayout();
    fillLayout.type = SWT.VERTICAL;
    
	main.setLayout(fillLayout);
	
	Composite container = new Composite(main, SWT.NULL);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    layout.verticalSpacing = 9;

    
    Person driver = (Person)entity;
    
    Label label = new Label(container, SWT.NULL);
    label.setText(Util.getString("name"));
    _name = new Text(container, SWT.BORDER | SWT.SINGLE);
    _name.setText(driver.getName());
    _name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    _name.addModifyListener(textModifyListener);

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("surname"));
    surname = new Text(container, SWT.BORDER | SWT.SINGLE);
    surname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    surname.setText(driver.getSurname());
    surname.addModifyListener(textModifyListener);

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("phone"));
    phone = new Text(container, SWT.BORDER | SWT.SINGLE);
    phone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    phone.setText(driver.getPhone());
    phone.addModifyListener(textModifyListener);
     
    createLine(container, layout.numColumns);

    final Group grpDriverList = new Group(main, SWT.NONE);
	grpDriverList.setText(Util.getString("bus.list"));
    GridLayout gridDriverList = new GridLayout(1, false);
    gridDriverList.verticalSpacing = 0;
    grpDriverList.setLayout(gridDriverList);

    
    TableViewer tableViewer=null;
    tableViewer = new TableViewer(grpDriverList,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
    tableViewer.setContentProvider(new ArrayContentProvider());
    tableViewer.setLabelProvider(new BusTableLabelProvider());
    Table table = tableViewer.getTable();
    table.setHeaderVisible(true);	
    table.setLinesVisible(true);
      
   String[] columnNames2 = {
		 Util.getString("bus.plate"),
		 Util.getString("bus.phoneNumber"),
	     Util.getString("driver.first.nameSurname"),
	     Util.getString("driver.second.nameSurname"),
	};
    
    for(int i = 0; i < columnNames2.length; i++) {
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText(columnNames2[i]);
		column.setWidth(150);
		
	}
    
    tableViewer.setInput(driver.getBusList());
    		
    dialogChanged();
    setControl(container);
  // super.
  }



  
  public boolean dialogChanged() {

	 
		if (this._name.getText().length() == 0) {
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

    updateStatus(null);
    setPageComplete(true);
    
    return true;
    
   }  
  


  
  public boolean canFlipToNextPage(){
	  System.out.println("geldi"); 
	  if (getErrorMessage() != null) 
		   return false;
	  
	  return dialogChanged();
	}
  
  
  
  private void createLine(Composite parent, int ncol) {
	    Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
	        | SWT.BOLD);
	    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
	    gridData.horizontalSpan = ncol;
	    line.setLayoutData(gridData);
	  }
  private void updateStatus(String message) {
	    setErrorMessage(message);
	    //setPageComplete(message == null);
	  }

public Text getNameVariable() {
	return _name;
}

public void setNameVariable(Text name) {
	this._name = name;
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

public ISelection getSelection() {
	return selection;
}

public void setSelection(ISelection selection) {
	this.selection = selection;
}

public ComboViewer getViewer() {
	return viewer;
}

public void setViewer(ComboViewer viewer) {
	this.viewer = viewer;
}

public class BusTableLabelProvider extends LabelProvider implements ITableLabelProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	public String getColumnText(Object element, int columnIndex) {
		Person p = (Person) element;
		String result = "";
		switch(columnIndex){
		case 0:
			result = p.getName();
			break;
		case 1:
			result = p.getSurname();
			break;
		case 2:
	        result= p.getPhone();
	        break;
		default:
			//should not reach here
			result = "";
		}
		return result;
	}
}

public AbsractInterface getEntity() {
	return entity;
}

public void setEntity(AbsractInterface entity) {
	this.entity = entity;
}
  
}