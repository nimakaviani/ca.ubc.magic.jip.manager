package com.jchapman.jipsnapman.models;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jchapman.jipsnapman.models.messages"; //$NON-NLS-1$
	public static String Snapshot_0;
	public static String Snapshot_1;
	public static String Snapshot_2;
	public static String Snapshot_3;
	public static String Snapshot_4;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
