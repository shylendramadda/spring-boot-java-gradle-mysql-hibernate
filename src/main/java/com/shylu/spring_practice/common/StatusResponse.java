package com.shylu.spring_practice.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatusResponse {
    private String status;
    private int statusCode;
    private String message;
    private Object data;


    public StatusResponse(String status, int statusCode, String message) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }
    public StatusResponse(String status, int statusCode, String message, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

}
