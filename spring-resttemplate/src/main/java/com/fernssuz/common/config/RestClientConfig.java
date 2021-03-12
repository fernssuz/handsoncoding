package com.fernssuz.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RestClientConfig {

    @Value("${spreedly.environment-key}")
    private String environmentKey;
    @Value("${spreedly.access-secret}")
    private String secret;


    @Bean("spreedlyRestClientBuilder")
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(new CustomRestTemplateCustomizer())
                .basicAuthentication(environmentKey, secret);

    }


}
