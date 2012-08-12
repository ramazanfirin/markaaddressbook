package test.wizard;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

	/**
	 * Your first JFace application
	 */
	public class Launch extends ApplicationWindow {
	  /**
	   * HelloWorld constructor
	   */
	  public Launch() {
	    super(null);
	  }

	  /**
	   * Runs the application
	   */
	  public void run() {
	    // Don't return from open() until window closes
	    setBlockOnOpen(true);

	    // Open the main window
	    open();

	    // Dispose the display
	    Display.getCurrent().dispose();
	  }

	  /**
	   * Creates the main window's contents
	   * 
	   * @param parent the main window
	   * @return Control
	   */
	  protected Control createContents(Composite parent) {
	    // Create a Hello, World label
		  
		final Shell s = parent.getShell();  
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		Label label = new Label(composite, SWT.CENTER);
	    label.setText("Hello, World");
	    
	    Button button = new Button(composite, SWT.NONE);
	    button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				 WizardDialog dialog = new WizardDialog(s, new AddEntryWizard());
				 dialog.open();
			}
			
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    return parent;
	  }

	  /**
	   * The application entry point
	   * 
	   * @param args the command line arguments
	   */
	  public static void main(String[] args) {
	    new Launch().run();
	  }
	}

