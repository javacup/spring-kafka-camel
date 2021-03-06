package com.gs.test.camel.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.test.camel.config.KafkaRouteProducer;
import com.gs.test.camel.model.Notification;

@Controller
@ResponseBody
@RequestMapping("/message")
public class MessageController {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	CamelContext camelContext;

	@Autowired
	@Qualifier("KafkaRouteProducer")
	RouteBuilder kafkaRouteProducer;

	@Autowired
	@Qualifier("KafkaRouteConsumer")
	RouteBuilder kafkaRouteConsumer;

	@EndpointInject(uri = KafkaRouteProducer.ROUTE)
	ProducerTemplate kafkaProducer;

	@Autowired
	ConsumerTemplate kafkaConsumer;

	@PostConstruct
	public void setup() {
		try {
			camelContext.addRoutes(kafkaRouteProducer);
			camelContext.addRoutes(kafkaRouteConsumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * GET to produce a message for Kafaka.
	 * 
	 * @param request
	 *            the {@link HttpServletRequest} object.
	 * @param response
	 *            the {@link HttpServletResponse} object.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void get(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Handling GET request method");
		try {
			kafkaProducer.sendBody(KafkaRouteProducer.ROUTE, "This is a message from the /message route!");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * POST a notification, send it as a message to Kafka.
	 * 
	 * @param request
	 *            the {@link HttpServletRequest} object.
	 * @param response
	 *            the {@link HttpServletResponse} object.
	 * @param notification
	 *            the {@link Notification} to be posted.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response, @RequestBody Notification notification) {
		System.out.println("Handling POST request method");
		try {
			kafkaProducer.sendBody(KafkaRouteProducer.ROUTE, mapper.writeValueAsString(notification));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
