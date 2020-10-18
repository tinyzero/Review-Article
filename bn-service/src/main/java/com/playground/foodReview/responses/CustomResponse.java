package com.playground.foodReview.responses;

public class CustomResponse {
    private Integer code;
    private String message;

    public CustomResponse(String message, Integer code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() { return this.message; }
    public Integer getCode() { return this.code; }
}
