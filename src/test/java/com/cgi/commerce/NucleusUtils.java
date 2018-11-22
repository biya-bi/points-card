package com.cgi.commerce;

import java.io.File;

import atg.nucleus.Nucleus;
import atg.nucleus.servlet.NucleusServlet;

public class NucleusUtils {
	/**
	 * Starts Nucleus using the given configuration directory
	 * 
	 * @param configPath
	 * @return
	 */
	public static Nucleus startNucleus(final File configPath) {
		return startNucleus(configPath.getAbsolutePath());
	}

	/**
	 * Starts Nucleus given an array of configuration path entries
	 * 
	 * @param configPath
	 * @return
	 */
	public static Nucleus startNucleus(final String configPath) {
		System.setProperty("atg.dynamo.license.read", "true");
		System.setProperty("atg.license.read", "true");
		NucleusServlet.addNamingFactoriesAndProtocolHandlers();
		return Nucleus.startNucleus(new String[] { configPath });
	}
}
