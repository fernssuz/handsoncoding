package com.fernssuz.common.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Interceptor to log incoming requests
 */
@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        logRequestDetails(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponseDetails(response);
        return response;
    }


    private void logRequestDetails(HttpRequest request, byte[] body) throws IOException{
        log.debug("Request {} : {}", request.getMethod(), request.getURI());
        if(body.length > 0)
            log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
    }

    private void logResponseDetails(ClientHttpResponse response) throws IOException {
        log.debug("Responded with {}", response.getStatusCode());
       // if (!response.getStatusCode().is2xxSuccessful()) {
            InputStreamReader isr = new InputStreamReader(
                    response.getBody(),
                    StandardCharsets.UTF_8);
            String body = new BufferedReader(isr)
                    .lines()
                    .collect(Collectors.joining("\n"));
            log.error("Response body: {}", body);

      //  }
    }


}
