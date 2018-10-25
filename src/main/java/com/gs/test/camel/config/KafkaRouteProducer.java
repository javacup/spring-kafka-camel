package com.gs.test.camel.config;

import org.apache.camel.builder.RouteBuilder;

public class KafkaRouteProducer extends RouteBuilder {

	public static final String ROUTE = "direct:kafkaRoute";
	
	private String route;

	public KafkaRouteProducer(String route) {
		this.route = route;
	}

	public void configure() {
		from(ROUTE).to(this.route);
	}
}
