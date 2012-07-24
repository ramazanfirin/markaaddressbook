package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class CTabFolderComposite extends Composite{

	private CTabFolder cTabFolder;
	 private CTabItem tabItemSearch;
	 private CTabItem tabItemDriver;
	 private CTabItem tabItemBus;
	
	public CTabFolderComposite(Composite parent) {
		super(parent, SWT.NONE);
		
		cTabFolder = new CTabFolder(parent.getShell(), SWT.BORDER);
	    cTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
	    
	    cTabFolder.setSimple(false);
	    cTabFolder.setUnselectedImageVisible(false);
	    cTabFolder.setUnselectedCloseVisible(false);
		
//	    tabItemDriver = new CTabItem(cTabFolder, SWT.NONE);
//	    tabItemDriver.setText("User List");
//	    Composite compositeDriver=new Composite(cTabFolder,SWT.NONE);
//	    GridLayout layout = new GridLayout(1, false);
//	    compositeDriver.setLayout(layout);
//	    tabItemDriver.setControl(compositeDriver);
//	    cTabFolder.setSelection(tabItemDriver);
//	    tabItemDriver.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/user_list.png"));
//	    
//	    tabItemBus = new CTabItem(cTabFolder, SWT.NONE);
//	    tabItemBus.setText("Bus List");
//	    Composite compositeBus=new Composite(cTabFolder,SWT.NONE);
//	    compositeBus.setLayout(new FillLayout());
//	    tabItemBus.setControl(compositeBus);
//	    cTabFolder.setSelection(tabItemDriver);
//	    tabItemBus.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/user_list.png"));
//	    
//	    tabItemSearch = new CTabItem(cTabFolder, SWT.NONE);
//	    tabItemSearch.setText("User Search");
//	    Composite compositeSearch=new Composite(cTabFolder,SWT.NONE);
//	    tabItemSearch.setControl(compositeSearch);
//	    tabItemSearch.setImage(new Image(Display.getCurrent(),"./img/artwork/toolbar/search.png"));
//	    
//	    compositeSearch.setLayout(new FormLayout());
	}

}
