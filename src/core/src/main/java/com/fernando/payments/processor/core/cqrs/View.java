package com.fernando.payments.processor.core.cqrs;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class View {
    protected View() {
    }
}