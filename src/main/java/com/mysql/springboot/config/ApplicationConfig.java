package com.mysql.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.mysql.springboot"})
public class ApplicationConfig {
	
	// Nothing to Configure.All configuration,dependencies required for
	// application will be taken care by SpringBoot
}
