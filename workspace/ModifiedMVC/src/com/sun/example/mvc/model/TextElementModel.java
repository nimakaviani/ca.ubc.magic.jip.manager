/*
 * TextElementModel.java
 *
 * Created on January 22, 2007, 3:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.example.mvc.model;

import com.sun.example.mvc.controller.DefaultController;
import java.awt.Font;
import java.beans.PropertyChangeListener;

/**
 * A sample class that mimics some properties found in a text element, including
 * its X and Y position, rotation, opacity, string, and font.
 *
 * @author Robert Eckstein
 */
public class 
TextElementModel
implements IModel
{
	ModelDelegate 	model_delegate;
    private String 	text;
    private Font 	font;
    private Integer x;
    private Integer y;
    private Integer opacity;
    private Integer rotation;
   
    public 
    TextElementModel()
    {
    	this.model_delegate = new ModelDelegate();
    }

    /**
     * Provides the means to set or reset the model to a default state.
     */   
    public void 
    initDefault() 
    {
        this.setText("Sample Text");
        this.setFont(new Font("Arial", Font.BOLD, 24));
        this.setX(50);
        this.setY(50);
        
        this.setOpacity(89);
        this.setRotation(0);
    }

    public String 
    getText() 
    {
        return text;
    }

    public void 
    setText
    (String text) 
    {
        String oldText = this.text;
        this.text = text;
        
        this.model_delegate.firePropertyChange(DefaultController.ELEMENT_TEXT_PROPERTY, oldText, text);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        
        Font oldFont = this.font;
        this.font = font;
        
        this.model_delegate.firePropertyChange(DefaultController.ELEMENT_FONT_PROPERTY, oldFont, font);
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        
        Integer oldX = this.x;
        this.x = x;
        
        this.model_delegate.firePropertyChange(DefaultController.ELEMENT_X_PROPERTY, oldX, x);
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        
        Integer oldY = this.y;
        this.y = y;
        
        this.model_delegate.firePropertyChange(DefaultController.ELEMENT_Y_PROPERTY, oldY, y);
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        
        Integer oldOpacity = this.opacity;
        this.opacity = opacity;
        
        this.model_delegate.firePropertyChange(DefaultController.ELEMENT_OPACITY_PROPERTY, oldOpacity, opacity);
        
    }
    
    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        
        Integer oldRotation = this.rotation;
        this.rotation = rotation;
        
        this.model_delegate.firePropertyChange(DefaultController.ELEMENT_ROTATION_PROPERTY, oldRotation, rotation);
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
    

