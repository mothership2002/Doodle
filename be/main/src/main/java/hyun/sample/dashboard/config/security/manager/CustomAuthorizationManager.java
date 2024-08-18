package hyun.sample.dashboard.config.security.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final AuthorizationDecision DENY = new AuthorizationDecision(false);
    private static final AuthorizationDecision PERMIT = new AuthorizationDecision(true);
    private final Map<String, List<RequestMatcher>> resourceMap = new HashMap<>();

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        // 2차원배열로 포문이 돌아가는 단점 있음. -> 시간복잡도 증가
        // 리소시스 관련으로 롤의 역할을 줄여놓음 -> 1차원배열로 낮춤
        for (GrantedAuthority auth : authorities) {
            for (RequestMatcher matcher : resourceMap.get(auth.getAuthority())) {
                if (matcher.matcher(context.getRequest()).isMatch()) {
                    return PERMIT;
                }
            }
        }
        return DENY;
    }
}
