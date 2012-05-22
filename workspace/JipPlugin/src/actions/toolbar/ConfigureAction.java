package actions.toolbar;

import jipplugin.Activator;
import jipplugin.DetailsDialog;
import jipplugin.JIPView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ConfigureAction 
extends Action 
{
	JIPView view;
	
	public 
	ConfigureAction
	(JIPView view)
	{
		this.view = view;
		this.setToolTipText("Set the path, name, port, and host to work with.");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/configure.gif"));
	}
	
	@Override
	public void run(){
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		//MessageDialog.openInformation( shell, "First plug-in", "Hello, world!" );
		DetailsDialog dialog = new DetailsDialog(shell);
		
		if(dialog.open() != InputDialog.OK){
			return;
		}		
		
		view.setSnapshotDetails("hello", "there", "you", "there");
	}
}
