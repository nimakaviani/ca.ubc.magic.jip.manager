package jipplugin.actions.toolbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jipplugin.Activator;
import jipplugin.ActivatorLog;
import jipplugin.resources.Messages;

import org.eclipse.jface.action.Action;




import com.jchapman.jipsnapman.events.ISnapshotEventListener;
import com.jchapman.jipsnapman.events.SnapshotEvent;
import com.jchapman.jipsnapman.events.SnapshotEventManager;
import com.jchapman.jipsnapman.models.ISnapshotInfoModel;
import com.jchapman.jipsnapman.models.Snapshot;
import com.mentorgen.tools.util.profile.Start;

// I think I finally need to introduce a model for the toolbar...
public class 
StartAction 
extends Action 
implements ISnapshotEventListener
{
	// this may need to be the same manager as the one used by
	// finish
	private final SnapshotEventManager snapshot_event_manager;
	
	public 
	StartAction
	(SnapshotEventManager snapshot_event_manager)
	{
		ActivatorLog.logMessage(
	    		Messages.JIP_Pathfile_Error
	    	);
		this.snapshot_event_manager 
			= snapshot_event_manager;
		this.snapshot_event_manager.addSnapshotEventListener(this);
		this.setToolTipText
		(Messages.JIP_Start_Tooltip);
		this.setEnabled(true);
	}
	
	@Override
	public void
	setEnabled
	(boolean enabled)
	{
		super.setEnabled(enabled);
		
		String image_path
			= enabled 
			? Messages.JIP_Active_Start_Path
			: Messages.JIP_Inactive_Start_Path;
	
		this.setImageDescriptor
		(Activator.getImageDescriptor(image_path));
	}
	
	@Override
	public void 
	run()
	{
	    Snapshot snapshot = getSnapshot();
	    
	    if (snapshot != null) {
	      boolean gotException = false;
	      try {
	        com.mentorgen.tools.util.profile.File.doFile(
	            snapshot.getHost(),
	            snapshot.getPort(),
	            snapshot.getPathAndName());
	      }
	      catch (IOException ioex) {
	        gotException = true;
	        ActivatorLog.logError(ioex.getMessage(), ioex);
	      }
	      
	      // if no exception, ie the above succeeded, then go to start
	      // command and report file success
	      if (!gotException) {
	    	  ActivatorLog.logMessage(Messages.JIP_File_Success_Message);
	        try {
	          Start.doStart(snapshot.getHost(),
	                        snapshot.getPort());
	        }
	        catch (IOException ioex) {
	          gotException = true;
	          ActivatorLog.logError(ioex.getMessage(), ioex);
	        }
	      }
	      // if no exception, ie the above succeeded, then report
	      // start success and send event
	      if (!gotException) {
	    	ActivatorLog.logMessage(Messages.JIP_Start_Success_Message);
	        this.snapshot_event_manager.fireSnapshotEvent(
	    			new SnapshotEvent(
	    	            SnapshotEvent.ID_SNAPSHOT_STARTED, 
	    	            null
	    	        )
	    		);
	        this.setEnabled(false);
	      }
	    } 
	}

	private Snapshot 
	getSnapshot() 
	{
		ISnapshotInfoModel info_model
			= Activator.getDefault().getModel();
		
	    // check path specified
	    String path = info_model.getSnapshotPath();
	    
	    if (path == null || path.trim().length() == 0) {
	    	ActivatorLog.logMessage(
	    		"A folder in which to store the snapshot much be specified."
	    	);
	      return null; 
	    }
	    
	    File pathFile = new File(path);
	    if (!pathFile.exists() || !pathFile.isDirectory()) {
	    	ActivatorLog.logMessage(
	    		"The folder " + pathFile.getPath() + " does not exist."
	    	);
	      return null; 
	    }
	    
	    if (!pathFile.canWrite() || !pathFile.canRead()) {
	    	// this doesn't appear to work correctly;
	    	// ask about this
	    	ActivatorLog.logMessage(
	    		"The folder " + pathFile.getPath() + " must have both read and write access."
	    	);
	      return null;
	    }
	    
	    String 			port 		= info_model.getSnapshotPort();
	    String 			host 		= info_model.getSnapshotHost();
	    final String 	origName 	= info_model.getSnapshotName();
	    String newName 				=  this.setNewName(origName, pathFile);
	   
	    return new Snapshot(
	        pathFile.getPath(),
	        newName,
	        origName,
	        port,
	        host
	    );
	  }
	
	 /* ---------------- from SnapshotEventListener --------------- */
	
	private String 
	setNewName
	(String origName, File pathFile) 
	{
		String newName;
		
		 // modify name if it already exists and is not empty
	    if (origName.length() > 0) {
	      boolean nameExists = true;
	      String tempName = origName;
	      String nameSuffix = ".txt";
	      if (origName.endsWith(".txt") || origName.endsWith(".xml")) {
	        tempName = origName.substring(0, origName.length() - 4);
	        nameSuffix = origName.substring(origName.length() - 4);
	      }
	      StringBuilder buf = new StringBuilder(tempName);
	      int baseLength = buf.length();
	      for (int cnt = 0; nameExists; cnt++) {
	        buf.setLength(baseLength);
	        if (cnt > 0) {
	          buf.append(cnt);
	        }
	        buf.append(nameSuffix);
	        File txtPath = new File(pathFile, buf.toString());
	        nameExists = txtPath.exists();
	      }
	      newName = buf.toString();
	    }
	    // name is empty, create name based on current time
	    // but leave origName empty
	    else {
	      StringBuilder buf = new StringBuilder();
	      buf.append(new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()));
	      buf.append(".txt");
	      newName = buf.toString();
	    }
	    return newName;
	}

	@Override
	public void
	handleSnapshotEvent
	(SnapshotEvent event) 
	{
		switch (event.getId()) {
		    case SnapshotEvent.ID_SNAPSHOT_CAPTURED:
		    case SnapshotEvent.ID_SNAPSHOT_CAPTURE_FAILED:
			    this.setEnabled(true);
			    break;
		}
	}
}
