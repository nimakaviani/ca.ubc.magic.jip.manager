package jip.eventlog.actions;

import jip.eventlog.events.EventLogEvent;

import com.jchapman.jipsnapman.models.ILogsModel;
import com.jchapman.jipsnapman.utils.JIPSnapManConstants;

/**
 *
 * @author not attributable
 * @version 1.0
 */
public class 
LogAction 
implements IEventLogAction 
{
    private final ILogsModel logs_model; 

    public 
    LogAction
    ( ILogsModel logs_model ) {
        this.logs_model = logs_model;
    }

    public void 
    performAction
    (EventLogEvent event) 
    {
        logs_model.updateLog((String) event.getProperty(
            JIPSnapManConstants.KEY_LOGS_DATA));
    }
}
