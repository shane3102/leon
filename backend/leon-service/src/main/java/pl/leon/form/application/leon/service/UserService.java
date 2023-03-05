package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.security.SecurityUtils;
import pl.leon.form.application.leon.mapper.UserMapper;
import pl.leon.form.application.leon.model.request.UserRegistrationRequest;
import pl.leon.form.application.leon.model.response.UserResponse;
import pl.leon.form.application.leon.repository.UserRepository;
import pl.leon.form.application.leon.repository.entities.UserEntity;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final SecurityUtils securityUtils;
    private final UserMapper mapper;
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username);
        // TODO rzucić customowe exceptiony (?)
        if (user == null) throw new UsernameNotFoundException("User with given username not found");
        return user;
    }

    public boolean existsUserByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public UserResponse registerUser(UserRegistrationRequest registrationRequest) {

        UserEntity newUser = UserEntity.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(securityUtils.getPasswordEncoder().encode(registrationRequest.getPassword()))
                .build();

        return mapper.mapToResponse(repository.save(newUser));
    }

    public UserEntity getCurrentlyLoggedUser() {
        UserEntity loggedUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication == null) && !authentication.getPrincipal().equals("anonymousUser")) {
            if (authentication.getPrincipal() instanceof String) {
                loggedUser = repository.findByUsername((String) authentication.getPrincipal());
            } else {
                loggedUser = (UserEntity) authentication.getPrincipal();
            }
        }

        return loggedUser;
    }

}
