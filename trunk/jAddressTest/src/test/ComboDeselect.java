package test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ComboDeselect {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());

    String[] ITEMS = { "A", "B", "C", "D" };

    final Combo combo = new Combo(shell, SWT.DROP_DOWN);
    combo.setItems(ITEMS);

    combo.deselect(0);
    
    combo.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        System.out.println(combo.getText());
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        System.out.println(combo.getText());
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