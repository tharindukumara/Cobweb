package com.cobweb.io.meta;

import java.util.Date;

public class Payload {
	
	/** The message. */
	private String message = "default meaasage";
	
	/** The date time. */
	private Date dateTime;
	
	/** The is deleted. */
	private boolean isDeleted = false;
	
	/**
	 * Instantiates a new payload.
	 *
	 * @param message the message
	 * @param dateTime the date time
	 */
	public Payload(String message, Date dateTime){
		this.message = message;
		this.dateTime = dateTime;		
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
