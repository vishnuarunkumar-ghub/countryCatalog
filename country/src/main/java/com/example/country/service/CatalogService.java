package com.example.country.service;

import com.example.country.domain.City;
import com.example.country.domain.Country;
import com.example.country.web.BadRequestException;
import com.example.country.web.PagedResponse;
import com.example.country.web.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Provides read-only access to the in-memory country and city catalog.
 */
@Service
public class CatalogService {

	private static final int MAX_PAGE_SIZE = 100;

	// The sample data is intentionally in-memory to keep the task focused on API behavior.
	private final List<Country> countries = List.of(
			new Country(1L, "India"),
			new Country(2L, "Germany"),
			new Country(3L, "United States"),
			new Country(4L, "Luxembourg"));

	private final List<City> cities = List.of(
			new City(1L, "Bengaluru", 1L, 13_193_000, "560001", "Technology hub and capital of Karnataka."),
			new City(2L, "Mumbai", 1L, 20_961_000, "400001", "Major financial center on India's west coast."),
			new City(3L, "Chennai", 1L, 11_776_000, "600001", "Coastal city known for culture, ports, and automotive industry."),
			new City(4L, "Hyderabad", 1L, 10_801_000, "500001", "Capital city of Telangana with a major technology sector."),
			new City(5L, "Delhi", 1L, 32_941_000, "110001", "National capital territory and major administrative center."),
			new City(6L, "Kolkata", 1L, 15_333_000, "700001", "Historic eastern Indian city on the Hooghly River."),
			new City(7L, "Pune", 1L, 7_166_000, "411001", "Maharashtra city known for education, manufacturing, and IT."),
			new City(8L, "Ahmedabad", 1L, 8_854_000, "380001", "Largest city in Gujarat and a major commercial center."),
			new City(9L, "Jaipur", 1L, 4_207_000, "302001", "Rajasthan capital known for heritage and tourism."),
			new City(10L, "Surat", 1L, 7_784_000, "395003", "Gujarat city known for textiles and diamond polishing."),
			new City(11L, "Lucknow", 1L, 3_945_000, "226001", "Capital of Uttar Pradesh with a strong cultural history."),
			new City(12L, "Kanpur", 1L, 3_234_000, "208001", "Industrial city in Uttar Pradesh."),
			new City(13L, "Nagpur", 1L, 2_991_000, "440001", "Central Indian city and logistics hub."),
			new City(14L, "Indore", 1L, 3_302_000, "452001", "Largest city in Madhya Pradesh and a commercial center."),
			new City(15L, "Thane", 1L, 2_535_000, "400601", "Mumbai metropolitan region city with rapid urban growth."),
			new City(16L, "Bhopal", 1L, 2_564_000, "462001", "Capital of Madhya Pradesh known for lakes and administration."),
			new City(17L, "Visakhapatnam", 1L, 2_385_000, "530001", "Major port city on India's east coast."),
			new City(18L, "Patna", 1L, 2_529_000, "800001", "Capital of Bihar on the southern bank of the Ganges."),
			new City(19L, "Vadodara", 1L, 2_233_000, "390001", "Gujarat city with manufacturing and cultural institutions."),
			new City(20L, "Coimbatore", 1L, 2_935_000, "641001", "Tamil Nadu city known for industry and engineering."),
			new City(21L, "Berlin", 2L, 3_878_000, "10115", "Germany's capital and largest city."),
			new City(22L, "Munich", 2L, 1_512_000, "80331", "Bavarian capital known for engineering, culture, and business."),
			new City(23L, "Hamburg", 2L, 1_892_000, "20095", "Major port city in northern Germany."),
			new City(24L, "Cologne", 2L, 1_085_000, "50667", "Rhine city known for media, trade fairs, and its cathedral."),
			new City(25L, "Frankfurt", 2L, 773_000, "60311", "Financial center and major European transport hub."),
			new City(26L, "Stuttgart", 2L, 632_000, "70173", "Baden-Wurttemberg capital known for automotive engineering."),
			new City(27L, "Dusseldorf", 2L, 629_000, "40213", "North Rhine-Westphalia capital with business and fashion sectors."),
			new City(28L, "Leipzig", 2L, 616_000, "04109", "Saxon city with strong cultural and logistics activity."),
			new City(29L, "Dortmund", 2L, 593_000, "44135", "Ruhr city with technology, services, and football culture."),
			new City(30L, "Essen", 2L, 584_000, "45127", "Ruhr metropolitan city with industrial heritage."),
			new City(31L, "Bremen", 2L, 569_000, "28195", "Northern German city with port and aerospace industries."),
			new City(32L, "Dresden", 2L, 563_000, "01067", "Saxony capital known for architecture and microelectronics."),
			new City(33L, "Hanover", 2L, 548_000, "30159", "Lower Saxony capital and trade fair city."),
			new City(34L, "Nuremberg", 2L, 523_000, "90402", "Franconian city with manufacturing and historic landmarks."),
			new City(35L, "Duisburg", 2L, 502_000, "47051", "Ruhr city with one of Europe's largest inland ports."),
			new City(36L, "New York", 3L, 8_258_000, "10001", "Largest city in the United States by population."),
			new City(37L, "Los Angeles", 3L, 3_821_000, "90001", "Southern California city known for entertainment and trade."),
			new City(38L, "Chicago", 3L, 2_665_000, "60601", "Major Midwest city on Lake Michigan."),
			new City(39L, "Houston", 3L, 2_314_000, "77001", "Texas city known for energy, medicine, and space operations."),
			new City(40L, "Phoenix", 3L, 1_650_000, "85001", "Arizona capital and large desert metropolitan center."),
			new City(41L, "Philadelphia", 3L, 1_550_000, "19102", "Historic Pennsylvania city and regional business hub."),
			new City(42L, "San Antonio", 3L, 1_496_000, "78205", "Texas city known for history, tourism, and military presence."),
			new City(43L, "San Diego", 3L, 1_388_000, "92101", "Southern California coastal city with defense and biotech sectors."),
			new City(44L, "Dallas", 3L, 1_303_000, "75201", "North Texas commercial and transport center."),
			new City(45L, "San Jose", 3L, 970_000, "95112", "Silicon Valley city with a major technology economy."),
			new City(46L, "Austin", 3L, 980_000, "73301", "Texas capital with a large technology and startup presence."),
			new City(47L, "Jacksonville", 3L, 986_000, "32202", "Florida city with port, logistics, and finance activity."),
			new City(48L, "San Francisco", 3L, 808_000, "94102", "Bay Area city known for technology and finance."),
			new City(49L, "Columbus", 3L, 913_000, "43215", "Ohio capital with education, government, and services sectors."),
			new City(50L, "Charlotte", 3L, 911_000, "28202", "North Carolina city and major banking center."),
			new City(51L, "Luxembourg City", 4L, 135_000, "L-1111", "Capital of Luxembourg and an international finance center."),
			new City(52L, "Esch-sur-Alzette", 4L, 37_000, "L-4001", "Second-largest city in Luxembourg."),
			new City(53L, "Differdange", 4L, 29_000, "L-4501", "Southwestern Luxembourg city with industrial roots."),
			new City(54L, "Dudelange", 4L, 22_000, "L-3401", "Southern city with industrial heritage and cultural venues."),
			new City(55L, "Ettelbruck", 4L, 9_700, "L-9001", "Northern transport and service center."),
			new City(56L, "Diekirch", 4L, 7_000, "L-9201", "Northern town known for administration and brewing history."),
			new City(57L, "Wiltz", 4L, 7_200, "L-9501", "Ardennes town with cultural festivals and local services."),
			new City(58L, "Echternach", 4L, 5_900, "L-6401", "Historic town near the German border."),
			new City(59L, "Mersch", 4L, 10_000, "L-7501", "Central Luxembourg municipality and regional hub."),
			new City(60L, "Remich", 4L, 3_800, "L-5501", "Moselle river town known for wine and tourism."),
			new City(61L, "Grevenmacher", 4L, 5_000, "L-6701", "Moselle town with wine, logistics, and cross-border links."),
			new City(62L, "Rumelange", 4L, 5_900, "L-3701", "Southern town with mining history."),
			new City(63L, "Petange", 4L, 20_000, "L-4701", "Southwestern municipality near the Belgian and French borders."),
			new City(64L, "Strassen", 4L, 10_500, "L-8001", "Residential and commercial commune west of Luxembourg City."),
			new City(65L, "Bertrange", 4L, 8_800, "L-8005", "Commune west of Luxembourg City with retail and office areas."));

	/**
	 * Finds every configured country.
	 *
	 * @return immutable list of country summaries
	 */
	public List<Country> findAllCountries() {
		return countries;
	}

	/**
	 * Finds cities belonging to a country and returns the requested page.
	 *
	 * @param countryId selected country id
	 * @param page zero-based page index
	 * @param size number of records per page
	 * @return cities for the requested page with paging metadata
	 */
	public PagedResponse<City> findCitiesByCountry(Long countryId, int page, int size) {
		validatePagination(page, size);
		ensureCountryExists(countryId);

		List<City> matchingCities = cities.stream()
				.filter(city -> city.countryId().equals(countryId))
				.toList();

		int totalItems = matchingCities.size();
		int totalPages = totalItems == 0 ? 0 : (int) Math.ceil((double) totalItems / size);
		int fromIndex = Math.min(page * size, totalItems);
		int toIndex = Math.min(fromIndex + size, totalItems);
		List<City> content = matchingCities.subList(fromIndex, toIndex);

		return new PagedResponse<>(
				content,
				page,
				size,
				totalItems,
				totalPages,
				page == 0,
				totalPages == 0 || page >= totalPages - 1);
	}

	/**
	 * Finds a city by its unique identifier.
	 *
	 * @param cityId city id to search for
	 * @return matching city
	 */
	public City findCityById(Long cityId) {
		return cities.stream()
				.filter(city -> city.id().equals(cityId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("City not found: " + cityId));
	}

	private void ensureCountryExists(Long countryId) {
		boolean exists = countries.stream().anyMatch(country -> country.id().equals(countryId));
		if (!exists) {
			throw new ResourceNotFoundException("Country not found: " + countryId);
		}
	}

	private void validatePagination(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("page must be greater than or equal to 0");
		}
		if (size < 1 || size > MAX_PAGE_SIZE) {
			throw new BadRequestException("size must be between 1 and " + MAX_PAGE_SIZE);
		}
	}
}
