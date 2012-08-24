package wizards;

import model.interfaces.AbsractInterface;
import model.model.Bus;
import model.model.BusOwner;
import model.model.Driver;
import model.model.Host;
import model.model.Muavin;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.Wizard;

import util.Util;


import com.AddressBookNew;


public class BusEntityWizard extends Wizard {
  
  private BusPage page1;

  private ISelection selection;
  
  private Driver tempDriver;
  
  public String title;
  
  AbsractInterface entity;

  public BusEntityWizard(AbsractInterface _entity,String _title) {
    super();
    title=_title;
    setNeedsProgressMonitor(true);
    entity = _entity;
    
  }

  public void addPages() {
    page1 = new BusPage(selection,entity,title);
    addPage(page1);
    
  
    
    System.out.println("test");
  }

  
  class DriverSelectionChangeProvider implements ISelectionChangedListener{

		@Override
		public void selectionChanged(SelectionChangedEvent arg0) {
			IStructuredSelection selection = (IStructuredSelection)page1.getViewer().getSelection();
			Driver driver = (Driver)selection.getFirstElement();
		}
	}
  
  
  public boolean performFinish() {

	  Bus bus = (Bus)Util.getApplicationInstance().getTabItemBus().getEntity();
	  bus.setPlate(page1.getPlate().getText());
	  bus.setPhone(page1.getPhone().getText());
	  bus.setShortCode(page1.getShortCode().getText());

	  
	  IStructuredSelection selection = (IStructuredSelection)page1.getViewer().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setFirstDriver(null);
	  }else{
		  Driver driver = (Driver)selection.getFirstElement();
		  bus.setFirstDriver(driver);
	  }
	  
      
      selection = (IStructuredSelection)page1.getViewer2().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setSecondDriver(null);
	  }else{
		  Driver driver = (Driver)selection.getFirstElement();
		  bus.setSecondDriver(driver);
	  }
	  
      
      selection = (IStructuredSelection)page1.getViewer3().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setThirdDriver(null);
	  }else{
		  Driver driver = (Driver)selection.getFirstElement();
		  bus.setThirdDriver(driver);
	  }
      
      selection = (IStructuredSelection)page1.getViewer4().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setHost(null);
	  }else{
		  Host host = (Host)selection.getFirstElement();
		  bus.setHost(host);
	  }
      
      selection = (IStructuredSelection)page1.getViewer5().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setFirstOwner(null);
	  }else{
		  BusOwner busOwner = (BusOwner)selection.getFirstElement();
		  bus.setFirstOwner(busOwner);
	  }
      
      selection = (IStructuredSelection)page1.getViewer6().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setSecondOwner(null);
	  }else{
		  BusOwner busOwner = (BusOwner)selection.getFirstElement();
		  bus.setSecondOwner(busOwner);
	  }
    
      selection = (IStructuredSelection)page1.getViewer7().getSelection();
      if(selection.isEmpty() || selection.getFirstElement() instanceof String){
		  bus.setMuavin(null);
	  }else{
		  Muavin muavin = (Muavin)selection.getFirstElement();
		  bus.setMuavin(muavin);
	  }
    
      
      return true;
  }
  
 
public Driver getTempDriver() {
	return tempDriver;
}

public void setTempDriver(Driver tempDriver) {
	this.tempDriver = tempDriver;
}


}