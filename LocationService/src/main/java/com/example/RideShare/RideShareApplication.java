package com.example.RideShare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;


@EnableDiscoveryClient
@ComponentScan(basePackages = {"Services.LocationService"})
@RestController
@SpringBootApplication
public class RideShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideShareApplication.class,args);

	}
}
