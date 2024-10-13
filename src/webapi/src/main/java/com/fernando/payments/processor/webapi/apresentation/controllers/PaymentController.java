package com.fernando.payments.processor.webapi.apresentation.controllers;

import com.fernando.payments.processor.core.cqrs.View;
import com.fernando.payments.processor.core.cqrs.interfaces.Mediator;
import com.fernando.payments.processor.webapi.application.commands.ProcessCreditCardPaymentCommand;
import com.fernando.payments.processor.webapi.application.commands.views.ProcessCreditCardPaymentCommandView;
import com.fernando.payments.processor.webapi.apresentation.dtos.common.BaseResponse;
import com.fernando.payments.processor.webapi.apresentation.dtos.requests.ProcessCreditCardPaymentRequest;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/payments")
public class PaymentController {

    @Autowired
    private Mediator mediator;

//    @ApiOperation("Lista todas as tarefas")
//    @ApiResponses({
//            @ApiResponse(responseCode = 200, message = "Sucesso", response = ResponseEntity.class),
//            @ApiResponse(code = 204, message = "Sem conteúdo"),
//            @ApiResponse(code = 500, message = "Erro interno no servidor")})
    @PostMapping("/credit-cards")
    @ResponseStatus(HttpStatus.ACCEPTED)
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
