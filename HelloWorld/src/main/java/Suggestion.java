package main.java;

public class Suggestion {
	// Fields
	public String displayText;
	public int type;
	public Boolean display;
	public Boolean enabled;
	
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
		if (this.type == HOTKEY) {
			throw new IllegalArgumentException("Hotkeys cannot be enabled or disabled");
		}
		return this.enabled;
	}
	
	public void setText(String text) {
		this.displayText = text;
	}
	
	public void setType(int type) {
		if (type != CONFIG && type != HOTKEY) {
			throw new IllegalArgumentException("Type must be of CONFIG (0) or HOTKEY (1).");
		}
		this.type = type;
	}
	
	public void setDisplay(Boolean disp) {
		this.display = disp;
	}
	
	public void setEnabled(Boolean enabled) {
		if (this.type == HOTKEY) {
			throw new IllegalArgumentException("Hotkeys cannot be enabled or disabled");
		}
		this.enabled = enabled;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Suggestion)) {
			return false;
		}
		Suggestion compared = (Suggestion) other;
		return this.displayText.equals(compared.displayText) && this.type == compared.type
				&& this.display == compared.display && this.enabled == compared.enabled;
	}
}
