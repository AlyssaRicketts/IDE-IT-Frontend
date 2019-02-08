package main.java;

import java.util.HashMap;
import java.util.Map;

public class Controller {
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	
	Map<String, Suggestion> idsToSuggestions = new HashMap<String, Suggestion>();
	
	Suggestion multilineComment = new Suggestion("Try using 'CMD + /' to comment several lines.", HOTKEY, true);

}
