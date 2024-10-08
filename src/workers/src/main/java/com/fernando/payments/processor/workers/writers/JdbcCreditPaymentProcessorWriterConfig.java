package com.fernando.payments.processor.workers.writers;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import jakarta.transaction.Transactional;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

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
                    String updatePaymentSql = "UPDATE db_credit_card_payments SET amount = ?, cardcvc = ?, card_expiry = ?, card_number = ?, recipient_document = ?, status = ? WHERE id = ?";

                    jdbcTemplate.update(updatePaymentSql,
                            payment.getAmount(),
                            payment.getCardCVC(),
                            payment.getCardExpiry(),
                            payment.getCardNumber(),
                            payment.getRecipientDocument(),
                            payment.getStatus().getCode(),
                            payment.getId()
                    );



                    try {
                        String insertProcessorSql = "INSERT INTO db_processor (id, created_at, response, status, processors) VALUES (DEFAULT, ?, ?, ?, ?)";

                        var firstProcessor = payment.getProcessors().stream().findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Nenhum processor encontrado"));

                        Instant createdAt = firstProcessor.getCreatedAt();
                        String response = firstProcessor.getResponse();
                        int status = firstProcessor.getStatus().getCode();
                        var paymentId = payment.getId();

                        Timestamp timestamp = Timestamp.from(createdAt);

                        jdbcTemplate.update("INSERT INTO db_processor (id, created_at, response, status, processors) VALUES (?, ?, ?, ?, ?)",
                                UUID.randomUUID().toString(), Timestamp.from(createdAt), response, status, paymentId);

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };
    }
}
