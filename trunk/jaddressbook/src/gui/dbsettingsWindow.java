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

import java.util.Properties;

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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import util.dbAccess;
import util.programSettings;

/**
 *
 * @author binary
 */
public class dbsettingsWindow extends Dialog{
    
    private Text tJDBCDriver;
    private Text tDBType;
    private Text tDBHost;
    private Text tDBName;
    private Text tDBLogin;
    private Text tDBPassword;
    private programSettings pSettings;
    
    /** Creates a new instance of dbsettingsWindow */
      public dbsettingsWindow(Shell parentShell){
        super(parentShell);
        pSettings=new programSettings();
}
      protected Control createDialogArea(Composite parent){
        parent.getShell().setText("DB Access settings");
        Composite content = (Composite)super.createDialogArea(parent);
        content.setLayout(new GridLayout(2,false));
        
        Composite top=new Composite(content,SWT.NONE);
        top.setLayout(new FillLayout());
        GridData g1=new GridData(GridData.FILL_HORIZONTAL);
        g1.horizontalSpan=2;
        top.setLayoutData(g1);
        
        Label ltop=new Label(top,SWT.NONE);
        ltop.setImage(new Image(Display.getCurrent(),"./img/dbsettings.png"));
        
        Label lJDBCDriver=new Label(content,SWT.NONE);
        lJDBCDriver.setText("JDBC Driver : ");
        
        tJDBCDriver=new Text(content,SWT.BORDER);
        tJDBCDriver.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lDBType=new Label(content,SWT.NONE);
        lDBType.setText("DB Type : ");
        
        tDBType=new Text(content,SWT.BORDER);
        tDBType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lDBHost=new Label(content,SWT.NONE);
        lDBHost.setText("DB Host : ");
        
        tDBHost=new Text(content,SWT.BORDER);
        tDBHost.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lDBName=new Label(content,SWT.NONE);
        lDBName.setText("DB Name : ");
        
        tDBName=new Text(content,SWT.BORDER);
        tDBName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lDBLogin=new Label(content,SWT.NONE);
        lDBLogin.setText("DB Login : ");
        
        tDBLogin=new Text(content,SWT.BORDER);
        tDBLogin.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lDBPassword=new Label(content,SWT.PASSWORD);
        lDBPassword.setText("DB Password : ");
        
        tDBPassword=new Text(content,SWT.BORDER);
        tDBPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Button checkConnection=new Button(content,SWT.NONE);
        checkConnection.setText("Check connection");
        checkConnection.setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/check_connection.png"));        
        loadSettings();
        
        checkConnection.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
            Properties p=getSettings();
            
            dbAccess db=new dbAccess(p);
            if (!db.getDriverData())
            {
                MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
                dialog.setText("Connection checking");
                dialog.setMessage("JDBC Driver not found !");
                dialog.open();
                return ;
            }
            String str=db.dbConnect();
            if (str!=null)
            {
                MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
                dialog.setText("Connection checking");
                dialog.setMessage("Failed to connect with DB !\nMessage from DB :\n"+str);
                dialog.open();
                return ;
            }
            MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_INFORMATION);
            dialog.setText("Connection checking");
            dialog.setMessage("Connection estabilished");
            dialog.open();
            }
            
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        
        content.pack();
        return parent;
    }
      
    protected void createButtonsForButtonBar(Composite parent){
    createButton(parent, 0, "Cancel", false).setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/cancel.png"));
    createButton(parent, 2, " Defaults", false).setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/load_defaults.png"));;
    createButton(parent, 1, "Ok", true).setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/ok.png"));
    //Setup Window size to display Buttons
  //  parent.getShell().setSize(330,330);
    }
    
    protected void buttonPressed(int buttonId){
        if (buttonId==1)
        {
            Properties p=getSettings();
            pSettings.setSettings(p);
            pSettings.saveSettings();
        }
        if (buttonId==2)
        {
            this.loadSettings();
        }
        if (buttonId!=2)
        super.buttonPressed(buttonId);
  } 
    
    /*
     * Get Settings
     */
    private Properties getSettings()
    {
        Properties settings=new Properties();
        
        settings.setProperty("JDBCDriver",tJDBCDriver.getText());
        settings.setProperty("dbType",tDBType.getText());
        settings.setProperty("dbHost",tDBHost.getText());
        settings.setProperty("dbName",tDBName.getText());
        settings.setProperty("dbLogin",tDBLogin.getText());
        settings.setProperty("dbPassword",tDBPassword.getText());
        
        return settings;
    }
    
    /*
     * Load default settings from file
     */
    private void loadSettings(){
        if (pSettings.loadSettings()==-1)
        		return ;
        
        Properties settings=pSettings.getSettings();
        
        tJDBCDriver.setText(settings.getProperty("JDBCDriver"));
        tDBType.setText(settings.getProperty("dbType"));
        tDBHost.setText(settings.getProperty("dbHost"));
        tDBName.setText(settings.getProperty("dbName"));
        tDBLogin.setText(settings.getProperty("dbLogin"));
        tDBPassword.setText(settings.getProperty("dbPassword"));
    }
}
