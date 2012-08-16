package wizards;

import java.awt.Container;

import model.interfaces.AbsractInterface;
import model.model.BusOwner;
import model.model.Person;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

class BusOwnerPage extends PersonPage {
  
 Text shortCode;
  
  public BusOwnerPage(ISelection selection,AbsractInterface _entity,String _title) {
    super(selection, _entity,_title);
    entity =  _entity;
  }

  
  @Override
  public void createOtherComponent(Composite container) {
//	  BusOwner driver = (BusOwner)entity;
//	  
//	  Label label = new Label(container, SWT.NULL);
//      label.setText(Util.getString("phone"));
//      shortCode = new Text(container, SWT.BORDER | SWT.SINGLE);
//      //shortCode.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//      shortCode.setText(driver.getShortCode());
//      shortCode.addModifyListener(textModifyListener);
//      shortCode.setEditable(Util.isAdmin()); 
  	
  }


@Override
public void createCustomPersonComponent(Composite container) {

	BusOwner driver = (BusOwner)entity;
	Label label = new Label(container, SWT.NULL);
  label.setText(Util.getString("busOwner.shortCode"));
  shortCode = new Text(container, SWT.BORDER | SWT.SINGLE);
  shortCode.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  shortCode.setText(driver.getShortCode());
  shortCode.addModifyListener(textModifyListener);
  shortCode.setEditable(Util.isAdmin()); 
}


public Text getShortCode() {
	return shortCode;
}


public void setShortCode(Text shortCode) {
	this.shortCode = shortCode;
}

  
 

  
}