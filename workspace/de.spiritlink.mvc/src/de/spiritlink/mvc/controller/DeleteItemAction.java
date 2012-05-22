/**
 * 
 */
package de.spiritlink.mvc.controller;

import org.eclipse.jface.action.Action;

import de.spiritlink.mvc.Activator;
import de.spiritlink.mvc.model.Item;

/**
 * @author tmseidel
 *
 */
public class DeleteItemAction extends Action {
    
    private Item selectedObject = null;
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#getText()
     */
    public String getText() {
        return "Delete selected object"; //$NON-NLS-1$
    }
    
    /**
     * @param firstElement
     */
    public DeleteItemAction(Item element) {
        this.selectedObject = element;
        setEnabled(this.selectedObject != null);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        Activator.getDefault().getObjectList().remove(this.selectedObject);
    }

    
    

}
