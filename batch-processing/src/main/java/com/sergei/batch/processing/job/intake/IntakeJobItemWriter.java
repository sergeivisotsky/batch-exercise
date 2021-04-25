package com.sergei.batch.processing.job.intake;

import java.util.List;

import com.sergei.batch.processing.domain.dao.Customer;
import com.sergei.batch.processing.domain.dao.dao.CustomerJpaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Component(value = "intakeWriter")
public class IntakeJobItemWriter implements ItemWriter<List<Customer>> {

    private final CustomerJpaRepository customerJpaRepository;

    public IntakeJobItemWriter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public void write(List<? extends List<Customer>> list) {
        customerJpaRepository.saveAll(list.get(0));
    }
}
