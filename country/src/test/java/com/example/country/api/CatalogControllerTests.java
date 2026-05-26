package com.example.country.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Verifies the country and city REST endpoints with the in-memory catalog.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CatalogControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void returnsCountries() throws Exception {
		mockMvc.perform(get("/countries"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("India"));
	}

	@Test
	void returnsPaginatedCitiesByCountry() throws Exception {
		mockMvc.perform(get("/countries/{countryId}/cities", 1).param("page", "0").param("size", "2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content.length()").value(2))
				.andExpect(jsonPath("$.page").value(0))
				.andExpect(jsonPath("$.size").value(2))
				.andExpect(jsonPath("$.totalItems").value(20))
				.andExpect(jsonPath("$.totalPages").value(10))
				.andExpect(jsonPath("$.first").value(true))
				.andExpect(jsonPath("$.last").value(false));
	}

	@Test
	void returnsExpectedCityCountsForEveryCountry() throws Exception {
		mockMvc.perform(get("/countries/{countryId}/cities", 1).param("size", "100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalItems").value(20));

		mockMvc.perform(get("/countries/{countryId}/cities", 2).param("size", "100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalItems").value(15));

		mockMvc.perform(get("/countries/{countryId}/cities", 3).param("size", "100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalItems").value(15));

		mockMvc.perform(get("/countries/{countryId}/cities", 4).param("size", "100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalItems").value(15));
	}

	@Test
	void returnsCityDetails() throws Exception {
		mockMvc.perform(get("/cities/{cityId}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Bengaluru"))
				.andExpect(jsonPath("$.countryId").value(1));
	}

	@Test
	void returnsNotFoundForMissingCity() throws Exception {
		mockMvc.perform(get("/cities/{cityId}", 999))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("City not found: 999"));
	}

	@Test
	void rejectsInvalidPageSize() throws Exception {
		mockMvc.perform(get("/countries/{countryId}/cities", 1).param("size", "0"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("size must be between 1 and 100"));
	}
}
