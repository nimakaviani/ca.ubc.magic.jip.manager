package actions.menu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import resources.Messages;



public class AboutAction 
extends Action 
{
	public
	AboutAction()
	{
		this.setToolTipText(Messages.JIP_About_Tooltip);
		this.setText(Messages.JIP_About_Label);
	}
	
	@Override
	public void 
	run()
	{
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openInformation( shell, "First plug-in", "About!" );
	}
}
