package com.fernando.payments.processor.webapi.apresentation.dtos.requests;

import com.fernando.payments.processor.webapi.apresentation.dtos.common.BaseResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Classe que representa a requisição para processar um pagamento com cartão de crédito.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessCreditCardPaymentRequest {

    @NotBlank(message = "Card number is required")
    @Size(min = 13, max = 19, message = "Card number must be between 13 and 19 digits")
    @Pattern(regexp = "^\\d+$", message = "Card number must be numeric")
    private String cardNumber;

    @NotBlank(message = "Card expiry is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Card expiry must be in the format MM/YY")
    private String cardExpiry;   // Format: MM/YY

    @NotBlank(message = "Card CVC is required")
    @Size(min = 3, max = 4, message = "Card CVC must be 3 or 4 digits")
    @Pattern(regexp = "^\\d+$", message = "Card CVC must be numeric")
    private String cardCVC;      // Card Verification Code

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Recipient document is required")
    private String recipientDocument;
}
