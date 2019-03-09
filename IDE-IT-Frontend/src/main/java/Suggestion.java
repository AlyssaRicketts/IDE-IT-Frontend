package main.java;

/**
 * The Suggestion class creates new Suggestion objects.
 *
 */
public class Suggestion {
	public String displayText;	// text displayed in window
	public String id;			// unique feature name
	public int type;			// 0 for CONFIG, 1 for HOTKEY
	public Boolean display;		// display if true, otherwise do not display in window
	public int counter;			// number of times Suggestion has been displayed
	
	private static final int CONFIG = 0;
	private static final int HOTKEY = 1;
	
	/**
	 * Constructor for new suggestion (config or hotkey)
	 * 
	 * @param id suggestion id
	 * @param displayText suggestion text 
	 * @param type value representing type of suggestion
	 * @param display value representing whether the suggestion is displayed
	 */
	public Suggestion(String id, String displayText, int type, Boolean display) {
		this.displayText = displayText;
		this.type = type;
		this.display = display;
		this.id = id;
	}
	
	/**
	 * Get suggestion ID.
	 * 
	 * @return suggestion ID
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * Get suggestion display text.
	 * 
	 * @return suggestion text
	 */
	public String getText() {
		return this.displayText;
	}
	
	/**
	 * Get suggestion type.
	 * 
	 * @return value representing type of suggestion
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * Increment a value by one.
	 * 
	 */
	public void increaseCount() {
		this.counter++;
	}
	
	/**
	 * Get count value for a suggestion.
	 * 
	 * @return value representing how many times a suggestion
	 * 			has been displayed
	 */
	public int getCount() {
		return this.counter;
	}
	
	/**
	 * Get value representing whether or not a suggestion is currently displayed.
	 * 
	 * @return value representing whether a suggestion is displayed.
	 */
	public Boolean getDisplay() {
		return this.display;
	}
	
	/**
	 * Set the display text for a suggestion.
	 * 
	 * @param text new display text for suggestion
	 */
	public void setText(String text) {
		this.displayText = text;
	}
	
	/**
	 * Set the type for a suggestion.
	 * 
	 * @param type the type of a suggestion (either CONFIG (0) or HOTKEY (1))
	 * 
	 * @throws IllegalArgumentException if type is not CONFIG (0) or HOTKEY (1)
	 */
	public void setType(int type) {
		if (type != CONFIG && type != HOTKEY) {
			throw new IllegalArgumentException("Type must be of CONFIG (0) or HOTKEY (1).");
		}
		this.type = type;
	}
	
	/**
	 * Set value representing whether a suggestion is currently displayed.
	 * 
	 * @param disp boolean value representing whether a suggestion is displayed
	 */
	public void setDisplay(Boolean disp) {
		this.display = disp;
	}
	

	/**
	 * Checks if two suggestions are equal.
	 * 
	 * @param other Suggestion for comparison
	 */
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
