package com.jchapman.jipsnapman.events;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

import jipplugin.Activator;

import models.Constants;

public class
EventLogger 
{
	// log any exceptions thrown
	public void 
	updateConsoleLog
	(Exception ex)
	throws IOException 
	{
		StringWriter swriter
			= new StringWriter();
		ex.printStackTrace(new PrintWriter(swriter));
		swriter.flush();
		swriter.close();
		updateConsoleLog(swriter.toString());
	}
		
	// log missing data dialog window errors
	public EventLogEvent 
	getErrorEvent() 
	{
		EventLogEvent event 
			= new EventLogEvent(
				this,
				EventLogEvent.ACTION_PERFORMED,
				""
			);
		event.addProperty(Constants.KEY_ERR_DLGTITLE,"dlg.title.missing");
		
		return event;
	}
	
	// log a successful event
	public void 
	updateForSuccessfulCall
	(String call) 
	throws IOException 
	{
		StringBuffer buf
			= new StringBuffer();
		buf.append(MessageFormat.format(
			"The {0} call was successful.",
			new Object[] {call})
		);
		buf.append('\n');
		
		updateConsoleLog(buf.toString());
	}
	
	// write to the event log: main work horse
	public void 
	updateConsoleLog
	(String message) 
	throws IOException
	{
		System.out.println("Updating the console log");
		
		EventLogEvent event 
			= new EventLogEvent(
				this,
				EventLogEvent.ACTION_PERFORMED,
				""
			);
		event.addProperty(Constants.KEY_LOGS_DATA, message);
		EventLogActionHandler action_handler
			= Activator.getDefault().getActionHandler();
		action_handler.performActionByKey(
			Constants.ACTKEY_LOG_DISPLAY,
			event
		);
	}

}