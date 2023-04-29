package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

public class MessageException {

    private String message;
    private Instant time;
    private Integer status;
    private String path;

    public MessageException() {
    }

    public MessageException(String message, Instant time, Integer status, String path) {
        this.message = message;
        this.time = time;
        this.status = status;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
