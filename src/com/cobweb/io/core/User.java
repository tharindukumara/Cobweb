package com.cobweb.io.core;

import java.util.Set;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.VertexQuery;

/**
 * The Class User.
 *
 * @author Yasith Lokuge
 */

public class User implements Vertex{

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getProperty(java.lang.String)
	 */
	@Override
	public <T> T getProperty(String key) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getPropertyKeys()
	 */
	@Override
	public Set<String> getPropertyKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String key, Object value) {
		
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#removeProperty(java.lang.String)
	 */
	@Override
	public <T> T removeProperty(String key) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#remove()
	 */
	@Override
	public void remove() {
		
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Element#getId()
	 */
	@Override
	public Object getId() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#getEdges(com.tinkerpop.blueprints.Direction, java.lang.String[])
	 */
	@Override
	public Iterable<Edge> getEdges(Direction direction, String... labels) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#getVertices(com.tinkerpop.blueprints.Direction, java.lang.String[])
	 */
	@Override
	public Iterable<Vertex> getVertices(Direction direction, String... labels) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#query()
	 */
	@Override
	public VertexQuery query() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tinkerpop.blueprints.Vertex#addEdge(java.lang.String, com.tinkerpop.blueprints.Vertex)
	 */
	@Override
	public Edge addEdge(String label, Vertex inVertex) {
		return null;
	}

}
