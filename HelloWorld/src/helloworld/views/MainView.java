package helloworld.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

import main.java.Suggestion;

public class MainView extends ViewPart {
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	private Button button;
	private Label hotkey;
	private Composite thisParent;
	private Suggestion firstSuggestion;

    public MainView() {
        super();
        firstSuggestion = new Suggestion("Try using 'CMD + /' to comment several lines.", HOTKEY, true);
    }

    @Override
    public void createPartControl(Composite parent) {
    	thisParent = parent;
        createHotkeyTip(firstSuggestion);
    }

    @Override
    public void setFocus() {
        hotkey.setFocus();
    }
    
    public void createHotkeyTip(Suggestion s) {
        // button = new Button(thisParent, SWT.CHECK);
        // button.setText(s.getText());
        
        // Display display = new Display();
        // Image image = new Image(display, "../../../icons/lighbulb.png");
        hotkey = new Label(thisParent, 0);
        // hotkey.setImage(image);
        hotkey.setText(s.getText());
    }

}
