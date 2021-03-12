package com.fernssuz.common.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 *  This will read the HTTP status from the response and either:
 *
 * Throw an exception that is meaningful to our application
 * Simply ignore the HTTP status and let the response flow continue without interruption
 */
@Component
@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    /**
     * Indicate whether the given response has any errors.
     *
     * @param response the response to inspect
     * @return {@code true} if the response indicates an error; {@code false} otherwise
     * @throws IOException in case of I/O errors
     */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (
                response
                        .getStatusCode()
                        .series() == HttpStatus.Series.CLIENT_ERROR || response
                        .getStatusCode()
                        .series() == HttpStatus.Series.SERVER_ERROR);
    }

    /**
     * Handle the error in the given response.
     *
     * @param response the response with the error
     * @throws IOException in case of I/O errors
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response
                .getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            log.error("handle server error {} {}", response.getStatusCode(), response.getStatusText());
        } else if (response
                .getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            log.error("Client error {} {}", response.getStatusCode(), response.getStatusText());
            if (response
                    .getStatusCode()
                    .equals(HttpStatus.NOT_FOUND)) {
                log.debug("Oops!! HttpStatus not found .Close the response and return");
                //response.close();
            }
        }

    }

}
