/**
 * 
 */
package de.spiritlink.mvc.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.spiritlink.mvc.controller.CreateItemAction;

/**
 * @author tmseidel
 *
 */
public class FormView extends ViewPart {

    Text idText;
    
    public static final String ID = "de.spiritlink.mvc.form"; //$NON-NLS-1$

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(2,false));
        Label idLabel = new Label(parent, SWT.NONE);
        idLabel.setText("New ID:"); //$NON-NLS-1$
        GridData gd = new GridData(SWT.BEGINNING,SWT.CENTER,false,false);
        gd.widthHint = 50;
        idLabel.setLayoutData(gd);
        
        this.idText = new Text(parent,SWT.BORDER);
        gd = new GridData(SWT.FILL,SWT.CENTER,true,false);
        this.idText.setLayoutData(gd);
        
        Button button = new Button(parent,SWT.PUSH);
        button.setText("Create"); //$NON-NLS-1$
        gd = new GridData(SWT.RIGHT,SWT.BEGINNING,true,false);
        gd.horizontalSpan = 2;
        button.setLayoutData(gd);
        
        // Adding the controller
        button.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                new CreateItemAction(FormView.this.idText.getText()).run();
            }
        });
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    public void setFocus() {
        this.idText.setFocus();

    }

}
