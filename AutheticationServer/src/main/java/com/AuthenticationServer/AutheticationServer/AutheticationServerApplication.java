package com.AuthenticationServer.AutheticationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AutheticationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutheticationServerApplication.class, args);
	}

}
