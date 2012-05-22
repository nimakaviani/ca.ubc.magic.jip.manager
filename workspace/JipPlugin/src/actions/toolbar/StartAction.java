package actions.toolbar;

import jipplugin.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

// I think I finally need to introduce a model for the toolbar...
public class 
StartAction 
extends Action 
{
	public 
	StartAction()
	{
		this.setToolTipText("Connect and profile to produce snapshot.");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/run_exc_active.gif"));
	}
	
	@Override
	public void 
	run()
	{
			Shell shell
				= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog.openInformation(shell, "First plug-in", "Start!" );
			this.setImageDescriptor(Activator.getImageDescriptor("icons/run_exc.gif"));
	}
}