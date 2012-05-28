package models;

import java.beans.PropertyChangeListener;

public interface 
IModel 
{
	void addPropertyChangeListener(PropertyChangeListener controllerDelegate);
	void removePropertyChangeListener(PropertyChangeListener controllerDelegate);
}
