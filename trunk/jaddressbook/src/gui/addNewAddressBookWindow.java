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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import util.dbAccess;
import util.miscActions;

/*
 * Add new AddressBook
 * @author binary
 */
public class addNewAddressBookWindow extends Dialog{

	private dbAccess db;
	private Tree addressBooksTree;
	private Text tAComments;
	
	private Text tName;
	private Text tComments;
	
	public addNewAddressBookWindow(Shell parentShell,dbAccess db)
	 {
	   super(parentShell);
	   this.db=db;
	 }
	
	 protected Control createDialogArea(Composite parent){
         parent.getShell().setText("Add new Address Book");
         Composite dlgContent = (Composite)super.createDialogArea(parent);
         dlgContent.setLayout(new GridLayout(1,false));
      
         Composite top=new Composite(dlgContent,SWT.NONE);
         top.setLayout(new FillLayout(SWT.HORIZONTAL));
         top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         new Label(top,SWT.NONE).setImage(new Image(Display.getCurrent(),"./img/addaddressbook.png"));
         
         Composite content=new Composite(dlgContent,SWT.NONE);
         content.setLayout(new GridLayout(2,false));
         content.setLayoutData(new GridData(GridData.FILL_BOTH));
         
         new Label(content,SWT.NONE).setText("Name(*) : ");
         
         tName=new Text(content,SWT.BORDER);
         tName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         new Label(content,SWT.NONE).setText("Comments : ");
         
         tComments=new Text(content,SWT.BORDER);
         tComments.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         /*
          * Address Books tree
          */
         Label l1=new Label(content,SWT.NONE);
         GridData g1=new GridData(GridData.FILL_HORIZONTAL);
         g1.horizontalSpan=2;
         l1.setText("Select Parent Address Book(*) : ");
         l1.setLayoutData(g1);
         
         addressBooksTree=new Tree(content,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
         GridData g2=new GridData(GridData.FILL_BOTH);
         g2.horizontalSpan=2;
         addressBooksTree.setLayoutData(g2);

         TreeItem Aitem = new TreeItem(addressBooksTree, SWT.NONE);
         Aitem.setText("No Parent");
         Aitem.setData(new String[]{"0","","","No Parent"});
         
         miscActions.buildAddressBookTree(db,addressBooksTree);
         
         new Label(content,SWT.NONE).setText("Comments : ");
         
         tAComments=new Text(content,SWT.BORDER);
         tAComments.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
         
         addressBooksTree.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (addressBooksTree.getSelectionCount()==1) {
				String data[]=(String[]) addressBooksTree.getSelection()[0].getData();				
				tAComments.setText(data[3]);
				}
			}
        	 
         });
         
         new Label(content,SWT.NONE).setText("* - Must be field");
         return parent;
	 }
	 
	 protected void createButtonsForButtonBar(Composite parent){
	  		createButton(parent, 1, "Create", true).setImage(new Image(getShell().getDisplay(),"./img/artwork/ok.png"));
	  		createButton(parent, 0, "Cancel", false).setImage(new Image(getShell().getDisplay(),"./img/artwork/cancel.png"));
 	    //Setup Window size to display Buttons
 	    parent.getShell().setSize(400,550);
	 	}
	 
protected void buttonPressed(int buttonId){
	if (buttonId==1) {
		//Check name of Address Book
		if (tName.getText().length()==0) {
			MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
	        dialog.setText("Enter name for Address Book");
	        dialog.setMessage("Enter Name for new Address Book");
	        dialog.open();
	        return ;
		}
		//Check Selected Parent
		if (addressBooksTree.getSelectionCount()!=1) {
			MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
	        dialog.setText("Select Parent");
	        dialog.setMessage("Select Parent address Book");
	        dialog.open();
	        return ;
		}
		String data[]=(String[]) addressBooksTree.getSelection()[0].getData();
		db.addAddressBook(data[0], tName.getText(), tComments.getText());
	}
	    super.buttonPressed(buttonId);
} 
}
