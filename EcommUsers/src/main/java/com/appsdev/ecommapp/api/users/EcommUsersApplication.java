package com.appsdev.ecommapp.api.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class EcommUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommUsersApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bcPass() {
		return new BCryptPasswordEncoder();
	}
}
