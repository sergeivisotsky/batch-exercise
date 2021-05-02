package com.sergei.batch.processing.rest.dto;

import com.google.gson.JsonObject;

/**
 * Job execution request
 *
 * @author Sergei Visotsky
 * @since 1.0
 */
public class JobRequest {

    private String jobName;
    private JsonObject parameters;

    public JobRequest() {
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JsonObject getParameters() {
        return parameters;
    }

    public void setParameters(JsonObject parameters) {
        this.parameters = parameters;
    }
}
