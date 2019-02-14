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
	private Composite thisParent;
	private Display display;
	private Map<String, Suggestion> suggestionsMap = new HashMap<String, Suggestion>();
	FeatureSuggestion fs = new FeatureSuggestion();
	FSObserver obs = new FSObserver(this);
	
    public MainView() {
        super();
        suggestionsMap.put("blockCommentSuggestion", new Suggestion("Try using 'CMD + /' to comment several lines.", HOTKEY, true));
        suggestionsMap.put("addImportStatementsSuggestion", new Suggestion("Try using 'Ctrl + Shift + O' to add import statements.", HOTKEY, true));
        suggestionsMap.put("removeUnusedImportsStatementSuggestion", new Suggestion("Try using 'Ctrl + Shift + O' to remove unused imports.", HOTKEY, true));
        suggestionsMap.put("correctIndentationsSuggestion", new Suggestion("Try using 'Ctrl + i' to correct indentation.", HOTKEY, true));
        suggestionsMap.put("variableRenameRefactorSuggestion", new Suggestion("Try using 'Alt + Shift + R' to rename all instances of a variable, class, or method.", HOTKEY, true));
        
        suggestionsMap.put("enableAutocompleteSuggestion", new Suggestion("Enable autocomplete", CONFIG, true, false));
    }
    
    @Override
    public void createPartControl(Composite parent) {
    	thisParent = parent;
    	fs.registerObserver(obs);
    	display = PlatformUI.getWorkbench().getDisplay();
    	RowLayout rowLayout = new RowLayout();
    	rowLayout.type = SWT.VERTICAL;
    	rowLayout.pack = true;
    	rowLayout.marginHeight = 0;
    	thisParent.setLayout(rowLayout);
    	/*
    	for (Suggestion s: suggestionsMap.values()) {
    		if (s.type == CONFIG) {
    			createConfigTip(s);
    		} else {
    			createHotkeyTip(s);
    		}
    	}
    	*/
    }
    
    public void addFeature(Suggestion s) {
    	if (s.getType() == CONFIG) {
			createConfigTip(s);
		} else {
			createHotkeyTip(s);
		}
    	thisParent.requestLayout();
    }

    @Override
    public void setFocus() {
        thisParent.setFocus();
    }
    
    public void createHotkeyTip(Suggestion s) {
    	HotkeyDisplayComposite hdc = new HotkeyDisplayComposite(thisParent, s, display);
    }
    
    public void createConfigTip(Suggestion s) {
    	ConfigDisplayComposite cdc = new ConfigDisplayComposite(thisParent, s);
    }
}
