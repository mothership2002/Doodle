package hyun.dashboard.publisher.config;

import lombok.Getter;
import lombok.Setter;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

@Getter
@Setter
@Configuration
@ConfigurationProperties("spring.h2")
public class H2Config {

    private String port;
    private Server h2Server;

    @EventListener(ContextRefreshedEvent.class)
    public void init() throws SQLException {
        h2Server = Server.createWebServer("-webPort", port, "-tcpAllowOthers").start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        h2Server.stop();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

}
