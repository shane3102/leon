package pl.leon.form.application.leon.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.leon.form.application.leon.core.exceptions.ExceptionMessages;
import pl.leon.form.application.leon.model.response.FormToCompleteResponse;
import pl.leon.form.application.leon.repository.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.DROPDOWN;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LINE_SCALE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LONG_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.MULTIPLE_CHOICE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SHORT_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SINGLE_CHOICE;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FormControllerIntegrationTest {

    private static final String URL = "/api/form";

    private static final int QUESTION_TYPE_COUNT = 6;

    private static final String QUESTION_CONTENT = "Pytanie na śniadanie";
    private static final String ANSWER_CONTENT_1 = "Odpowiedz 1";
    private static final String ANSWER_CONTENT_2 = "Odpowiedz 2";
    private static final String ANSWER_CONTENT_3 = "Odpowiedz 3";
    private static final String ANSWER_CONTENT_4 = "Odpowiedz 4";

    private static DropdownQuestionEntity dropdownQuestion1;
    private static DropdownQuestionEntity dropdownQuestion2;
    private static DropdownQuestionEntity dropdownQuestion3;
    private static DropdownQuestionEntity dropdownQuestion4;

    private static LineScaleQuestionEntity lineScaleQuestion1;
    private static LineScaleQuestionEntity lineScaleQuestion2;
    private static LineScaleQuestionEntity lineScaleQuestion3;
    private static LineScaleQuestionEntity lineScaleQuestion4;

    private static LongAnswerQuestionEntity longAnswerQuestion1;
    private static LongAnswerQuestionEntity longAnswerQuestion2;
    private static LongAnswerQuestionEntity longAnswerQuestion3;
    private static LongAnswerQuestionEntity longAnswerQuestion4;

    private static MultipleChoiceQuestionEntity multipleChoiceQuestion1;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion2;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion3;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion4;

    private static ShortAnswerQuestionEntity shortAnswerQuestion1;
    private static ShortAnswerQuestionEntity shortAnswerQuestion2;
    private static ShortAnswerQuestionEntity shortAnswerQuestion3;
    private static ShortAnswerQuestionEntity shortAnswerQuestion4;

    private static SingleChoiceQuestionEntity singleChoiceQuestion1;
    private static SingleChoiceQuestionEntity singleChoiceQuestion2;
    private static SingleChoiceQuestionEntity singleChoiceQuestion3;
    private static SingleChoiceQuestionEntity singleChoiceQuestion4;

    @Autowired
    private DropdownQuestionRepository dropdownQuestionRepository;

    @Autowired
    private LineScaleQuestionRepository lineScaleQuestionRepository;

    @Autowired
    private LongAnswerQuestionRepository longAnswerQuestionRepository;

    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Autowired
    private ShortAnswerQuestionRepository shortAnswerQuestionRepository;

    @Autowired
    private SingleChoiceQuestionRepository singleChoiceQuestionRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    private static Stream<Arguments> provideParametersForGeneratingRandomForms() {
        return Stream.of(
                // number of questions to generate per type;
                // a pair of entities to save and number of entities unused:
                // dropdown,
                // line scale,
                // multiple choice,
                // single choice,
                // short answer,
                // long answer;
                // expected exception message
                Arguments.of(
                        2,
                        List.of(dropdownQuestion1, dropdownQuestion2, dropdownQuestion3),
                        1,
                        List.of(lineScaleQuestion1, lineScaleQuestion2, lineScaleQuestion3, lineScaleQuestion4),
                        2,
                        List.of(multipleChoiceQuestion1, multipleChoiceQuestion2, multipleChoiceQuestion3, multipleChoiceQuestion4),
                        2,
                        List.of(singleChoiceQuestion3, singleChoiceQuestion4),
                        0,
                        List.of(shortAnswerQuestion1, shortAnswerQuestion2, shortAnswerQuestion3, shortAnswerQuestion4),
                        2,
                        List.of(longAnswerQuestion2, longAnswerQuestion3, longAnswerQuestion4),
                        1,
                        null
                ),
                Arguments.of(
                        4,
                        List.of(dropdownQuestion1, dropdownQuestion2, dropdownQuestion3, dropdownQuestion4),
                        0,
                        List.of(lineScaleQuestion1, lineScaleQuestion2, lineScaleQuestion3, lineScaleQuestion4),
                        0,
                        List.of(multipleChoiceQuestion1, multipleChoiceQuestion2, multipleChoiceQuestion3, multipleChoiceQuestion4),
                        0,
                        List.of(singleChoiceQuestion1, singleChoiceQuestion2, singleChoiceQuestion3, singleChoiceQuestion4),
                        0,
                        List.of(shortAnswerQuestion1, shortAnswerQuestion2, shortAnswerQuestion3, shortAnswerQuestion4),
                        0,
                        List.of(longAnswerQuestion1, longAnswerQuestion2, longAnswerQuestion3, longAnswerQuestion4),
                        0,
                        null
                ),
                Arguments.of(
                        5,
                        List.of(dropdownQuestion1, dropdownQuestion2, dropdownQuestion3, dropdownQuestion4),
                        0,
                        List.of(lineScaleQuestion1, lineScaleQuestion2, lineScaleQuestion3, lineScaleQuestion4),
                        0,
                        List.of(multipleChoiceQuestion1, multipleChoiceQuestion2, multipleChoiceQuestion3, multipleChoiceQuestion4),
                        0,
                        List.of(singleChoiceQuestion1, singleChoiceQuestion2, singleChoiceQuestion3, singleChoiceQuestion4),
                        0,
                        List.of(shortAnswerQuestion1, shortAnswerQuestion2, shortAnswerQuestion3, shortAnswerQuestion4),
                        0,
                        List.of(longAnswerQuestion1, longAnswerQuestion2, longAnswerQuestion3, longAnswerQuestion4),
                        0,
                        ExceptionMessages.TOO_MANY_QUESTIONS_TO_GENERATE.getMessage()
                )
        );
    }

    @BeforeAll
    @BeforeEach
    void setUp() {

        dropdownQuestion1 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion2 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion3 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion4 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();

        lineScaleQuestion1 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion2 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion3 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion4 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();

        multipleChoiceQuestion1 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion2 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion3 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion4 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();

        singleChoiceQuestion1 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion2 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion3 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion4 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();

        shortAnswerQuestion1 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        shortAnswerQuestion2 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        shortAnswerQuestion3 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        shortAnswerQuestion4 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();

        longAnswerQuestion1 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        longAnswerQuestion2 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        longAnswerQuestion3 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        longAnswerQuestion4 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
    }

    @AfterAll
    void tearDown() {
        dropdownQuestionRepository.deleteAll();
        lineScaleQuestionRepository.deleteAll();
        multipleChoiceQuestionRepository.deleteAll();
        singleChoiceQuestionRepository.deleteAll();
        shortAnswerQuestionRepository.deleteAll();
        longAnswerQuestionRepository.deleteAll();
    }

    //TODO parametrize and enchance (and name xd)
    @ParameterizedTest
    @MethodSource("provideParametersForGeneratingRandomForms")
    void given_when_then(int numberOfQuestionsToGeneratePerType,
                         List<DropdownQuestionEntity> dropdownQuestions,
                         int expectedUnusedDropdownQuestions,
                         List<LineScaleQuestionEntity> lineScaleQuestions,
                         int expectedUnusedLineScaleQuestions,
                         List<MultipleChoiceQuestionEntity> multipleChoiceQuestions,
                         int expectedUnusedMultipleChoiceQuestions,
                         List<SingleChoiceQuestionEntity> singleChoiceQuestions,
                         int expectedUnusedSingleChoiceQuestions,
                         List<ShortAnswerQuestionEntity> shortAnswerQuestions,
                         int expectedUnusedShortAnswerQuestions,
                         List<LongAnswerQuestionEntity> longAnswerQuestions,
                         int expectedUnusedLongAnswerQuestions,
                         String expectedExceptionMessage) throws Exception {
        // given
        dropdownQuestionRepository.saveAll(dropdownQuestions);
        lineScaleQuestionRepository.saveAll(lineScaleQuestions);
        multipleChoiceQuestionRepository.saveAll(multipleChoiceQuestions);
        singleChoiceQuestionRepository.saveAll(singleChoiceQuestions);
        shortAnswerQuestionRepository.saveAll(shortAnswerQuestions);
        longAnswerQuestionRepository.saveAll(longAnswerQuestions);

        // when + then
        if (expectedExceptionMessage == null) {
            String responseJson = mockMvc.perform(get(URL + "/get-random-form").param("question-count", Integer.toString(numberOfQuestionsToGeneratePerType)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            FormToCompleteResponse formToCompleteResponse = mapper.readValue(responseJson, FormToCompleteResponse.class);

            // then
            assertAll(
                    () -> assertNotNull(formToCompleteResponse, "Response is null"),
                    () -> assertNotNull(formToCompleteResponse.getQuestions(), "Questions are null"),
                    () -> assertEquals(QUESTION_TYPE_COUNT * numberOfQuestionsToGeneratePerType, formToCompleteResponse.getQuestions().size(), "Question count not equal expected"),
                    () -> assertEquals(numberOfQuestionsToGeneratePerType, formToCompleteResponse.getQuestions().stream().filter(q -> DROPDOWN.equals(q.getType())).count(), "Question count not equal expected"),
                    () -> assertEquals(numberOfQuestionsToGeneratePerType, formToCompleteResponse.getQuestions().stream().filter(q -> LINE_SCALE.equals(q.getType())).count(), "Question count not equal expected"),
                    () -> assertEquals(numberOfQuestionsToGeneratePerType, formToCompleteResponse.getQuestions().stream().filter(q -> MULTIPLE_CHOICE.equals(q.getType())).count(), "Question count not equal expected"),
                    () -> assertEquals(numberOfQuestionsToGeneratePerType, formToCompleteResponse.getQuestions().stream().filter(q -> SINGLE_CHOICE.equals(q.getType())).count(), "Question count not equal expected"),
                    () -> assertEquals(numberOfQuestionsToGeneratePerType, formToCompleteResponse.getQuestions().stream().filter(q -> SHORT_ANSWER.equals(q.getType())).count(), "Question count not equal expected"),
                    () -> assertEquals(numberOfQuestionsToGeneratePerType, formToCompleteResponse.getQuestions().stream().filter(q -> LONG_ANSWER.equals(q.getType())).count(), "Question count not equal expected")
            );

            long unusedDropdownQuestions = dropdownQuestionRepository.count() - formToCompleteResponse.getQuestions().stream().filter(question -> DROPDOWN.equals(question.getType())).count();
            long unusedLineScaleQuestions = lineScaleQuestionRepository.count() - formToCompleteResponse.getQuestions().stream().filter(question -> LINE_SCALE.equals(question.getType())).count();
            long unusedMultipleChoiceQuestions = multipleChoiceQuestionRepository.count() - formToCompleteResponse.getQuestions().stream().filter(question -> MULTIPLE_CHOICE.equals(question.getType())).count();
            long unusedSingleChoiceQuestions = singleChoiceQuestionRepository.count() - formToCompleteResponse.getQuestions().stream().filter(question -> SINGLE_CHOICE.equals(question.getType())).count();
            long unusedShortAnswerQuestions = shortAnswerQuestionRepository.count() - formToCompleteResponse.getQuestions().stream().filter(question -> SHORT_ANSWER.equals(question.getType())).count();
            long unusedLongAnswerQuestions = longAnswerQuestionRepository.count() - formToCompleteResponse.getQuestions().stream().filter(question -> LONG_ANSWER.equals(question.getType())).count();

            assertAll(
                    () -> assertEquals(expectedUnusedDropdownQuestions, unusedDropdownQuestions),
                    () -> assertEquals(expectedUnusedLineScaleQuestions, unusedLineScaleQuestions),
                    () -> assertEquals(expectedUnusedMultipleChoiceQuestions, unusedMultipleChoiceQuestions),
                    () -> assertEquals(expectedUnusedSingleChoiceQuestions, unusedSingleChoiceQuestions),
                    () -> assertEquals(expectedUnusedShortAnswerQuestions, unusedShortAnswerQuestions),
                    () -> assertEquals(expectedUnusedLongAnswerQuestions, unusedLongAnswerQuestions)
            );

        } else {
            Exception exception = mockMvc.perform(get(URL + "/get-random-form").param("question-count", Integer.toString(numberOfQuestionsToGeneratePerType)))
                    .andDo(print())
                    .andReturn()
                    .getResolvedException();

            assertEquals(expectedExceptionMessage, exception == null ? "" : exception.getMessage(), "Exception message not equal expected");
        }
    }
}