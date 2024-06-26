package pl.leon.form.application.leon.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.leon.form.application.leon.service.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private final UserService userService;
    private final String secret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService, String secret) {
        super(authenticationManager);
        this.userService = userService;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken auth = getAuthentication(request);
        if (auth == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token == null || !token.startsWith(TOKEN_PREFIX) || token.contains("undefined")) {
                return null;
            }

            String username = null;
            try {
                username = JWT.require(Algorithm.HMAC256(secret))
                        .build()
                        .verify(token.replace(TOKEN_PREFIX, ""))
                        .getSubject();
            } catch (JWTDecodeException | TokenExpiredException | AlgorithmMismatchException ignored) {
            }
            if (username == null) return null;
            UserDetails userDetails = userService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        } catch (UsernameNotFoundException ignored) {
            return null;
        }
    }
}
