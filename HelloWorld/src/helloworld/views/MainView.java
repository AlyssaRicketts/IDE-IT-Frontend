package helloworld.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class MainView extends ViewPart {

	private Button button;

    public MainView() {
        super();
    }

    @Override
    public void createPartControl(Composite parent) {
        button = new Button(parent,SWT.CHECK);
        button.setText("An example of a checkbox");
    }

    @Override
    public void setFocus() {
        button.setFocus();
    }

}
