package com.cobweb.io.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cobweb.io.meta.DeviceType;

/**
 * The Class DeviceValidator.
 * @author Yasith Lokuge
 */
public class DeviceValidator {

	/** The Constant NULL_DEVICE_NAME. */
	private static final String NULL_DEVICE_NAME 		= "Device name should not be null";
	
	/** The Constant NULL_DESCRIPTION. */
	private static final String NULL_DESCRIPTION 		= "Device description should not be null";
	
	/** The Constant NULL_DEVICE_TYPE. */
	private static final String NULL_DEVICE_TYPE 		= "Device type should not be null";
	
	/** The Constant NULL_OTHER_TYPE. */
	private static final String NULL_OTHER_TYPE 		= "Please submit the otherType parameter";	
	
	/** The Constant INVALID_DEVICE_NAME. */
	private static final String INVALID_DEVICE_NAME 	= "Invalid Device name";
	
	/** The Constant INVALID_DESCRIPTION. */
	private static final String INVALID_DESCRIPTION 	= "Invalid Description";
	
	/** The Constant INVALID_OTHER_TYPE. */
	private static final String INVALID_OTHER_TYPE 		= "Invalid Other type";
	
	/** The Constant DEVICE_TYPE_ERROR. */
	private static final String DEVICE_TYPE_ERROR 		= "Invalid Device type. user other type parameter";
	
	/** The Constant DEVICE_NAME_PATTERN. */
	private static final String DEVICE_NAME_PATTERN 	= "^[a-zA-Z0-9 ]*{3,15}$";
	
	/** The Constant OTHER_TYPE_PATTERN. */
	private static final String OTHER_TYPE_PATTERN 		= "^[a-zA-Z0-9 ]*{3,15}$";
	
	/** The Constant DEVICE_DESC_PATTERN. */
	private static final String DEVICE_DESC_PATTERN 	= "^[a-zA-Z0-9 ]*{3,200}$";
		
	/** The Constant SUCCESS. */
	private static final String SUCCESS					= "SUCCESS";
	


	/**
	 * Validate.
	 *
	 * @param jsonData the json data
	 * @return the string
	 */
	public String validate(JSONObject incomingData){
		
		String deviceName 	= null;
		String description	= null;
		String type 		= null;		
		String otherType	= null;			
			
		try {
			deviceName 	= (String) incomingData.get("name");
			description = (String) incomingData.get("description");
			type 		= (String) incomingData.get("type");			
			otherType 	= (String) incomingData.get("otherType");				
		} catch (JSONException e) {
			return e.toString();
		}				

		{	
			if(isNull(deviceName))
				return NULL_DEVICE_NAME;
			if(isNull(description))
				return NULL_DESCRIPTION;		
			if(isNull(type))
				return NULL_DEVICE_TYPE;
			if(type.equalsIgnoreCase("OTHER")){
				if(isNull(otherType))
					return NULL_OTHER_TYPE;
			}
		}
		
		{			
			if(!regexCheck(deviceName,DEVICE_NAME_PATTERN))				
				return INVALID_DEVICE_NAME;			
			if(!regexCheck(description,DEVICE_DESC_PATTERN))
				return INVALID_DESCRIPTION;		
			if(type.equalsIgnoreCase("OTHER")){
				if(!regexCheck(otherType,OTHER_TYPE_PATTERN))
					return INVALID_OTHER_TYPE;
			}			
			if(!contains(type))
				return DEVICE_TYPE_ERROR;
		}
		
		return SUCCESS;		
	}
	
	
	
	/**
	 * Contains.
	 *
	 * @param data the data
	 * @return true, if successful
	 */
	public boolean contains(String data) {
		
	    for (DeviceType deviceType : DeviceType.values()) {
	        if (deviceType.name().equals(data)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Checks if is null.
	 *
	 * @param data the data
	 * @return true, if is null
	 */
	public boolean isNull(String data){		
		if(data == null || data.equalsIgnoreCase("") || data.equalsIgnoreCase("null"))
			return true;
		else
			return false;
	}
	
	 /**
 	 * Regex check.
 	 *
 	 * @param data the data
 	 * @param dataPattern the data pattern
 	 * @return true, if successful
 	 */
 	public boolean regexCheck(final String data, final String dataPattern){
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(dataPattern);
		matcher = pattern.matcher(data);
		return matcher.matches();
	 }
}
