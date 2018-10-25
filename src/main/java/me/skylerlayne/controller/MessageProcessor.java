package me.skylerlayne.controller;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String event = exchange.getIn().getBody(String.class);
        System.out.println(new Date() + " : " + event);
    }
}
