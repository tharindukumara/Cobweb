package com.cobweb.io.meta;

import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class UserSubscribes.
 */
public class UserFollowsUser {
	
	
	/** The user in. */
	private Vertex userIn;
	
	/** The user out. */
	private Vertex userOut;
	
	/**
	 * Gets the user in.
	 *
	 * @return the user in
	 */
	public Vertex getUserIn() {
		return userIn;
	}

	/**
	 * Sets the user in.
	 *
	 * @param userIn the new user in
	 */
	public void setUserIn(Vertex userIn) {
		this.userIn = userIn;
	}

	/**
	 * Gets the user out.
	 *
	 * @return the user out
	 */
	public Vertex getUserOut() {
		return userOut;
	}

	/**
	 * Sets the user out.
	 *
	 * @param userOut the new user out
	 */
	public void setUserOut(Vertex userOut) {
		this.userOut = userOut;
	}	
}
