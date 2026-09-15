package com.example.dto;

import java.time.LocalDateTime;

public class ErrorResponseDto {

    private int statusCode;
    private LocalDateTime timeStamp;
    private String message;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(int statusCode, LocalDateTime timeStamp, String message) {
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
