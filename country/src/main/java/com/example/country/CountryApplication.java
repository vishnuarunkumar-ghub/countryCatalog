package com.example.country;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts the countries and cities Spring Boot API.
 */
@SpringBootApplication
public class CountryApplication {

	/**
	 * Application entry point.
	 *
	 * @param args command-line arguments passed to Spring Boot
	 */
	public static void main(String[] args) {
		SpringApplication.run(CountryApplication.class, args);
	}

}
