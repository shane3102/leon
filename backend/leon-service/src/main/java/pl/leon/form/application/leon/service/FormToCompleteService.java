package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.model.response.FormToCompleteResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.service.question.QuestionServiceInterface;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class FormToCompleteService {
    List<QuestionServiceInterface> questionServices;

    public FormToCompleteResponse generateFormToComplete(Short questionsToGenerate) {

        log.info("generateFormToComplete({})", questionsToGenerate);
        if (questionsToGenerate < questionServices.size()) {
            //TODO custom exception + to gdzieś do walidacji bardziej
            throw new RuntimeException();
        }

        Short questionsForEachType = (short) (questionsToGenerate / questionServices.size());

        List<QuestionResponse> questions = questionServices
                .stream().map(
                        service -> (List<QuestionResponse>) service.getRandomQuestions(questionsForEachType)
                )
                .flatMap(Collection::stream).collect(Collectors.toList());

        FormToCompleteResponse response = FormToCompleteResponse.builder().questions(questions).build();
        log.info("generateFormToComplete({}) = {}", questionsToGenerate, response);

        return response;

    }
}
