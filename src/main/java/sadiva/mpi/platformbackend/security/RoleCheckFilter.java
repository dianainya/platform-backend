package sadiva.mpi.platformbackend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleCheckFilter implements HandlerInterceptor {
    private static final String HTTP_BEARER = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith(HTTP_BEARER)) {
            token = token.substring(HTTP_BEARER.length());

            }
        return true;
    }
}
