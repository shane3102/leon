package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.core.exceptions.bad_request.concrete.TooManyQuestionsToGenerate;
import pl.leon.form.application.leon.core.exceptions.i_am_a_teapot.concrete.ThereIsNoQuestionService;
import pl.leon.form.application.leon.model.response.forms.FormToCompleteResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.service.question.interfaces.QuestionServiceInterface;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class FormToCompleteService {
    private final List<QuestionServiceInterface> questionServices;

    public List<FormToCompleteResponse> generateFormsToComplete(Short formCount, Short questionToGenerateFOrEachFormCount) {
        log.info("generateFourFormsToComplete({})", questionToGenerateFOrEachFormCount);

        List<FormToCompleteResponse> resultForms = new ArrayList<>();

        Map<QuestionServiceInterface, List<Long>> usedQuestionsIdsForEachQuestionType = new HashMap<>();

        for (int i = 0; i < formCount; i++) {
            List<QuestionResponse> questions = questionServiceAndQuestionsToGenerateMap(questionToGenerateFOrEachFormCount)
                    .entrySet()
                    .stream().map(serviceQuestionCountEntry -> getQuestionsAndTemporarilyMarkAsUsed(serviceQuestionCountEntry, usedQuestionsIdsForEachQuestionType))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            FormToCompleteResponse singleForm = FormToCompleteResponse.builder().questions(questions).build();

            resultForms.add(singleForm);
        }

        log.info("generateFourFormsToComplete({}) = {}", questionToGenerateFOrEachFormCount, resultForms);
        return resultForms;
    }

    public FormToCompleteResponse generateFormToComplete(Short questionToGenerateCount) {

        log.info("generateFormToComplete({})", questionToGenerateCount);

        List<QuestionResponse> questions = questionServiceAndQuestionsToGenerateMap(questionToGenerateCount)
                .entrySet()
                .stream().map(
                        serviceQuestionCountEntry -> (List<QuestionResponse>) serviceQuestionCountEntry.getKey().getQuestionsWithMinimumCount(serviceQuestionCountEntry.getValue())
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        FormToCompleteResponse response = FormToCompleteResponse.builder().questions(questions).build();
        log.info("generateFormToComplete({}) = {}", questionToGenerateCount, response);

        return response;

    }

    private Map<QuestionServiceInterface, Short> questionServiceAndQuestionsToGenerateMap(Short questionToGenerateCount) {
        log.info("questionServiceAndQuestionsToGenerateMap()");

        long allQuestions = questionServices.stream().mapToLong(questionService -> questionService.getRepository().count()).sum();

        Map<QuestionServiceInterface, Short> serviceQuestionCountInterface = questionServices.stream()
                .map(questionService -> new AbstractMap.SimpleEntry<>(
                        questionService,
                        (short) Math.round((double) questionService.getRepository().count() / allQuestions * questionToGenerateCount)
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assureThatOverallQuestionCountIsEqualToRequestedCount(serviceQuestionCountInterface, questionToGenerateCount);

        log.info("questionServiceAndQuestionsToGenerateMap() -> {}", serviceQuestionCountInterface);
        return serviceQuestionCountInterface;
    }

    private void assureThatOverallQuestionCountIsEqualToRequestedCount(Map<QuestionServiceInterface, Short> serviceQuestionCountInterface, Short questionToGenerateCount) {
        while (serviceQuestionCountInterface.values().stream().mapToLong(s -> s).sum() < questionToGenerateCount) {
            Map.Entry<QuestionServiceInterface, Short> minQuestionServiceQuestionCount = serviceQuestionCountInterface
                    .entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow(ThereIsNoQuestionService::new);
            minQuestionServiceQuestionCount.setValue((short) (minQuestionServiceQuestionCount.getValue() + 1));
            log.info("Current count: {} \nExpected count: {}", minQuestionServiceQuestionCount.getValue(), questionToGenerateCount);
        }

        while (serviceQuestionCountInterface.values().stream().mapToLong(s -> s).sum() > questionToGenerateCount) {
            Map.Entry<QuestionServiceInterface, Short> maxQuestionServiceQuestionCount = serviceQuestionCountInterface
                    .entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow(ThereIsNoQuestionService::new);
            maxQuestionServiceQuestionCount.setValue((short) (maxQuestionServiceQuestionCount.getValue() - 1));
            log.info("Current count: {} \nExpected count: {}", maxQuestionServiceQuestionCount.getValue(), questionToGenerateCount);
        }
    }

    private List<QuestionResponse> getQuestionsAndTemporarilyMarkAsUsed(Map.Entry<QuestionServiceInterface, Short> serviceQuestionCountEntry, Map<QuestionServiceInterface, List<Long>> usedQuestionsIdsForEachQuestionType) {
        if (!usedQuestionsIdsForEachQuestionType.containsKey(serviceQuestionCountEntry.getKey())) {
            List<QuestionResponse> questionsWithMinimumCount = serviceQuestionCountEntry.getKey().getQuestionsWithMinimumCount(serviceQuestionCountEntry.getValue());

            usedQuestionsIdsForEachQuestionType.put(serviceQuestionCountEntry.getKey(), questionsWithMinimumCount.stream().map(QuestionResponse::getId).collect(Collectors.toList()));

            return questionsWithMinimumCount;
        } else {
            List<Long> usedQuestions = usedQuestionsIdsForEachQuestionType.get(serviceQuestionCountEntry.getKey());

            List<QuestionResponse> questionsWithMinimumCountWhereIdNotIn = serviceQuestionCountEntry.getKey().getQuestionsWithMinimumCountWhereIdNotIn(serviceQuestionCountEntry.getValue(), usedQuestions);

            List<Long> newUsedQuestions = Stream.concat(usedQuestions.stream(), questionsWithMinimumCountWhereIdNotIn.stream().map(QuestionResponse::getId)).collect(Collectors.toList());

            usedQuestionsIdsForEachQuestionType.put(serviceQuestionCountEntry.getKey(), newUsedQuestions);

            return questionsWithMinimumCountWhereIdNotIn;
        }
    }
}
