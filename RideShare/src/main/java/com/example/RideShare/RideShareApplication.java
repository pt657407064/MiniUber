package com.example.RideShare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"Services.LocationService"})
@EnableEurekaClient
@RestController
@SpringBootApplication
public class RideShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideShareApplication.class,args);

	}
}
