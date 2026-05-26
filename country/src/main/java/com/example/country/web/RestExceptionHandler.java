package com.example.country.web;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Converts domain exceptions into consistent HTTP error responses.
 */
@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * Handles missing country or city resources.
	 *
	 * @param ex exception containing the missing resource message
	 * @param request current HTTP request
	 * @return 404 response with an {@link ApiError} body
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
	}

	/**
	 * Handles invalid client input such as bad pagination values or invalid path types.
	 *
	 * @param ex exception containing the validation message
	 * @param request current HTTP request
	 * @return 400 response with an {@link ApiError} body
	 */
	@ExceptionHandler({BadRequestException.class, MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ApiError> handleBadRequest(Exception ex, HttpServletRequest request) {
		return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
	}

	private ResponseEntity<ApiError> buildError(HttpStatus status, String message, HttpServletRequest request) {
		ApiError error = new ApiError(
				Instant.now(),
				status.value(),
				status.getReasonPhrase(),
				message,
				request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
}
