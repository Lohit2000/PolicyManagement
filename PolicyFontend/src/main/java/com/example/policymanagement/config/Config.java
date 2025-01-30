package com.example.policymanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
// import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Value("${baseUrl}")
    private String BASE_URL;

    @Bean
    public RestClient restClient(){
        return RestClient.create(BASE_URL);
    }
}

