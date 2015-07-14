package com.cobweb.io.mqtt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * The Class MqttSubscribe.
 * @author YasithLokuge
 */
public class MqttSubscribe implements MqttCallback {
	
	/** The log. */
	private Log log = LogFactory.getLog(MqttSubscribe.class);
	
	/** The broker url. */
	private String brokerUrl = "tcp://localhost:1883";
	
	/** The client id. */
	private String clientId = "001";
	
	public static void main(String[] args) {
		MqttSubscribe mqttSubscribe = new MqttSubscribe();
		//mqttSubscribe.subscribe("", password, topic);
	}
	
	/**
	 * Subscribe.
	 *
	 * @param username the username
	 * @param password the password
	 * @param topic the topic
	 */
	public void subscribe(String username, String password, String topic){
			
		MemoryPersistence persistence = new MemoryPersistence();

		try {
			
			MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setPassword(password.toCharArray());			
			connOpts.setCleanSession(true);
			
			log.debug("Connecting to broker: " + brokerUrl);
			sampleClient.connect(connOpts);
			log.debug("Connected");
			
			sampleClient.setCallback(this);
			sampleClient.subscribe(topic);
			log.debug("Subscribed");
			log.debug("Listening");

		} catch (MqttException me) {
			
			log.debug("reason " + me.getReasonCode());
			log.debug("msg " + me.getMessage());
			log.debug("loc " + me.getLocalizedMessage());
			log.debug("cause " + me.getCause());
			log.debug("excep " + me);
			me.printStackTrace();
			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.Throwable)
	 */
	@Override
	public void connectionLost(Throwable arg0) {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken)
	 */
	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {		
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String, org.eclipse.paho.client.mqttv3.MqttMessage)
	 */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {		
		log.debug(message);
	}
	
}
