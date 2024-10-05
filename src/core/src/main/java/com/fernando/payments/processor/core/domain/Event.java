package com.fernando.payments.processor.core.domain;

import com.fernando.payments.processor.core.common.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event extends Message {

    private Instant createdAt = Instant.now();



}
