package com.microservicio.bff_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


import com.microservicio.bff_gateway.exception.CustomResponseErrorHandler;

@Configuration
public class RestTemplateConfig {

   
    @Autowired
    private CustomResponseErrorHandler customResponseErrorHandler;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        
    
        restTemplate.setErrorHandler(customResponseErrorHandler);
        
        return restTemplate;
    }
}