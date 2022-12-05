package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.leon.form.application.leon.model.request.UserRegistrationRequest;
import pl.leon.form.application.leon.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        return ResponseEntity.ok(service.registerUser(registrationRequest));
    }

    @GetMapping
    public ResponseEntity<?> getLoggedUser() {
        return ResponseEntity.ok(service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
