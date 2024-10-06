package com.fernando.payments.processor.workers.writers;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import jakarta.transaction.Transactional;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcCreditPaymentProcessorWriterConfig {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    @Transactional
    public ItemWriter<CreditCardPayment> jdbcCursorWriter() {
        return new ItemWriter<CreditCardPayment>() {
            @Override
            public void write(Chunk<? extends CreditCardPayment> items) throws Exception {
                for (CreditCardPayment payment : items) {
                    String insertPaymentSql = "INSERT INTO db_credit_card_payments (id, created_at, aggregate_id, amount, cardcvc, card_expiry, card_number, recipient_document, status, payment_processor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    jdbcTemplate.update(insertPaymentSql,
                            payment.getId(),
                            payment.getCreatedAt(),
                            payment.getAggregateId(),
                            payment.getAmount(),
                            payment.getCardCVC(),
                            payment.getCardExpiry(),
                            payment.getCardNumber(),
                            payment.getRecipientDocument(),
                            payment.getStatus(),
                            payment.getPaymentProcessor().getId()
                    );

                    String insertProcessorSql = "INSERT INTO db_processor (id, created_at, external_id, response, status, processors) VALUES (?, ?, ?, ?, ?, ?)";
                    jdbcTemplate.update(insertProcessorSql,
                            payment.getProcessors().stream().findFirst().get().getId(),
                            payment.getProcessors().stream().findFirst().get().getCreatedAt(),
                            payment.getProcessors().stream().findFirst().get().getExternalId(),
                            payment.getProcessors().stream().findFirst().get().getResponse(),
                            payment.getProcessors().stream().findFirst().get().getStatus(),
                            payment.getProcessors().stream().findFirst().get().getId()
                    );
                }
            }
        };
    }
}
