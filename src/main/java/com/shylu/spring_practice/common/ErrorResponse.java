package com.shylu.spring_practice.common;

public class ErrorResponse {
    private String message;
    private String details;
    private int statusCode;

    public ErrorResponse(String message, String details, int statusCode) {
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
    }

}