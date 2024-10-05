package com.fernando.payments.processor.webapi.apresentation.controllers;

import com.fernando.payments.processor.core.cqrs.View;
import com.fernando.payments.processor.core.cqrs.interfaces.Mediator;
import com.fernando.payments.processor.webapi.application.commands.ProcessCreditCardPaymentCommand;
import com.fernando.payments.processor.webapi.application.commands.views.ProcessCreditCardPaymentCommandView;
import com.fernando.payments.processor.webapi.apresentation.dtos.common.BaseResponse;
import com.fernando.payments.processor.webapi.apresentation.dtos.requests.ProcessCreditCardPaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/payments")
public class PaymentController {

    @Autowired
    private Mediator mediator;

    @PostMapping("/credit-cards")
    public ResponseEntity<BaseResponse<ProcessCreditCardPaymentCommandView>> receiveCreditCardPaymentOrder(
            @RequestBody ProcessCreditCardPaymentRequest request) {

        var command = ProcessCreditCardPaymentCommand.builder()
                .amount(request.getAmount())
                .cardCVC(request.getCardCVC())
                .cardExpiry(request.getCardExpiry())
                .cardNumber(request.getCardNumber())
                .aggregateId(UUID.randomUUID())
                .recipientDocument(request.getRecipientDocument())
                .build();

        ProcessCreditCardPaymentCommandView response = (ProcessCreditCardPaymentCommandView)mediator.send(command);

        return ResponseEntity.accepted().body(new BaseResponse<>(HttpStatus.ACCEPTED.value(), "Payment order received", response) );
    }


}
