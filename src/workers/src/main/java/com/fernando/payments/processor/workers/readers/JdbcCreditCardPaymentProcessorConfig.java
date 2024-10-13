package com.fernando.payments.processor.workers.readers;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.core.domain.enums.PaymentStatus;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class JdbcCreditCardPaymentProcessorConfig {

    @Bean
    public JdbcPagingItemReader<CreditCardPayment> jdbcCreditPaymentProcessorReader(
            @Qualifier("appDataSource") DataSource dataSource) {

        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);


        return new JdbcPagingItemReaderBuilder<CreditCardPayment>()
                .name("jdbcCreditPaymentProcessorReader")
                .dataSource(dataSource)
                .selectClause("SELECT *")
                .fromClause("FROM postgres.public.db_credit_card_payments")
                .whereClause("WHERE status = '8'")
                .sortKeys(sortKeys)
                .rowMapper(rowMapper())
                .pageSize(100)
                .build();
    }

    private RowMapper<CreditCardPayment> rowMapper() {
        return new BeanPropertyRowMapper<CreditCardPayment>() {
            @Override
            public CreditCardPayment mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return clienteRowMapper(rs);
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
