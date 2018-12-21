package com.github.kuhn_he;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.kuhn_he.saas.ds.annotation.EnableDynamicRouteDataSource;


@SpringBootApplication
@EnableDynamicRouteDataSource
public class ApplicationBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBootstrap.class, args);
	}
}
