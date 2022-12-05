package pl.leon.form.application.leon.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import pl.leon.form.application.leon.service.UserService;

@Configuration
@RequiredArgsConstructor
public class JwtSecurityConfiguration {

    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    private AuthenticationManager authenticationManager;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests((auth) -> {
                    try {
                        auth.antMatchers("/api/user").authenticated()
                                .anyRequest().permitAll()
                                .and()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilter(authenticationFilter())
                                .addFilter(new JwtAuthorizationFilter(authenticationManager, userService, secret))
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.I_AM_A_TEAPOT));

                    } catch (Exception e) {
                        // TODO custom exception
                        throw new RuntimeException();
                    }
                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
