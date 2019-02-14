package helloworld.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import main.java.Suggestion;

public class ConfigDisplayComposite {

	public ConfigDisplayComposite(final Composite parent, Suggestion s) {
		final Composite baseComposite = new Composite(parent, SWT.NONE);
		
		RowLayout rowLayout = new RowLayout();
    	rowLayout.type = SWT.HORIZONTAL;
    	rowLayout.pack = true;
    	baseComposite.setLayout(rowLayout);
    	
    	Button checkBox = new Button(baseComposite, SWT.CHECK);
    	checkBox.setText(s.getText());
    	
    	Button exitButton = new Button(baseComposite, SWT.NONE);
    	exitButton.setText("X");
	}
	
}