package com.cobweb.io.service;

import com.tinkerpop.blueprints.TransactionalGraph;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractService.
 *
 * @author Yasith Lokuge
 */

public abstract class AbstractService {
	
	/** The ss. */
	private ServiceSamurai ss = ServiceSamurai.getInstance();
	
	/** The graph. */
	private TransactionalGraph graph = ss.getGraphDB();

}
