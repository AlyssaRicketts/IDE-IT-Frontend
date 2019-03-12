package plugin.views;

import java.util.Map;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
	private Composite child;
	private ScrolledComposite sc;
	private Display display;
	FeatureSuggestionInterface fs = new FeatureSuggestion();
	FSObserver obs = new FSObserver(this);
	
	/**
	 * Instantiates the MainView class to display window view
	 */
    public MainView() {
        super();
      }
    
    /**
     * Creates the view's control with parent composite object
     * 
     * @param parent Composite for window display
     */
    @Override
    public void createPartControl(Composite parent) {
    	thisParent = parent;
    	fs.registerObserver(obs);
    	fs.start();
    	display = PlatformUI.getWorkbench().getDisplay();

    	org.eclipse.swt.graphics.Color white = display.getSystemColor(SWT.COLOR_WHITE);
    	
    	sc = new ScrolledComposite(thisParent, SWT.H_SCROLL | SWT.V_SCROLL);
    	sc.setBackgroundMode(SWT.INHERIT_DEFAULT);
    	sc.setBackground(white);
    	
    	child = new Composite(sc, SWT.NONE);
    	
    	RowLayout layout = new RowLayout(SWT.VERTICAL);
    	layout.fill = true;
    	layout.wrap = false;
    	child.setLayout(layout);

    	hardCodeConfigs();
    	
    	child.setSize(400, 400);
    	
    	sc.setContent(child);
    }
     
    /**
     * Adds configuration suggestions to the window
     */
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
    
    /**
     * Adds feature suggestion s to window with either configuration checkbox or
     * hotkey tip depending on type of suggestion
     * @param s - Suggestion object to be added to view
     */
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
    			child.requestLayout();
    			}
    		});
    }

    /**
     * Accepts focus of parent composite object
     */
    @Override
    public void setFocus() {
        child.setFocus();
    }
    
    /**
     * Creates new hotkey display composite object with Suggestion s,
     * with a lightbulb icon, suggestion, and 'x' button
     * @param s - Suggestion object that is a hotkey tip
     */
    public void createHotkeyTip(Suggestion s) {
    	Display.getDefault().asyncExec(new Runnable() {
    		public void run() {
    			new HotkeyDisplayComposite(child, s, display);
    		}
    	});
    }
    
    /**
     * Creates new configuration display composite object with Suggestion s
     * with a checkbox, suggestion, and 'x' button
     * @param s - Suggestion object that is a configuration setting
     */
    public void createConfigTip(Suggestion s) {
    	Display.getDefault().asyncExec(new Runnable() {
    		@Override
    		public void run() {
    			new ConfigDisplayComposite(child, s, display);
    		}
    	});
    }
}
