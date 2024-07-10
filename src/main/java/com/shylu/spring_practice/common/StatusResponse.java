package com.shylu.spring_practice.common;

public class StatusResponse {
    private String status;
    private int statusCode;
    private String message;
    private Object data;

    // Constructors, getters, and setters
    public StatusResponse() {}

    public StatusResponse(String status, int statusCode, String message, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
