package plugin.views;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import main.java.Suggestion;

public class ConfigDisplayComposite {

	public ConfigDisplayComposite(final Composite parent, Suggestion s) {
		final Composite baseComposite = new Composite(parent, SWT.NONE);

		// Set layout
		RowLayout rowLayout = new RowLayout();
    	rowLayout.type = SWT.HORIZONTAL;
    	rowLayout.pack = true;
    	baseComposite.setLayout(rowLayout);

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
    	Button exitButton = new Button(baseComposite, SWT.NONE);
    	exitButton.setText("X");
    	exitButton.addListener(SWT.Selection, new Listener() {
  	      public void handleEvent(Event e) {
  	    	  baseComposite.dispose();
  	    	  parent.requestLayout();
  	      }
    	});
	}

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