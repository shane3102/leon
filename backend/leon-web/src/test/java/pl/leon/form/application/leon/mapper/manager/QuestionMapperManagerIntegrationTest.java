package pl.leon.form.application.leon.mapper.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.leon.form.application.leon.model.response.questions.OptionResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionMapperManagerIntegrationTest {

    private static final String QUESTION = "Pytanie na śniadanie";
    private static final String OPTION_1 = "Odpowiedz 1";
    private static final String OPTION_2 = "Odpowiedz 2";
    private static final String OPTION_3 = "Odpowiedz 3";
    private static final String OPTION_1_LINE_SCALE = "1";
    private static final String OPTION_2_LINE_SCALE = "2";
    private static final String OPTION_3_LINE_SCALE = "3";

    private static DropdownQuestionEntity dropdownQuestion;
    private static LineScaleQuestionEntity lineScaleQuestion;
    private static LongAnswerQuestionEntity longAnswerQuestion;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion;
    private static ShortAnswerQuestionEntity shortAnswerQuestion;
    private static SingleChoiceQuestionEntity singleChoiceQuestion;

    private static QuestionResponse textAnswerResponse;
    private static QuestionResponse choiceAnswerResponse;
    private static QuestionResponse lineScaleAnswerResponse;

    @Autowired
    private QuestionMapperManager questionMapperManager;

    private static Stream<Arguments> entityArguments() {
        return Stream.of(
                Arguments.of(dropdownQuestion, choiceAnswerResponse),
                Arguments.of(lineScaleQuestion, lineScaleAnswerResponse),
                Arguments.of(longAnswerQuestion, textAnswerResponse),
                Arguments.of(multipleChoiceQuestion, choiceAnswerResponse),
                Arguments.of(shortAnswerQuestion, textAnswerResponse),
                Arguments.of(singleChoiceQuestion, choiceAnswerResponse)
        );
    }

    @BeforeAll
    void beforeAll() {
        dropdownQuestion = DropdownQuestionEntity.builder()
                .question(QUESTION)
                .options(List.of(
                        OptionEntity.builder().content(OPTION_1).build(),
                        OptionEntity.builder().content(OPTION_2).build(),
                        OptionEntity.builder().content(OPTION_3).build()))
                .build();

        lineScaleQuestion = LineScaleQuestionEntity.builder()
                .question(QUESTION)
                .options(List.of(
                        OptionEntity.builder().content(OPTION_1_LINE_SCALE).build(),
                        OptionEntity.builder().content(OPTION_2_LINE_SCALE).build(),
                        OptionEntity.builder().content(OPTION_3_LINE_SCALE).build()))
                .build();

        longAnswerQuestion = LongAnswerQuestionEntity.builder()
                .question(QUESTION)
                .build();

        multipleChoiceQuestion = MultipleChoiceQuestionEntity.builder()
                .question(QUESTION)
                .options(List.of(
                        OptionEntity.builder().content(OPTION_1).build(),
                        OptionEntity.builder().content(OPTION_2).build(),
                        OptionEntity.builder().content(OPTION_3).build()))
                .build();

        shortAnswerQuestion = ShortAnswerQuestionEntity.builder()
                .question(QUESTION)
                .build();

        singleChoiceQuestion = SingleChoiceQuestionEntity.builder()
                .question(QUESTION)
                .options(List.of(
                        OptionEntity.builder().content(OPTION_1).build(),
                        OptionEntity.builder().content(OPTION_2).build(),
                        OptionEntity.builder().content(OPTION_3).build()))
                .build();

        textAnswerResponse = QuestionResponse.builder()
                .question(QUESTION)
                .build();

        choiceAnswerResponse = QuestionResponse.builder()
                .question(QUESTION)
                .options(List.of(
                        OptionResponse.builder().content(OPTION_1).build(),
                        OptionResponse.builder().content(OPTION_2).build(),
                        OptionResponse.builder().content(OPTION_3).build()))
                .build();

        lineScaleAnswerResponse = QuestionResponse.builder()
                .question(QUESTION)
                .options(List.of(
                        OptionResponse.builder().content(OPTION_1_LINE_SCALE).build(),
                        OptionResponse.builder().content(OPTION_2_LINE_SCALE).build(),
                        OptionResponse.builder().content(OPTION_3_LINE_SCALE).build()))
                .build();
    }

    @ParameterizedTest
    @MethodSource("entityArguments")
    void givenQuestionEntity_whenMapToQuestionResponse_thenResponseEqualExpected(Object mappedQuestionEntity, QuestionResponse expectedResponse) {
        // given

        // when
        QuestionResponse response = questionMapperManager.mapToResponse(mappedQuestionEntity);

        // then
        assertEquals(expectedResponse, response, "Expected response not equal expected");
    }
}
