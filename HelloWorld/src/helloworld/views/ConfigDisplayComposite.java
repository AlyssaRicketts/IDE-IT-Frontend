package helloworld.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

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
    	exitButton.addListener(SWT.Selection, new Listener() {
  	      public void handleEvent(Event e) {
  	    	  baseComposite.dispose();
                parent.requestLayout();
  	      }
  	});
	}
	
}