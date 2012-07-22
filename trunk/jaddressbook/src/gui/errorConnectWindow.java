/********************************************************************************
 *
 * jAddressBook - advanced address book
 *
 * Copyright (C) 2006 jAddressBook development team
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details ( see the LICENSE file ).
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 ********************************************************************************/

package gui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/*
 * Error DB connection window
 * @author binary
 */
public class errorConnectWindow extends Dialog{

	private String msg;
	public errorConnectWindow(Shell parentShell,String msg)
    {
        super(parentShell);
        this.msg=msg;
    }
	
	/*
	 * Create Dialog content
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	 protected Control createDialogArea(Composite parent){
	        
	        parent.getShell().setText("Failed to connect with DataBase");
	        Composite dlgcontent = (Composite)super.createDialogArea(parent);
	        dlgcontent.setLayout(new GridLayout(2,false));
	        
	        new Label(dlgcontent,SWT.NONE).setImage(getShell().getDisplay().getSystemImage(SWT.ICON_ERROR));
	        new Label(dlgcontent,SWT.NONE).setText("Failed to connect with Data Base, error message :\n"+msg);
	        return parent;
	 }
	 
	 /*
	  * Create buttons of dialog
	  * (non-Javadoc)
	  * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	  */
	 protected void createButtonsForButtonBar(Composite parent){
		    createButton(parent, SWT.RETRY, "Retry", true).setImage(new Image(Display.getCurrent(),"./img/artwork/check_connection.png"));
		    createButton(parent, SWT.HELP, "Setup", false).setImage(new Image(Display.getCurrent(),"./img/artwork/wizard.png"));
		    createButton(parent, SWT.Settings, "Settings", false).setImage(new Image(Display.getCurrent(),"./img/artwork/db.png"));		    
		    createButton(parent, SWT.CLOSE, "Exit ", false).setImage(new Image(Display.getCurrent(),"./img/artwork/cancel.png"));
		   
		    //parent.getShell().setSize(400,500);
		    }
		     
		    protected void buttonPressed(int buttonId){
		    super.buttonPressed(buttonId);
		    super.setReturnCode(buttonId);
		    super.close();
		  } 
}
