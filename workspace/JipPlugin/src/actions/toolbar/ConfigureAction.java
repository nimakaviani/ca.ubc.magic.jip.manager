package actions.toolbar;

import jipplugin.Activator;
import jipplugin.DetailsDialog;
import jipplugin.IView;
import jipplugin.Model;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import actions.ControllerDelegate;

public class 
ConfigureAction 
extends Action 
{
	private ControllerDelegate 	controller;
	private DetailsDialog 		dialog;
	
	public 
	ConfigureAction
	( IView main_view )
	{
		controller 
			= new ControllerDelegate();
		
		this.setToolTipText
		( "Set the path, name, port, and host to work with." );
		this.setImageDescriptor
		( Activator.getImageDescriptor("icons/configure.gif") );
		
		Shell shell
			= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		dialog 
			= new DetailsDialog(shell, controller);
		
		this.controller.addModel(Activator.getDefault().getModel());
		this.controller.addView(main_view);
		this.controller.addView(dialog);
	}
	
	@Override
	public void 
	run()
	{
		if(dialog.open() != InputDialog.OK){
			return;
		}		
	}
}
