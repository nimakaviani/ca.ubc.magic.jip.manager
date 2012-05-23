package jipplugin.actions.toolbar;

import jipplugin.Activator;
import jipplugin.resources.Messages;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;




import com.jchapman.jipsnapman.events.ISnapshotEventListener;
import com.jchapman.jipsnapman.events.SnapshotEvent;
import com.jchapman.jipsnapman.events.SnapshotEventManager;
import com.jchapman.jipsnapman.models.Snapshot;

public class 
FinishAction 
extends Action 
implements ISnapshotEventListener
{
	private final SnapshotEventManager 	snapshot_event_manager;
	private Snapshot 					current_snapshot;
	
	public
	FinishAction
	( SnapshotEventManager snapshot_event_manager )
	{
		// we'll see if something else needs a reference to this
		this.snapshot_event_manager
			= snapshot_event_manager;
		this.snapshot_event_manager.addSnapshotEventListener(this);
		
		this.setToolTipText
		(Messages.JIP_Finish_Tooltip);
		
		this.setEnabled(false);
		
	}
	
	@Override
	public void
	setEnabled
	(boolean enabled)
	{
		super.setEnabled(enabled);
		
		String image_path
			= enabled 
			? Messages.JIP_Active_Disconnect_Path
			: Messages.JIP_Inactive_Disconnect_Path;
		
		this.setImageDescriptor
		(Activator.getImageDescriptor(image_path));
	}
	@Override
	public void run()
	{
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openInformation
		( shell, "First plug-in", "Finish!" );
		
		this.snapshot_event_manager.fireSnapshotEvent( 
			new SnapshotEvent(
				SnapshotEvent.ID_SNAPSHOT_CAPTURED,
				current_snapshot
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
		if (event.getId() == SnapshotEvent.ID_SNAPSHOT_STARTED) {
		      this.setEnabled(true);
		      this.current_snapshot = event.getSnapshot();
		    }
	}
}
