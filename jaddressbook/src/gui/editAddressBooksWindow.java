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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import util.dbAccess;
import util.miscActions;

/**
 *
 * @author binary
 */
public class editAddressBooksWindow extends Dialog{
    
    private Tree addressBooksTree;
    private dbAccess db;
    
    //Address Book Data
    private Text tABookName;
    private Text tABookComments;
    private Combo cABookParent; 
    
    public editAddressBooksWindow(Shell parentShell,dbAccess db)
    {
        super(parentShell);
        //super.setShellStyle(SWT.RESIZE);
        this.db=db;
    }
    
    
    protected Control createDialogArea(Composite parent){
        
        parent.getShell().setText("Edit Address Books");
        Composite dlgcontent = (Composite)super.createDialogArea(parent);
        dlgcontent.setLayout(new GridLayout(1,true));
        
        //Top Message
        Composite top=new Composite(dlgcontent,SWT.NONE);
        top.setLayout(new FillLayout());
        top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Label topText=new Label(top,SWT.NONE);
        topText.setImage(new Image(Display.getCurrent(),"./img/editaddressbooks.png"));
        
        //Content of Dialog
        Composite content=new Composite(dlgcontent,SWT.NONE);
        content.setLayout(new FormLayout());
        content.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        FormData bookListData = new FormData();
        bookListData.top = new FormAttachment(0);
        bookListData.left = new FormAttachment(0);
        bookListData.right = new FormAttachment(100);
        bookListData.bottom = new FormAttachment(60);
        
        
        //Show Address Book Tree
        addressBooksTree=new Tree(content,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        addressBooksTree.setLayoutData(bookListData);
        
        Composite controls=new Composite(content,SWT.NONE);
        
        FormData controlsFormData = new FormData();
        controlsFormData.top = new FormAttachment(60);
        controlsFormData.left = new FormAttachment(0);
        controlsFormData.right = new FormAttachment(100);
        controlsFormData.bottom = new FormAttachment(100);
        
        controls.setLayout(new GridLayout(2,false));
        controls.setLayoutData(controlsFormData);
        
        Label lAbookName=new Label(controls,SWT.NONE);
        lAbookName.setText("Address book name : ");
        
        tABookName=new Text(controls,SWT.BORDER);
        tABookName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lAbookComments=new Label(controls,SWT.NONE);
        lAbookComments.setText("Address book comments : ");
        
        GridData gdata2=new GridData();
        gdata2.horizontalSpan=2;
        lAbookComments.setLayoutData(gdata2);
        
        tABookComments=new Text(controls,SWT.BORDER); 

        GridData gdata3=new GridData(GridData.FILL_HORIZONTAL);
        gdata3.horizontalSpan=2;
        tABookComments.setLayoutData(gdata3);       

        Label lAbookParent=new Label(controls,SWT.NONE);
        lAbookParent.setText("Address Book parent : ");
        
        cABookParent=new Combo(controls,SWT.BORDER | SWT.SIMPLE | SWT.DROP_DOWN | SWT.READ_ONLY); 
        cABookParent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Composite cButtons=new Composite(controls,SWT.NONE);
        cButtons.setLayout(new FillLayout(SWT.HORIZONTAL));
        GridData gdata1=new GridData(GridData.FILL_HORIZONTAL);
        gdata1.horizontalSpan=2;
        cButtons.setLayoutData(gdata1);
        
        Button bABookAdd=new Button(cButtons,SWT.NONE);
       
        bABookAdd.setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/add.png"));
        
        Button bABookEdit=new Button(cButtons,SWT.NONE);
        
        bABookEdit.setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/edit.png"));
        
        Button bABookRemove=new Button(cButtons,SWT.NONE);
        bABookRemove.setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/remove.png"));
       
        this.buildData();
        
        //Adding handlers...
        
        addressBooksTree.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (addressBooksTree.getSelectionCount()==1)
				{
					String data[]=(String[]) addressBooksTree.getSelection()[0].getData();
					tABookName.setText(data[2]);
					tABookComments.setText(data[3]);
					
					if (data[1].equals("0"))  
							cABookParent.select(0);
					else {
						String data_abooks[][]=db.getAddressBooks();
						for(int i=0;i<data_abooks.length;i++)
						{
							if (data_abooks[i][0].equals(data[1])) 
								 cABookParent.select(i+1);
								
						}
					}
				}
				else {
					cABookParent.select(0);
					tABookName.setText("");
					tABookComments.setText("");
				}
			}
        	
        	
        });
        //Add new Address Book
        bABookAdd.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				int pID=cABookParent.getSelectionIndex();
				String data[][]=db.getAddressBooks();
				if (pID!=0)
					db.addAddressBook(data[pID-1][0], tABookName.getText(), tABookComments.getText());
				else
					db.addAddressBook("0", tABookName.getText(), tABookComments.getText());
				buildData();
			}
        	
        });
        //Edit Selected Address Book
        bABookEdit.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (addressBooksTree.getSelectionCount()==1)
				{
					String data[]=(String[]) addressBooksTree.getSelection()[0].getData();
					
					int pID=cABookParent.getSelectionIndex();
					String data2[][]=db.getAddressBooks();
					if (pID!=0)
					{
						if (isParent(data[0],data2[pID-1][0])==Boolean.FALSE)
						db.updateAddressBook(data[0], data2[pID-1][0], tABookName.getText(), tABookComments.getText());
						else
						{
							MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
				            dialog.setText("Failed to set parent");
				            dialog.setMessage("Failed to set parent");
				            dialog.open();
				            return ;
						}
					}
					else
					db.updateAddressBook(data[0], "0", tABookName.getText(), tABookComments.getText());
					buildData();
				}
			}}
        );
        
        //Remove selected Address Book
        bABookRemove.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (addressBooksTree.getSelectionCount()==1)
				{
					String data[]=(String[]) addressBooksTree.getSelection()[0].getData();
					
					MessageBox dialog = new MessageBox(getShell(),SWT.YES | SWT.NO | SWT.ICON_INFORMATION);
		            dialog.setText("Confirm Delete");
		            dialog.setMessage("Confirm delete of addressbook");
		            if (dialog.open()==SWT.YES)
					db.removeAddressBook(data[0]);
					buildData();
				}
			}});
        return parent;
    }
    
    /*
     * Create buttons
     */
    protected void createButtonsForButtonBar(Composite parent){
    createButton(parent, 1, "Ok", true).setImage(new Image(Display.getCurrent(),"./img/artwork/ok.png"));
    //Setup Window size to display Buttons
    parent.getShell().setSize(400,500);
    }
     
    protected void buttonPressed(int buttonId){
    super.buttonPressed(buttonId);
  } 
    
    /*
     * Build Address Book data
     */
    private void buildData() {
    	addressBooksTree.removeAll();
    		
        miscActions.buildAddressBookTree(db,addressBooksTree);
        cABookParent.removeAll();
        //cABookParent.setItems(new String[]{});
        String data[][]=db.getAddressBooks();
        cABookParent.add("No Parent");
        if (data==null) return ;
        for(int i=0;i<data.length;i++)
        	cABookParent.add(data[i][2],i+1);
    }
    
    /*
     * Check if parent parentID have child childID
     * @param parentID ID of Parent
     * @param childID ID of child
     * @return true if is, false if not
     */
    private Boolean isParent(String parentID,String childID) {
    	String data[]=db.getAddressBook(childID);
    	
    	if (data==null) return Boolean.FALSE;
    	if (data[0].equals("0")) return Boolean.FALSE;
    	if (data[0].equals(parentID)) return Boolean.TRUE;    	
    	return isParent(parentID,data[1]);
    }
}
