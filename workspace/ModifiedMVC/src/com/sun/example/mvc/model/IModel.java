package com.sun.example.mvc.model;

import java.beans.PropertyChangeListener;

public interface 
IModel 
{
	// now only the required methods are visible to the controller (2 not 3)
	// I don't like inheritance...it's like you're splitting up the implementation
	// of a single class into multiple files...it sucks
	// doing it for the sake of grouping common code...I don't like that either
	void addPropertyChangeListener(PropertyChangeListener abstractController);
	void removePropertyChangeListener(PropertyChangeListener abstractController);
}
