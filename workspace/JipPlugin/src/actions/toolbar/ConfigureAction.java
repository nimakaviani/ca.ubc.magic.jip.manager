package actions.toolbar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import jipplugin.Activator;
import jipplugin.DetailsDialog;
import jipplugin.JIPView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ConfigureAction 
extends Action 
implements PropertyChangeListener
{
	// this should eventually be turned into a "view" that can
	// be in the list of views to be updated
	JIPView jipView;
	
	// basically I am trying to understand the reference's technique for 
	// keeping track of the previous path and displaying it
	public 
	ConfigureAction
	( JIPView jipView )
	{
		this.jipView = jipView;
		this.setToolTipText("Set the path, name, port, and host to work with.");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/configure.gif"));
		
		Activator.getDefault().getModel().addPropertyChangeListener(this);
	}
	
	@Override
	public void run(){
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		DetailsDialog dialog = new DetailsDialog(shell);
		
		if(dialog.open() != InputDialog.OK){
			return;
		}		
		
		//view.setSnapshotDetails("hello", "there", "you", "there");
	}

	@Override
	public void 
	propertyChange
	(PropertyChangeEvent arg0) 
	{
		jipplugin.Model snapshot = Activator.getDefault().getModel();
		// the main view should be updated in response to this
		// for now cheat
		jipView.setSnapshotDetails(
			snapshot.getSnapshotPath(), 
			snapshot.getSnapshotName(), 
			snapshot.getSnapshotPort(),
			snapshot.getSnapshotHost()
		);
	}
}
