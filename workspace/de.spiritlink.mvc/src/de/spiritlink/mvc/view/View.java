package de.spiritlink.mvc.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.spiritlink.mvc.Activator;
import de.spiritlink.mvc.controller.DeleteItemAction;
import de.spiritlink.mvc.model.Item;

public class View extends ViewPart implements PropertyChangeListener {
	public static final String ID = "de.spiritlink.mvc.view"; //$NON-NLS-1$

	private TableViewer viewer;

	
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
            // do nothing
		}

		public void dispose() {
            // do nothing
		}

		public Object[] getElements(Object parent) {
			return Activator.getDefault().getObjectList().toArray();
		}
	}

	class ViewLabelProvider extends LabelProvider {
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
        public String getText(Object element) {
            return ((Item) element).getId();
        }
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		this.viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		this.viewer.setContentProvider(new ViewContentProvider());
		this.viewer.setLabelProvider(new ViewLabelProvider());
		this.viewer.setInput(Activator.getDefault().getObjectList());
        hookContextMenu();
        // add listener to model to keep on track. 
        Activator.getDefault().getObjectList().addPropertyChangeListener(this);
	}
    
    private void hookContextMenu() {
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager manager) {
                // Adding controller to context-menu
                manager.add(new DeleteItemAction((Item) ((IStructuredSelection) viewer.getSelection()).getFirstElement()));
            }
        });
        Menu menu = menuMgr.createContextMenu(this.viewer.getControl());
        this.viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuMgr, this.viewer);
    }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		this.viewer.getControl().setFocus();
	}
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#dispose()
     */
    public void dispose() {
        // deregister listener
        Activator.getDefault().getObjectList().removePropertyChangeListener(this);
        super.dispose();
    }

    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // the viewer represents simple model. refresh should be enough.
        if ("ITEM_ADD".equals(evt.getPropertyName())) { //$NON-NLS-1$
            this.viewer.refresh();
        }
        // event on deletion --> also just refresh
        if ("ITEM_REMOVE".equals(evt.getPropertyName())) { //$NON-NLS-1$
            this.viewer.refresh();
        }
        
    }
}