package views;

import model_controllers.Snapshot;

import org.eclipse.jface.viewers.LabelProvider;


public class 
SnapshotsLabelProvider 
extends LabelProvider 
{
	@Override
	public String
	getText
	( Object element )
	{
		Snapshot snapshot 
		 	= (Snapshot) element;
		
		return snapshot.getName() 
			+ "(" + snapshot.getHost()
			+ ":" + snapshot.getPort() 
			+ ")";
	}
}
