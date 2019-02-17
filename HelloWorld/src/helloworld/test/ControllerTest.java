package helloworld.test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import main.java.Controller;
import main.java.Suggestion;

public class ControllerTest {
	private Controller controller;
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	
	@Before
	public void setUp() throws Exception {
		controller = new Controller();
	}
	
	@Test
	public void testMap() {
		Map<String, Suggestion> suggestionsMap = new HashMap<String, Suggestion>();
		
        suggestionsMap.put("blockCommentSuggestion", new Suggestion("Try using 'CMD + /' to comment several lines.", HOTKEY, true));
        suggestionsMap.put("addImportStatementsSuggestion", new Suggestion("Try using 'Ctrl + Shift + O' to add import statements.", HOTKEY, true));
        suggestionsMap.put("removeUnusedImportsStatementSuggestion", new Suggestion("Try using 'Ctrl + Shift + O' to remove unused imports.", HOTKEY, true));
        suggestionsMap.put("correctIndentationsSuggestion", new Suggestion("Try using 'Ctrl + i' to correct indentation.", HOTKEY, true));
        suggestionsMap.put("variableRenameRefactorSuggestion", new Suggestion("Try using 'Alt + Shift + R' to rename all instances of a variable, class, or method.", HOTKEY, true));
        suggestionsMap.put("enableAutocompleteSuggestion", new Suggestion("Enable autocomplete", CONFIG, true, false));
		
		assertTrue(suggestionsMap.equals(controller.getSuggestionsMap()));
	}
}
