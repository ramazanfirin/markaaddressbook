package widgets;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;

public class BasicCTabFolder extends CTabFolder{

	public BasicCTabFolder(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	public void refreshAllTabItems(){
		
		for (int i = 0; i < getItemCount(); i++) {
			if(getItem(i) instanceof BasicTabItem)
				((BasicTabItem)getItem(i)).refresh();
		}
	}
}
