package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.java.Suggestion;

public class SuggestionTest {
	private Suggestion hotkey;
	private Suggestion config;
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	
	@Before
	public void setUp() throws Exception {
		hotkey = new Suggestion("blockCommentSuggestion", "Try using 'CMD + /' to comment several lines.", HOTKEY, true);
		config = new Suggestion("enableAutocompleteSuggestion", "Enable autocomplete", CONFIG, true);
	}
	
	@Test
	public void testHotkeyGetText() {
		assertEquals("Try using 'CMD + /' to comment several lines.", hotkey.getText());
	}
	
	@Test
	public void testConfigGetText() {
		assertEquals("Enable autocomplete", config.getText());
	}
	
	@Test
	public void testHotkeyType() {
		assertEquals(1, hotkey.getType());
	}
	
	@Test
	public void testConfigType() {
		assertEquals(0, config.getType());
	}
	
	@Test
	public void testIncreaseCount() {
		hotkey.increaseCount();
		assertEquals(1, hotkey.counter);
	}
	
	@Test
	public void testGetCount() {
		hotkey.increaseCount();
		assertEquals(1, hotkey.getCount());
	}
	
	@Test
	public void testHotkeyDisplay() {
		assertEquals(true, hotkey.getDisplay());
	}
	
	@Test
	public void testConfigDisplay() {
		assertEquals(true, config.getDisplay());
	}
	
	@Test
	public void testHotkeySetText() {
		hotkey.setText("hello");
		assertEquals("hello", hotkey.getText());
	}
	
	@Test
	public void testConfigSetText() {
		config.setText("hi");
		assertEquals("hi", config.getText());
	}
	
	@Test
	public void testHotkeySetType() {
		hotkey.setType(0);
		assertEquals(0, hotkey.getType());
	}
	
	@Test
	public void testConfigSetType() {
		config.setType(1);
		assertEquals(1, config.getType());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalType() {
		hotkey.setType(2);
	}
	
	@Test
	public void testHotkeySetDisplay() {
		hotkey.setDisplay(false);
		assertEquals(false, hotkey.getDisplay());
	}
	
	@Test
	public void testConfigSetDisplay() {
		config.setDisplay(false);
		assertEquals(false, config.getDisplay());
	}
}
