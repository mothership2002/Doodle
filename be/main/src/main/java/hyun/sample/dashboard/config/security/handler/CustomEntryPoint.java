package hyun.sample.dashboard.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun.post.dashboard.exception.auth.ExpiredAccessTokenException;
import hyun.post.dashboard.model.common.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final CommonRespHeaderComponent headerComponent;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        headerComponent.addContentTypeResponse(response);
        objectMapper.writeValue(response.getWriter(),
                new CommonResponse<>("Fail Authentication", authException.getMessage()));
    }
}
