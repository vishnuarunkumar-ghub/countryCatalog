package com.example.country.api;

import com.example.country.domain.City;
import com.example.country.domain.Country;
import com.example.country.service.CatalogService;
import com.example.country.web.PagedResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing country and city catalog endpoints.
 */
@RestController
@RequestMapping
public class CatalogController {

	private final CatalogService catalogService;

	/**
	 * Creates a controller backed by the catalog service.
	 *
	 * @param catalogService service used to load countries and cities
	 */
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	/**
	 * Returns all available countries.
	 *
	 * @return countries from the in-memory catalog
	 */
	@GetMapping("/countries")
	public List<Country> listCountries() {
		return catalogService.findAllCountries();
	}

	/**
	 * Returns paginated cities for the selected country.
	 *
	 * @param countryId country identifier from the path
	 * @param page zero-based page index
	 * @param size requested number of cities per page
	 * @return a page of cities and pagination metadata
	 */
	@GetMapping("/countries/{countryId}/cities")
	public PagedResponse<City> listCitiesByCountry(
			@PathVariable Long countryId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return catalogService.findCitiesByCountry(countryId, page, size);
	}

	/**
	 * Returns details for a city by id.
	 *
	 * @param cityId city identifier from the path
	 * @return matching city details
	 */
	@GetMapping("/cities/{cityId}")
	public City getCityDetails(@PathVariable Long cityId) {
		return catalogService.findCityById(cityId);
	}
}
