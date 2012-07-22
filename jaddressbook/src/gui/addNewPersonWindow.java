package gui;

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


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import util.dbAccess;
import util.miscActions;

/*
 * Create "Add new Person dialog"
 * @author binary
 */
public class addNewPersonWindow extends Dialog{
	
	private dbAccess db;
	private Text tComments;
	private Tree addressBooksTree;
	private Button bNext;
	
	
	 public addNewPersonWindow(Shell parentShell,dbAccess db)
	 {
	   super(parentShell);
	   this.db=db;
	 }
	 /*
	  * Create Content
	  * (non-Javadoc)
	  * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	  */
	     protected Control createDialogArea(Composite parent){
	         parent.getShell().setText("Add new Person");
	         Composite dlgContent = (Composite)super.createDialogArea(parent);
	         dlgContent.setLayout(new GridLayout(1,false));
	         
	         Composite top=new Composite(dlgContent,SWT.NONE);
	         top.setLayout(new FillLayout(SWT.HORIZONTAL));
	         top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	         Label ltop=new Label(top,SWT.NONE);
	         //ltop.setText("Add new Person");
	         ltop.setImage(new Image(Display.getCurrent(),"./img/addcontact.png"));
	         
	         Composite content=new Composite(dlgContent,SWT.NONE);
	         content.setLayout(new GridLayout(1,false));
	         content.setLayoutData(new GridData(GridData.FILL_BOTH));
	         
	         addressBooksTree=new Tree(content,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
	         
	        
	         
	         addressBooksTree.setLayoutData(new GridData(GridData.FILL_BOTH));
	        
	         miscActions.buildAddressBookTree(db,addressBooksTree);
	         
	         new Label(content,SWT.NONE).setText("Comments : ");
	         
	         tComments=new Text(content,SWT.BORDER);
	         tComments.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	         
	         addressBooksTree.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					if (addressBooksTree.getSelectionCount()==1) {
					bNext.setEnabled(true);
					String data[]=(String[]) addressBooksTree.getSelection()[0].getData();				
					tComments.setText(data[3]);
					}
				}
	        	 
	         });
	         return parent;
	     }
	     
	  protected void createButtonsForButtonBar(Composite parent){
		  		createButton(parent, 0, "Cancel", false).setImage(new Image(getShell().getDisplay(),"./img/artwork/cancel.png"));
	    	   bNext=createButton(parent, 5, "Next", true);
	    	    bNext.setEnabled(false);
	    	    bNext.setImage(new Image(getShell().getDisplay(),"./img/artwork/next.png"));
	    	    //Setup Window size to display Buttons
	    	    parent.getShell().setSize(400,500);
	 }
	 protected void buttonPressed(int buttonId){
		 	if (buttonId==5) {
		 		String data[]=(String[]) addressBooksTree.getSelection()[0].getData();
		 		//System.out.println(data[0]);
		 		String personID=db.registerPerson(data[0]);
		 		
		 		this.close();
		 		
		 		editUserDataWindow dialog=new editUserDataWindow(getShell(),db,personID);
				dialog.open();
		 	}
	   	    super.buttonPressed(buttonId);
	  } 
}
