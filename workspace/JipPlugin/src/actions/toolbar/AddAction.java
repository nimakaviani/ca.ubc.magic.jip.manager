package actions.toolbar;

import jipplugin.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class 
AddAction 
extends Action
{
	public
	AddAction()
	{
		this.setToolTipText("Add the snapshot to the partitioner's working set.");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/add_att.gif"));
	}
	
	@Override
	public void run()
	{
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openInformation( shell, "First plug-in", "Add!" );
	}
}
