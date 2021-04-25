package com.sergei.batch.processing.config;

import java.util.List;

import com.sergei.batch.processing.domain.dao.Customer;
import com.sergei.batch.processing.job.intake.IntakeJobItemProcessor;
import com.sergei.batch.processing.job.intake.IntakeJobItemWriter;
import com.sergei.batch.xsd.dto.CustomersOrders;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilder;
    private final StepBuilderFactory stepBuilder;

    public BatchConfig(JobBuilderFactory jobBuilder, StepBuilderFactory stepBuilder) {
        this.jobBuilder = jobBuilder;
        this.stepBuilder = stepBuilder;
    }

    @Bean
    public Job intakeJob(Step intakeStep) {
        return jobBuilder.get("batchIntake")
                .start(intakeStep)
                .build();
    }

    @Bean
    public Step intakeStep(ItemReader<CustomersOrders> intakeReader,
                           IntakeJobItemProcessor intakeProcessor,
                           IntakeJobItemWriter intakeWriter) {
        return stepBuilder.get("intakeStep")
                .<CustomersOrders, List<Customer>>chunk(10)
                .reader(intakeReader)
                .processor(intakeProcessor)
                .writer(intakeWriter)
                .build();
    }
}
