package com.fernssuz.service;

import com.fernssuz.model.TemplateRequest;
import com.fernssuz.model.TemplateResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface RestTemplateService {

    String getRestTemplate(@RequestBody TemplateRequest request);

    Optional<TemplateResponse> postRestTemplate(@RequestBody TemplateRequest request) ;
}
