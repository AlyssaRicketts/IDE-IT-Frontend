package main.java;

public class Suggestion {
	public String displayText;	// text displayed in window
	public String id;			// unique feature name
	public int type;			// 0 for CONFIG, 1 for HOTKEY
	public Boolean display;		// display if true, otherwise do not display in window
	public int counter;			// number of times Suggestion has been displayed
	
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
	
	public void increaseCount() {
		this.counter++;
	}
	
	public int getCount() {
		return this.counter;
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
