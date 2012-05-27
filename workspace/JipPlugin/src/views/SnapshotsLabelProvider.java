package views;

import org.eclipse.jface.viewers.LabelProvider;

import com.jchapman.jipsnapman.models.Snapshot;

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