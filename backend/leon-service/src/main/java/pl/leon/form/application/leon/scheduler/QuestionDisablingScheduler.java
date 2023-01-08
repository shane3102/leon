package pl.leon.form.application.leon.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class QuestionDisablingScheduler {

    private final FormRepository formRepository;

    @Autowired
    private final List<QuestionServiceInterface> questionServices;

    @Scheduled(cron = "0 0 0 * * *")
    public void disableExpiredQuestionsAndForms() {
        log.info("Disabling expired questions and forms...");

        Stream<FormEntity> formsEnding = (Stream<FormEntity>) questionServices.stream().flatMap(QuestionServiceInterface::disableQuestions);

        formsEnding.forEach(form -> {
            form.setDisabled(true);
            formRepository.save(form);
        });

        log.info("Disabling expired questions and forms completed.");
    }

}
