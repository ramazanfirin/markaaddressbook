package wizards;

import java.util.List;

import model.DBManager;
import model.interfaces.AbsractInterface;
import model.model.Authority;
import model.model.Bus;
import model.model.User;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

class UserPage extends PersonPage {
  
  private ISelection selection;
  
  ComboViewer viewer;
  
  private Text username;

  private Text password;

  private Text passwordAgain;

  public UserPage(ISelection selection, AbsractInterface _entity,String title) {
	super(selection, _entity,title);
}


  @Override
  public void createTable(Composite main) {
  	
  }
  
  public void createOtherComponent(Composite main) {
	 
	  Composite container = new Composite(main, SWT.NULL);
      GridLayout layout = new GridLayout();
      container.setLayout(layout);
      layout.numColumns = 2;
      layout.verticalSpacing = 9;
	  
	  User user = (User)getEntity();
      Label label= null;
	  
      label = new Label(container, SWT.NULL);
      label.setText(Util.getString("user.name"));

      username = new Text(container, SWT.BORDER | SWT.SINGLE);
      username.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      username.setText(user.getUsername());
      username.addModifyListener(textModifyListener);
      
	  label = new Label(container, SWT.NULL);
	  label.setText(Util.getString("user.password"));

	    password = new Text(container, SWT.BORDER | SWT.SINGLE);
	    password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    password.setText(user.getPassword());
	    password.setEchoChar('*');
	    password.addModifyListener(textModifyListener);

	    label = new Label(container, SWT.NULL);
	    label.setText(Util.getString("user.passwordAgain"));

	    passwordAgain = new Text(container, SWT.BORDER | SWT.SINGLE);
	    passwordAgain.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    passwordAgain.setText(user.getPassword());
	    passwordAgain.setEchoChar('*');
	    passwordAgain.addModifyListener(textModifyListener);
	    createLine(container, 2);

	    label = new Label(container, SWT.NULL);
	    label.setText(Util.getString("user.authority"));

	    viewer = new ComboViewer(container, SWT.READ_ONLY);
	    viewer.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    viewer.setContentProvider(new ArrayContentProvider());
	    viewer.setLabelProvider(new LabelProvider() {
	    	@Override
	    	public String getText(Object element) {
	    		if (element instanceof Authority) {
	    			Authority authority = (Authority) element;
	    			return authority.getAuthority();
	    		}
	    		return super.getText(element);
	    	}
	    });
	    List busList=DBManager.getInstance().loadAuthorith();
	    viewer.setInput(busList);
	    viewer.addSelectionChangedListener(comboSelectionChangeProvider);
	    
	    if(user.getAuthority()!=null)
	    		viewer.getCombo().setText(user.getAuthority().getAuthority());
	      	

	  }
  
  
  public boolean dialogChanged() {
	  if(!super.dialogChanged())
	     return false;
	  
	  setPageComplete(false);
	  
	  if (this.username.getText().length() == 0) {
	      updateStatus("Kullanici zorunludur");
	      return false;
	    }else
	      updateStatus(null);	 
	    
	    
	    if (this.password.getText().length() == 0) {
	      updateStatus("Sifre zorunludur.");
	      return false;
	    } else 
	    	updateStatus(null);	

	    
	    if (!this.password.getText().equals(this.passwordAgain.getText())) {
	        updateStatus("Sifreler ayni olmalidir");
	        return false;
	      } else 
	      	updateStatus(null);	

	    
	    if (this.viewer.getSelection().isEmpty()) {
	        updateStatus("Yetki secilmesi zorunludur");
	        return false;
	      } else 
	      	updateStatus(null);	
	    
	    updateStatus(null);
	    setPageComplete(true);
	    
	    return true;
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

public Text getUsername() {
	return username;
}


public void setUsername(Text username) {
	this.username = username;
}


public Text getPassword() {
	return password;
}


public void setPassword(Text password) {
	this.password = password;
}


public Text getPasswordAgain() {
	return passwordAgain;
}


public void setPasswordAgain(Text passwordAgain) {
	this.passwordAgain = passwordAgain;
}
  
}