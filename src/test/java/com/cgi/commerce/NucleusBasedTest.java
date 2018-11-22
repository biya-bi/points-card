package com.cgi.commerce;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import atg.nucleus.Nucleus;
import atg.nucleus.ServiceException;
import atg.test.util.DBUtils;
import atg.test.util.FileUtil;

public class NucleusBasedTest {
	protected static final File CONFIG_PATH = new File("target/test-classes/config".replace("/", File.separator));
	protected static Nucleus nucleus;
	private static DBUtils dbUtils;
	protected static final Properties DB_PROPS = DBUtils.getHSQLDBInMemoryDBConnection("testdb");

	private static Nucleus startNucleus() throws IOException {
		// Copy all related properties and definition files to the previously
		// configured configuration path
		FileUtil.copyDirectory("src/main/config", CONFIG_PATH.getPath(), Collections.<String>emptyList());

		return NucleusUtils.startNucleus(CONFIG_PATH);
	}

	private static DBUtils getDb() throws Exception {
		final DBUtils db = new DBUtils(DB_PROPS.getProperty("URL"), DB_PROPS.getProperty("driver"),
				DB_PROPS.getProperty("user"), DB_PROPS.getProperty("password"));
		return db;
	}

	@BeforeClass
	public static void setup() throws Exception {
		nucleus = startNucleus();
		dbUtils = getDb();
	}

	@AfterClass
	public static void cleanup() throws ServiceException, SQLException {
		// Shut down Nucleus
		if (nucleus != null)
			nucleus.stopService();
		// Shut down HSQLDB
		if (dbUtils != null)
			dbUtils.shutdown();
	}
}