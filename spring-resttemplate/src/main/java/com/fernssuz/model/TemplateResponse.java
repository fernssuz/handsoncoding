package com.fernssuz.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TemplateResponse {

    @Builder.Default
    private boolean success = false;
    @Builder.Default
    private String message = "Hello REST Template";

}
