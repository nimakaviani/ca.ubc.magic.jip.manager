package views;

import java.beans.PropertyChangeEvent;

import jipplugin.Activator;


import models.Constants;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

import com.jchapman.jipsnapman.events.EventLogActionHandler;
import com.jchapman.jipsnapman.events.SnapshotEventManager;

import controllers.ControllerDelegate;
import controllers.IController;

import actions.eventlog.ErrorDisplayAction;
import actions.eventlog.LogAction;
import actions.menu.*;
import actions.toolbar.*;

// this will need to be refactored into a proper MVC later
// chances are that will happen automatically as I attempt to mold
// the architecture of the original implementation into the plugin
public class 
JIPView 
extends ViewPart 
implements IView
{
	String 			path;
	String 			name;
	String 			port;
	String 			host;
	
	Label 			label;
	BasicListTable 	snapshots_table;
	BasicListTable	log_console_table;
	
	public 
	JIPView() 
	{}
	
	@Override
	public void 
	createPartControl
	(Composite parent) 
	{
		initializeGridLayout(parent);
		initializeLabel(parent);
		
		IActionBars actionBars 	
			= super.getViewSite().getActionBars();
		SnapshotEventManager snapshot_event_manager
			= new SnapshotEventManager();
		this.initializeToolbar(actionBars.getToolBarManager(), snapshot_event_manager);
		
		this.initializeDropDownMenu(actionBars.getMenuManager());
		
		this.snapshots_table 
			= new BasicListTable(parent, "Snapshots", new SnapshotsLabelProvider());
		
		this.snapshots_table.setContents(
			Activator.getDefault().getModel().getSnapshotsList()
		);

		this.log_console_table
			= new BasicListTable(parent, "Event Log", null);
		this.log_console_table.setContents(
			Activator.getDefault().getModel().getEventLogList()
		);
		
		this.initializeEventLogActionHandler();
	}

	private void 
	initializeEventLogActionHandler() 
	{
		EventLogActionHandler action_handler
			= Activator.getDefault().getActionHandler();
		
		action_handler.registerAction(
			Constants.ACTKEY_ERROR_DISPLAY,
			new ErrorDisplayAction()
		);
		action_handler.registerAction(
			Constants.ACTKEY_LOG_DISPLAY, 
			new LogAction()
		);
	}

	private void 
	initializeGridLayout
	(Composite parent) 
	{
		GridLayout grid_layout 
			= new GridLayout(1, false);
		grid_layout.marginWidth = 4;
		parent.setLayout(grid_layout);
	}
	
	private void 
	initializeLabel
	(Composite parent) 
	{
		this.label 				
			= new Label(parent, SWT.LEFT);
		this.label.setText("Hello World");
		
		GridData grid_data 			
			= new GridData(SWT.FILL,SWT.FILL, true, false);
		grid_data.horizontalSpan 	
			= 1 ;
		this.label.setLayoutData( grid_data );
	}

	private void 
	initializeToolbar
	(IToolBarManager toolBar, SnapshotEventManager snapshot_event_manager) 
	{		
		IController controller = new ControllerDelegate();
		controller.addView(this);
		
		IAction details	
			= new ConfigureAction(this, snapshot_event_manager);
		IAction finish	
			= new FinishAction(snapshot_event_manager, controller);
		IAction start	
			= new StartAction(snapshot_event_manager, controller);
				
		toolBar.add(details);
		toolBar.add(start);
		toolBar.add(finish);
	}
	
	private void 
	initializeDropDownMenu
	(IMenuManager dropDownMenu) 
	{
		IAction about		
			= new AboutAction();
		dropDownMenu.add(about);
	}
	
	@Override
	public void 
	setFocus() 
	{}
	
	public void
	refresh()
	{
		String display_name 
			= this.name == null || this.name.equals("") 
			? "Name not set"
			: this.name;
		
		label.setText(
			display_name 
			+ "   ( Port: " 
			+ this.port 
			+ ", Host: " 
			+ this.host
			+ ", Path: "
			+ this.path 
			+ " )"
		);
		snapshots_table.refresh();
	}

	@Override
	public void 
	modelPropertyChange
	(PropertyChangeEvent evt) 
	{
		System.out.println("modelPropertyChange(): Property changed");
		switch(evt.getPropertyName()){
		case Constants.PATH_PROPERTY:
			this.path = (String) evt.getNewValue();
			break;
		case Constants.HOST_PROPERTY:
			this.host = (String) evt.getNewValue();
			break;
		case Constants.NAME_PROPERTY:
			this.name = (String) evt.getNewValue();
			break;
		case Constants.PORT_PROPERTY:
			this.port = (String) evt.getNewValue();
			System.out.println(this.port);
			System.out.println(this.host);
			break;
		case Constants.SNAPSHOT_PROPERTY:
			this.snapshots_table.refresh();
			break;
		case Constants.EVENT_LIST_PROPERTY:
			this.log_console_table.refresh();
			break;
		}
		this.refresh();
	}
}
