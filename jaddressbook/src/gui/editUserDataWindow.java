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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import util.dbAccess;
import util.miscActions;

public class editUserDataWindow extends Dialog{
	
	private dbAccess db;
	
	private String userID;
	//Person personal data 
	private Text tSurName;
	private Text tName;
	private Text tNickName;
	private Text tComments;
	private Combo cAddressBookParent;
	
	//Person Foto
	private Label lPersonFoto;
	private Text tUserFotoComments;
	private Text tPathFoto;
	private ScrolledComposite scroll1;
	
	//Address
	private Table tabAddressData;
	//Tel
	private Table tabTelData;
	//TelMob
	private Table tabTelMobData;
	//Fax
	private Table tabFaxData;
	//Web Page
	private Table tabWebData;
	//Email
	private Table tabEMailData;
	
	 public editUserDataWindow(Shell parentShell,dbAccess db,String userID)
	    {
	        super(parentShell);
	        super.setShellStyle(SWT.RESIZE | SWT.DIALOG_TRIM);
	        
	        this.db=db;
	        this.userID=userID;
	    }
	 
	 protected Control createDialogArea(Composite parent){
	        
	        parent.getShell().setText("Edit person data");
	        Composite dlgcontent = (Composite)super.createDialogArea(parent);
	        dlgcontent.setLayout(new GridLayout(1,false));
	        
	        Composite top=new Composite(dlgcontent,SWT.NONE);
	        top.setLayout(new FillLayout());
	        
	        top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	        Label dlgTitle=new Label(top,SWT.None);
	        dlgTitle.setImage(new Image(Display.getCurrent(),"./img/editcontact.png"));
	        
	        Composite content=new Composite(dlgcontent,SWT.NONE);
	        content.setLayout(new FillLayout());
	        content.setLayoutData(new GridData(GridData.FILL_BOTH));
	        
	        //TabFolder tabFolder = new TabFolder(content, SWT.NONE);
	        
	        CTabFolder tabFolder = new CTabFolder(content, SWT.BORDER);
	        //cTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
	        
	        tabFolder.setSimple(false);
	        tabFolder.setUnselectedImageVisible(false);
	        tabFolder.setUnselectedCloseVisible(false);
	        
	        CTabItem personDataTab = new CTabItem(tabFolder,SWT.NONE);
	        personDataTab.setText("Person Data");
	        Composite personDataComp=new Composite(tabFolder,SWT.NONE);
	        personDataTab.setControl(personDataComp);

	        personDataComp.setLayout(new GridLayout(2,false));
	        
	        Label lSurName=new Label(personDataComp,SWT.NONE);
	        lSurName.setText("Sur name : ");
	        
	        tSurName=new Text(personDataComp,SWT.BORDER);
	        tSurName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	        
	        Label lName=new Label(personDataComp,SWT.NONE);
	        lName.setText("Name : ");
	        
	        tName=new Text(personDataComp,SWT.BORDER);
	        tName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	        
	        Label lNickName=new Label(personDataComp,SWT.NONE);
	        lNickName.setText("Nick Name : ");
	        
	        tNickName=new Text(personDataComp,SWT.BORDER);
	        tNickName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	        
	        Label lAddressBookParent=new Label(personDataComp,SWT.NONE);
	        lAddressBookParent.setText("Address Book : ");
	        
	        cAddressBookParent=new Combo(personDataComp,SWT.BORDER | SWT.SIMPLE | SWT.DROP_DOWN | SWT.READ_ONLY);
	        cAddressBookParent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	        
	        Label lComments=new Label(personDataComp,SWT.NONE);
	        lComments.setText("Comments : ");
	        lComments.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
	        
	        tComments=new Text(personDataComp,SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
	        tComments.setLayoutData(new GridData(GridData.FILL_BOTH));
	        
	        CTabItem personFotoTab = new CTabItem(tabFolder,SWT.NONE);
	        personFotoTab.setText("Person Foto");
	        Composite personFotoComp=new Composite(tabFolder,SWT.NONE);
	        personFotoTab.setControl(personFotoComp);

	        personFotoComp.setLayout(new GridLayout(2,false));
	        
	        scroll1=new ScrolledComposite(personFotoComp,SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	        GridData gdata=new GridData(GridData.FILL_BOTH);
	        gdata.horizontalSpan=2;
	        scroll1.setLayout(new FillLayout(SWT.CENTER | SWT.VERTICAL));
	        scroll1.setLayoutData(gdata);
	        
	        lPersonFoto=new Label(scroll1,SWT.CENTER);
	        scroll1.setExpandHorizontal(true);
	        scroll1.setExpandVertical(true);
	        scroll1.setContent(lPersonFoto);
	        
	        Label luserFotoComments=new Label(personFotoComp,SWT.NONE);
	        luserFotoComments.setText("Comments : ");
	        
	        tUserFotoComments=new Text(personFotoComp,SWT.BORDER);
	        tUserFotoComments.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	        
	        Label lpathFoto=new Label(personFotoComp,SWT.NONE);
	        lpathFoto.setText("Path to new foto : ");
	        
	        tPathFoto=new Text(personFotoComp,SWT.BORDER);
	        tPathFoto.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	        
	        Composite cButtons=new Composite(personFotoComp,SWT.NONE);
	        cButtons.setLayout(new FillLayout(SWT.HORIZONTAL));
	        GridData g2=new GridData(GridData.HORIZONTAL_ALIGN_END);
	        g2.horizontalSpan=2;
	        cButtons.setLayoutData(g2);
	        
	        Button bBrowseFoto=new Button(cButtons,SWT.NONE);
	        bBrowseFoto.setText("Browse for new foto");
	        bBrowseFoto.setImage(new Image(Display.getCurrent(),"./img/artwork/open.png"));
	        //Browse Foto
	        bBrowseFoto.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					FileDialog dialog = new FileDialog(getShell(), SWT.OPEN );
					dialog.setFilterExtensions(new String[] {"*.*"});
					String str=dialog.open();
					if (str!=null) {
					tPathFoto.setText(str);
					try {
						setupImage(new FileInputStream(str));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
					
				}
	        	
	        });
	        
	        Button bReloadFoto=new Button(cButtons,SWT.NONE);
	        bReloadFoto.setText("Reload image ");
	        bReloadFoto.setImage(new Image(Display.getCurrent(),"./img/artwork/reload.png"));
	        
	        bReloadFoto.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					String str=tPathFoto.getText();
					try {
						setupImage(new FileInputStream(str));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
	        	
	        });
	        
	        Button bLoadNoFoto=new Button(cButtons,SWT.NONE);
	        bLoadNoFoto.setText("No foto");
	        bLoadNoFoto.setImage(new Image(Display.getCurrent(),"./img/artwork/foto_undo.png"));
	        bLoadNoFoto.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					tPathFoto.setText("./img/nofoto.png");
					try {
						setupImage(new FileInputStream(tPathFoto.getText()));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        	
	        });
	        
	        //Address Tab
	        Properties data=new Properties();
	        data.setProperty("Title", "Address");
	        data.setProperty("Column1","Address");
	        data.setProperty("Column2","Comments");
	        
	        data.setProperty("ownerID", userID);
	        data.setProperty("tableName","address");

	        data.setProperty("Field1","address");
	        data.setProperty("Field2","comments");
	        
	        data.setProperty("Field1Edit", "Address :");
	        data.setProperty("Field2Edit", "Comments :");
	        tabAddressData=miscActions.createPersonDataTab(db,data,tabFolder);

	        //Tel Tab
	        data.setProperty("Title", "Telephone");
	        data.setProperty("Column1","Telephone");
	        data.setProperty("Column2","Comments");
	        
	        data.setProperty("ownerID", userID);
	        data.setProperty("tableName","tel");

	        data.setProperty("Field1","tel");
	        data.setProperty("Field2","comments");
	        
	        data.setProperty("Field1Edit", "Telephone :");
	        data.setProperty("Field2Edit", "Comments :");
	        tabTelData=miscActions.createPersonDataTab(db,data,tabFolder);

	        //MobTel Tab
	        data.setProperty("Title", "Mobile Telephone");
	        data.setProperty("Column1","Mobile Telephone");
	        data.setProperty("Column2","Comments");
	        
	        data.setProperty("ownerID", userID);
	        data.setProperty("tableName","telmob");

	        data.setProperty("Field1","telmob");
	        data.setProperty("Field2","comments");
	        
	        data.setProperty("Field1Edit", "Mobile Telephone :");
	        data.setProperty("Field2Edit", "Comments :");
	        tabTelMobData=miscActions.createPersonDataTab(db,data,tabFolder);
	        
	        //Fax Tab
	        data.setProperty("Title", "Fax");
	        data.setProperty("Column1","Fax");
	        data.setProperty("Column2","Comments");
	        
	        data.setProperty("ownerID", userID);
	        data.setProperty("tableName","fax");

	        data.setProperty("Field1","fax");
	        data.setProperty("Field2","comments");
	        
	        data.setProperty("Field1Edit", "Fax :");
	        data.setProperty("Field2Edit", "Comments :");
	        tabFaxData=miscActions.createPersonDataTab(db,data,tabFolder);
	        
	        //WebPage
	        data.setProperty("Title", "Web Page");
	        data.setProperty("Column1","Web Page");
	        data.setProperty("Column2","Comments");
	        
	        data.setProperty("ownerID", userID);
	        data.setProperty("tableName","web");

	        data.setProperty("Field1","web");
	        data.setProperty("Field2","comments");
	        
	        data.setProperty("Field1Edit", "Web Page :");
	        data.setProperty("Field2Edit", "Comments :");
	        tabWebData=miscActions.createPersonDataTab(db,data,tabFolder);
	        
	        //WebPage
	        data.setProperty("Title", "E-Mail");
	        data.setProperty("Column1","E-Mail");
	        data.setProperty("Column2","Comments");
	        
	        data.setProperty("ownerID", userID);
	        data.setProperty("tableName","email");

	        data.setProperty("Field1","email");
	        data.setProperty("Field2","comments");
	        
	        data.setProperty("Field1Edit", "E-Mail :");
	        data.setProperty("Field2Edit", "Comments :");
	        tabEMailData=miscActions.createPersonDataTab(db,data,tabFolder);
	        
	        //Get Data to complete user form
	        this.getData();
	        return parent;
	 }
	 
	//Setup Image
	 private void setupImage(InputStream in) {
		 Image img=new Image(Display.getCurrent(),in);
		 lPersonFoto.setImage(img);
		 scroll1.setMinWidth(img.getImageData().width);
		 scroll1.setMinHeight(img.getImageData().height);
		 }
	 
protected void createButtonsForButtonBar(Composite parent){
    createButton(parent, 1, "Ok", true).setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/ok.png"));
    createButton(parent, 0, "Cancel", false).setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/cancel.png"));;
    //Setup Window size to display Buttons
    parent.getShell().setSize(500,450);
    }
     
    protected void buttonPressed(int buttonId){
    	if (buttonId==1) {
    		setData();
    	}
    super.buttonPressed(buttonId);
  } 
    
    /*
     * Set data to DB
     */
    private void setData() {
    	int aBookID=cAddressBookParent.getSelectionIndex();
    	
    	//if (aBookID!=0) {
    		String data[][]=db.getAddressBooks();
    		aBookID=Integer.parseInt(data[aBookID][0]);
    	//}
     	db.setPersonPersonalData(userID, new String[]{aBookID+"",tSurName.getText(),tName.getText(),tNickName.getText(),tComments.getText()});
     	if (tPathFoto.getText().length()!=0) {
     		try {
     			File f=new File(tPathFoto.getText());
     			db.setPersonFoto(userID, new FileInputStream(f), f.length());
     		}catch(IOException e) {
     			MessageBox dialog = new MessageBox(new Shell(Display.getCurrent()),SWT.OK | SWT.ICON_ERROR);
	            dialog.setText("Failed to add Foto");
	            dialog.setMessage("Foto image not found !");
	            dialog.open();
     		}
     	}
     	db.setPersonFotoComments(userID, tUserFotoComments.getText());
     	
     	db.setPersonData(userID, this.tabAddressData, "address");
     	db.setPersonData(userID, this.tabTelData, "tel");
     	db.setPersonData(userID, this.tabTelMobData, "telmob");
     	db.setPersonData(userID, this.tabFaxData, "fax");
     	db.setPersonData(userID, this.tabWebData, "web");
     	db.setPersonData(userID, this.tabEMailData, "email");
    }
    
    /*
     * Get Data From DB
     */
    private void getData() {
    	//Build Address Books list
    	cAddressBookParent.removeAll();
        String data[][]=db.getAddressBooks();
        //cAddressBookParent.add("No Parent");
        for(int i=0;i<data.length;i++)
        	cAddressBookParent.add(data[i][2],i);
        
        //Load Person's data
        String pdata[]=db.getPersonPersonalData(userID);
        tSurName.setText(pdata[2]);
        tName.setText(pdata[3]);
        tNickName.setText(pdata[4]);
        tComments.setText(pdata[5]);
        
        if (pdata[1].equals("0"))
        	cAddressBookParent.select(0);
        	else {
        		int i=0;
        		while(!data[i][0].equals(pdata[1])) i++;
        		cAddressBookParent.select(i);
        	}
        
        //Load User's foto
        setupImage(db.getPersonFoto(userID));
        tUserFotoComments.setText(db.getPersonFotoComments(userID));
        /*
        try {
			File f=new File("wm.gif");
			db.setPersonFoto(userID, new FileInputStream(f), f.length());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
    
}