package com.fernando.payments.processor.core.domain.comon;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
public abstract class Entity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Entity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Instant createdAt = Instant.now();
}
