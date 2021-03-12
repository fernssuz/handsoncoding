package com.fernssuz.controller;

import com.fernssuz.model.TemplateRequest;
import com.fernssuz.model.TemplateResponse;
import com.fernssuz.service.RestTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/v1")
@RestController
public class RestTemplateController {
    private final RestTemplateService service;

    public RestTemplateController(RestTemplateService service) {
        this.service = service;
    }

    @GetMapping("/resttemplate")
    public String getRestTemplate(@RequestBody TemplateRequest request) {
        return service.getRestTemplate(request);
    }

    @PostMapping("/resttemplate/push")
    public Optional<TemplateResponse> postRestTemplate(@RequestBody TemplateRequest request) {
        return service.postRestTemplate(request);
    }


}
