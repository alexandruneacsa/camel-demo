package com.example.camel.route;

import com.example.camel.processor.ResponseLoggerProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CommentsRoute extends RouteBuilder {
	
	@Override
	public void configure() {
		
		onException(Exception.class)
			.handled(true)
			.log("Exception in /comments: ${exception.message}")
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
			.setBody().constant("Internal Server Error");
		
		from("netty-http:http://localhost:8080/comments").routeId("commentsroute")
			.log("Incoming request to /comments")
			.removeHeaders("CamelHttp*")
			.to("https://jsonplaceholder.typicode.com/comments?bridgeEndpoint=true&connectTimeout=3000&socketTimeout=5000")
			.process(new ResponseLoggerProcessor())
			.log("Fetched comments");
	}
}
