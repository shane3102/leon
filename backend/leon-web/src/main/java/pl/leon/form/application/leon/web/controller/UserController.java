package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.leon.form.application.leon.model.request.UserRegistrationRequest;
import pl.leon.form.application.leon.service.UserService;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @GetMapping("/exists")
    public ResponseEntity<?> existsUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(service.existsUserByUsername(username));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        return ResponseEntity.ok(service.registerUser(registrationRequest));
    }

    @GetMapping
    public ResponseEntity<?> getLoggedUser(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
