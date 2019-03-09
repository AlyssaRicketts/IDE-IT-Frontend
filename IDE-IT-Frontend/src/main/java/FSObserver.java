package main.java;

import java.util.Map;

import plugin.views.MainView;
import main.interfaces.FeatureSuggestionObserver;

public class FSObserver extends FeatureSuggestionObserver {
	MainView view;
	Map<String, Suggestion> suggestionsMap; // Map from suggestion ID's to suggestion objects
	
	/**
	 * Sets up a new Controller and gets the suggestion map from it.
	 * 
	 * @param mainView the MainView to be used
	 */
	public FSObserver(MainView mainView) {		
		view = mainView;
		Controller control = new Controller();
		suggestionsMap = control.getSuggestionsMap();
	}
	

	/**
	 * Prints to the console when a feature has been registered and sends it 
	 * to the view to be displayed in the user's plugin window.
	 * 
	 * @param featureID String representing a suggestion ID
	 */
	@Override
	public void notify(String featureID) {
		// Add method here for searching map for featureID		
		System.out.println("Feature suggestion for " + featureID);
		Suggestion feature = suggestionsMap.get(featureID);
		System.out.println(feature.getID());
		view.addFeature(feature);
	}
}
