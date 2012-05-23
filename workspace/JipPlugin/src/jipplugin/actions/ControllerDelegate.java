package jipplugin.actions;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import jipplugin.views.IView;



import com.jchapman.jipsnapman.models.ISnapshotInfoModel;

public class 
ControllerDelegate 
implements IController
{
	private List<ISnapshotInfoModel>	registered_models;
	private List<IView> 				registered_views;
		
	public 
	ControllerDelegate()
	{
		registered_models
			= new ArrayList<ISnapshotInfoModel>();
		registered_views
			= new ArrayList<IView>();
	}
	
	@Override
	public void 
	propertyChange
	( PropertyChangeEvent evt ) 
	{
		for(IView view : registered_views){
			view.modelPropertyChange(evt);
		}
	}
	
	@Override
	public void 
	addModel
	(ISnapshotInfoModel model) 
	{
		registered_models.add(model);
		model.addPropertyChangeListener(this);
	}

	@Override
	public void 
	removeModel
	(ISnapshotInfoModel model) 
	{
		registered_models.remove(model);
		model.removePropertyChangeListener(this);
	}

	@Override
	public void 
	addView
	(IView view) 
	{
		registered_views.add(view);
	}

	@Override
	public void 
	removeView
	(IView view)
	{
		registered_views.remove(view);
	}
	
	@Override
	public void 
	setModelProperty
	(String property_name, Object new_value) 
	{
		for ( ISnapshotInfoModel model: registered_models ) {
            try {
                Method method 
                	= model.getClass().getMethod( 
                		"set" + property_name, 
                		new Class[] { new_value.getClass() }
                	);
                method.invoke(model, new_value);
            } catch (NoSuchMethodException ex) {
            	System.err.printf( 
            		"No method set%s() in class %s\n", 
            		property_name, model.getClass()
            	);
            } catch (Exception ex) {
            	ex.printStackTrace();
			}
        }
	}
}
