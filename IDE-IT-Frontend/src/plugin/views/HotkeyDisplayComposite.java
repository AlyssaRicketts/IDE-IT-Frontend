package plugin.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import main.java.Suggestion;

public class HotkeyDisplayComposite {
	public HotkeyDisplayComposite(final Composite parent, Suggestion s, Display display) {
		final Composite baseComposite = new Composite(parent, SWT.NONE);
		
		GridLayout GridLayout = new GridLayout();
		GridLayout.numColumns = 2;
		GridLayout.makeColumnsEqualWidth = true;
    	baseComposite.setLayout(GridLayout);
    	
    	Image image = new Image(display, getClass().getResourceAsStream("../../../icons/LightBulb.jpeg"));
        CLabel hotkey = new CLabel(baseComposite, 0);
        hotkey.setImage(image);
        hotkey.setText(s.getText());

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
