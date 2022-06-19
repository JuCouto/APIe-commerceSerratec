package com.example.ecommerce.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
	public class CorsConfiguration implements WebMvcConfigurer {

	    private static final int UMA_HORA = 3600;

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry
	            .addMapping("/api/**")
	            .allowedOrigins("http://localhost:4200")
	            .allowedOrigins("http://localhost:8080/**")
	            .allowedMethods("*")
	            .allowedHeaders("*")
	            .allowCredentials(false)
	            .maxAge(UMA_HORA);
	    }
	}

