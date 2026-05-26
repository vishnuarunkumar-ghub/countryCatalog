package com.example.country.web;

/**
 * Exception used when a requested country or city does not exist.
 */
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Creates a not-found exception.
	 *
	 * @param message resource-specific message returned to the client
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
