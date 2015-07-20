package com.cobweb.io.transformers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.cobweb.io.meta.Payload;
import com.tinkerpop.blueprints.Vertex;


/**
 * The Class VertexToPayload.
 * @author Yasith Lokuge
 */
public class VertexToPayload {

	
	/**
	 * Transform.
	 *
	 * @param payloadVertex the payload vertex
	 * @return the payload
	 */
	public Payload transform(Vertex payloadVertex){
		
		
		String message		= payloadVertex.getProperty("message");
		String id			= payloadVertex.getProperty("idValue");
		String dateTime		= payloadVertex.getProperty("dateTime");
		
		Payload payload = new Payload(message);
		payload.setId(id);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		
		try {
			Date date = format.parse(dateTime);
			payload.setDateTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return payload;		
	}
}
