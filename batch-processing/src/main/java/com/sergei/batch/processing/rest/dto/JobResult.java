package com.sergei.batch.processing.rest.dto;

import java.time.LocalDateTime;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
public class JobResult {

    private final LocalDateTime timestamp;
    private final JobStatus status;

    public JobResult(LocalDateTime timestamp, JobStatus status) {
        this.timestamp = timestamp;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public JobStatus getStatus() {
        return status;
    }
}
