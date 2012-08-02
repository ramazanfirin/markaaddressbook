package wizards;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.User;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

class UserUpdatePage extends PersonPage {
  
  private ISelection selection;
  
  ComboViewer viewer;
  
  private Text username;

  private Text password;
  
  private Text currentPassword;

  private Text passwordAgain;

  Button updatePassword;
  
  public UserUpdatePage(ISelection selection, AbsractInterface _entity,String title) {
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
      //layout.
	  
	  User user = (User)getEntity();
      Label label= null;
	       
      updatePassword = new Button(container, SWT.CHECK);
      updatePassword.setText(Util.getString("checkPasswordUpdate"));
      updatePassword.addSelectionListener(new SelectionListener() {
	      public void widgetSelected(SelectionEvent event) {
	    	  controlCheckButton();
	    	  dialogChanged();
	      }
	      public void widgetDefaultSelected(SelectionEvent event) {
	        //text.setText("No worries!");
	      }
	 });
      new Label(container, SWT.NONE);
              
	  label = new Label(container, SWT.NULL);
	  label.setText(Util.getString("user.name"));
	    
	  username = new Text(container, SWT.BORDER | SWT.SINGLE);
	  username.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  username.setText(user.getUsername());
	  username.addModifyListener(textModifyListener);
	    
	  label = new Label(container, SWT.NULL);
	  label.setText(Util.getString("user.currentAgain"));

	  currentPassword = new Text(container, SWT.BORDER | SWT.SINGLE);
	  currentPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  currentPassword.setEchoChar('*');
	  currentPassword.addModifyListener(textModifyListener);
	  
	  label = new Label(container, SWT.NULL);
	  label.setText(Util.getString("user.password"));

      password = new Text(container, SWT.BORDER | SWT.SINGLE);
	  password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  password.setEchoChar('*');
	  password.addModifyListener(textModifyListener);

	  label = new Label(container, SWT.NULL);
	  label.setText(Util.getString("user.passwordAgain"));

	  passwordAgain = new Text(container, SWT.BORDER | SWT.SINGLE);
	  passwordAgain.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	  passwordAgain.setEchoChar('*');
	  passwordAgain.addModifyListener(textModifyListener);
	  
	  controlCheckButton();
      
	  }
  
  public void controlCheckButton(){
	  username.setEnabled(updatePassword.getSelection());
	  currentPassword.setEnabled(updatePassword.getSelection());
	  password.setEnabled(updatePassword.getSelection());
	  passwordAgain.setEnabled(updatePassword.getSelection());
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
	  
	  if(updatePassword.getSelection()){
		  if (this.currentPassword.getText().length() == 0) {
		      updateStatus("Mevcut sifre zorundulur");
		      return false;
		    }else
		      updateStatus(null);
		  
		  if (this.password.getText().length() == 0) {
		      updateStatus("Sifre zorundulur");
		      return false;
		    }else
		      updateStatus(null);
		  
		  if (this.passwordAgain.getText().length() == 0) {
		      updateStatus("Sifre tekrar zorundulur");
		      return false;
		    }else
		      updateStatus(null);
		  
		  if (!this.password.getText().equals(this.passwordAgain.getText())) {
		        updateStatus("Sifreler ayni olmalidir");
		        return false;
		      } else 
		      	updateStatus(null);	
	  }
	       
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


public Text getCurrentPassword() {
	return currentPassword;
}


public void setCurrentPassword(Text currentPassword) {
	this.currentPassword = currentPassword;
}


public Button getUpdatePassword() {
	return updatePassword;
}


public void setUpdatePassword(Button updatePassword) {
	this.updatePassword = updatePassword;
}
  
}