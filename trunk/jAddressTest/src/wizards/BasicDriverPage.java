package wizards;

import model.model.Bus;
import model.model.Driver;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import widgets.BasicTabItem.TableLabelProviders;

import com.AddressBookNew;

class BasicDriverPage extends WizardPage {
  
  private Text _name;

  private Text surname;

  private Text phone;

  private Text emailText;
 
  private ISelection selection;
  
  ComboViewer viewer;

  

  
  public BasicDriverPage(ISelection selection) {
    super("wizardPage");
    setTitle(Util.getString("driver"));
    setDescription("This wizard creates a new contact.");
    this.selection = selection;
    setPageComplete(false);
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

    
    Driver driver = (Driver)AddressBookNew.getInstance().getTabItemDriver().getEntity();
    
    Label label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.name"));

    _name = new Text(container, SWT.BORDER | SWT.SINGLE);
    _name.setText(driver.getName());

    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    _name.setLayoutData(gd);
    _name.addKeyListener(new KeyListener() {

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void keyReleased(KeyEvent e) {
			if (!_name.getText().isEmpty()) {
				dialogChanged();
			}
		}

	});

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.surname"));

    surname = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    surname.setLayoutData(gd);
    surname.setText(driver.getSurname());
    
    surname.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("driver.phoneNumber"));

    phone = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    phone.setLayoutData(gd);
    phone.setText(driver.getPhone());
    createLine(container, layout.numColumns);

    
    /*
    
    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("bus"));

    viewer = new ComboViewer(container, SWT.READ_ONLY);
    viewer.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    viewer.setContentProvider(new ArrayContentProvider());
    viewer.setLabelProvider(new LabelProvider() {
    	@Override
    	public String getText(Object element) {
    		if (element instanceof Bus) {
    			Bus bus = (Bus) element;
    			return bus.getPlate();
    		}
    		return super.getText(element);
    	}
    });
    List busList=DBManager.getInstance().loadAllBus2();
    viewer.setInput(busList);
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
    	@Override
    	public void selectionChanged(SelectionChangedEvent event) {
    		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
    		if(selection.isEmpty()==false)
    		System.out.println(((Bus) selection.getFirstElement()).getPlate());
    		dialogChanged();
    	}
    }); 
    
    if(driver.getBus()!=null)
    		viewer.getCombo().setText(driver.getBus().getPlate());
    		
    */		
    
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
    Table table = tableViewer.getTable();
    table.setHeaderVisible(true);	
    table.setLinesVisible(true);
    //table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));

    
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
      updateStatus("Sofor ismi zorunludur");
      return false;
    }else
      updateStatus(null);	 
    
    
    if (this.surname.getText().length() == 0) {
      updateStatus("Sofor soyisim zorunludur.");
      return false;
    } else 
    	updateStatus(null);	
    	
//    if (this.viewer.getSelection().isEmpty()) {
//        updateStatus("Otobus secilmesi zorunludur");
//        return false;
//      } else 
//      	updateStatus(null);	

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

public Text getEmailText() {
	return emailText;
}

public void setEmailText(Text emailText) {
	this.emailText = emailText;
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
		    	   return p.getFirstDriver().getNameSurname();
		       else
		    	   return "";
		case 3:
		       if(p.getSecondDriver()!=null)
		    	   return p.getSecondDriver().getNameSurname();
		       else
		    	   return "";
		default:
			//should not reach here
			result = "";
		}
		return result;
	}
}
  
}