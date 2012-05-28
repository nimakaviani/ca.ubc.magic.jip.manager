package controllers;

import java.beans.PropertyChangeListener;

import models.IModel;

import views.IView;

public interface
IController
extends PropertyChangeListener 
{
	public void addModel(IModel model);
	public void removeModel(IModel model);
	public void addView(IView view);
	public void removeView(IView view);
	public void setModelProperty(String property_name, Object new_value);
}