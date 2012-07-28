package wizards;

import java.util.List;

import model.DBManager;
import model.model.Authority;
import model.model.Bus;
import model.model.User;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import util.Util;

import com.AddressBookNew;

class BasicUserPage extends WizardPage {
  
  private Text username;

  private Text password;

  private Text passwordAgain;

  private Text authority;
 
  private ISelection selection;
  
  ComboViewer viewer;

  

  
  public BasicUserPage(ISelection selection) {
    super("wizardPage");
    setTitle(Util.getString("user.name"));
    setDescription("This wizard creates a new user.");
    this.selection = selection;
    setPageComplete(false);
  }

  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    layout.verticalSpacing = 9;

    
    User user = (User)AddressBookNew.getInstance().getTabItemUser().getEntity();
    
    Label label = new Label(container, SWT.NULL);
    label.setText(Util.getString("user.name"));

    username = new Text(container, SWT.BORDER | SWT.SINGLE);
    username.setText(user.getUsername());

    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    username.setLayoutData(gd);
    username.addKeyListener(new KeyListener() {

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void keyReleased(KeyEvent e) {
			if (!username.getText().isEmpty()) {
				dialogChanged();
			}
		}

	});

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("user.password"));

    password = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    password.setLayoutData(gd);
    password.setText(user.getPassword());
    password.setEchoChar('*');
    
    password.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });

    label = new Label(container, SWT.NULL);
    label.setText(Util.getString("user.passwordAgain"));

    passwordAgain = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    passwordAgain.setLayoutData(gd);
    passwordAgain.setText(user.getPassword());
    passwordAgain.setEchoChar('*');
    createLine(container, layout.numColumns);

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
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
    	@Override
    	public void selectionChanged(SelectionChangedEvent event) {
    		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
    		if(selection.isEmpty()==false)
    		System.out.println(((Authority) selection.getFirstElement()).getAuthority());
    		dialogChanged();
    	}
    }); 
    
    if(user.getAuthority()!=null)
    		viewer.getCombo().setText(user.getAuthority().getAuthority());
    dialogChanged();
    setControl(container);
  // super.
  }



  
  public boolean dialogChanged() {

	 
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
    	
    if (this.viewer.getSelection().isEmpty()) {
        updateStatus("Yetki secilmesi zorunludur");
        return false;
      } else 
      	updateStatus(null);	
    
    
    if (!this.password.getText().equals(this.passwordAgain.getText())) {
        updateStatus("Sifreler ayni olmalidir");
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

public Text getAuthority() {
	return authority;
}

public void setAuthority(Text authority) {
	this.authority = authority;
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



  
}