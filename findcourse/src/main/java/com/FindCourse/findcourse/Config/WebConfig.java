package com.FindCourse.findcourse.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow all paths
                        .allowedOrigins("http://localhost:3000")  // Allow only frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);  // Allow credentials
            }
        };
    }
}