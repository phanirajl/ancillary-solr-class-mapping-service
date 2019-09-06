package com.delta.rm.ancillary.offer.mapping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.datastax.driver.core.LocalDate;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class SwaggerConfig
 * Swagger configuration
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/** The constant that has the info about: DETAIL_DESCRIPTION*/
	private static final String DETAIL_DESCRIPTION = "RESTful API for service endpoints. \n \n"
			+ "This micro-service provides class mapping  information \n \n" 
			+ "Below is a list of available REST API.";
	/** The constant that has the info about: SERVICE_NAME*/
	private static final String SERVICE_NAME = "Class Mapping Rest Service";

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("ancillary-ftp-service")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(paths())
				.build()
				.apiInfo(apiInfo())
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class);
	}
	 
	@SuppressWarnings("unchecked")
	private Predicate<String> paths() {
		return Predicates.or(PathSelectors.regex("/ancillary.*"));

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(SERVICE_NAME).description(DETAIL_DESCRIPTION)
				.contact(new Contact("RM Ancillary Offer Team", "www.delta.com", "RMAncillaryTeam.IT@delta.com"))
				.termsOfServiceUrl("http://springfox.io").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("1.0.0.0").build();
	}
}
