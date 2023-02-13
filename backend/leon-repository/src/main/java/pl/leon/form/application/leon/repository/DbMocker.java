package pl.leon.form.application.leon.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class DbMocker {

    private final FormRepository formRepository;

    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final DropdownQuestionRepository dropdownQuestionRepository;
    private final LineScaleQuestionRepository lineScaleQuestionRepository;
    private final SingleChoiceQuestionRepository singleChoiceQuestionRepository;
    private final LongAnswerQuestionRepository longAnswerQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;

    private final Random random = new Random();

    @PostConstruct
    void mockForms() {
        generateForms();
    }

    private void generateForms() {
        for (int i = 0; i < 5; i++) {

            int multipleChoiceQuestionsCount = random.nextInt(20, 30);
            int dropdownQuestionsCount = random.nextInt(10, 20);
            int lineScaleQuestionsCount = random.nextInt(5, 15);
            int singleChoiceQuestionsCount = random.nextInt(10, 20);
            int longAnswerQuestionsCount = random.nextInt(0, 10);
            int shortAnswerQuestionsCount = random.nextInt(5, 15);

            List<MultipleChoiceQuestionEntity> multipleChoiceQuestions = getMultipleChoiceQuestions(multipleChoiceQuestionsCount, i + 1);
            multipleChoiceQuestions = multipleChoiceQuestionRepository.saveAll(multipleChoiceQuestions);

            List<DropdownQuestionEntity> dropdownQuestions = getDropdownQuestions(dropdownQuestionsCount, i + 1);
            dropdownQuestions = dropdownQuestionRepository.saveAll(dropdownQuestions);

            List<LineScaleQuestionEntity> lineScaleQuestions = getLineScaleQuestions(lineScaleQuestionsCount, i + 1);
            lineScaleQuestions = lineScaleQuestionRepository.saveAll(lineScaleQuestions);

            List<SingleChoiceQuestionEntity> singleChoiceQuestions = getSingleChoiceQuestions(singleChoiceQuestionsCount, i + 1);
            singleChoiceQuestions = singleChoiceQuestionRepository.saveAll(singleChoiceQuestions);

            List<LongAnswerQuestionEntity> longAnswerQuestions = getLongAnswerQuestions(longAnswerQuestionsCount, i + 1);
            longAnswerQuestions = longAnswerQuestionRepository.saveAll(longAnswerQuestions);

            List<ShortAnswerQuestionEntity> shortAnswerQuestions = getShortAnswerQuestions(shortAnswerQuestionsCount, i + 1);
            shortAnswerQuestions = shortAnswerQuestionRepository.saveAll(shortAnswerQuestions);

            FormEntity form = FormEntity.builder()
                    .title("Tytuł formularza numer " + (i + 1))
                    .subject("Opis formularza numer " + (i + 1))
                    .multipleChoiceQuestions(multipleChoiceQuestions)
                    .dropdownQuestions(dropdownQuestions)
                    .lineScaleQuestions(lineScaleQuestions)
                    .singleChoiceQuestions(singleChoiceQuestions)
                    .longAnswerQuestions(longAnswerQuestions)
                    .shortAnswerQuestions(shortAnswerQuestions)
                    .build();
            formRepository.save(form);

        }
    }

    private List<MultipleChoiceQuestionEntity> getMultipleChoiceQuestions(int count, int formNumber) {
        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> MultipleChoiceQuestionEntity.builder()
                .question("Pytanie z wielokrotnym wyborem numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptions("wielokrotny wybór", formNumber))
                .build()).collect(Collectors.toList());
    }

    private List<DropdownQuestionEntity> getDropdownQuestions(int count, int formNumber) {
        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> DropdownQuestionEntity.builder()
                .question("Pytanie z listą rozwijaną numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptions("lista wybieralna", formNumber))
                .build()).collect(Collectors.toList());
    }

    private List<LineScaleQuestionEntity> getLineScaleQuestions(int count, int formNumber) {
        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> LineScaleQuestionEntity.builder()
                .question("Pytanie ze skalą liniową numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptionsLineScale("skala liniowa", formNumber))
                .build()).collect(Collectors.toList());
    }

    private List<SingleChoiceQuestionEntity> getSingleChoiceQuestions(int count, int formNumber) {
        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> SingleChoiceQuestionEntity.builder()
                .question("Pytanie jednokrotnego wyboru numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptions("jednokrotny wybór", formNumber))
                .build()).collect(Collectors.toList());
    }

    private List<ShortAnswerQuestionEntity> getShortAnswerQuestions(int count, int formNumber) {
        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> ShortAnswerQuestionEntity.builder()
                .question("Pytanie o krótkiej odpowiedzi numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .build()).collect(Collectors.toList());
    }

    private List<LongAnswerQuestionEntity> getLongAnswerQuestions(int count, int formNumber) {
        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> LongAnswerQuestionEntity.builder()
                .question("Pytanie o długiej odpowiedzi numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .build()).collect(Collectors.toList());
    }

    private List<OptionEntity> getOptions(String questionTypeName, int formNumber) {
        int count = random.nextInt(3, 6);

        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> OptionEntity.builder()
                .content("Odpowiedz na pytanie typu " + questionTypeName + " numer " + (i + 1) + " na pytanie z ankiety numer " + formNumber)
                .build()).collect(Collectors.toList());
    }

    private List<OptionEntity> getOptionsLineScale(String questionTypeName, int formNumber) {
        int count = random.nextInt(3, 6);

        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> OptionEntity.builder()
                .content("Odp nr " + (i + 1) + " a " + formNumber)
                .build()).collect(Collectors.toList());
    }
}
