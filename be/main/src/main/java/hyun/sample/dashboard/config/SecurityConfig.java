package hyun.sample.dashboard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun.sample.dashboard.config.security.filter.AuthenticationLoginFilter;
import hyun.sample.dashboard.config.security.filter.CustomExceptionFilter;
import hyun.sample.dashboard.config.security.filter.JwtAuthenticationFilter;
import hyun.sample.dashboard.config.security.handler.*;
import hyun.sample.dashboard.config.security.manager.CustomAuthorizationManager;
import hyun.sample.dashboard.config.security.provider.CustomAuthenticationProvider;
import hyun.sample.dashboard.config.security.provider.JwtProvider;
import hyun.sample.dashboard.config.security.provider.SecurityCryptoProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration config;
    private final CustomFailureHandler failureHandler;
    private final CustomSuccessHandler successHandler;
    private final SecurityCryptoProvider securityCryptoProvider;
    private final ObjectMapper objectMapper;
    private final MemberService memberService;
    private final CommonRespHeaderComponent headerComponent;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomAuthorizationManager authorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .anonymous(anonymous -> anonymous.authorities("ANONYMOUS"))
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandler ->
                        exceptionHandler
                                .accessDeniedHandler(customAccessDeniedHandler())
                                .authenticationEntryPoint(customEntryPoint())
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.anyRequest().access(authorizationManager))

                .addFilterBefore(customExceptionFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationLoginFilter authenticationLoginFilter() throws Exception {
        return new AuthenticationLoginFilter(
                authenticationManager(),
                successHandler,
                failureHandler,
                objectMapper,
                securityCryptoProvider);
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(memberService, passwordEncoder);
    }

    @Bean
    public AuthenticationEntryPoint customEntryPoint() {
        return new CustomEntryPoint(objectMapper, headerComponent);
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler(objectMapper, headerComponent);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtProvider);
    }

    @Bean
    public CustomExceptionFilter customExceptionFilter() {
        return new CustomExceptionFilter(objectMapper, headerComponent);
    }

    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter() {
        return new AnonymousAuthenticationFilter("key", "anonymousUser",
                AuthorityUtils.createAuthorityList("ANONYMOUS"));
    }

}
