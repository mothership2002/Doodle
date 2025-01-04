package hyun.dashboard.publisher.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.TransactionExecution;
import org.springframework.transaction.TransactionExecutionListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collection;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class TransactionConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager() {
        R2dbcTransactionManager tx = new R2dbcTransactionManager(connectionFactory);
        tx.addListener(new CustomTxListener());
        return tx;
    }

    public static class CustomTxListener implements TransactionExecutionListener {
        
        @Override
        public void afterCommit(TransactionExecution transaction, Throwable commitFailure) {
            TransactionExecutionListener.super.afterCommit(transaction, commitFailure);
        }
    }
}
