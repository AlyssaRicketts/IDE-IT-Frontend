package plugin.views;

import java.util.Map;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import interfaces.FeatureSuggestion;
import main.java.Controller;
import main.java.FSObserver;
import main.java.Suggestion;

public class MainView extends ViewPart {
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	private Composite thisParent;
	private Display display;
	FeatureSuggestion fs = new FeatureSuggestion();
	FSObserver obs = new FSObserver(this);
	
    public MainView() {
        super();
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
    	hardCodeConfigs();
    }
    
    // For version 1, we will hard code configuration suggestions to appear
    public void hardCodeConfigs() {
    	Controller control = new Controller();
    	Map<String, Suggestion> suggestionsMap = control.getSuggestionsMap();
    	
    	// Disable content assist auto activation so we can suggest it
    	IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
		prefs.put("content_assist_autoactivation", "false");
		try {
			prefs.flush(); 
		} catch (org.osgi.service.prefs.BackingStoreException f) {
			f.printStackTrace();
		}
		
		// Add content assist auto activation suggestion to the window
    	Suggestion autocomplete = suggestionsMap.get("enableAutocompleteSuggestion");
    	addFeature(autocomplete);
    	
    	// Add smart semicolon suggestion to the window
    	Suggestion semicolon = suggestionsMap.get("enableSmartSemicolonSuggestion");
    	addFeature(semicolon);
    	
    	// Add shadow variable warning suggestion to the window
    	Suggestion shadowVariable = suggestionsMap.get("enableShadowedVariableWarning");
    	addFeature(shadowVariable);
    }
    
    public void addFeature(Suggestion s) {
    	if (s.getType() == HOTKEY && s.getDisplay() && s.getCount() < 3) {
    		s.increaseCount();
    		s.setDisplay(false);
    		createHotkeyTip(s);
    	} else if (s.getType() == CONFIG) {
    		createConfigTip(s);
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
