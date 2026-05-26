package com.example.country.web;

import java.util.List;

/**
 * Generic response wrapper for paginated API results.
 *
 * @param <T> type of item contained in the page
 * @param content records in the current page
 * @param page zero-based page index
 * @param size requested page size
 * @param totalItems number of matching records across all pages
 * @param totalPages number of available pages
 * @param first whether this response is the first page
 * @param last whether this response is the last page
 */
public record PagedResponse<T>(
		List<T> content,
		int page,
		int size,
		long totalItems,
		int totalPages,
		boolean first,
		boolean last) {
}
