package com.gs.test.camel.config;

import org.codehaus.jackson.map.ObjectMapper;

import com.gs.test.camel.model.Notification;

public class KafkaOutputBean {

	public void doWork(String body) {
		System.out.println("KafkaBody result >>>>> " + this.retrieve(body));
	}

	public Notification retrieve(String body) {
		System.out.println("Body in KafkaOutputBean: " + body);
		Notification message;
		try {
			message = new ObjectMapper().readValue(body, Notification.class);
			System.out.println("Found message: " + message);
		} catch (Exception e) {
			message = new Notification();
		}
		return message;
	}
}
