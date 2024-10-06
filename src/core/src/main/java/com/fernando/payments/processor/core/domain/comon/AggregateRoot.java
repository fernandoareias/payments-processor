package com.fernando.payments.processor.core.domain.comon;

import jakarta.persistence.MappedSuperclass;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@SuperBuilder
public abstract class AggregateRoot extends Entity {

}
