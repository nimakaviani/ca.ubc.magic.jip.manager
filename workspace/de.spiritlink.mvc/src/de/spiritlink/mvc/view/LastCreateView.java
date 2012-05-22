/**
 * 
 */
package de.spiritlink.mvc.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import de.spiritlink.mvc.Activator;
import de.spiritlink.mvc.model.Item;

/**
 * @author tmseidel
 *
 */
public class LastCreateView extends ViewPart implements PropertyChangeListener {
    
    
    public static final String ID = "de.spiritlink.mvc.lastcreated"; //$NON-NLS-1$
    private Label idLabel;

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        Label lastLable = new Label(parent,SWT.NONE);
        lastLable.setText("Last createdID");
        GridData gd = new GridData(SWT.BEGINNING,SWT.BEGINNING,true,false);
        lastLable.setLayoutData(gd);
        
        this.idLabel = new Label(parent,SWT.NONE);
        gd = new GridData(SWT.BEGINNING,SWT.BEGINNING,true,false);
        gd.verticalIndent = 10;
        this.idLabel.setLayoutData(gd);
        this.idLabel.setText("n.a."); //$NON-NLS-1$
        
        Activator.getDefault().getObjectList().addPropertyChangeListener(this);
        

    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    public void setFocus() {
        this.idLabel.setFocus();
    }

    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // we just react on add events
        if ("ITEM_ADD".equals(evt.getPropertyName())) { //$NON-NLS-1$
            this.idLabel.setText(((Item) evt.getNewValue()).getId());
        }
        
    }

}
