package jipplugin.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jipplugin.resources.Messages;



import com.jchapman.jipsnapman.models.ISnapshotInfoModel;

public class
Model 
implements ISnapshotInfoModel
{
	private String path = new String("");
	private String name = new String("");
	private String port = new String("");
	private String host = new String("");
	
	protected transient PropertyChangeSupport listeners 
 		= new PropertyChangeSupport(this);
	
	/**
     * Adds a property-change listener.
     * 
     * @param l the listener
     */
    public void 
    addPropertyChangeListener
    (PropertyChangeListener l)
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
		
		this.firePropertyChange(Messages.JIP_Path_Property_Method, old_path, this.path);
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
		
		this.firePropertyChange(Messages.JIP_Name_Property_Method, old_name, this.name);
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
		
		this.firePropertyChange(Messages.JIP_Port_Property_Method, old_port, this.port);
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
		
		this.firePropertyChange(Messages.JIP_Host_Property_Method, old_host, this.host);
	}

}
