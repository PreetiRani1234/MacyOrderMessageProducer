package com.macy.producer;


import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class MacyOrderMessageProducerApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(MacyOrderMessageProducerApplication.class, args);
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"MacyOrderMessageProducer REST API Documentation",
				 "Stock REST APIs released by Zensar LTD.",
				 "2.5",
				 "http://zensar.com/termsoservice",
				 new Contact("Preeti","http://preeti.com","preeti.rani@zensar.com"),
				 "GPL",
			      "http://gpl.com",
			      new ArrayList<VendorExtension>());
	}
	
	
	@Bean
	public Docket getCustomizedDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.macy.producer"))
				.paths(PathSelectors.any())
				.build();
	}
	
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	
}
