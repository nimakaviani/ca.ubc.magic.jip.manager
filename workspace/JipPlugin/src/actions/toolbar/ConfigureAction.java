package actions.toolbar;

import jipplugin.Activator;
import jipplugin.DetailsDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ConfigureAction 
extends Action 
{
	// basically I am trying to understand the reference's technique for 
	// keeping track of the previous path and displaying it
	public 
	ConfigureAction()
	{
		this.setToolTipText("Set the path, name, port, and host to work with.");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/configure.gif"));
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
}
