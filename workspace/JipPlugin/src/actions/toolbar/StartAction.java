package actions.toolbar;

import jipplugin.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.jchapman.jipsnapman.events.ISnapshotEventListener;
import com.jchapman.jipsnapman.events.SnapshotEvent;
import com.jchapman.jipsnapman.events.SnapshotEventManager;

// I think I finally need to introduce a model for the toolbar...
public class 
StartAction 
extends Action 
implements ISnapshotEventListener
{
	// this may need to be the same manager as the one used by
	// finish
	private final SnapshotEventManager snapshot_event_manager;
	
	public 
	StartAction
	(SnapshotEventManager snapshot_event_manager)
	{
		this.snapshot_event_manager 
			= snapshot_event_manager;
		this.snapshot_event_manager.addSnapshotEventListener(this);
		this.setToolTipText
		("Connect and profile to produce snapshot.");
		this.setEnabled(true);
	}
	
	@Override
	public void
	setEnabled
	(boolean enabled)
	{
		super.setEnabled(enabled);
		
		String image_path
			= enabled 
			? "icons/run_exc_active.gif"
			: "icons/run_exc.gif";
	
		this.setImageDescriptor
		(Activator.getImageDescriptor(image_path));
	}
	
	@Override
	public void 
	run()
	{
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openInformation
		(shell, "First plug-in", "Start!" );
		
		this.snapshot_event_manager.fireSnapshotEvent(
			new SnapshotEvent(
	            SnapshotEvent.ID_SNAPSHOT_STARTED, 
	            null
	        )
		);
	    this.setEnabled(false);
	}

	 /* ---------------- from SnapshotEventListener --------------- */
	
	@Override
	public void
	handleSnapshotEvent
	(SnapshotEvent event) 
	{
		switch (event.getId()) {
		    case SnapshotEvent.ID_SNAPSHOT_CAPTURED:
		    case SnapshotEvent.ID_SNAPSHOT_CAPTURE_FAILED:
			    this.setEnabled(true);
			    break;
		}
	}
}
