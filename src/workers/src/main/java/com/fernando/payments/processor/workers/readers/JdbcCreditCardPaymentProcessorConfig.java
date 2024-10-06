package com.fernando.payments.processor.workers.readers;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.core.domain.enums.PaymentStatus;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Configuration
public class JdbcCreditCardPaymentProcessorConfig {

    @Bean
    public JdbcCursorItemReader<CreditCardPayment> jdbcCreditPaymentProcessorReader(
            @Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<CreditCardPayment>()
                .name("jdbcCreditPaymentProcessorReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM postgres.public.db_credit_card_payments WHERE status = '8'")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<CreditCardPayment> rowMapper() {
        return new BeanPropertyRowMapper<CreditCardPayment>() {
            @Override
            public CreditCardPayment mapRow(ResultSet rs, int rowNumber) throws SQLException {

                var payment =  clienteRowMapper(rs);

                return payment;
            }

            private CreditCardPayment clienteRowMapper(ResultSet rs) throws SQLException {

                return CreditCardPayment.builder()
                        .id(rs.getLong("id"))
                        .createdAt(rs.getTimestamp("created_at").toInstant())
                        .aggregateId((UUID) rs.getObject("aggregate_id"))
                        .amount(rs.getBigDecimal("amount"))
                        .cardCVC(rs.getString("cardcvc"))
                        .cardExpiry(rs.getString("card_expiry"))
                        .cardNumber(rs.getString("card_number"))
                        .recipientDocument(rs.getString("recipient_document"))
                        .status(PaymentStatus.fromCode(rs.getInt("status")))
                        .build();
            }
        };
    }
}
