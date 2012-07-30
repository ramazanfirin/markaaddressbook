package widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import model.DBManager;
import model.interfaces.AbsractInterface;
import model.model.Driver;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import util.Util;

public abstract class BasicTabItem extends CTabItem{

	Shell shell;
	
	public TableViewer tableViewer;
	public Table table;
	
	private int lastSortColumn= -1;
	Menu popUpMenu;
	
	WizardDialog wizardDialog;

	AbsractInterface entity;
	
	List<AbsractInterface> entityList=new ArrayList<AbsractInterface>();
	
	Group grpLocation=null;
	
	public BasicTabItem(CTabFolder parent,String name) {
		super(parent, SWT.NONE);
		
		shell = parent.getShell();
		setText(name);
		Composite composite=new Composite(parent,SWT.NONE);
	    GridLayout layout = new GridLayout(1, false);
	    composite.setLayout(layout);
	    setControl(composite);
		
	    grpLocation = new Group(composite, SWT.NONE);
	    grpLocation.setText("arama");
	    grpLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	    GridLayout grpLayout = new GridLayout(2, false);
	    grpLayout.verticalSpacing = 0;
	    grpLocation.setLayout(grpLayout);
	    
	    prepareComponents(grpLocation);
	    
	    Button searchDriverButton = new Button(grpLocation, SWT.NONE);
		searchDriverButton.setText(Util.getString("search"));
		searchDriverButton.addSelectionListener(new SelectionListener() {
		      public void widgetSelected(SelectionEvent event) {
		    	  search();
		      }
		      public void widgetDefaultSelected(SelectionEvent event) {
		        //text.setText("No worries!");
		      }
		 });
		
		final Group grpDriverList = new Group(composite, SWT.NONE);
		grpDriverList.setText(getName());
		grpDriverList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    GridLayout gridDriverList = new GridLayout(1, false);
	    gridDriverList.verticalSpacing = 0;
	    grpDriverList.setLayout(gridDriverList);
	    
	    tableViewer = new TableViewer(grpDriverList,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
	    tableViewer.setContentProvider(new ArrayContentProvider());
	    tableViewer.setLabelProvider(new TableLabelProviders());
	    
	    table = tableViewer.getTable();
	   
	    table.setHeaderVisible(true);	
	    table.setLinesVisible(true);
	    table.setMenu(createPopUpMenu());	
	    table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
	    table.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();
				if (items.length > 0)
						editEntity(items[0].getData());
			}
		});
	
	    
	    for(int i = 0; i < getColumNames().length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(getColumNames()[i]);
			column.setWidth(150);
			final int columnIndex = i;
			column.addSelectionListener(new SelectionAdapter() {		
				public void widgetSelected(SelectionEvent e) {
					sort(columnIndex);
				}
			});
		}
	    
	    notifyAllTabItems();
	}

	
	
	
	abstract void search();
	abstract void prepareComponents(Composite composite);
	abstract String[] getColumNames();
	abstract Wizard getNewWizard();
	abstract void createNewEntity();
	//abstract ITableLabelProvider getTableLabelProvider();
	abstract void loadData();
	abstract String getTableColumValues(Object object,int columnIndex);
	abstract void saveData();
	abstract String getName();
	
	public void refresh(){
		//loadData();
		tableViewer.setInput(entityList);
		tableViewer.refresh();
	}
	
	public void notifyAllTabItems(){
		 BasicCTabFolder basicCTabFolder = (BasicCTabFolder)getParent();
	        basicCTabFolder.refreshAllTabItems();
		
	}
	
	public void newEntity(){
		createNewEntity();
		showEditWindow();
	}
	public void editEntity(Object object){
		entity = (AbsractInterface)object;
		showEditWindow();
	}
	
	
	public void showEditWindow(){
		wizardDialog = new WizardDialog(shell,getNewWizard());
		if(wizardDialog.open()==Window.OK){
			try {
				DBManager.getInstance().saveOrUpdate(entity);
				//saveData();
				getParent().setSelection(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			notifyAllTabItems();
		}
	}
	
	
	public void delete(Object object){
		
		try {
			DBManager.getInstance().delete(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyAllTabItems();
	}
	
	public void save(Object object){
		
		try {
			DBManager.getInstance().saveOrUpdate(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	private Menu createPopUpMenu() {
		popUpMenu = new Menu(shell, SWT.POP_UP);

		/** 
		 * Adds a listener to handle enabling and disabling 
		 * some items in the Edit submenu.
		 */
		popUpMenu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				Menu menu = (Menu)e.widget;
				MenuItem[] items = menu.getItems();
				int count = table.getSelectionCount();
				items[2].setEnabled(count != 0); // edit
				//items[3].setEnabled(count != 0); // copy
				items[4].setEnabled(count != 0); // paste
				//items[5].setEnabled(count != 0); // delete
				//items[7].setEnabled(driverTable.getItemCount() != 0); // find
			}
		});

		//New
		MenuItem item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_new"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
					newEntity();
			}
		});
		
		new MenuItem(popUpMenu, SWT.SEPARATOR);	
		
		//Edit
		item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_edit"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();
				if (items.length == 0) return;
					editEntity(items[0].getData());
				}
		});

		new MenuItem(popUpMenu, SWT.SEPARATOR);	
		
		//Delete
		item = new MenuItem(popUpMenu, SWT.PUSH);
		item.setText(Util.getString("Pop_up_delete"));
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();
				if (items.length == 0) return;
				boolean answer = MessageDialog.openConfirm(shell, "Onaylama", "Silmek istiyor musunuz?");
				if(answer)
					delete(items[0].getData());
				
			}
		});
		
		
		
		
		return popUpMenu;
	}

	
	
	
	
	
	
	
	
	
	//***************************************************************************************************************************
	
	
	private void sort(int column) {
		if(table.getItemCount() <= 1) return;

		TableItem[] items = table.getItems();
		String[][] data = new String[items.length][table.getColumnCount()];
		for(int i = 0; i < items.length; i++) {
			for(int j = 0; j < table.getColumnCount(); j++) {
				data[i][j] = items[i].getText(j);
			}
		}
		
		Arrays.sort(data, new RowComparator(column));
		
		if (lastSortColumn != column) {
			table.setSortColumn(table.getColumn(column));
			table.setSortDirection(SWT.DOWN);
			for (int i = 0; i < data.length; i++) {
				items[i].setText(data[i]);
			}
			lastSortColumn = column;
		} else {
			// reverse order if the current column is selected again
			table.setSortDirection(SWT.UP);
			int j = data.length -1;
			for (int i = 0; i < data.length; i++) {
				items[i].setText(data[j--]);
			}
			lastSortColumn = -1;
		}
		
	}
	
	/**
	 * To compare entries (rows) by the given column
	 */
	private class RowComparator implements Comparator {
		private int column;
		
		/**
		 * Constructs a RowComparator given the column index
		 * @param col The index (starting at zero) of the column
		 */
		public RowComparator(int col) {
			column = col;
		}
		
		/**
		 * Compares two rows (type String[]) using the specified
		 * column entry.
		 * @param obj1 First row to compare
		 * @param obj2 Second row to compare
		 * @return negative if obj1 less than obj2, positive if
		 * 			obj1 greater than obj2, and zero if equal.
		 */
		public int compare(Object obj1, Object obj2) {
			String[] row1 = (String[])obj1;
			String[] row2 = (String[])obj2;
			
			return row1[column].compareTo(row2[column]);
		}
	}

	
	public class TableLabelProviders implements ITableLabelProvider{

		/**
		   * Gets the image for the column
		   * 
		   * @param element
		   *            the element
		   * @param columnIndex
		   *            the column index
		   */
		  public Image getColumnImage(Object element, int columnIndex) {
		    return null;
		  }

		  /**
		   * Gets the text for the column
		   * 
		   * @param element
		   *            the element
		   * @param columnIndex
		   *            the column index
		   */
		  public String getColumnText(Object element, int columnIndex) {
			  return getTableColumValues(element,columnIndex);
		  }

		  /**
		   * Adds a listener
		   * 
		   * @param listener
		   *            the listener
		   */
		  public void addListener(ILabelProviderListener listener) {
		    // Do nothing
		  }

		  /**
		   * Disposes any resources
		   */
		  public void dispose() {
		    // Do nothing
		  }

		  /**
		   * Returns true if changing the property for the element would change the
		   * label
		   * 
		   * @param element
		   *            the element
		   * @param property
		   *            the property
		   */
		  public boolean isLabelProperty(Object element, String property) {
		    return false;
		  }

		  /**
		   * Removes a listener
		   * 
		   * @param listener
		   *            the listener
		   */
		  public void removeListener(ILabelProviderListener listener) {
		    // Do nothing
		  }
	
	}
	
	public AbsractInterface getEntity() {
		return entity;
	}

	public void setEntity(AbsractInterface entity) {
		this.entity = entity;
	}

}
