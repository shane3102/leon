package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.not_found.concrete.FormNotFound;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.request.forms.FormCreateRequest;
import pl.leon.form.application.leon.model.response.forms.FormResponse;
import pl.leon.form.application.leon.model.response.forms.FormSnippetResponse;
import pl.leon.form.application.leon.repository.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;

    private final DropdownQuestionRepository dropdownQuestionRepository;
    private final LineScaleQuestionRepository lineScaleQuestionRepository;
    private final LongAnswerQuestionRepository longAnswerQuestionRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    private final SingleChoiceQuestionRepository singleChoiceQuestionRepository;

    public FormResponse addNewForm(FormCreateRequest request) {
        log.info("addNewForm({})", request);
        request.getQuestions()
                .forEach(question -> question.setDisabledFormRandomFormGenerating(request.isDisableQuestionsFromRandomGeneratedForms()));

        FormEntity formEntity = mapper.mapCreateRequestToEntity(request);

        formEntity.setDropdownQuestions(dropdownQuestionRepository.saveAll(formEntity.getDropdownQuestions()));
        formEntity.setLineScaleQuestions(lineScaleQuestionRepository.saveAll(formEntity.getLineScaleQuestions()));
        formEntity.setLongAnswerQuestions(longAnswerQuestionRepository.saveAll(formEntity.getLongAnswerQuestions()));
        formEntity.setMultipleChoiceQuestions(multipleChoiceQuestionRepository.saveAll(formEntity.getMultipleChoiceQuestions()));
        formEntity.setShortAnswerQuestions(shortAnswerQuestionRepository.saveAll(formEntity.getShortAnswerQuestions()));
        formEntity.setSingleChoiceQuestions(singleChoiceQuestionRepository.saveAll(formEntity.getSingleChoiceQuestions()));

        formEntity = formRepository.save(formEntity);
        FormResponse response = mapper.mapToResponse(formEntity);
        log.info("addNewForm({}) = {}", request, response);
        return response;
    }

    public List<FormSnippetResponse> list() {
        return mapper.mapToSnippetResponses(formRepository.findAll());
    }

    public FormResponse readConcreteForm(Long id) {
        log.info("readConcreteForm({})", id);
        FormEntity formEntity = formRepository.findById(id).orElseThrow(FormNotFound::new);
        FormResponse formResponse = mapper.mapToResponse(formEntity);
        log.info("readConcreteForm({}) = {}", id, formResponse);
        return formResponse;
    }
}
