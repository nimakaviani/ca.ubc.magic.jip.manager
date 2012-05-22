package jipplugin;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.jchapman.jipsnapman.models.ISnapshotInfoModel;

public class
Model 
implements ISnapshotInfoModel
{
	String path = new String("");
	
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

    ///////////////////////////////////////////////////////////////////////
    //	
    ///////////////////////////////////////////////////////////////////////
    
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
		this.path = path;
	}
	
	@Override
	public String 
	getSnapshotName() 
	{
		return null;
	}

	@Override
	public String 
	getSnapshotPort() 
	{
		return null;
	}

	@Override
	public String 
	getSnapshotHost() 
	{
		return null;
	}
}
