package jipplugin;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISizeProvider;
import org.eclipse.ui.part.ViewPart;

import actions.menu.*;
import actions.toolbar.*;

// this will need to be refactored into a proper MVC later
// chances are that will happen automatically as I attempt to mold
// the architecture of the original implementation into the plugin
public class 
JIPView 
extends ViewPart 
implements ISizeProvider
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
		initializeToolbar(actionBars.getToolBarManager());
		initializeDropDownMenu(actionBars.getMenuManager());
		
		this.snapshots_table 
			= new BasicListTable(parent, "Snapshots");
		this.snapshots_table.setContents(
			new ArrayList<String>(Arrays.asList( 
				new String[]{ 
					"C", "C++", "Java", "smalltalk"
				})
			));
		this.log_console_table
			= new BasicListTable( parent, "Log Console");
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
	(IToolBarManager toolBar) 
	{
		IAction details	= new ConfigureAction(this);
		IAction finish	= new FinishAction();
		IAction start	= new StartAction();
		IAction launch	= new LaunchViewerAction();
		IAction add		= new AddAction();
		
		toolBar.add(details);
		toolBar.add(start);
		toolBar.add(finish);
		toolBar.add(add);
		toolBar.add(new GroupMarker("edit"));
		toolBar.add(launch);

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
	setSnapshotDetails
	( String path, String name, String port, String host )
	{
		this.path = path;
		this.name = name;
		this.port = port;
		this.host = host;
		
		// I wonder when this gets redrawn (?)
		label.setText(this.path + " " + this.name + " " + this.port + " " + this.host);
		snapshots_table.addEntry("Haskell");
		snapshots_table.addEntry("Lua");
		snapshots_table.refresh();
	}

	@Override
	public int 
	getSizeFlags
	(boolean width) 
	{
		return SWT.MAX | SWT.MIN;
	}

	@Override
	public int 
	computePreferredSize
	(	boolean width, 
		int availableParallel, 
		int availablePerpendicular, 
		int preferredResult) {
		return preferredResult;
	}
}