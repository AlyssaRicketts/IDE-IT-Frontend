package plugin.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import main.java.Suggestion;

public class HotkeyDisplayComposite {
	public HotkeyDisplayComposite(final Composite parent, Suggestion s, Display display) {
		final Composite baseComposite = new Composite(parent, SWT.NONE);
		
		RowLayout rowLayout = new RowLayout();
    	rowLayout.type = SWT.HORIZONTAL;
    	rowLayout.pack = true;
    	baseComposite.setLayout(rowLayout);
    	
    	Image image = new Image(display, getClass().getResourceAsStream("../../../icons/lightbulb_2.png"));
        CLabel hotkey = new CLabel(baseComposite, 0);
        hotkey.setImage(image);
        hotkey.setText(s.getText());
    	
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
