package com.example.country.domain;

/**
 * Country summary returned by the API.
 *
 * @param id unique country identifier
 * @param name country display name
 */
public record Country(Long id, String name) {
}
