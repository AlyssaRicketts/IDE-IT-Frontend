package plugin.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import main.java.Suggestion;

/**
 * Class defining a new composite object, containing a lightbulb icon, text, and an exit button.
 */
public class HotkeyDisplayComposite {
	
	/**
	 * Creates a new HotkeyDisplayComposite object.
	 * 
	 * @param parent. The composite object that this composite will be a child of.
	 * @param s. The suggestion object for whose composite object we will be making. The
	 * 				text from this object will be the text displayed in the composite object.
	 * @param display. The display that will be appended.
	 */
	public HotkeyDisplayComposite(final Composite parent, Suggestion s, Display display) {
		final Composite baseComposite = new Composite(parent, SWT.NONE);
		
		// Set layout
		GridLayout GridLayout = new GridLayout();
		GridLayout.numColumns = 2;
		GridLayout.makeColumnsEqualWidth = true;
    	baseComposite.setLayout(GridLayout);
    	
    	// Add lightbulb icon
    	Image image = new Image(display, getClass().getResourceAsStream("../../../icons/Lightbulb.jpeg"));
        CLabel hotkey = new CLabel(baseComposite, 0);
        hotkey.setImage(image);
        hotkey.setText(s.getText());

        // Add exit button
        Image exitButton = new Image(display, getClass().getResourceAsStream("../../../icons/ExitButton.png"));
    	Label test = new Label(baseComposite, SWT.NONE);
    	test.setImage(exitButton);
    	test.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				baseComposite.dispose();
				parent.requestLayout();
				s.setDisplay(true);
			}
			
			@Override
			public void mouseDown(MouseEvent e) {}

			@Override
			public void mouseUp(MouseEvent e) {
				baseComposite.dispose();
				parent.requestLayout();
				s.setDisplay(true);
			}	
    	});
	}
}
