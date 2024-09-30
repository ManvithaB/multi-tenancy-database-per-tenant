package com.manvitha.database_per_tenant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiTenancyMultipleDbApplication {

	@Value("${server.port}")
	private static String port;

	public static void main(String[] args) {
		SpringApplication.run(MultiTenancyMultipleDbApplication.class, args);
		System.out.println("port value - " + port);
	}

}
