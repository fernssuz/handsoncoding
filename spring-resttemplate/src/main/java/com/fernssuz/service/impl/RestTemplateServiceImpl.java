package com.fernssuz.service.impl;

import com.fernssuz.model.TemplateRequest;
import com.fernssuz.model.TemplateResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RestTemplateServiceImpl {


    @Value("${resttemplate.post-uri}")
    private String postUri;
    @Value("${resttemplate.get-uri}")
    private String getUri;
    @Value("${resttemplate.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public RestTemplateServiceImpl(@Qualifier("restClientBuilder") RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public String getRestTemplate(@RequestBody TemplateRequest request){
        //Get Plain JSON
        return restTemplate.getForEntity(baseUrl+request.getId(),String.class).getBody();

    }

    public Optional<TemplateResponse> postRestTemplate(@RequestBody TemplateRequest request) {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", request.getId());
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);

        HttpEntity<TemplateRequest> requestEntity =
                new HttpEntity<>(request);

        TemplateResponse response = restTemplate
                .exchange(
                        builder.buildAndExpand(urlParams).toUri(),
                        HttpMethod.POST,
                        requestEntity,
                        TemplateResponse.class).getBody();

        return Optional.ofNullable(response);
    }
}
