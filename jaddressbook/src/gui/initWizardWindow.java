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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import util.dbAccess;
import util.programSettings;

/*
 * DataBase init Wizard
 * @author binary
 */
public class initWizardWindow extends Dialog{
	
	/*
	 * Settings
	 */
	private programSettings pSettings;
    private dbAccess db;
    
    private int ok=1;
    
	public initWizardWindow(Shell parent) {
		super(parent);
	}
	
	/*
	 * Create dialog content
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent){
        
        parent.getShell().setText("Initialize Data Base");
        Composite dlgcontent = (Composite)super.createDialogArea(parent);
        dlgcontent.setLayout(new GridLayout(1,true));
        
        //Top Message
        Composite top=new Composite(dlgcontent,SWT.NONE);
        top.setLayout(new FillLayout());
        top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Label topText=new Label(top,SWT.NONE);
        topText.setImage(new Image(Display.getCurrent(),"./img/initdb.png"));
        //Content of Dialog
        Composite content=new Composite(dlgcontent,SWT.NONE);
        content.setLayout(new GridLayout(2,false));
        content.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        /*
         * Settings file
         */
        new Label(content,SWT.NONE).setText("Settings file : ");
        
        pSettings=new programSettings();
        if(pSettings.loadSettings()==0) {
        	Label l1=new Label(content,SWT.NONE);
        	l1.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_GREEN));
        	l1.setText("OK");
        } else {
        	ok=0;
        	Label l1=new Label(content,SWT.NONE);
        	l1.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_RED));
        	l1.setText("Failed to load");
        }

        /*
         * DB connection
         */
        db=new dbAccess(pSettings.getSettings());
        
        new Label(content,SWT.NONE).setText("Data Base : ");
        
        if (db.dbConnect()==null) {
        	Label l1=new Label(content,SWT.NONE);
        	l1.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_GREEN));
        	l1.setText("OK");
        } else  {
        	//ok=0;
        	Label l1=new Label(content,SWT.NONE);
        	l1.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_RED));
        	l1.setText("Failed to connect");
        }
        
        new Label(content,SWT.NONE).setText("Data Base Name : ");
        new Label(content,SWT.NONE).setText(pSettings.getSettings().getProperty("dbName")+"");
        
        if (ok==0) {
        	Label l1=new Label(content,SWT.NONE);
        	l1.setForeground(getShell().getDisplay().getSystemColor(SWT.COLOR_RED));
        	l1.setText("You must edit Data Base settings to initialize data base");
        	GridData g1=new GridData(GridData.FILL_HORIZONTAL);
        	g1.horizontalSpan=2;
        	l1.setLayoutData(g1);
        }
        return parent;
	}
	/*
	 * Create buttons
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	 protected void createButtonsForButtonBar(Composite parent){
		  Button okButton=createButton(parent, 1, "Ok", true);
		  okButton.setImage(new Image(Display.getCurrent(),"./img/artwork/ok.png"));
		  if (ok==0)
			   okButton.setEnabled(false);
		  createButton(parent, 0, "Cancel", true).setImage(new Image(Display.getCurrent(),"./img/artwork/cancel.png"));
		  //Setup Window size to display Buttons
		//  parent.getShell().setSize(400,500);
		  }
		     
    protected void buttonPressed(int buttonId){
    	   if (buttonId==1) {
    		   MessageBox dialog = new MessageBox(getShell(),SWT.YES | SWT.NO | SWT.ICON_WARNING);
               dialog.setText("Initialize DB");
               dialog.setMessage("Do you want to initialize DB ?\nALL data will be losed !");
               if (dialog.open()==SWT.YES) 
            	  if (db.initDB()==-1) {
            		  MessageBox dialogFailed = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
            		  dialogFailed.setText("Failed to initialize DB");
            		  dialogFailed.setMessage("Failed to initialize DB\ncheck DB connection settings");
            		  dialogFailed.open();
            	  } else {
            		  MessageBox dialogOk = new MessageBox(getShell(),SWT.OK | SWT.ICON_INFORMATION);
            		  dialogOk.setText("DB initialized");
            		  dialogOk.setMessage("Data Base initialized");
            		  dialogOk.open();
            	  }
    	   }
		    super.buttonPressed(buttonId);
		  } 
}
