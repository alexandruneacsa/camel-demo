package com.example.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResponseLoggerProcessor implements Processor {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseLoggerProcessor.class);
	
	@Override
	public void process(Exchange exchange) {
		
		var body = exchange.getMessage().getBody(String.class);
		var statusCode = exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		Map<String, Object> headers = exchange.getMessage().getHeaders();
		
		logger.info("HTTP Status: {}", statusCode);
		logger.info("Response Headers: {}", headers);
		logger.debug("Response Body: {}", body);
	}
}
