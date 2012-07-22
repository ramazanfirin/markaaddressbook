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


import java.sql.SQLException;
import java.text.Collator;
import java.util.Locale;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import util.dbAccess;
import util.miscActions;
import util.programSettings;

/**
 * 
 * @author binary
 */
public class mainWindow extends  ApplicationWindow {
	
	/*
	 * Select person direction
	 */
	
	private Menu menuBar;
	
    private programSettings pSettings;
    private dbAccess db;
    
    private Table userList;
    private Tree addressBooksTree;
    
    private CTabFolder cTabFolder;
    private CTabItem itemUserSearch;
    private CTabItem itemUserList;
    
    private ScrolledComposite cUserData;
    /*
     * Person Data
     */
    private Label lFotoData;
    private Text tComments;
    private Table SuserList;
  
    /*
     * Search Form
     */
    private ScrolledComposite cSUserData;
    private Label lSFoto;
    private Tree SaddressBooksTree;
    private Text[] SText;
    private Button[] TText;
    
    private String[] sitems;
    /** Creates a new instance of mainWindow */
    public mainWindow() {
        super(null);
       
       
        
    }
    
    protected Control createContents(Composite parent){
    	
    	/*
    	 * Load Data
    	 */
    	int ok;
         
         do { 
         ok=0;
         
         pSettings=new programSettings();
         pSettings.loadSettings();
         
    	 db=new dbAccess(pSettings.getSettings());
              
         if (!db.getDriverData())
         {
             MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
             dialog.setText("Driver not found");
             dialog.setMessage("JDBC Driver not found !");
             dialog.open();
         }
         
        
         String str=null; 
         str=db.dbConnect();
         
         if (str!=null)
         {
        	 ok=1;
             errorConnectWindow dialog = new errorConnectWindow(getShell(),str);
             switch(dialog.open()) {
             case SWT.Settings : {
            	 showDBSettings();
            	 break;
             }
             case SWT.CLOSE : {
            	 System.exit(-1);
            	 break;
             }
             case SWT.RETRY : break;
             case SWT.HELP : {
            	 initWizardWindow dl=new initWizardWindow(getShell());
            	 dl.open();
            	 break;
             }
             }
             
             /*
              * Check DB usability
              */
             try {
            	 db.selectDB();
             }catch(SQLException e) {
        	 ok=1;
        	 MessageBox dialogDB = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
             dialogDB.setText("Failed to use DB");
             dialogDB.setMessage("Failed to use DB, need to initialize ...");
             dialogDB.open();
             
             initWizardWindow dl=new initWizardWindow(getShell());
        	 dl.open();
         }
         }
         }while(ok==1);
         //db.selectDB();
         /*
          * Create AddressBook GUI contents
          */
        parent.getShell().setText("jAddressBook");
        
        Composite cContent=new Composite(parent,SWT.NONE);
        cContent.setLayout(new GridLayout(1,false));
        
        setupMenu(parent.getShell()); 
        
        Composite cMenu=new Composite(cContent,SWT.NONE);
        cMenu.setLayout(new FillLayout());
        cMenu.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        setupToolBar(cMenu);
        cMenu.pack();
        
        /*
         * Setup TabFolder data
         */
        cTabFolder = new CTabFolder(cContent, SWT.BORDER);
        cTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        cTabFolder.setSimple(false);
        cTabFolder.setUnselectedImageVisible(false);
        cTabFolder.setUnselectedCloseVisible(false);
        
        /*
         * User List
         */
        itemUserList = new CTabItem(cTabFolder, SWT.NONE);
        itemUserList.setText("User List");
        Composite coUserList=new Composite(cTabFolder,SWT.NONE);
        coUserList.setLayout(new FillLayout());
        itemUserList.setControl(coUserList);
        
        cTabFolder.setSelection(itemUserList);
        
        itemUserList.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/user_list.png"));
        
        Composite cProgContent=new Composite(coUserList,SWT.NONE);
        cProgContent.setLayout(new FormLayout());
        //cProgContent.setLayoutData(new GridData(GridData.FILL_BOTH));
 
        /*
         * ============
         * User List
         * ============
         */
        Composite cUserList=new Composite(cProgContent,SWT.BORDER);
        
        FormData f1 = new FormData();
        f1.top = new FormAttachment(0);
        f1.left = new FormAttachment(0);
        f1.right = new FormAttachment(80);
        f1.bottom = new FormAttachment(100);
        
        cUserList.setLayout(new FillLayout());
        cUserList.setLayoutData(f1);
    	
        userList=this.buildPersonTable(cUserList);
       
       
        
        /*
         * Selection Listener for user's list
         */
        userList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (userList.getSelectionCount()==1) {
					String data[]=(String[])userList.getSelection()[0].getData();

					Image q=new Image(getShell().getDisplay().getCurrent(),db.getPersonFoto(data[0]));
					lFotoData.setImage(q);
					cUserData.setMinWidth(q.getImageData().width);
					cUserData.setMinHeight(q.getImageData().height);
					//q.getImageData().width
				}
			} 
        	
        } );
        /*
         * ==========
         * User Data
         * ==========
         */
        cUserData=new ScrolledComposite(cProgContent,SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        
        
        FormData f2 = new FormData();
        f2.top = new FormAttachment(0);
        f2.left = new FormAttachment(80);
        f2.right = new FormAttachment(100);
        f2.bottom = new FormAttachment(60);
        
        cUserData.setLayout(new FillLayout());
        cUserData.setLayoutData(f2);
        
        
        lFotoData=new Label(cUserData,SWT.CENTER);
        
        cUserData.setContent(lFotoData);
        cUserData.setExpandHorizontal(true);
        cUserData.setExpandVertical(true);

        /*
         * ================
         * Address Books 
         * ================
         */
        Composite cAddressBookData=new Composite(cProgContent,SWT.BORDER);
        
        FormData f3 = new FormData();
        f3.top = new FormAttachment(60);
        f3.left = new FormAttachment(80);
        f3.right = new FormAttachment(100);
        f3.bottom = new FormAttachment(100);
        
        cAddressBookData.setLayout(new GridLayout(1,false));
        cAddressBookData.setLayoutData(f3);
        
        new Label(cAddressBookData,SWT.NONE).setText("Address Books : ");
        
        addressBooksTree=new Tree(cAddressBookData,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        addressBooksTree.setLayoutData(new GridData(GridData.FILL_BOTH));
       
      /*  Aitem = new TreeItem(addressBooksTree, SWT.NONE);
        Aitem.setText("All Books");
        Aitem.setData(new String[]{"0","","","All Books"});
        
        miscActions.buildAddressBookTree(db,addressBooksTree);
        
        addressBooksTree.setSelection(Aitem);
        */
        this.buildAddressBooksList();
        
        Label lcomments=new Label(cAddressBookData,SWT.NONE);
        lcomments.setText("Comments : ");
        tComments=new Text(cAddressBookData,SWT.BORDER);
        tComments.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tComments.setText("All Books");
        
        /*
         * Configure listener
         */
        addressBooksTree.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (addressBooksTree.getSelectionCount()==1)
				{
					String data[]=(String[]) addressBooksTree.getSelection()[0].getData();
					tComments.setText(data[3]);
				}
				else
				{
					tComments.setText("");
				}
			loadData();
			}
        	
        });
        
        /*
         * ==========================================================
         * ==========================================================
         * ==========================================================
         * Create Search Tab
         * Search TAB going here !!!!!!!!!!!
         * ==========================================================
         * ==========================================================
         * ==========================================================
         */
        itemUserSearch = new CTabItem(cTabFolder, SWT.NONE);
        itemUserSearch.setText("User Search");
        Composite coUserSearch=new Composite(cTabFolder,SWT.NONE);
        
        itemUserSearch.setControl(coUserSearch);
        
        itemUserSearch.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/search.png"));
        
        /*
         * Configure Selection Listener of Main Tabs
         */
        cTabFolder.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				buildAddressBooksList();
				loadData();
			}
        	
        });
        
        coUserSearch.setLayout(new FormLayout());
        
        FormData fs0 = new FormData();
        fs0.top = new FormAttachment(0);
        fs0.left = new FormAttachment(0);
        fs0.right = new FormAttachment(80);
        fs0.bottom = new FormAttachment(100);
        
        Composite searchForm=new Composite(coUserSearch,SWT.NONE);
        searchForm.setLayoutData(fs0);
        
        /*
         * User Data Composite
         */
        cSUserData= new ScrolledComposite(coUserSearch,SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        
        FormData fS2 = new FormData();
        fS2.top = new FormAttachment(0);
        fS2.left = new FormAttachment(80);
        fS2.right = new FormAttachment(100);
        fS2.bottom = new FormAttachment(60);
        
        cSUserData.setLayout(new FillLayout(SWT.VERTICAL));
        cSUserData.setLayoutData(fS2);
        
        cSUserData.setExpandHorizontal(true);
        cSUserData.setExpandVertical(true);
		
        lSFoto=new Label(cSUserData,SWT.CENTER);
        cSUserData.setContent(lSFoto);
        
        
        /*
         * Address Books List
         */
        Composite cSAddressBookData=new Composite(coUserSearch,SWT.BORDER);
        
        FormData fS3 = new FormData();
        fS3.top = new FormAttachment(60);
        fS3.left = new FormAttachment(80);
        fS3.right = new FormAttachment(100);
        fS3.bottom = new FormAttachment(100);
        
        cSAddressBookData.setLayout(new GridLayout(1,false));
        cSAddressBookData.setLayoutData(fS3);
        
        
        new Label(cSAddressBookData,SWT.NONE).setText("Address Books : ");
        
        SaddressBooksTree=new Tree(cSAddressBookData,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        SaddressBooksTree.setLayoutData(new GridData(GridData.FILL_BOTH));
       
        new Label(cSAddressBookData,SWT.NONE).setText("Comments : ");
        final Text tSComments=new Text(cSAddressBookData,SWT.BORDER);
        tSComments.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        tSComments.setText("All Books");
        
        /*
         * Configure listener
         */
        SaddressBooksTree.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (SaddressBooksTree.getSelectionCount()==1)
				{
					String data[]=(String[]) SaddressBooksTree.getSelection()[0].getData();
					tSComments.setText(data[3]);
				}
				else
				{
					tSComments.setText("");
				}
			loadData();
			}
        	
        });
        
        /*
         * Setup search form fields...
         */
        searchForm.setLayout(new GridLayout(6,false));
        
        sitems=new String[]{"Surname :","Name :","Nick Name : ","Address : ","Tel : ","Tel Mob :","Fax : ","E-Mail : ","Web Page :"};
        SText=new Text[sitems.length];
        TText=new Button[sitems.length];
        
        for(int i=0;i<sitems.length;i++)
        {
        	Label l1=new Label(searchForm,SWT.NONE);
        	l1.setText(sitems[i]);
        	Text t1=new Text(searchForm,SWT.BORDER);
        	t1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        	SText[i]=t1;
        	final Button b1=new Button(searchForm,SWT.NONE);
        	TText[i]=b1;
        	b1.setText(" OR ");
        	b1.addSelectionListener(new SelectionListener(){
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					if (b1.getText().equals(" OR "))
						b1.setText("AND");
						else
							b1.setText(" OR ");
				}
        		
        	});
        	
        }
        
        Button bSearch=new Button(searchForm,SWT.NONE);
        bSearch.setText("Search");
        bSearch.setImage(new Image(Display.getCurrent(),"./img/artwork/search.png"));
        
        bSearch.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				loadData();
			}
        	
        });
        
        Button bInit=new Button(searchForm,SWT.NONE);
        bInit.setText("Clean");
        bInit.setImage(new Image(Display.getCurrent(),"./img/artwork/clean.png"));
        bInit.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				 //SText=new Text[sitems.length];
			       // TText=new Button[sitems.length];
			        
			        for(int i=0;i<sitems.length;i++) {
			        	SText[i].setText("");
			        	TText[i].setText(" OR ");
			        }
			}
        	
        });
         
        /*
         * Setup user List Composite
         */
        Composite cSUserList=new Composite(searchForm,SWT.BORDER);
        cSUserList.setLayout(new FillLayout());
        GridData g1=new GridData(GridData.FILL_BOTH);
        g1.horizontalSpan=6;
        cSUserList.setLayoutData(g1);
        
        
        SuserList=this.buildPersonTable(cSUserList);
        
        SuserList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				if (SuserList.getSelectionCount()==1) {
					String data[]=(String[])SuserList.getSelection()[0].getData();

					Image q=new Image(getShell().getDisplay().getCurrent(),db.getPersonFoto(data[0]));
					lSFoto.setImage(q);
					cSUserData.setMinWidth(q.getImageData().width);
					cSUserData.setMinHeight(q.getImageData().height);

				}
			} 
        	
        } );
        //Packing...
        parent.pack();
        parent.getShell().setSize(600,500);
        
        this.loadData();
        
        return parent;
    }
    
    /*
     * Setup Window's main menu
     */
    private void setupMenu(Shell parent)
    {
        Menu menuBar = new Menu(parent, SWT.BAR);
        //File Menu
        MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
        fileItem.setText("&File");
        
        Menu fileMenu = new Menu(fileItem);
        fileItem.setMenu(fileMenu);

        MenuItem userList = new MenuItem(fileMenu, SWT.PUSH);
        userList.setText("User List");
        userList.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/user_list.png"));
        
        userList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				selectUserListTab();
			}
        	
        });
        
        MenuItem userSearch = new MenuItem(fileMenu, SWT.PUSH);
        userSearch.setText("User Search");
        userSearch.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/search.png"));

        userSearch.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				selectSearchTab();
			}
        	
        });
        
        new MenuItem(fileMenu, SWT.SEPARATOR);
        
        MenuItem newABook = new MenuItem(fileMenu, SWT.PUSH);
        newABook.setText("Add new Address Book");
        newABook.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/abook.png"));
        
        newABook.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showAddAddressBook();
			} 
        	
        });
        
        MenuItem newPerson = new MenuItem(fileMenu, SWT.PUSH);
        newPerson.setText("Add new Person");
        newPerson.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/person.png"));
        
        newPerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showAddNewPerson();
			}
        	
        });
        
        new MenuItem(fileMenu, SWT.SEPARATOR);
        
        MenuItem exit = new MenuItem(fileMenu, SWT.PUSH);
        exit.setText("Exit");
        exit.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/exit.png"));
        exit.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				exitProg();
			}
        	
        });
        
        
        MenuItem editItem = new MenuItem(menuBar, SWT.CASCADE);
        editItem.setText("&Edit");
        
        Menu editMenu = new Menu(editItem);
        editItem.setMenu(editMenu);
        
        MenuItem editAddressBooks = new MenuItem(editMenu, SWT.PUSH);
        editAddressBooks.setText("Edit Address Book data");
        editAddressBooks.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/edit_abooks.png"));
        
        editAddressBooks.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
            	showEditAbooks();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        
        MenuItem editUser = new MenuItem(editMenu, SWT.PUSH);
        editUser.setText("Edit selected User");
        editUser.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/edit_person.png"));
        
        editUser.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showEditPerson();
			}
        	
        });
        
        MenuItem deletePerson = new MenuItem(editMenu, SWT.PUSH);
        deletePerson.setText("Delete selected user");
        deletePerson.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/delete_person.png"));
        deletePerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				deletePerson();
			} 
        	
        });
        
        MenuItem settingsItem = new MenuItem(menuBar, SWT.CASCADE);
        settingsItem.setText("&Settings");
        
        Menu settingsMenu = new Menu(settingsItem);
        settingsItem.setMenu(settingsMenu);
        
        /* 
         * DB Settings
         */ 
        MenuItem DBSettings = new MenuItem(settingsMenu, SWT.PUSH);
        DBSettings.setText("DB Settings");
        DBSettings.setImage(new Image(getShell().getDisplay().getCurrent(),"./img/artwork/toolbar/db.png"));
        
        DBSettings.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
            	showDBSettings();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        
        /*
         * Init Wizard
         */
        MenuItem initWiz = new MenuItem(settingsMenu, SWT.PUSH);
        initWiz.setText("Initialize Data Base");
        initWiz.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/wizard.png"));
        
        initWiz.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showInitWizard();
			}
        	
        });
        
        MenuItem helpItem = new MenuItem(menuBar, SWT.CASCADE);
        helpItem.setText("&Help");
        
        Menu helpMenu = new Menu(helpItem);
        helpItem.setMenu(helpMenu);
        
        MenuItem About = new MenuItem(helpMenu, SWT.PUSH);
        About.setText("About");
        About.setImage(new Image(getShell().getDisplay(),"./img/artwork/toolbar/about.png"));
        About.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
            	showAboutWindow();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
       
        
        parent.setMenuBar(menuBar);
    }

    /*
     * Setup Tool Bar
     */
    private void setupToolBar(Composite parent) {
    	ToolBar bar = new ToolBar (parent, SWT.NONE);
    	
    	ToolItem userList = new ToolItem (bar, SWT.PUSH);
    	userList.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/user_list.png"));
    	
    	userList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				selectUserListTab();
			}
    		
    	});

    	ToolItem userSearch = new ToolItem (bar, SWT.PUSH);
    	userSearch.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/search.png"));
    	
    	userSearch.addSelectionListener(new SelectionListener () {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				selectSearchTab();
			}
    		
    	});

    	new ToolItem(bar,SWT.SEPARATOR);
    	
    	ToolItem addNewPerson = new ToolItem (bar, SWT.PUSH);
    	addNewPerson.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/person.png"));
    	
    	addNewPerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showAddNewPerson();
			}
    		
    	});
    	
    	ToolItem addAddressBook = new ToolItem (bar, SWT.PUSH);
    	addAddressBook.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/abook.png"));
    	
    	addAddressBook.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showAddAddressBook();
			}
    		
    	});
    	
    	new ToolItem (bar, SWT.SEPARATOR);
    	
    	ToolItem EditPerson = new ToolItem (bar, SWT.PUSH);
    	EditPerson.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/edit_person.png"));

    	EditPerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showEditPerson();
			}
    		
    	});
    	
    	ToolItem EditABooks = new ToolItem (bar, SWT.PUSH);
    	EditABooks.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/edit_abooks.png"));
    	
    	EditABooks.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showEditAbooks();
			}
    		
    	});
    	
    	ToolItem delPerson = new ToolItem (bar, SWT.PUSH);
    	delPerson.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/delete_person.png"));
    	
    	delPerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				deletePerson();
			}
    		
    	});
    	
    	new ToolItem(bar,SWT.SEPARATOR);
    	
    	ToolItem dbSettings = new ToolItem (bar, SWT.PUSH);
    	dbSettings.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/db.png"));
    	
    	dbSettings.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showDBSettings();
				
			}
    		
    	});
    	
    	ToolItem initWiz = new ToolItem (bar, SWT.PUSH);
    	initWiz.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/wizard.png"));
    	initWiz.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showInitWizard();
			}
    		
    	});
    	
    	new ToolItem(bar,SWT.SEPARATOR);
    	
    	ToolItem aboutjAddressBook = new ToolItem (bar, SWT.PUSH);
    	aboutjAddressBook.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/about.png"));
    	
    	aboutjAddressBook.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showAboutWindow();
			}
    		
    	});
    	
    	ToolItem exitApp = new ToolItem (bar, SWT.PUSH);
    	exitApp.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/exit.png"));
    	exitApp.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				exitProg();
			}
    		
    	});
    }
    
    /*
     * =================================================================
     * GUI methods
     * =================================================================
     */
    
    /*
     * Show init Wizard
     */
    private void showInitWizard() {
    initWizardWindow dl=new initWizardWindow(getShell());
	dl.open();
	loadData();
	buildAddressBooksList();
    }
    
    /*
     * Show add new Address Book dialog
     */
    private void showAddAddressBook() {
    	addNewAddressBookWindow dialog=new addNewAddressBookWindow(getShell(),db);
		dialog.open();
		buildAddressBooksList();
    }
    
    /*
     * Show Add new Person dialog
     */
    private void showAddNewPerson() {
    	addNewPersonWindow dialog=new addNewPersonWindow(getShell(),db);
		dialog.open();
		loadData();
    }
    
    /*
     * Show Edit Address Books
     */
    private void showEditAbooks() { 
    editAddressBooksWindow dialog=new editAddressBooksWindow(getShell(),db);
    dialog.open();
    buildAddressBooksList();
    }
    
    /*
     * Show Edit Selected person
     */
    private void showEditPerson() {
    	Table xuserList;
    	if (cTabFolder.getSelection()==itemUserList) 
    		xuserList=userList;
    	else
    		xuserList=SuserList;
    	if (xuserList.getSelectionCount()==1) {
			String data[]=(String[])xuserList.getSelection()[0].getData();
			
			editUserDataWindow dialog=new editUserDataWindow(getShell(),db,data[0]);
			dialog.open();
			loadData();
		} else {
			MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
	        dialog.setText("Select person");
	        dialog.setMessage("Select person to edit");
	        dialog.open();
		}
    }
    
    /*
     * Show About Window
     */
    private void  showAboutWindow() {
    aboutWindow dialog=new aboutWindow(getShell());
    dialog.open();
   
    }
    
    /*
     * Show DB Settings
     */
   private void showDBSettings() {
	   dbsettingsWindow dialog=new dbsettingsWindow(getShell());
       dialog.open();
   }
    
    /*
     * Delete person
     */
    private void deletePerson() {
    	Table xuserList;
    	if (cTabFolder.getSelection()==itemUserList) 
    		xuserList=userList;
    	else
    		xuserList=SuserList;
    	
    	if (xuserList.getSelectionCount()==1) {
			String data[]=(String[])xuserList.getSelection()[0].getData();
			
			MessageBox dialog = new MessageBox(getShell(),SWT.YES | SWT.NO | SWT.ICON_QUESTION);
	        dialog.setText("Confirm delete");
	        dialog.setMessage("Confirm delete of person");
	        if (dialog.open()==SWT.YES)
	        {
	        		db.deletePerson(data[0]);
	        		loadData();
	        		return ;
	        }
		} else {
			MessageBox dialog = new MessageBox(getShell(),SWT.OK | SWT.ICON_ERROR);
	        dialog.setText("Select person");
	        dialog.setMessage("Select person to delete");
	        dialog.open();
	        return ;
		}
    	
    	
    }
    
    /*
     * Exit from Program
     */
    private void exitProg(){
    	MessageBox dialog = new MessageBox(getShell(),SWT.YES | SWT.NO | SWT.ICON_QUESTION);
        dialog.setText("Confirm exit");
        dialog.setMessage("Are you soure ?");
        if(dialog.open()==SWT.YES)
    			close();
    			else
    			return ;
    }
    
    /*
     * Select Tabs
     */
    private void selectUserListTab() {
    	cTabFolder.setSelection(itemUserList);
    }
    
    private void selectSearchTab() {
    	cTabFolder.setSelection(itemUserSearch);
    }
    
    
    
    /*
     * Load Data From DB
     */
    private void loadData() {
    	if (cTabFolder.getSelection()==itemUserList) {
    	userList.removeAll();
    	
    	String ids[]=new String[addressBooksTree.getSelectionCount()];
		for(int i=0;i<addressBooksTree.getSelectionCount();i++) {
		 String data2[]=(String[])(addressBooksTree.getSelection()[i].getData());
		 ids[i]=data2[0];
		}
    	
    	String data[][]=db.getPersonPrimaryData(ids);

    	if (data==null) return ;
    	
    	for(int i=0;i<data.length;i++) { 
    		TableItem item = new TableItem(userList, SWT.NONE);
    		item.setData(data[i]);
    		for(int j=1;j<data[i].length;j++) 
        	item.setText(j-1, data[i][j]);
    	}
    	}
    	else {
    		/*
    		 * Search Query
    		 */
    		SuserList.removeAll();
        	
        	String ids[]=new String[SaddressBooksTree.getSelectionCount()];
    		for(int i=0;i<SaddressBooksTree.getSelectionCount();i++) {
    		 String data2[]=(String[])(SaddressBooksTree.getSelection()[i].getData());
    		 ids[i]=data2[0];
    		}
        	
        	//String data[][]=db.getPersonPrimaryData(ids);
    		
    		String data[][]=db.ExecSearchQuery(ids, SText, TText);

        	if (data==null) return ;
        	
        	for(int i=0;i<data.length;i++) { 
        		TableItem item = new TableItem(SuserList, SWT.NONE);
        		item.setData(data[i]);
        		for(int j=1;j<data[i].length;j++) 
            	item.setText(j-1, data[i][j]);
        	}
    	}
    }

    /*
     * Build Address Books tree
     */
    private void buildAddressBooksList() {
    	Tree xaddressBooksTree;	
    	if (cTabFolder.getSelection()==itemUserList) 
    		xaddressBooksTree=addressBooksTree;
    	else
    		xaddressBooksTree=SaddressBooksTree;

    	xaddressBooksTree.removeAll();
        
    	TreeItem Aitem = new TreeItem(xaddressBooksTree, SWT.NONE);
        Aitem.setText("All Books");
        Aitem.setData(new String[]{"0","","","All Books"});
    	
        miscActions.buildAddressBookTree(db,xaddressBooksTree);
        xaddressBooksTree.setSelection(Aitem);
    }
    
    /*
     * Build Person's list Table
     * @param parent Parent composite
     * @return Table structure
     */
   private Table buildPersonTable(Composite parent) {
	   TableViewer viewer = new TableViewer(parent,SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL ); 
       TableLayout layout = new TableLayout();
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       layout.addColumnData(new ColumnWeightData(100,100, true));
       
       viewer.getTable().setLayout(layout);
       
       viewer.getTable().setLinesVisible(true);
       viewer.getTable().setHeaderVisible(true);
       
      final Table userList=viewer.getTable();
      
       final TableColumn column0 = new TableColumn(userList,SWT.CENTER);
       column0.setText("Address Book");
       
       final TableColumn column1 = new TableColumn(userList,SWT.CENTER);
       column1.setText("Surname");
       
       
       final TableColumn column2 = new TableColumn(userList,SWT.CENTER);
       column2.setText("Name");
       
       final TableColumn column3 = new TableColumn(userList,SWT.CENTER);
       column3.setText("Nick");
       
       final TableColumn column4 = new TableColumn(userList,SWT.CENTER);
       column4.setText("Address");
       
       final TableColumn column5 = new TableColumn(userList,SWT.CENTER);
       column5.setText("Telephone");

       final TableColumn column6 = new TableColumn(userList,SWT.CENTER);
       column6.setText("Tel. Mob");
       
       final TableColumn column7 = new TableColumn(userList,SWT.CENTER);
       column7.setText("Fax");
       
       final TableColumn column8 = new TableColumn(userList,SWT.CENTER);
       column8.setText("Web Page");
       
       final TableColumn column9 = new TableColumn(userList,SWT.CENTER);
       column9.setText("EMail");
       
       /*
        * Setup sort Listeners
        */
       Listener sortListener = new Listener() {
    	   private int dir=1;
           public void handleEvent(Event e) {
               TableItem[] items =userList.getItems();
               Collator collator = Collator.getInstance(Locale.getDefault());
               TableColumn column = (TableColumn)e.widget;
               int index=0;
               if (column==column0) index=0;
               if (column==column1) index=1;
               if (column==column2) index=2;
               if (column==column3) index=3;
               if (column==column4) index=4;
               if (column==column5) index=5;
               if (column==column6) index=6;
               if (column==column7) index=7;
               if (column==column8) index=8;
               if (column==column9) index=9;
               
               items = userList.getItems();
               int ok=1;
               do {
            	   ok=1;
            	   for (int i = 0; i < items.length-1; i++) {
            		   String value1 = items[i].getText(index);
            		   String value2 = items[i+1].getText(index);
            		   Boolean status;
            		   if (dir==1)
            		   status= collator.compare(value1, value2)<0 ? Boolean.TRUE : Boolean.FALSE; 
            		   else
            			   status=collator.compare(value1, value2)>0 ? Boolean.TRUE : Boolean.FALSE;
            		   
            		   if (status==Boolean.TRUE) {
            			   
            			   String[] values1=new String[10];
                    	   for(int i2=0;i2<10;i2++) {
                    		   values1[i2]=items[i].getText(i2);
                    	   }
                           Object data1=items[i].getData();
                    	   
                           String[] values2=new String[10];
                    	   for(int i2=0;i2<10;i2++) {
                    		   values2[i2]=items[i+1].getText(i2);
                    	   }
                           Object data2=items[i+1].getData();
                           
                           items[i].dispose();
                           items[i+1].dispose();
                    	   
                           TableItem item = new TableItem(userList, SWT.NONE, i);
                           item.setText(values2);
                           item.setData(data2);
                           
                           TableItem item2 = new TableItem(userList, SWT.NONE, i+1);
                           item2.setText(values1);
                           item2.setData(data1);
                           
                           items = userList.getItems();
                           ok=0;
                           break;
            		   }
            	   }
               }while(ok==0);
               dir=dir==1 ? 0 : 1;
               
               userList.setSortColumn(column);
           }
       };
       
       column0.addListener(SWT.Selection, sortListener);       
       column1.addListener(SWT.Selection, sortListener);
       column2.addListener(SWT.Selection, sortListener);
       column3.addListener(SWT.Selection, sortListener);
       column4.addListener(SWT.Selection, sortListener);
       column5.addListener(SWT.Selection, sortListener);
       column6.addListener(SWT.Selection, sortListener);
       column7.addListener(SWT.Selection, sortListener);
       column8.addListener(SWT.Selection, sortListener);
       column9.addListener(SWT.Selection, sortListener);
       
       
       /*
        * Create Context menu
        */
       menuBar = new Menu(parent.getShell(), SWT.POP_UP);
       MenuItem itemNewPerson = new MenuItem(menuBar, SWT.PUSH);
       itemNewPerson.setText("New Person");
       itemNewPerson.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/person.png"));
       
       MenuItem itemEditPerson = new MenuItem(menuBar, SWT.PUSH);
       itemEditPerson.setText("Edit Person");
       itemEditPerson.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/edit_person.png"));
       
       MenuItem itemDeletePerson = new MenuItem(menuBar, SWT.PUSH);
       itemDeletePerson.setText("Delete Person");
       itemDeletePerson.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/delete_person.png"));
       
       userList.setMenu(menuBar);
       
       /*
        * Add Pop-up Listener
        */
       userList.addMouseListener(new MouseListener() {
       	  public void mouseDown(MouseEvent event) {
       	    if(event.button == 2)
       	    {
       	      menuBar.setVisible(true);
       	    }
       	  }

			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
       	 
       	});

       /*
        * ==================
        * Pop-Up Menu
        * ==================
        */
       
       /*
        * New Item
        */
       itemNewPerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showAddNewPerson();
			}
       	
       });
       
       /*
        * Edit Item
        */
       itemEditPerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				showEditPerson();
			}
       	
       });
       
       /*
        * Delete item
        */
       itemDeletePerson.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				deletePerson();
			} 
       	
       });
       return userList;
   }
    
}
