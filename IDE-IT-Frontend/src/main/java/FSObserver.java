package main.java;

import java.util.Map;

import plugin.views.MainView;
import main.interfaces.FeatureSuggestionObserver;

public class FSObserver extends FeatureSuggestionObserver {
	MainView view;
	Map<String, Suggestion> suggestionsMap;
	
	public FSObserver(MainView mainView) {		
		view = mainView;
		Controller control = new Controller();
		suggestionsMap = control.getSuggestionsMap();
	}
	
	@Override
	public void notify(String featureID) {
		// Add method here for searching map for featureID		
		System.out.println("Feature suggestion for " + featureID);
		Suggestion feature = suggestionsMap.get(featureID);
		view.addFeature(feature);
	}
}
