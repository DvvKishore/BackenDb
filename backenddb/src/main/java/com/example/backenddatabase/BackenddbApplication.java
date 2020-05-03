package com.example.backenddatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.backenddb.controller","com.example.backenddb.exception","com.example.backenddb.service"})
@EnableJpaRepositories("com.example.backenddb.dao")
@EntityScan("com.example.backenddb.pojo")
public class BackenddbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackenddbApplication.class, args);
	}
}
