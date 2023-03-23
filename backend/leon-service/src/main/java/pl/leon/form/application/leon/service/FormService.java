package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.not_found.concrete.FormNotFound;
import pl.leon.form.application.leon.core.exceptions.unauthorized.concrete.FormsPrivateToUser;
import pl.leon.form.application.leon.core.exceptions.unauthorized.concrete.StatisticsNotAvailableToUser;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.request.forms.FormCreateRequest;
import pl.leon.form.application.leon.model.response.forms.FormResponse;
import pl.leon.form.application.leon.model.response.forms.FormSnippetResponse;
import pl.leon.form.application.leon.model.response.forms.FormStatisticsResponse;
import pl.leon.form.application.leon.repository.question.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.question.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.question.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.question.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.question.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.question.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;

import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class FormService {

    private final FormMapper mapper;
    private final FormRepository formRepository;
    private final UserService userService;

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

        FormEntity formTmp = formRepository.save(new FormEntity());

        FormEntity formEntity = mapper.mapCreateRequestToEntity(request);

        formEntity.setFormForEachQuestion(formTmp);

        formEntity.setDropdownQuestions(dropdownQuestionRepository.saveAll(formEntity.getDropdownQuestions()));
        formEntity.setLineScaleQuestions(lineScaleQuestionRepository.saveAll(formEntity.getLineScaleQuestions()));
        formEntity.setLongAnswerQuestions(longAnswerQuestionRepository.saveAll(formEntity.getLongAnswerQuestions()));
        formEntity.setMultipleChoiceQuestions(multipleChoiceQuestionRepository.saveAll(formEntity.getMultipleChoiceQuestions()));
        formEntity.setShortAnswerQuestions(shortAnswerQuestionRepository.saveAll(formEntity.getShortAnswerQuestions()));
        formEntity.setSingleChoiceQuestions(singleChoiceQuestionRepository.saveAll(formEntity.getSingleChoiceQuestions()));

        formEntity.setId(formTmp.getId());
        formEntity.setDateAdded(LocalDate.now());
        formEntity = formRepository.save(formEntity);
        FormResponse response = mapper.mapToResponse(formEntity);
        log.info("addNewForm({}) = {}", request, response);
        return response;
    }

    public Page<FormSnippetResponse> list(Pageable pageable, String username) {
        log.info("list({}, {})", pageable, username);
        Page<FormSnippetResponse> response;
        if (username != null) {
            if (!userService.getCurrentlyLoggedUser().getUsername().equals(username)) {
                throw new FormsPrivateToUser();
            }
            response = formRepository.findAllByUserUsername(username, pageable).map(mapper::mapToSnippetResponse);
        } else {
            response = formRepository.findAll(pageable).map(mapper::mapToSnippetResponse);
        }

        log.info("list({}, {}) = {}", pageable, username, response);
        return response;
    }

    public FormResponse readConcreteForm(Long id) {
        log.info("readConcreteForm({})", id);
        FormEntity formEntity = formRepository.findById(id).orElseThrow(FormNotFound::new);
        FormResponse formResponse = mapper.mapToResponse(formEntity);
        log.info("readConcreteForm({}) = {}", id, formResponse);
        return formResponse;
    }

    public FormStatisticsResponse readConcreteFormWithStatistics(Long id) {
        log.info("readConcreteFormWithStatistics({})", id);

        FormEntity formEntity = formRepository.findById(id).orElseThrow(FormNotFound::new);

        if (!formEntity.isResultsAvailableForEveryone() && !Objects.equals(formEntity.getUser(), userService.getCurrentlyLoggedUser())) {
            throw new StatisticsNotAvailableToUser();
        }

        FormStatisticsResponse formStatisticsResponse = mapper.mapToStatisticsResponse(formEntity);
        log.info("readConcreteFormWithStatistics({}) = {}", id, formStatisticsResponse);
        return formStatisticsResponse;
    }
}
