package com.sergei.batch.processing.rest.dto;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
public class ResponseError {

    private Integer status;
    private String description;

    public ResponseError() {
    }

    public ResponseError(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
