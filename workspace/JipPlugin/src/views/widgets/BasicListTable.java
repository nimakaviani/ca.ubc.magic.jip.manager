package views.widgets;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class 
BasicListTable 
{
	Table 			table;
	TableViewer 	table_viewer;
	List<String> 	contents;
	
	String[] 		column_name;
	
	public BasicListTable
	( final Composite parent, String header )
	{
		this.column_name = new String[]{header};
		
		initializeTable(parent);
		initializeTableViewer();
		
		this.table_viewer.setContentProvider(
			new ArrayContentProvider()
		);
		this.table_viewer.setLabelProvider( new LabelProvider() );
	}
	
	private void 
	initializeTable
	(final Composite parent) 
	{
		final int style
			= SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION 
			| SWT.BORDER | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
	
		this.table 
			= new Table(parent, style);
		
		TableColumn col 
			= new TableColumn( table, SWT.LEFT, 0 );
		col.setText(this.column_name[0]);
		
		//this.setContentProvider(new ArrayContentProvider());
		GridData grid_data = new GridData(SWT.FILL, SWT.FILL, true, true);
		table.setLayoutData(grid_data);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		// if we want to set the size of each column relative to the parent
		// or the table, we have to wait until the table is drawn or resized
		// to get a value; if we simply call getClientArea() in the constructor
		// we will get a value of 0,0
		parent.addControlListener( 
			new ControlAdapter(){
				public void controlResized
				( ControlEvent e)
				{
					// will need to ask about the correct way to do this
					// and why 6 works
					
					// ask about how one detects when the scroll bar is visible
					// or not
					int width 
						= parent.getClientArea().width - 6*table.getBorderWidth();
					width -= table.getVerticalBar().getSize().x;
					table.getColumn(0).setWidth( width );
				}
			});
	}
	
	private void 
	initializeTableViewer() 
	{
		this.table_viewer 
			= new TableViewer(this.table);
		this.table_viewer.setColumnProperties(this.column_name);
	}

	public void
	setContents
	(List<String> contents)
	{
		this.contents = contents;
		this.table_viewer.setInput(this.contents);
	}
	
	public void 
	refresh() 
	{
		this.table_viewer.refresh(false);
	}

	public void 
	addEntry
	(String string) 
	{
		this.contents.add(string);
	}
}
