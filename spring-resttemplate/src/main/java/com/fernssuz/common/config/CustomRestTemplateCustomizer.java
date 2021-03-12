package com.fernssuz.common.config;

import com.fernssuz.common.handler.RestTemplateResponseErrorHandler;
import com.fernssuz.common.interceptors.CustomClientHttpRequestInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {
    /**
     * Callback to customize a {@link RestTemplate} instance.
     *
     * @param restTemplate the template to customize
     */
    @Override
    public void customize(RestTemplate restTemplate) {
        log.debug("customize the restTemplate");
        ObjectMapper om = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        for (HttpMessageConverter converter : restTemplate.getMessageConverters() )
        {
            if ( converter instanceof MappingJackson2HttpMessageConverter)
            {
                MappingJackson2HttpMessageConverter m =
                        (MappingJackson2HttpMessageConverter)converter;
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignore unknown properties
                m.setObjectMapper(om);
            }
        }

        restTemplate.setInterceptors( Collections.singletonList(new CustomClientHttpRequestInterceptor()) );
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        log.debug("set the buffer so it won't destroy the response body when intercepted");
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

    }
}
