package com.example.country.domain;

/**
 * City details returned by the API.
 *
 * @param id unique city identifier
 * @param name city display name
 * @param countryId identifier of the country that contains the city
 * @param population approximate city population
 * @param zipCode representative postal or ZIP code
 * @param description short human-readable city summary
 */
public record City(
		Long id,
		String name,
		Long countryId,
		Integer population,
		String zipCode,
		String description) {
}
