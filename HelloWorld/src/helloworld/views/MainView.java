package helloworld.views;

import java.util.Map;

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
    	
    	// Temporary hard-code content assist suggestion
    	Controller control = new Controller();
    	Map<String, Suggestion> suggestionsMap = control.getSuggestionsMap();
    	Suggestion feature = suggestionsMap.get("enableAutocompleteSuggestion");
    	addFeature(feature);
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
