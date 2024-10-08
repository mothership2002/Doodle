package hyun.sample.dashboard.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaConfig {

    private final EntityManagerFactory emf;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(emf.createEntityManager());
    }

}
