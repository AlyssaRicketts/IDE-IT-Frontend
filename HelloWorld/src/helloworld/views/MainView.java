package helloworld.views;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import interfaces.FeatureSuggestion;
import main.java.FSObserver;
import main.java.Suggestion;

public class MainView extends ViewPart {
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	private Button button;
	private CLabel hotkey;
	private Composite thisParent;
	private Display display;
	private Map<String, Suggestion> suggestionsMap = new HashMap<String, Suggestion>();
	
	FeatureSuggestion fs = new FeatureSuggestion();
	FSObserver obs = new FSObserver();
	
    public MainView() {
        super();
        suggestionsMap.put("firstSuggestion", new Suggestion("Try using 'CMD + /' to comment several lines.", HOTKEY, true));
        suggestionsMap.put("secondSuggestion", new Suggestion("Enable autocomplete", CONFIG, true, false));
        
    }

    @Override
    public void createPartControl(Composite parent) {
    	thisParent = parent;
    	fs.registerObserver(obs);
    	display = PlatformUI.getWorkbench().getDisplay();
    	RowLayout rowLayout = new RowLayout();
    	rowLayout.type = SWT.VERTICAL;
    	rowLayout.pack = true;
    	thisParent.setLayout(rowLayout);

    	for (Suggestion s: suggestionsMap.values()) {
    		if (s.type == CONFIG) {
    			createConfigTip(s);
    		} else {
    			createHotkeyTip(s);
    		}
    	}
    }

    @Override
    public void setFocus() {
        thisParent.setFocus();
    }
    
    public void createHotkeyTip(Suggestion s) {
        Image image = new Image(display, getClass().getResourceAsStream("../../../icons/lightbulb_2.png"));
        hotkey = new CLabel(thisParent, 0);
        hotkey.setImage(image);
        hotkey.setText(s.getText());
    }
    
    public void createConfigTip(Suggestion s) {
    	button = new Button(thisParent, SWT.CHECK);
    	button.setText(s.getText());
    }
}
