package com.springproject.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;



@OpenAPIDefinition(
	info = @Info(
			title = "ReviewHub - Backend Services",
			version = "1.1.0",
			description = "A Spring Boot application where users can write and read reviews for movies, creating a hub of valuable film opinions."
	)
)

@SpringBootApplication
public class MoviesApplication { 

	public static void main(String[] args) { 
		SpringApplication.run(MoviesApplication.class, args);
		System.out.println("Hello World");
	}
}
