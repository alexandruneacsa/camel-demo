package com.example.camel.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig extends RouteBuilder {
	
	@Override
	public void configure() {
		
		restConfiguration().component("netty-http").port(8080);
	}
}
