/*
 * DocumentModel.java
 *
 * Created on January 22, 2007, 3:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.example.mvc.model;

import java.beans.PropertyChangeListener;

import com.sun.example.mvc.controller.DefaultController;

/**
 * A sample class that mimics some properties found in a document, including
 * its name, width, and height.
 *
 * @author Robert Eckstein
 */
public class 
DocumentModel
implements IModel
{
	ModelDelegate 	model_delegate;
    private String 	name;
    private Integer width;
    private Integer height;
   
    public 
    DocumentModel()
    {   
    	this.model_delegate = new ModelDelegate();
    }
    
    /**
     * Provides the means to set or reset the model to a default state.
     */
    public void 
    initDefault()
    {
        this.setName("Sample Document");
        this.setWidth(500);
        this.setHeight(500);
    }
    
    public String 
    getName() 
    {
        return name;
    }

    public void 
    setName
    (String name) 
    {
        String oldName 	= this.name;
        this.name 		= name;
        
        this.model_delegate.firePropertyChange( DefaultController.DOCUMENT_NAME_PROPERTY, oldName, name );
    }


    public Integer 
    getWidth() 
    {
        return width;
    }

    public void 
    setWidth
    (Integer width) 
    {
        Integer oldWidth = this.width;
        this.width = width;
        
        this.model_delegate.firePropertyChange(DefaultController.DOCUMENT_WIDTH_PROPERTY, oldWidth, width);
    }

    public Integer 
    getHeight()
    {
        return height;
    }

    public void 
    setHeight
    (Integer height)
    {
        Integer oldHeight = this.height;
        this.height = height;
        
        this.model_delegate.firePropertyChange(DefaultController.DOCUMENT_HEIGHT_PROPERTY, oldHeight, height);
    }

	@Override
	public void 
	addPropertyChangeListener
	( PropertyChangeListener abstractController) 
	{
		this.model_delegate.addPropertyChangeListener(abstractController);
	}

	@Override
	public void 
	removePropertyChangeListener
	( PropertyChangeListener abstractController) 
	{
		this.model_delegate.removePropertyChangeListener(abstractController);
	} 
}
    

