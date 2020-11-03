package com.in28minutes.microservices.limitsservice.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.beans.LimitConfiguration;
import com.in28minutes.microservices.limitsservice.configuration.Configuration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	Configuration configuration;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(LimitsConfigurationController.class);
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		//step 1- harcoding the values, not picking up from application.properties
		//return new LimitConfiguration(1000,1);
		logger.info("Entering intp Limits services");
		//step 2- pick from application.properties files(comment step 1 when execute this)
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
		
		//step 3 - pick from spring cloud config server instead of application.properties
		
	}
	
	//fault tolerance test
	@GetMapping("/limits-fault-tolerance")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveLimitsFromConfiguration")
	public LimitConfiguration retrieveConfiguration() {
		throw new RuntimeException("Not Available");
		
	}
	public LimitConfiguration fallbackRetrieveLimitsFromConfiguration() {
		return new LimitConfiguration(5,5555);
		
	} 

}
