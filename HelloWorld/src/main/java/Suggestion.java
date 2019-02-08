package main.java;

public class Suggestion {
	// Fields
	String displayText;
	int type;
	Boolean display;
	Boolean enabled;
	
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	
	public Suggestion(String displayText, int type, Boolean display) {
		this(displayText, type, display, null);
	}
	
	public Suggestion(String displayText, int type, Boolean display, Boolean enabled) {
		this.displayText = displayText;
		this.type = type;
		this.display = display;
		this.enabled = enabled;
	}
	
	public String getText() {
		return this.displayText;
	}
	
	public int getType() {
		return this.type;
	}
	
	public Boolean getDisplay() {
		return this.display;
	}
	
	public Boolean getEnabled() {
		return this.enabled;
	}
	
	public void setText(String text) {
		this.displayText = text;
	}
	
	public void setType(int type) {
		if(type != CONFIG || type != HOTKEY) {
			throw new IllegalArgumentException("Type must be of CONFIG (0) or HOTKEY (1).");
		}
		this.type = type;
	}
	
	public void setDisplay(Boolean disp) {
		this.display = disp;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
