package com.fernando.payments.processor.workers.readers;

import com.fernando.payments.processor.workers.dominio.creditcards.CreditCardPayment;
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

@Configuration
public class JdbcCreditCardPaymentProcessorConfig {

    @Bean
    public JdbcCursorItemReader<CreditCardPayment> jdbcCreditPaymentProcessorReader(
            @Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<CreditCardPayment>()
                .name("jdbcCreditPaymentProcessorReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM db_credit_card_payments WHERE status = '8'")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<CreditCardPayment> rowMapper() {
        return new BeanPropertyRowMapper<CreditCardPayment>() {
            @Override
            public CreditCardPayment mapRow(ResultSet rs, int rowNumber) throws SQLException {
                if (rs.getRow() == 11) {
                    String email = rs.getString("email");
                    // Log the invalid client information
                    System.err.println(String.format("Encerrando a execução - Cliente inválido: %s", email));
                    throw new SQLException(String.format("Cliente inválido: %s", email));
                }
                return clienteRowMapper(rs);
            }

            private CreditCardPayment clienteRowMapper(ResultSet rs) throws SQLException {
                return CreditCardPayment.builder()
                        .build();
//                CreditCardPayment creditCardPayment = new CreditCardPayment();
//                cliente.setNome(rs.getString("nome"));
//                cliente.setSobrenome(rs.getString("sobrenome"));
//                cliente.setIdade(rs.getString("idade"));
//                cliente.setEmail(rs.getString("email"));
//

            }
        };
    }
}
