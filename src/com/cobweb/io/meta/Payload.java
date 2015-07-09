package com.cobweb.io.meta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Payload {
	
	/** The id. */
	private String id;
	
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
	public Payload(String message){
		this.message = message;		
		Date date = new Date();
		this.dateTime = date;
		
		UUID uuid = UUID.randomUUID();
		setId(uuid.toString());
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

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public String getTimeStamp(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(dateTime);
	}
}
