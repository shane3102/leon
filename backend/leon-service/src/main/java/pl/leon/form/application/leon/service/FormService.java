package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class FormService {

    private final FormRepository formRepository;

    public FormEntity create(FormEntity entity) {
        entity = formRepository.save(entity);
        return entity;
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

    public List<FormEntity> list(){
        return formRepository.findAll();
    }
}
