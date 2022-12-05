package pl.leon.form.application.leon.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${jwt.expiration}")
    private int expirationTime;
    @Value("${jwt.secret}")
    private String secret;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        try {
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            String token = JWT.create()
                    .withSubject(userService.loadUserByUsername(principal.getUsername()).getUsername())
                    .withExpiresAt(Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.systemDefault()).toInstant().toEpochMilli() + expirationTime))
                    .sign(Algorithm.HMAC256(secret));

            response.addHeader("Authorization", "Bearer " + token);
            response.addHeader("Content-type", "application/json");
            response.getWriter().write("{\"token\": \"" + token + "\"}");
        } catch(UsernameNotFoundException ignored) {

        }
    }
}
