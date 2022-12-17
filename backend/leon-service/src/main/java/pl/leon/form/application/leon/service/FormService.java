package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.request.forms.FormCreateRequest;
import pl.leon.form.application.leon.model.response.forms.FormResponse;
import pl.leon.form.application.leon.model.response.forms.FormSnippetResponse;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;

    public FormResponse addNewForm(FormCreateRequest request) {
        log.info("addNewForm({})", request);
        FormEntity formEntity = mapper.mapCreateRequestToEntity(request);
        formEntity = formRepository.save(formEntity);
        FormResponse response = mapper.mapToResponse(formEntity);
        log.info("addNewForm({}) = {}", request, response);
        return response;
    }

    public List<FormSnippetResponse> list() {
        return mapper.mapToSnippetResponses(formRepository.findAll());
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
}
