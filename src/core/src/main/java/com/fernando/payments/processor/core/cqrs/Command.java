package com.fernando.payments.processor.core.cqrs;

import com.fernando.payments.processor.core.common.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public abstract class Command<TResponse>   extends Message {
}
