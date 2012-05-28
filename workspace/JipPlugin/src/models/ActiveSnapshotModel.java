package models;

import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

public class
ActiveSnapshotModel 
implements ISnapshotInfoModel
{
	private String path = new String("");
	private String name = new String("");
	private String port = new String("");
	private String host = new String("");
	
	private List<Object> event_log_list
		= new LinkedList<Object>();
	
	private PropertyChangeDelegate property_change_delegate
		= new PropertyChangeDelegate();
	
	@Override
	public String
	getSnapshotPath() 
	{
		return this.path;
	}

	@Override
	public void 
	setSnapshotPath
	(String path) 
	{
		String old_path = this.path;
		this.path = path;
		
		System.out.println(this.path);
		this.property_change_delegate.
			firePropertyChange(Constants.PATH_PROPERTY, old_path, this.path);
	}
	
	@Override
	public String 
	getSnapshotName() 
	{
		return this.name;
	}
	
	@Override
	public void
	setSnapshotName
	(String name)
	{
		String old_name = this.name;
		this.name 		= name;
		
		this.property_change_delegate.firePropertyChange(
			Constants.NAME_PROPERTY, old_name, this.name
		);
	}

	@Override
	public String 
	getSnapshotPort() 
	{
		return this.port;
	}
	
	@Override
	public void
	setSnapshotPort
	(String port)
	{
		String old_port = this.port;
		this.port = port;
		
		this.property_change_delegate.firePropertyChange(
			Constants.PORT_PROPERTY, old_port, this.port
		);
	}

	@Override
	public String 
	getSnapshotHost() 
	{
		return this.host;
	}
	
	@Override
	public void
	setSnapshotHost
	(String host)
	{
		String old_host = this.host;
		this.host = host;
		
		this.property_change_delegate.firePropertyChange(
			Constants.HOST_PROPERTY, old_host, this.host
		);
	}

	public void 
	updateLog
	(String property) 
	{
		this.event_log_list.add(property);
		
		this.property_change_delegate.firePropertyChange(
			Constants.EVENT_LIST_PROPERTY, null, this.event_log_list
		);
	}
	
	public List<Object>
	getEventLogList()
	{
		return this.event_log_list;
	}

	@Override
	public void 
	addPropertyChangeListener
	( PropertyChangeListener l) 
	{
		this.property_change_delegate.addPropertyChangeListener(l);
	}

	@Override
	public void 
	removePropertyChangeListener
	( PropertyChangeListener l) 
	{
		this.property_change_delegate.removePropertyChangeListener(l);
	}
}
