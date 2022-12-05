package pl.leon.form.application.leon.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.CouldNotParseLoginRequestAttempt;
import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.concrete.OtherExceptionWhileExtractingToken;
import pl.leon.form.application.leon.model.request.UserLoginAttemptRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            UserLoginAttemptRequest authenticationRequest = objectMapper.readValue(sb.toString(), UserLoginAttemptRequest.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            );
            setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new CouldNotParseLoginRequestAttempt();
        } catch (Exception e) {
            throw new OtherExceptionWhileExtractingToken(e.getMessage());
        }
    }
}
