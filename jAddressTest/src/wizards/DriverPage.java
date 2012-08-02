package wizards;

import javax.swing.text.html.parser.Entity;

import model.interfaces.AbsractInterface;
import model.model.Bus;
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
import wizards.PersonPage.BusTableLabelProvider;

import com.AddressBookNew;

class DriverPage extends PersonPage {
  
  private ISelection selection;
  
  ComboViewer viewer;

  public DriverPage(ISelection selection, AbsractInterface _entity,String title) {
	super(selection, _entity,title);
	setTitle(Util.getString("driver"));
    setDescription("This wizard creates a new contact.");
    this.selection = selection;
    setPageComplete(false);
}


  @Override
  public void createTable(Composite main) {
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
      Person person = (Person)entity;
      tableViewer.setInput(person.getBusList());
  	
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