package main.java;

import java.util.HashMap;
import java.util.Map;

public class Controller {
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	
	public Map<String, Suggestion> suggestionsMap;
	
	public Controller() {
		suggestionsMap = new HashMap<String, Suggestion>();
		
        suggestionsMap.put("blockCommentSuggestion", new Suggestion("blockCommentSuggestion", "Try using 'CMD + /' to comment several lines.", HOTKEY, true));
        suggestionsMap.put("addImportStatementsSuggestion", new Suggestion("addImportStatementsSuggestion", "Try using 'Ctrl + Shift + O' to add import statements.", HOTKEY, true));
        suggestionsMap.put("removeUnusedImportsStatementSuggestion", new Suggestion("removeUnusedImportsStatementSuggestion", "Try using 'Ctrl + Shift + O' to remove unused imports.", HOTKEY, true));
        suggestionsMap.put("correctIndentationsSuggestion", new Suggestion("correctIndentationsSuggestion", "Try using 'Ctrl + i' to correct indentation.", HOTKEY, true));
        suggestionsMap.put("variableRenameRefactorSuggestion", new Suggestion("variableRenameRefactorSuggestion", "Try using 'Alt + Shift + R' to rename all instances of a variable, class, or method.", HOTKEY, true));
        
        suggestionsMap.put("enableAutocompleteSuggestion", new Suggestion("enableAutocompleteSuggestion", "Enable content assist auto activation", CONFIG, true, false));
	}
	
	public Map<String, Suggestion> getSuggestionsMap() {
		return suggestionsMap;
	}

}
