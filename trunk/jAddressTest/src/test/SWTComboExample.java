package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SWTComboExample {
  Display d;

  Shell s;

  SWTComboExample() {
    d = new Display();
    s = new Shell(d);
    s.setSize(250, 250);
    s.setText("A Combo Example");
    final Combo c = new Combo(s, SWT.READ_ONLY);
    c.setBounds(50, 50, 150, 65);
    String items[] = { "Item One", "Item Two", "Item Three", "Item Four",
        "Item Five" };
    c.setItems(items);
    c.select(2);
    
    s.open();
    while (!s.isDisposed()) {
      if (!d.readAndDispatch())
        d.sleep();
    }
    d.dispose();
  }

  public static void main(String[] argv) {
    new SWTComboExample();
  }
}