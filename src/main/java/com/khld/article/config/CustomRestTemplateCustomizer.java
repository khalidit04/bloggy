package com.khld.article.config;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * customize rest template with an interceptor
 */
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(new CustomClientHttpRequestInterceptor());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }
}