package pl.leon.form.application.leon.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
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

import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.COULD_NOT_PARSE_LOGIN_REQUEST_ATTEMPT;
import static pl.leon.form.application.leon.core.exceptions.ExceptionMessages.OTHER_EXCEPTION_WHILE_EXTRACTING_TOKEN;

public class JsonObjectAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
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
            // TODO ogarnąć by był ładny message bo tera niema
        } catch (IOException e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), COULD_NOT_PARSE_LOGIN_REQUEST_ATTEMPT.getMessage());
        } catch (Exception e) {
            response.sendError(HttpStatus.I_AM_A_TEAPOT.value(), OTHER_EXCEPTION_WHILE_EXTRACTING_TOKEN.getMessage());
        }
        return null;
    }
}
