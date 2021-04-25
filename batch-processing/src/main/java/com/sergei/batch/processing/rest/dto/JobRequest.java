package com.sergei.batch.processing.rest.dto;

import java.util.Map;

/**
 * Job execution request
 *
 * @author Sergei Visotsky
 * @since 1.0
 */
public class JobRequest {

    private final String jobName;
    private final Map<String, String> parameters;

    public JobRequest(String jobName, Map<String, String> parameters) {
        this.jobName = jobName;
        this.parameters = parameters;
    }

    public String getJobName() {
        return jobName;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
