package com.cobweb.io.meta;

import com.tinkerpop.blueprints.Vertex;

// TODO: Auto-generated Javadoc
/**
 * The Class UserSubscribes.
 */
public class UserSubscribes {
	
	/** The is parent only. */
	private boolean isParentOnly = true;

	/** The user in. */
	private Vertex userIn;
	
	/** The user out. */
	private Vertex userOut;
	
	/**
	 * Checks if is parent only.
	 *
	 * @return the parentOnly
	 */
	public boolean isParentOnly() {
		return isParentOnly;
	}

	/**
	 * Sets the parent only.
	 *
	 * @param isParentOnly the new parent only
	 */
	public void setParentOnly(boolean isParentOnly) {
		this.isParentOnly = isParentOnly;
	}

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
