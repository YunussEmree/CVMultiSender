package com.yunussemree.multimailsender.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;
}
