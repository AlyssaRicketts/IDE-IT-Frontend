package main.java;

import java.util.HashMap;
import java.util.Map;

import helloworld.views.MainView;

public class Controller {
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	
	Map<String, Suggestion> idsToSuggestions;
	
	public Controller() {
		idsToSuggestions = new HashMap<String, Suggestion>();
		Suggestion multilineComment = new Suggestion("Try using 'CMD + /' to comment several lines.", HOTKEY, true);
		idsToSuggestions.put("multiline comment", multilineComment);
	}

}
