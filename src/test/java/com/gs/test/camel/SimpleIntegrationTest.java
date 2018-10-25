package com.gs.test.camel;

import java.util.Date;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gs.test.camel.config.KafkaCamelRoute;
import com.gs.test.camel.config.KafkaRouteProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {KafkaCamelRoute.class})
public class SimpleIntegrationTest extends CamelTestSupport{
	
	@Autowired
	@Qualifier("KafkaRouteProducer")
	RouteBuilder kafkaRouteProducer;

	@EndpointInject(uri = KafkaRouteProducer.ROUTE)
	ProducerTemplate kafkaProducer;
	
	@Before
	public void before() {
		assertNotNull(kafkaRouteProducer);
		try {		
			context.addRoutes(kafkaRouteProducer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSimpleMessage() {
		kafkaProducer.sendBody(KafkaRouteProducer.ROUTE, "This is a message from the JUNIT message route @ "+new Date()+"!");
	}
}
