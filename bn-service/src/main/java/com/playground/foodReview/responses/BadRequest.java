package com.playground.foodReview.responses;

public class BadRequest {
    private Integer code;
    private String message;

    public BadRequest(String message, Integer code){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() { return this.code; }
    public String getMessage() { return this.message; }
}
