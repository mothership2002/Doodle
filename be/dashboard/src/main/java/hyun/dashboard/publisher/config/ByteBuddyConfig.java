package hyun.dashboard.publisher.config;

import net.bytebuddy.ByteBuddy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ByteBuddyConfig {

    @Bean
    public ByteBuddy byteBuddy() {
        return new ByteBuddy();
    }
}
