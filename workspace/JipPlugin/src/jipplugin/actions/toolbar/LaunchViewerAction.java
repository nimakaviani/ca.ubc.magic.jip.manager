package jipplugin.actions.toolbar;

import jipplugin.Activator;
import jipplugin.resources.Messages;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;




public class 
LaunchViewerAction 
extends Action 
{
	public
	LaunchViewerAction()
	{
		this.setToolTipText(Messages.JIP_Launch_Tooltip);
		this.setImageDescriptor(Activator.getImageDescriptor(Messages.JIP_Active_Visualize_Path));
	}
	
	@Override
	public void run()
	{
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openInformation( shell, "First plug-in", "Launch!" );
		this.setImageDescriptor(Activator.getImageDescriptor(Messages.JIP_Inactive_Visualize_Path));
	}
}
