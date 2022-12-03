package pl.leon.form.application.leon.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginAttemptRequest {
    private String username;
    private String password;
}
