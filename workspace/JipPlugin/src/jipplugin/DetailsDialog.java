package jipplugin;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class 
DetailsDialog 
extends Dialog
{
	public 
	DetailsDialog
	(Shell parentShell) 
	{
		super( parentShell );
	}
	
	@Override
	public Control
	createDialogArea
	( Composite parent )
	{
		Composite container 
			= (Composite) super.createDialogArea(parent);
		
		initializeGridLayout(container);
		initializeRows(container);
		
		return parent;
	}
	
	private void 
	initializeRows
	(Composite container) 
	{
		Label path_label 
			= createLabel( container, "Path: " );
		Text path_text 
			= createPath( container, 1 );
		
		Button browse_button 
			= new Button( container, SWT.NONE );
		browse_button.setText( "Browse" );
		browse_button.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected( SelectionEvent event ){
				FileDialog file_dialog 
					= new FileDialog( 
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN
					);
				file_dialog.setText("Select File");
				file_dialog.setFilterPath("C:/");
				String selected = file_dialog.open();
			}
		});
		
		Label name_label
			= createLabel( container, "Name: " );
		Text name_text 
			= createPath( container, 2 );
		
		Label port_label 
			= createLabel(container, "Port: " );
		Text port_text 
			= createPath( container, 2 );
		
		Label host_label 
			= createLabel( container, "Host: " );
		Text host_text 
			= createPath( container, 2 );
	}

	private void 
	initializeGridLayout
	( Composite container)
	{
		final GridLayout grid_layout
			= new GridLayout();
		grid_layout.numColumns = 3;
		container.setLayout(grid_layout);
	}

	private Text 
	createPath
	(Composite container, int num_columns) 
	{
		Text local_text
			= new Text(container, SWT.SINGLE | SWT.BORDER);
		GridData grid_data 
			= new GridData( SWT.FILL, SWT.FILL, false, false, num_columns, 1);
		
		grid_data.grabExcessHorizontalSpace = true;
		grid_data.widthHint = 200;
		local_text.setLayoutData(grid_data);
		
		return local_text;
	}

	private Label 
	createLabel
	(Composite container, String label_text) 
	{
		Label local_label 
			= new Label(container, SWT.LEFT);
		GridData grid_data = new GridData(SWT.BEGINNING, SWT.FILL, false, false);
		local_label.setLayoutData(grid_data);
		local_label.setText(label_text);
		
		return local_label;
		
	}

	@Override
	protected boolean
	isResizable()
	{
		return true;
	}
}