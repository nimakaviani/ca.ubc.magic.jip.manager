/*
 * AbstractModel.java
 *
 * Created on January 22, 2007, 3:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.example.mvc.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class provides base level functionality for all models, including a 
 * support for a property change mechanism (using the PropertyChangeSupport class),
 * as well as a convenience method that other objects can use to reset model state.
 * @author Robert Eckstein
 */
public class 
ModelDelegate
{ 
    /**
     * Convenience class that allow others to observe changes to the model properties
     */
    private PropertyChangeSupport propertyChangeSupport;

    public 
    ModelDelegate()
    {
        this.propertyChangeSupport 
        	= new PropertyChangeSupport(this);
    }
    
    public void 
    addPropertyChangeListener
    (PropertyChangeListener listener) 
    {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void 
    removePropertyChangeListener
    (PropertyChangeListener listener) 
    {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
        
    /**
     * Fires an event to all registered listeners informing them that a property in
     * this model has changed.
     * 
     * @param propertyName The name of the property
     * @param oldValue The previous value of the property before the change
     * @param newValue The new property value after the change
     */
    public void 
    firePropertyChange
    ( String propertyName, Object oldValue, Object newValue ) 
    {
    	// oddly enough, this is not a function call to the controller;
    	// instead, the information will be packaged into
    	//
    	// this is programmed so that a call to a listener will not be made if
    	// oldvalue and newvalue match
        this.propertyChangeSupport.firePropertyChange( propertyName, oldValue, newValue );
    }
}
    

