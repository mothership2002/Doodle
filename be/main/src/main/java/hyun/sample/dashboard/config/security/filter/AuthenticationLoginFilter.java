package hyun.sample.dashboard.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun.sample.dashboard.config.security.handler.CustomFailureHandler;
import hyun.sample.dashboard.config.security.handler.CustomSuccessHandler;
import hyun.sample.dashboard.config.security.provider.SecurityCryptoProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;


public class AuthenticationLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final SecurityCryptoProvider securityCryptoProvider;

    public AuthenticationLoginFilter(AuthenticationManager authenticationManager,
                                     CustomSuccessHandler successHandler,
                                     CustomFailureHandler failureHandler,
                                     ObjectMapper objectMapper,
                                     SecurityCryptoProvider securityCryptoProvider) {

        super(authenticationManager);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login"));
        super.setAuthenticationSuccessHandler(successHandler);
        super.setAuthenticationFailureHandler(failureHandler);
        this.securityCryptoProvider = securityCryptoProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException {
        MemberLoginDto member = null;
        try {
            member = objectMapper.readValue(req.getInputStream(), MemberLoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 암호화 풀 이유가 없지 않나?
        // TODO 이 부분 실수함
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(new Member(member.getAccount(), member.getPassword(),null),
                null);
        return getAuthenticationManager().authenticate(token);
    }
}
