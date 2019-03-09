package plugin.views;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import main.java.Suggestion;

/*
 * Class defining a new composite object, containing a checkbox, text, and an exit button.
 */
public class ConfigDisplayComposite {
	
	/**
	 * Creates a new ConfigDisplayComposite object.
	 * 
	 * @param parent. The composite object that this composite will be a child of.
	 * @param s. The suggestion object for whose composite object we will be making. The
	 * 				text from this object will be the text displayed in the composite object.
	 * @param display. The display that will be appended.
	 */
	public ConfigDisplayComposite(final Composite parent, Suggestion s, Display display) {
		final Composite baseComposite = new Composite(parent, SWT.NONE);

		// Set layout
		GridLayout GridLayout = new GridLayout();
		GridLayout.numColumns = 2;
		GridLayout.makeColumnsEqualWidth = true;
    	baseComposite.setLayout(GridLayout);

    	// Add checkbox
    	Button checkBox = new Button(baseComposite, SWT.CHECK);
    	checkBox.setText(s.getText());

    	// Add proper event handler for checkbox based on suggestion type
    	if (s.getID().equals("enableAutocompleteSuggestion")) {
    		autoActivationCheckbox(checkBox);
    	} else if(s.getID().equals("enableSmartSemicolonSuggestion")) {
    		smartSemicolonCheckbox(checkBox);
    	} else if(s.getID().equals("enableShadowedVariableWarning")) {
    		shadowVariableWarning(checkBox);
    	} else if(s.getID().equals("trailingWhiteSpaceSuggestion")) {
    		trailingWhitespace(checkBox);
    	}

    	// Add exit button
    	Image exitButton = new Image(display, getClass().getResourceAsStream("../../../icons/ExitButton.png"));
    	Label test = new Label(baseComposite, SWT.NONE);
    	test.setImage(exitButton);
    	test.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				baseComposite.dispose();
				parent.requestLayout();
				s.setDisplay(true);
			}
			
			@Override
			public void mouseDown(MouseEvent e) {}

			@Override
			public void mouseUp(MouseEvent e) {}	
    	});
	}

	/**
	 * Adds a listener to the checkbox, which enables content assist auto activation when checked,
	 * and disables it when unchecked.
	 * 
	 * @param checkBox. The checkbox to add a listener to.
	 */
	public void autoActivationCheckbox(Button checkBox) {
		checkBox.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent event) {
    			Button btn = (Button) event.getSource();

    			if(btn.getSelection()) { // Checked, so enable autoactivation
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    				prefs.put("content_assist_autoactivation", "true");

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			} else { // Not checked, so disable autoactivation
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    				prefs.put("content_assist_autoactivation", "false");

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			}
    		}
    	});
	}

	/**
	 * Adds a listener to the checkbox, which enables content assist smart semicolon when checked,
	 * and disables it when unchecked.
	 * 
	 * @param checkBox. The checkbox to add a listener to.
	 */
	public void smartSemicolonCheckbox(Button checkBox) {
		checkBox.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent event) {
    			Button btn = (Button) event.getSource();

    			if(btn.getSelection()) { // Checked, so enable smart semicolon
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    				prefs.put("smart_semicolon", "true");

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			} else { // Not checked, so disable smart semicolon
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    				prefs.put("smart_semicolon", "false");

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			}
    		}
    	});
	}

	/**
	 * Adds a listener to the checkbox, which enables a compiler warning for shadowed variables when
	 * checked, and ignores it when unchecked.
	 * 
	 * @param checkBox. The checkbox to add a listener to.
	 */
	public void shadowVariableWarning(Button checkBox) {
		checkBox.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent event) {
    			Button btn = (Button) event.getSource();

    			if(btn.getSelection()) { // Checked, so shadowed variable warning
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.core");
    				prefs.put("org.eclipse.jdt.core.compiler.problem.fieldHiding", "warning");
    				System.out.println("Checked pref: " + prefs.get("org.eclipse.jdt.core.compiler.problem.fieldHiding", "default"));

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			} else { // Not checked, so disable shadowed variable warning
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.core");
    				prefs.put("org.eclipse.jdt.core.compiler.problem.fieldHiding", "ignore");
    				System.out.println("Unchecked pref: " + prefs.get("org.eclipse.jdt.core.compiler.problem.fieldHiding", "default"));

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			}
    		}
    	});
	}

	/**
	 * Adds a listener to the checkbox, which enables trailing white spaces to be removed upon
	 * save when checked, and disables it when unchecked.
	 * 
	 * @param checkBox. The checkbox to add a listener to.
	 */
	public void trailingWhitespace(Button checkBox) {
		checkBox.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent event) {
    			Button btn = (Button) event.getSource();

    			if(btn.getSelection()) { // Checked, so enable smart semicolon
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    				prefs.put("editor_save_participant_org.eclipse.jdt.ui.postsavelistener.cleanup", "true");

    				prefs.put("sp_cleanup.remove_trailing_whitespaces", "true");
    				prefs.put("sp_cleanup.remove_trailing_whitespaces_all", "true");
    				prefs.put("sp_cleanup.remove_trailing_whitespaces_ignore_empty", "false");

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			} else { // Not checked, so disable smart semicolon
    				IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode("org.eclipse.jdt.ui");
    				prefs.put("sp_cleanup.remove_trailing_whitespaces", "false");
    				prefs.put("sp_cleanup.remove_trailing_whitespaces_all", "false");

    				try {
    					prefs.flush();
    				} catch (org.osgi.service.prefs.BackingStoreException f) {
    					f.printStackTrace();
    				}
    			}
    		}
    	});
	}
}