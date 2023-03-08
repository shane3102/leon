package pl.leon.form.application.leon.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.UserEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class DbMocker {

    private final UserRepository userRepository;

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
        UserEntity user = UserEntity.builder().username("user").build();
        user = userRepository.save(user);
        generateForms(user);
    }

    private void generateForms(UserEntity user) {

        LocalDate dateAdded = LocalDate.now().minusDays(10);

        for (int i = 0; i < 25; i++) {

            generateSingleForm(i, i == 5 ? LocalDate.now().minusDays(10) : dateAdded, i == 5, i == 6, user);

            dateAdded = dateAdded.plusDays(1);

        }
    }

    private void generateSingleForm(int i, LocalDate dateAdded, boolean disabled, boolean resultsAvailableForEveryone, UserEntity user) {

        FormEntity form = FormEntity.builder()
                .user(user)
                .dateAdded(dateAdded)
                .disabled(disabled)
                .resultsAvailableForEveryone(resultsAvailableForEveryone)
                .title("Tytuł formularza numer " + (i + 1))
                .subject("Opis formularza numer " + (i + 1))
                .build();

        form = formRepository.save(form);

        int multipleChoiceQuestionsCount = random.nextInt(1, 5);
        int dropdownQuestionsCount = random.nextInt(1, 5);
        int lineScaleQuestionsCount = random.nextInt(1, 5);
        int singleChoiceQuestionsCount = random.nextInt(1, 5);
        int longAnswerQuestionsCount = random.nextInt(1, 5);
        int shortAnswerQuestionsCount = random.nextInt(1, 5);

        List<MultipleChoiceQuestionEntity> multipleChoiceQuestions = getMultipleChoiceQuestions(multipleChoiceQuestionsCount, i + 1, form);
        multipleChoiceQuestions = multipleChoiceQuestionRepository.saveAll(multipleChoiceQuestions);

        List<DropdownQuestionEntity> dropdownQuestions = getDropdownQuestions(dropdownQuestionsCount, i + 1, form);
        dropdownQuestions = dropdownQuestionRepository.saveAll(dropdownQuestions);

        List<LineScaleQuestionEntity> lineScaleQuestions = getLineScaleQuestions(lineScaleQuestionsCount, i + 1, form);
        lineScaleQuestions = lineScaleQuestionRepository.saveAll(lineScaleQuestions);

        List<SingleChoiceQuestionEntity> singleChoiceQuestions = getSingleChoiceQuestions(singleChoiceQuestionsCount, i + 1, form);
        singleChoiceQuestions = singleChoiceQuestionRepository.saveAll(singleChoiceQuestions);

        List<LongAnswerQuestionEntity> longAnswerQuestions = getLongAnswerQuestions(longAnswerQuestionsCount, i + 1, form);
        longAnswerQuestions = longAnswerQuestionRepository.saveAll(longAnswerQuestions);

        List<ShortAnswerQuestionEntity> shortAnswerQuestions = getShortAnswerQuestions(shortAnswerQuestionsCount, i + 1, form);
        shortAnswerQuestions = shortAnswerQuestionRepository.saveAll(shortAnswerQuestions);

        form.setMultipleChoiceQuestions(multipleChoiceQuestions);
        form.setDropdownQuestions(dropdownQuestions);
        form.setLineScaleQuestions(lineScaleQuestions);
        form.setSingleChoiceQuestions(singleChoiceQuestions);
        form.setLongAnswerQuestions(longAnswerQuestions);
        form.setShortAnswerQuestions(shortAnswerQuestions);
        formRepository.save(form);
    }

    private List<MultipleChoiceQuestionEntity> getMultipleChoiceQuestions(int count, int formNumber, FormEntity form) {

        List<MultipleChoiceQuestionEntity> result = Stream.iterate(0, i -> i + 1).limit(count).map(i -> MultipleChoiceQuestionEntity.builder()
                .question("Pytanie z wielokrotnym wyborem numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptions("wielokrotny wybór", formNumber))
                .form(form)
                .build()).collect(Collectors.toList());

        result.forEach(oneResult -> {
            oneResult.setCountAnswers(oneResult.getOptions().stream().mapToLong(OptionEntity::getCount).sum());
        });

        return result;
    }

    private List<DropdownQuestionEntity> getDropdownQuestions(int count, int formNumber, FormEntity form) {
        List<DropdownQuestionEntity> result = Stream.iterate(0, i -> i + 1).limit(count).map(i -> DropdownQuestionEntity.builder()
                .question("Pytanie z listą rozwijaną numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptions("lista wybieralna", formNumber))
                .form(form)
                .build()).collect(Collectors.toList());

        result.forEach(oneResult -> {
            oneResult.setCountAnswers(oneResult.getOptions().stream().mapToLong(OptionEntity::getCount).sum());
        });

        return result;
    }

    private List<LineScaleQuestionEntity> getLineScaleQuestions(int count, int formNumber, FormEntity form) {
        List<LineScaleQuestionEntity> result = Stream.iterate(0, i -> i + 1).limit(count).map(i -> LineScaleQuestionEntity.builder()
                .question("Pytanie ze skalą liniową numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptionsLineScale("skala liniowa", formNumber))
                .countAnswers(0L)
                .form(form)
                .build()).collect(Collectors.toList());

        result.forEach(oneResult -> {
            oneResult.setCountAnswers(oneResult.getOptions().stream().mapToLong(OptionEntity::getCount).sum());
        });

        return result;
    }

    private List<SingleChoiceQuestionEntity> getSingleChoiceQuestions(int count, int formNumber, FormEntity form) {
        List<SingleChoiceQuestionEntity> result = Stream.iterate(0, i -> i + 1).limit(count).map(i -> SingleChoiceQuestionEntity.builder()
                .question("Pytanie jednokrotnego wyboru numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .options(getOptions("jednokrotny wybór", formNumber))
                .countAnswers(0L)
                .form(form)
                .build()).collect(Collectors.toList());

        result.forEach(oneResult -> {
            oneResult.setCountAnswers(oneResult.getOptions().stream().mapToLong(OptionEntity::getCount).sum());
        });

        return result;
    }

    private List<ShortAnswerQuestionEntity> getShortAnswerQuestions(int count, int formNumber, FormEntity form) {
        List<ShortAnswerQuestionEntity> result = Stream.iterate(0, i -> i + 1).limit(count).map(i -> ShortAnswerQuestionEntity.builder()
                .question("Pytanie o krótkiej odpowiedzi numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .countAnswers(random.nextLong(1, 10))
                .form(form)
                .build()).collect(Collectors.toList());


        for (ShortAnswerQuestionEntity oneResult : result) {
            ShortAnswerQuestionEntity finalOneResult = shortAnswerQuestionRepository.save(oneResult);

            List<AnswerEntity> answers = Stream
                    .iterate(0, i -> i + 1)
                    .limit(oneResult.getCountAnswers())
                    .map(i -> AnswerEntity.builder().shortAnswerQuestionEntity(finalOneResult).content("Odpowiedz dla pytania typu krótka odpowiedź numer " + i).build()).collect(Collectors.toList());

            oneResult.setAnswers(answers);

        }

        return result;
    }

    private List<LongAnswerQuestionEntity> getLongAnswerQuestions(int count, int formNumber, FormEntity form) {
        List<LongAnswerQuestionEntity> result = Stream.iterate(0, i -> i + 1).limit(count).map(i -> LongAnswerQuestionEntity.builder()
                .question("Pytanie o długiej odpowiedzi numer " + (i + 1) + " do ankiety numer " + formNumber + "?")
                .countAnswers(random.nextLong(20,100))
                .form(form)
                .build()).collect(Collectors.toList());

        for (LongAnswerQuestionEntity oneResult : result) {
            LongAnswerQuestionEntity finalOneResult = longAnswerQuestionRepository.save(oneResult);

            List<AnswerEntity> answers = Stream
                    .iterate(0, i -> i + 1)
                    .limit(oneResult.getCountAnswers())
                    .map(i -> AnswerEntity.builder().longAnswerQuestionEntity(finalOneResult).content("Odpowiedz dla pytania typu długa odpowiedź numer " + i).build()).collect(Collectors.toList());

            oneResult.setAnswers(answers);

        }
        return result;
    }

    private List<OptionEntity> getOptions(String questionTypeName, int formNumber) {
        int count = random.nextInt(3, 6);

        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> OptionEntity.builder()
                .content("Odpowiedz na pytanie typu " + questionTypeName + " numer " + (i + 1) + " na pytanie z ankiety numer " + formNumber)
                .count(random.nextLong(0, 100))
                .build()).collect(Collectors.toList());
    }

    private List<OptionEntity> getOptionsLineScale(String questionTypeName, int formNumber) {
        int count = random.nextInt(3, 6);

        return Stream.iterate(0, i -> i + 1).limit(count).map(i -> OptionEntity.builder()
                .content("Odp nr " + (i + 1) + " a " + formNumber)
                .count(0L)
                .build()).collect(Collectors.toList());
    }
}
