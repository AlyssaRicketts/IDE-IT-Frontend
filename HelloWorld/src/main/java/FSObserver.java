package main.java;

import interfaces.FeatureSuggestionObserver;

public class FSObserver extends FeatureSuggestionObserver {
	
	@Override
	public void notify(String featureID) {
		// Add method here for searching map for featureID
		System.out.println("Feature suggestion for " + featureID);
	}
}
