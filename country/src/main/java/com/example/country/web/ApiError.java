package com.example.country.web;

import java.time.Instant;

/**
 * Standard error payload returned by REST exception handling.
 *
 * @param timestamp time when the error response was generated
 * @param status HTTP status code
 * @param error HTTP reason phrase
 * @param message detailed error message
 * @param path request path that caused the error
 */
public record ApiError(
		Instant timestamp,
		int status,
		String error,
		String message,
		String path) {
}
