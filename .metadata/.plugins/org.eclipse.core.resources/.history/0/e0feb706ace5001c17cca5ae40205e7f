
package com.example.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		
		return new OpenAPI().info(new Info().title("API E-commerce Grupo 7").version(appVersion)
				.description("Trabalho final para matéria de Desenvolvimento de API Restful."));
	}
}