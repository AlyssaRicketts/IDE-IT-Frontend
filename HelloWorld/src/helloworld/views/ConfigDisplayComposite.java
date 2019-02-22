package helloworld.views;

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
		
		RowLayout rowLayout = new RowLayout();
    	rowLayout.type = SWT.HORIZONTAL;
    	rowLayout.pack = true;
    	baseComposite.setLayout(rowLayout);
    	
    	Button checkBox = new Button(baseComposite, SWT.CHECK);
    	checkBox.setText(s.getText());
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
    	
    	Button exitButton = new Button(baseComposite, SWT.NONE);
    	exitButton.setText("X");
    	exitButton.addListener(SWT.Selection, new Listener() {
  	      public void handleEvent(Event e) {
  	    	  baseComposite.dispose();
                parent.requestLayout();
  	      }
  	});
	}
	
}