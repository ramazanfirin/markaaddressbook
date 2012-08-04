package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class dfd {

  public static void main(String[] a) {
    Display display = new Display();

    // Create the main window
    Shell shell = new Shell(display);

    Text fahrenheit = new Text(shell, SWT.BORDER);
    fahrenheit.setData("Type a temperature in Fahrenheit");
    fahrenheit.setBounds(20, 20, 100, 20);

    fahrenheit.addVerifyListener(new VerifyListener() {
      public void verifyText(VerifyEvent event) {
        // Assume we don't allow it
        event.doit = false;

        // Get the character typed
        char myChar = event.character;
        String text = ((Text) event.widget).getText();

        // Allow '-' if first character
        if (myChar == '-' && text.length() == 0)
          event.doit = true;

        // Allow 0-9
        if (Character.isDigit(myChar))
          event.doit = true;

        // Allow backspace
        if (myChar == '\b')
          event.doit = true;
      }

    });
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
}
