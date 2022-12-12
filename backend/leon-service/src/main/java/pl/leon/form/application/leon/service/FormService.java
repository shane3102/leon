package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.request.FormRequest;
import pl.leon.form.application.leon.model.response.FormResponse;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.UserEntity;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;

    private final UserService userService;

    public FormResponse create(FormRequest request) {
        log.info("create({})", request);
        FormEntity formEntity = mapper.mapToEntity(request);
        // TODO w mapperze to przypisanie
        System.out.println(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        formEntity.setUser((UserEntity) userService.loadUserByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        formEntity = formRepository.save(formEntity);
        FormResponse response = mapper.mapToResponse(formEntity);
        log.info("create({}) = {}", request, response);
        return response;
    }

    public FormEntity read(Long id) {
        return formRepository.findById(id).orElse(null);
    }

    public FormEntity update(Long id, FormEntity formEntity) {
        formEntity.setId(id);
        return formRepository.save(formEntity);
    }

    public void delete(Long id) {
        if (formRepository.existsById(id)) {
            formRepository.deleteById(id);
        }
    }

    public List<FormEntity> list() {
        return formRepository.findAll();
    }
}
