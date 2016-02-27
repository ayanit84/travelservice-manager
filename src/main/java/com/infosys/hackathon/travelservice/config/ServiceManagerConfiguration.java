package com.infosys.hackathon.travelservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"com.infosys.hackathon.travelservice.json.processors",
		"com.infosys.hackathon.travelservice.restservices" })
public class ServiceManagerConfiguration {

}
