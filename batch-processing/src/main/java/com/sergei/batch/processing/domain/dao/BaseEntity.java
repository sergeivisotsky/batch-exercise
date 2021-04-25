package com.sergei.batch.processing.domain.dao;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * Base entity holder.
 *
 * @author Sergei Visotsky
 * @since 1.0
 */
@MappedSuperclass
public class BaseEntity implements EntityId, Serializable {

    private static final long serialVersionUID = 7778891407983648464L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "master_seq_gen_name", sequenceName = "master_seq")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
