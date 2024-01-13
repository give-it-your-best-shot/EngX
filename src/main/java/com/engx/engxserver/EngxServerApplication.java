package com.engx.engxserver;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EngxServerApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("${CLIENT_URL}")
                        .allowedHeaders("*");
                ;
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(EngxServerApplication.class, args);
    }
}
