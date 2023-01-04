package pl.leon.form.application.leon.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.repository.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionDisablingScheduler {

    private final FormRepository formRepository;

    private final DropdownQuestionRepository dropdownQuestionRepository;
    private final LineScaleQuestionRepository lineScaleQuestionRepository;
    private final LongAnswerQuestionRepository longAnswerQuestionRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    private final SingleChoiceQuestionRepository singleChoiceQuestionRepository;

    @Scheduled(cron = "0 0 0 * * *")
    private void disableQuestions() {
        log.info("Disabling expired questions...");
        List<FormEntity> forms = formRepository.findAll();

        forms.forEach(form -> {
            if (LocalDate.now().isEqual(form.getDateTo()) && form.isDisableQuestionsAfterDateTo()) {
                dropdownQuestionRepository.saveAll(
                        form.getDropdownQuestions().stream()
                                .peek(question -> question.setDisabled(true))
                                .collect(Collectors.toList()));

                lineScaleQuestionRepository.saveAll(
                        form.getLineScaleQuestions().stream()
                                .peek(question -> question.setDisabled(true))
                                .collect(Collectors.toList()));

                longAnswerQuestionRepository.saveAll(
                        form.getLongAnswerQuestions().stream()
                                .peek(question -> question.setDisabled(true))
                                .collect(Collectors.toList()));

                multipleChoiceQuestionRepository.saveAll(
                        form.getMultipleChoiceQuestions().stream()
                                .peek(question -> question.setDisabled(true))
                                .collect(Collectors.toList()));

                shortAnswerQuestionRepository.saveAll(
                        form.getShortAnswerQuestions().stream()
                                .peek(question -> question.setDisabled(true))
                                .collect(Collectors.toList()));

                singleChoiceQuestionRepository.saveAll(
                        form.getSingleChoiceQuestions().stream()
                                .peek(question -> question.setDisabled(true))
                                .collect(Collectors.toList()));
            }
            log.info("Disabled questions from form: id: {}, subject: {}", form.getId(), form.getSubject());
        });
        log.info("Disabling expired questions completed.");
    }

}
