package com.starter.dto;

/**
 * Created by adam.wells on 2/04/2016.
 */
public class RestBody {

    private String message;
    private Object data;

    public RestBody() {
    }

    public RestBody(String message, Object data) {
        this.message = message;
        this.data = data;
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
