package test;

/*
 * RowLayout snippet: align widgets in a column
 * 
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.1
 */
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Snippet177 {

public static void main(String[] args) {
	Display display = new Display();
	Shell shell = new Shell(display);
	RowLayout layout = new RowLayout(SWT.VERTICAL);
	layout.wrap = false;
	layout.fill = true;
	layout.justify = false;
	shell.setLayout(layout);

	Button b = new Button(shell, SWT.PUSH);
	b.setText("Button 1");
	b = new Button(shell, SWT.PUSH);

	b.setText("Button 2");

	b = new Button(shell, SWT.PUSH);
	b.setText("Button 3");

	b = new Button(shell, SWT.PUSH);
	b.setText("Not shown");
	b.setVisible(false);
	RowData data = new RowData();
	data.exclude = true;
	b.setLayoutData(data);

	b = new Button(shell, SWT.PUSH);
	b.setText("Button 200 high");
	data = new RowData();
	data.height = 200;
	b.setLayoutData(data);

	b = new Button(shell, SWT.PUSH);
	b.setText("Button 200 wide");
	data = new RowData();
	data.width = 200;
	b.setLayoutData(data);

	shell.pack();
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}
}