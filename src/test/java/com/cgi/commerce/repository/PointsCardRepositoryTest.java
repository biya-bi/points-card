package com.cgi.commerce.repository;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.cgi.commerce.NucleusBasedTest;

import atg.adapter.gsa.GSARepository;
import atg.adapter.gsa.GSATestUtils;
import atg.dtm.TransactionDemarcation;
import atg.repository.MutableRepository;
import atg.repository.MutableRepositoryItem;
import atg.repository.RepositoryItem;

public class PointsCardRepositoryTest extends NucleusBasedTest {

	private static Logger log = Logger.getLogger(PointsCardRepositoryTest.class);

	@Test
	public void createItem_AllFieldsAreValid_ItemCreated() throws Exception {
		// Define the path to our repository definition file called
		// "pointsCardRepository.xml"
		final String[] definitionFiles = { "/com/cgi/commerce/repository/pointsCardRepository.xml" };
		log.info(" definitionFile[0]=" + definitionFiles[0]);

		// Setup our testing configuration path
		// RH: disabled logging (last argument to false) to get rid of the
		// double logging statements
		GSATestUtils.getGSATestUtils().initializeMinimalConfigpath(CONFIG_PATH, "/PointsCardRepository", definitionFiles,
				DB_PROPS, null, null, null, false);

		boolean rollback = true;

		final TransactionDemarcation transactionDemarcation = new TransactionDemarcation();
		final MutableRepository repository = (MutableRepository) nucleus.resolveName("/PointsCardRepository");

		try {
			// Start a new transaction
			transactionDemarcation.begin(((GSARepository) repository).getTransactionManager());
			// Create the item
			final MutableRepositoryItem item = repository.createItem("pointsCard");
			item.setPropertyValue("number", "C00001");
			// Persist to the repository
			repository.addItem(item);
			// Try to get it back from the repository
			final String id = item.getRepositoryId();
			final RepositoryItem item2 = repository.getItem(id, "pointsCard");
			Assert.assertNotNull("We did not get back the item just created from the repository.", item2);
			rollback = false;
		} finally {
			// End the transaction, roll back on error
			if (transactionDemarcation != null)
				transactionDemarcation.end(rollback);
		}
	}
}