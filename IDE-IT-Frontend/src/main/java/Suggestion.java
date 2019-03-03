package main.java;

public class Suggestion {
	// Fields
	public String displayText;
	public String id;
	public int type;
	public Boolean display;
	
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;

	
	public Suggestion(String id, String displayText, int type, Boolean display) {
		this.displayText = displayText;
		this.type = type;
		this.display = display;
		this.id = id;
	}
	
	public String getID() {
		return this.id;
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
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Suggestion)) {
			return false;
		}
		Suggestion compared = (Suggestion) other;
		return this.displayText.equals(compared.displayText) && this.type == compared.type
				&& this.display == compared.display;
	}
}
