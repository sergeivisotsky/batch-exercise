package com.sergei.batch.processing.domain.dao.dao;

import com.sergei.batch.processing.domain.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

}
