package actions.toolbar;

import jipplugin.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.jchapman.jipsnapman.events.ISnapshotEventListener;
import com.jchapman.jipsnapman.events.SnapshotEvent;
import com.jchapman.jipsnapman.events.SnapshotEventManager;

import views.DetailsDialog;
import views.IView;

import actions.ControllerDelegate;

public class 
ConfigureAction 
extends Action 
implements ISnapshotEventListener
{
	private ControllerDelegate 	controller;
	private DetailsDialog 		dialog;
	
	private SnapshotEventManager snapshot_event_manager;
	
	public 
	ConfigureAction
	( IView main_view, SnapshotEventManager snapshot_event_manager )
	{
		this.controller 
			= new ControllerDelegate();
		this.snapshot_event_manager
		 	= snapshot_event_manager;
		this.snapshot_event_manager.addSnapshotEventListener(this);
		
		this.setToolTipText
		( "Set the path, name, port, and host to work with." );
		this.setEnabled(true);
		
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		this.dialog 
			= new DetailsDialog(shell, this.controller);
		
		this.controller.addModel(Activator.getDefault().getModel());
		this.controller.addView(main_view);
		this.controller.addView(this.dialog);
	}
	
	@Override
	public void
	setEnabled
	( boolean enabled )
	{
		super.setEnabled(enabled);
		
		String image_path
			= enabled 
			? "icons/configure.gif"
			: "icons/configure_inactive.gif";
		
		this.setImageDescriptor
		(Activator.getImageDescriptor(image_path));
	}

	@Override
	public void 
	run()
	{
		if(this.dialog.open() != InputDialog.OK){
			return;
		}
	}
	
	// --------------- from SnapshotEventListener --------------- 

    public void 
    handleSnapshotEvent
    ( SnapshotEvent event ) 
    {
        switch (event.getId()) {
            case SnapshotEvent.ID_SNAPSHOT_STARTED:
                this.setEnabled(false);
                break;
            case SnapshotEvent.ID_SNAPSHOT_CAPTURED:
                this.setEnabled(true);
                break;
        }
    }
}
