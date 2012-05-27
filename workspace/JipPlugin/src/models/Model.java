package models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

import com.jchapman.jipsnapman.models.ISnapshotInfoModel;
import com.jchapman.jipsnapman.models.Snapshot;

// if I put too much data in this object too many messages
// will get passed around
public class
Model 
implements ISnapshotInfoModel
{
	private String path = new String("");
	private String name = new String("");
	private String port = new String("");
	private String host = new String("");
	
	private List<Object> snapshots_list 
		= new LinkedList<Object>();
	private List<Object> event_log_list
		= new LinkedList<Object>();
	
	protected transient PropertyChangeSupport listeners 
 		= new PropertyChangeSupport(this);
	
	/**
     * Adds a property-change listener.
     * 
     * @param l the listener
     */
    public void 
    addPropertyChangeListener
    ( PropertyChangeListener l )
    {
        if (l == null) {
            throw new IllegalArgumentException();
        }
        this.listeners.addPropertyChangeListener(l);
    }
    
    
    public void 
    removePropertyChangeListener
    (PropertyChangeListener l)
    {
        this.listeners.removePropertyChangeListener(l);
    }
    
    /**
     * Notifies all listeners to a model-change
     * 
     * @param prop the property-id
     * @param old the old-value
     * @param newValue the new value
     */
    protected void 
    firePropertyChange
    ( String prop, Object old, Object newValue )
    {
        if (this.listeners.hasListeners(prop)) {
            this.listeners.firePropertyChange(prop, old, newValue);
        }
    }

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
		this.firePropertyChange(Constants.PATH_PROPERTY, old_path, this.path);
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
		
		this.firePropertyChange(Constants.NAME_PROPERTY, old_name, this.name);
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
		
		this.firePropertyChange(Constants.PORT_PROPERTY, old_port, this.port);
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
		
		this.firePropertyChange(Constants.HOST_PROPERTY, old_host, this.host);
	}

	public void 
	setAdditionalSnapshot
	(Snapshot snapshot) 
	{
		boolean should_add 
			= this.snapshots_list.isEmpty() 
			|| this.snapshots_list.get(this.snapshots_list.size() - 1) != snapshot;
		
		if(should_add){
			this.snapshots_list.add(snapshot);
			this.firePropertyChange(Constants.SNAPSHOT_PROPERTY, null, this.snapshots_list);
			System.out.println("setAdditionalSnapshot(): snapshots_list length: " + this.snapshots_list.size());
		}
	}

	public List<Object> 
	getSnapshotsList() 
	{
		return this.snapshots_list;
	}


	public void 
	updateLog
	(String property) 
	{
		this.event_log_list.add(property);
		
		this.firePropertyChange(Constants.EVENT_LIST_PROPERTY, null, this.event_log_list);
	}
	
	public List<Object>
	getEventLogList()
	{
		return this.event_log_list;
	}
}
