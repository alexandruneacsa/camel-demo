package com.example.camel.route;

import com.example.camel.processor.ResponseLoggerProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TodosRoute extends RouteBuilder {
	
	@Override
	public void configure() {
		
		onException(Exception.class)
			.handled(true)
			.log("Exception in /todos: ${exception.message}")
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
			.setBody().constant("Internal Server Error");
		
		from("netty-http:http://localhost:8080/todos").routeId("todosroute")
			.log("Incoming request to /todos")
			.removeHeaders("CamelHttp*") 
			.to("https://jsonplaceholder.typicode.com/todos?bridgeEndpoint=true&connectTimeout=3000&socketTimeout=5000")
			.process(new ResponseLoggerProcessor())
			.log("Fetched todos");
	}
}
