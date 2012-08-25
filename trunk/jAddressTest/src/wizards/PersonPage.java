package wizards;

import java.util.List;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.Person;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import util.Util;

abstract class  PersonPage extends BasicPersonPage {
  
 
  private ISelection selection;
  
  ComboViewer viewer;

  public AbsractInterface entity;

  public Table table;
  
  public PersonPage(ISelection selection,AbsractInterface _entity,String _title) {
    super(selection,_title);
    entity =  _entity;
  }

  @Override
  public void createControl(Composite parent) {
	  super.createControl(parent);
  }

  @Override
  public void createMainComponent(Composite main) {
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
      _name.setEditable(Util.isAdmin());	

      label = new Label(container, SWT.NULL);
      label.setText(Util.getString("surname"));
      surname = new Text(container, SWT.BORDER | SWT.SINGLE);
      surname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      surname.setText(driver.getSurname());
      surname.addModifyListener(textModifyListener);
      surname.setEditable(Util.isAdmin());	
      
      label = new Label(container, SWT.NULL);
      label.setText(Util.getString("phone"));
      phone = new Text(container, SWT.BORDER | SWT.SINGLE);
      phone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      phone.setText(driver.getPhone());
      phone.addModifyListener(textModifyListener);
      phone.setEditable(Util.isAdmin()); 
      phone.addVerifyListener(digitVerifyListener);
      phone.setTextLimit(10);
      
      label = new Label(container, SWT.NULL);
      label.setText(Util.getString("phoneSecond"));
      phoneSecond = new Text(container, SWT.BORDER | SWT.SINGLE);
      phoneSecond.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      phoneSecond.setText(driver.getPhoneSecond());
      phoneSecond.addModifyListener(textModifyListener);
      phoneSecond.setEditable(Util.isAdmin()); 
      phoneSecond.addVerifyListener(digitVerifyListener);
      phoneSecond.setTextLimit(10);
      
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
      if(driver.getAddress().getCity()!=null)
    	  city.getCombo().setText(driver.getAddress().getCity().getName());
      else
    	  city.getCombo().setText(Util.getString("select")); 
      
      label = new Label(container, SWT.NULL);
      label.setText(Util.getString("address"));
      address = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
      GridData ad= new GridData(GridData.FILL_BOTH);
      ad.heightHint=75;	
      address.setLayoutData(ad);
      address.setText(driver.getAddress().getDescription());
      address.addModifyListener(textModifyListener);
      address.setEditable(Util.isAdmin()); 
      //address.set
      
      createCustomPersonComponent(container);
      createLine(container, layout.numColumns);
      
    
  	
  }
  
  public abstract void createCustomPersonComponent(Composite container);

  @Override
  public void createTable(Composite main) {
  	final Group grpDriverList = new Group(main, SWT.NONE);
  	grpDriverList.setText(Util.getString("bus.list"));
  	//grpDriverList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
  	GridLayout gridDriverList = new GridLayout(1, false);
  	gridDriverList.verticalSpacing = 0;
  	grpDriverList.setLayout(gridDriverList);
  	
  	TableViewer tableViewer=null;
      tableViewer = new TableViewer(grpDriverList,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
      tableViewer.setContentProvider(new ArrayContentProvider());
      tableViewer.setLabelProvider(new BusTableLabelProvider());
      table = tableViewer.getTable();
      table.setHeaderVisible(true);	
      table.setLinesVisible(true);
      table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));  
      table.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();
				if (items.length > 0){
					Bus bus = (Bus)items[0].getData();
					BusEntityWizard busWizard = new BusEntityWizard(bus,Util.getString("bus")); 
					WizardDialog wizardDialog = new WizardDialog(getShell(), busWizard);
					if(wizardDialog.open()==Window.OK){
						if(Util.isAdmin())
							try {
								Util.getApplicationInstance().getDataProvider().saveOrUpdate(bus);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
			}
		});
      
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
      Person person = (Person)entity;
      tableViewer.setInput(person.getBusList());
  	
  }

  @Override
  public void createOtherComponent(Composite composite) {
  	// TODO Auto-generated method stub
  	
  }

  
  
  


  
  public boolean canFlipToNextPage(){
	  System.out.println("geldi"); 
	  if (getErrorMessage() != null) 
		   return false;
	  
	  return dialogChanged();
	}
  
  
  
  public void createLine(Composite parent, int ncol) {
	    Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
	        | SWT.BOLD);
	    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
	    gridData.horizontalSpan = ncol;
	    line.setLayoutData(gridData);
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
		Bus p = (Bus) element;
		String result = "";
		switch(columnIndex){
		case 0:
			result = p.getPlate();
			break;
		case 1:
			result = p.getPhone();
			break;
		case 2:
	        if(p.getFirstDriver()!=null)
	        	result= p.getFirstDriver().getNameSurname();
	        else	
	        	result="";
	        break;
		case 3:
	        if(p.getSecondDriver()!=null)
	        	result= p.getSecondDriver().getNameSurname();
	        else	
	        	result="";
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