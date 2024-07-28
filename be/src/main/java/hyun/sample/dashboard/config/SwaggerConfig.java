package hyun.sample.dashboard.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${version}") String appVersion) {


        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Board API Docs")
                        .version(appVersion)
                        .description("spring boot 3을 이용한 Board API Docs"));
    }
}
