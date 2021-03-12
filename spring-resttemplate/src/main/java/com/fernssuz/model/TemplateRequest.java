package com.fernssuz.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TemplateRequest {

    @Builder.Default
    private String id = "1";
}
