package com.cobweb.io.service;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;


/**
 * The Class AbstractService.
 *
 * @author Yasith Lokuge
 */

public interface AbstractService {
	
	/** The ss. */
	ServiceSamurai ss = ServiceSamurai.getInstance();
	
	/** The graph. */
	OrientGraph graph = ss.getGraphDB();
	
	
}
