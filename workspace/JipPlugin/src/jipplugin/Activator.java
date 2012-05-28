package jipplugin;


import models.ActiveSnapshotModel;
import models.SnapshotsListModel;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


import events.logging.EventLogActionHandler;

public class 
Activator 
extends AbstractUIPlugin
{
	public static final String PLUGIN_ID 
		= "JipPlugin"; 

	private static Activator plugin;
	
	ActiveSnapshotModel 	active_snapshot_model;
	SnapshotsListModel		snapshots_list_model;
	EventLogActionHandler 	action_handler;
	
	public 
	Activator() 
	{}

	public void 
	start
	(BundleContext context) 
	throws Exception 
	{
		super.start(context);
		plugin = this;
		
		this.active_snapshot_model 
			= new ActiveSnapshotModel();
		this.snapshots_list_model
			= new SnapshotsListModel();
		this.action_handler 
			= new EventLogActionHandler();
	}

	public void 
	stop
	(BundleContext context) 
	throws Exception 
	{
		plugin = null;
		super.stop(context);
	}

	public static 
	Activator getDefault() 
	{
		return plugin;
	}

	public static 
	ImageDescriptor 
	getImageDescriptor
	(String path) 
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public ActiveSnapshotModel 
	getActiveSnapshotModel() 
	{
		return this.active_snapshot_model;
	}
	
	public SnapshotsListModel
	getSnapshotsListModel()
	{
		return this.snapshots_list_model;
	}
	
	public EventLogActionHandler
	getActionHandler()
	{
		return this.action_handler;
	}
}
