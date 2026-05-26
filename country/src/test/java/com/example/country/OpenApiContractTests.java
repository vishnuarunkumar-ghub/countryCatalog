package com.example.country;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;

/**
 * Verifies that the static OpenAPI contract is valid YAML and exposes the expected paths.
 */
class OpenApiContractTests {

	@Test
	void openApiContractCanBeParsed() {
		YamlMapFactoryBean yamlFactory = new YamlMapFactoryBean();
		yamlFactory.setResources(new ClassPathResource("static/openapi.yaml"));

		Map<String, Object> contract = yamlFactory.getObject();

		assertThat(contract).isNotNull();
		assertThat(contract).containsEntry("openapi", "3.1.0");
		assertThat(contract.get("paths")).asInstanceOf(org.assertj.core.api.InstanceOfAssertFactories.MAP)
				.containsKeys("/countries", "/countries/{countryId}/cities", "/cities/{cityId}");
	}
}
