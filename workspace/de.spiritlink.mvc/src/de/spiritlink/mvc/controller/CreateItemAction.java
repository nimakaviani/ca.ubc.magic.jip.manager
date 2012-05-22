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
public class CreateItemAction extends Action {
    
    private String newItemId;

    public CreateItemAction(String id) {
        this.newItemId = id;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        Item newItem = new Item();
        newItem.setId(this.newItemId);
        Activator.getDefault().getObjectList().add(newItem);
    }
}
