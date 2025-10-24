package com.techzone.techzone.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
// Configuración global de CORS
@Override
public void addCorsMappings(CorsRegistry registry) {
registry.addMapping("/api/**") // rutas que comienzan con '/api/'
.allowedOrigins("http://localhost:4200") // Permite este origen
.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP
.allowedHeaders("*")
.allowCredentials(true);
}
}