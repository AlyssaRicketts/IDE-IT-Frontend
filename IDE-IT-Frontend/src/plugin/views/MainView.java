package plugin.views;

import java.util.Map;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import main.interfaces.FeatureSuggestion;
import main.interfaces.FeatureSuggestionInterface;
import main.java.Controller;
import main.java.FSObserver;
import main.java.Suggestion;

public class MainView extends ViewPart {
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	private Composite thisParent;
	private Display display;
	FeatureSuggestionInterface fs = new FeatureSuggestion();
	FSObserver obs = new FSObserver(this);
	
	// Instantiates the MainView class to display window view
    public MainView() {
        super();
      }
    
    // Creates the view's control with parent composite object
    @Override
    public void createPartControl(Composite parent) {
    	thisParent = parent;
    	fs.registerObserver(obs);
    	fs.start();
    	display = PlatformUI.getWorkbench().getDisplay();		
    	
    	RowLayout RowLayout = new RowLayout(SWT.VERTICAL);
    	RowLayout.wrap = false;
    	RowLayout.fill = true;
    	thisParent.setLayout(RowLayout);
    	hardCodeConfigs();
    }
    
    // Adds configuration suggestions to the window
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
    
    // Adds feature suggestion to window
    // Displays Suggestion s with configuration checkbox or hotkey tip
    // depending on type of s
    public void addFeature(Suggestion s) {
    	if (s.getType() == HOTKEY && s.getDisplay() && s.getCount() < 3) {
    		s.increaseCount();
    		s.setDisplay(false);
    		createHotkeyTip(s);
    	} else if (s.getType() == CONFIG && s.getDisplay()) {
    		s.setDisplay(false);
    		createConfigTip(s);
    	}
    	Display.getDefault().asyncExec(new Runnable() {
    		public void run() {
    			thisParent.requestLayout();
    			}
    		});
    }

    // Accepts focus of parent composite object
    @Override
    public void setFocus() {
        thisParent.setFocus();
    }
    
    // Creates new hotkey display composite object with Suggestion s,
    // with a lightbulb icon, suggestion, and 'x' button
    public void createHotkeyTip(Suggestion s) {
    	Display.getDefault().asyncExec(new Runnable() {
    		public void run() {
    			new HotkeyDisplayComposite(thisParent, s, display);
    		}
    	});
    }
    
    // Creates new configuration display composite object with Suggestion s
    // with a checkbox, suggestion, and 'x' button
    public void createConfigTip(Suggestion s) {
    	new ConfigDisplayComposite(thisParent, s, display);
    }
}
