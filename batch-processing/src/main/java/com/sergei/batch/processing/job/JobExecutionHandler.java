package com.sergei.batch.processing.job;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.sergei.batch.processing.rest.dto.JobRequest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.sergei.batch.processing.job.JobConstants.INTAKE_JOB;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Component
public class JobExecutionHandler {

    private final ApplicationContext context;
    private final JobLauncher jobLauncher;

    public JobExecutionHandler(ApplicationContext context, JobLauncher jobLauncher) {
        this.context = context;
        this.jobLauncher = jobLauncher;
    }

    public Mono<JobExecution> determineAndExecuteJob(JobRequest request) {
        if (INTAKE_JOB.equals(request.getJobName())) {
            Job intakeJob = (Job) context.getBean("intakeJob");
            try {
                return Mono.just(jobLauncher.run(intakeJob, transformParams(request)));
            } catch (JobExecutionAlreadyRunningException | JobRestartException |
                    JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                return Mono.error(e);
            }
        }
        return Mono.empty();
    }

    private JobParameters transformParams(JobRequest request) {
        if (request.getParameters() == null) {
            return new JobParameters();
        }
        JsonObject requestParams = request.getParameters();

        Map<String, JobParameter> params = new HashMap<>();
        for (String key : requestParams.keySet()) {
            String param = requestParams.get(key)
                    .toString()
                    .replaceAll("\"", "");
            params.put(key, new JobParameter(param));
        }

        return new JobParameters(params);
    }
}
