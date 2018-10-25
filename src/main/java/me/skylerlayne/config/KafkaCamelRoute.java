package me.skylerlayne.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.skylerlayne.controller.MessageProcessor;

@Configuration
public class KafkaCamelRoute {

	@Bean(name = "KafkaRouteProducer")
	public RouteBuilder kafkaRouteProducer() {
		return new KafkaRouteProducer(
				"kafka:teamview.gs.local:9092?topic=OCTOBER_FEST&groupId=testing&autoOffsetReset=earliest&consumersCount=1");
	}

	@Bean(name = "KafkaRouteConsumer")
	public RouteBuilder kafkaRouteConsumer() {
		return new RouteBuilder() {
			public void configure() {
				from("kafka:teamview.gs.local:9092?topic=OCTOBER_FEST&groupId=testing&autoOffsetReset=earliest&consumersCount=1")
				.process(new MessageProcessor())
						.bean(KafkaOutputBean.class, "doWork");
			}
		};
	}
}