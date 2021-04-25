package com.sergei.batch.processing.rest;

import com.sergei.batch.processing.job.JobExecutionHandler;
import com.sergei.batch.processing.rest.dto.JobRequest;
import org.springframework.batch.core.JobExecution;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class BatchRestController {

    private final JobExecutionHandler jobHandler;

    public BatchRestController(JobExecutionHandler jobHandler) {
        this.jobHandler = jobHandler;
    }

    @PostMapping("/job/start")
    public Mono<JobExecution> executeBatch(JobRequest request) {
        return jobHandler.determineAndExecuteJob(request);
    }
}
