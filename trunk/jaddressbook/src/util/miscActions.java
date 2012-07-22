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

package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 *
 * @author binary
 */
public class miscActions {
    
    /*
     * Create Tree 
     * @param db DB to extract data
     * @param tree Tree Class 
     */
    public static void buildAddressBookTree(dbAccess db,Tree tree) {
        String data[][]=db.getAddressBooks();
        int i;
       
        if (data==null)
        	return ;
        for(i=0;i<data.length;i++)
            if (data[i][1].equals("0"))
            {
            TreeItem item = new TreeItem(tree, SWT.NONE);
            item.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/abook_item.png"));
            item.setText(data[i][2]);
            item.setData(data[i]);
            buildAddressBookChildTree(db,item,data[i][0]);
            }
    }
    
    /*
     * Browse Childs...
     * @param db DB to access data
     * @param parentItem Parent item
     * @oaram parent Parent ID
     */
    public static void buildAddressBookChildTree(dbAccess db,TreeItem parentItem,String parentID) {
    	 String data[][]=db.getAddressBooks();
         int i;
     
         if (data==null)
        	 	return ;
         for(i=0;i<data.length;i++)
        	 if (data[i][1].equals(parentID))
        	 {
        		 TreeItem item=new TreeItem(parentItem,SWT.NONE);
        		 item.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/abook_item.png"));
        		 parentItem.setExpanded(true);
        		 item.setText(data[i][2]);
        		 item.setData(data[i]);
        		 buildAddressBookChildTree(db,item,data[i][0]);
        	 }
         
    }
    
    /*
     * Create Person Data Tab
     * @param db Data Base to access data
     * @param data Data to create Tab
     * @param tabFolder Parent Tab folder
     */
    public static Table createPersonDataTab(dbAccess db,Properties data, CTabFolder tabFolder) {
    	
    	//Create and setup Content
    	CTabItem dataTab = new CTabItem(tabFolder,SWT.NONE);
        dataTab.setText(data.getProperty("Title"));
        Composite dataComp=new Composite(tabFolder,SWT.NONE);
        dataTab.setControl(dataComp);
    	dataComp.setLayout(new GridLayout(1,false));
    	
    	//Create Tree 
    	Composite dataTable=new Composite(dataComp,SWT.NONE);
    	dataTable.setLayout(new FillLayout());
    	GridData g=new GridData(GridData.FILL_BOTH);
    	dataTable.setLayoutData(g);
    	
        TableViewer viewer = new TableViewer(dataTable,SWT.BORDER | SWT.FULL_SELECTION);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(33, false));
        layout.addColumnData(new ColumnWeightData(33, false));
        layout.addColumnData(new ColumnWeightData(13, true));
        
        viewer.getTable().setLayout(layout);
        
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
        
         final Table table=viewer.getTable();
        
        TableColumn column1 = new TableColumn(table,SWT.CENTER);
        column1.setText(data.getProperty("Column1"));
        
        TableColumn column2 = new TableColumn(table,SWT.CENTER);
        column2.setText(data.getProperty("Column2"));

        TableColumn column3 = new TableColumn(table,SWT.CENTER);
        column3.setText("Primary");
        
        
        //Extract Data From DB 
        ResultSet result=db.getRawData(data.getProperty("ownerID"), data.getProperty("tableName"));
        try {
        while(result.next()) {
        	TableItem item = new TableItem(table, SWT.NONE);
        	item.setText(0, result.getString(data.getProperty("Field1")));
        	item.setText(1, result.getString(data.getProperty("Field2")));
        	if (result.getString("first").equals("1"))
        		item.setText(2,"*");
        		else
        		item.setText(2,"");
        			
        }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
        //Create Controls
        Composite cControls=new Composite(dataComp,SWT.NONE);
        cControls.setLayout(new GridLayout(2,false));
        cControls.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lField1Edit=new Label(cControls,SWT.NONE);
        lField1Edit.setText(data.getProperty("Field1Edit"));
        
        final Text tField1Edit=new Text(cControls,SWT.BORDER);
        tField1Edit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label lField2Edit=new Label(cControls,SWT.NONE);
        lField2Edit.setText(data.getProperty("Field2Edit"));
        
        final Text tField2Edit=new Text(cControls,SWT.BORDER);
        tField2Edit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        final Label lFieldCheck=new Label(cControls,SWT.NONE);
        lFieldCheck.setText("Primary : ");
        
        final Button bPrimaryField=new Button(cControls,SWT.CHECK);
        //bFieldCheck.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Composite cButtons=new Composite(cControls,SWT.NONE);
        cButtons.setLayout(new FillLayout());
        GridData g2=new GridData(GridData.FILL_HORIZONTAL);
        g2.horizontalSpan=2;
        cButtons.setLayoutData(g2);
        
        Button bAdd=new Button(cButtons,SWT.NONE);
        bAdd.setImage(new Image(Display.getCurrent(),"./img/artwork/add.png"));
        
        Button bEdit=new Button(cButtons,SWT.NONE);
        bEdit.setImage(new Image(Display.getCurrent(),"./img/artwork/edit.png"));
        
        Button bRemove=new Button(cButtons,SWT.NONE);
        bRemove.setImage(new Image(Display.getCurrent(),"./img/artwork/remove.png"));
        
        /*
         * Config event listeners
         */
        
        //Table Listener
        table.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (table.getSelectionCount()==1) {
					tField1Edit.setText(table.getSelection()[0].getText(0));
					tField2Edit.setText(table.getSelection()[0].getText(1));
					if (table.getSelection()[0].getText(2).length()==0)
						bPrimaryField.setSelection(false);
					else
						bPrimaryField.setSelection(true);
				}
				else  {
					tField1Edit.setText("");
					tField2Edit.setText("");
					bPrimaryField.setSelection(false);
				}
			}
        	
        });
        
        //Add new item to table
        bAdd.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(0,tField1Edit.getText());
				item.setText(1,tField2Edit.getText());
				item.setText(2,"");
				//Recheck <First> field
				if (bPrimaryField.getSelection()) {
					for(int i=0;i<table.getItemCount();i++)
						table.getItems()[i].setText(2,"");
					item.setText(2,"*");
				}
				table.setSelection(item);
			}
        	
        });
        
        //Edit
        bEdit.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (table.getSelectionCount()==1) {
					TableItem item=table.getSelection()[0];
					item.setText(0, tField1Edit.getText());
					item.setText(1, tField2Edit.getText());
					//Recheck <First> field
					item.setText(2,"");
					if (bPrimaryField.getSelection()) {
						for(int i=0;i<table.getItemCount();i++)
							table.getItems()[i].setText(2,"");
					item.setText(2,"*");
					}
				}else
				{
					MessageBox dialog = new MessageBox(new Shell(Display.getCurrent()),SWT.OK | SWT.ICON_ERROR);
		            dialog.setText("Select one Item");
		            dialog.setMessage("Please select one item");
		            dialog.open();
				}
			}
        	
        });
        
        //Remove
        bRemove.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (table.getSelectionCount()==1) {
					MessageBox dialog = new MessageBox(new Shell(Display.getCurrent()),SWT.YES | SWT.NO | SWT.ICON_QUESTION);
		            dialog.setText("Confirm delete");
		            dialog.setMessage("Are you soure to delete item ?");
		            if (dialog.open()==SWT.YES) {
		            	table.remove(table.getSelectionIndex());
		            }
				}else
				{
					MessageBox dialog = new MessageBox(new Shell(Display.getCurrent()),SWT.OK | SWT.ICON_ERROR);
		            dialog.setText("Select one Item");
		            dialog.setMessage("Please select one item to delete");
		            dialog.open();
				}
			}

        	
        });
    	return table;
    }
}
