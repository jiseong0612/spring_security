package com.example.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*"); // e.g. http://domain1.com
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/api/**", config);
		return new CorsFilter(source);
	}
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		
//		configuration.setAllowCredentials(true);
//		configuration.setAllowedOrigins(List.of("http://localhost:5173"));
//		configuration.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE"));
//		configuration.setAllowedHeaders(List.of("*"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/api/**", configuration);
//		return source;
//	}
}
