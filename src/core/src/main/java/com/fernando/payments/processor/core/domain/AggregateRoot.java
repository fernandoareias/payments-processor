package com.fernando.payments.processor.core.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@MappedSuperclass
@SuperBuilder
public abstract class AggregateRoot extends Entity {

}
