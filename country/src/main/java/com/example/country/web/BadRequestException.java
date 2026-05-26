package com.example.country.web;

/**
 * Exception used when the client sends an invalid request.
 */
public class BadRequestException extends RuntimeException {

	/**
	 * Creates a bad request exception.
	 *
	 * @param message validation message returned to the client
	 */
	public BadRequestException(String message) {
		super(message);
	}
}
