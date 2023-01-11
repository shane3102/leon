package pl.leon.form.application.leon.web.controller.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.leon.form.application.leon.mapper.question.manager.QuestionMapperManager;
import pl.leon.form.application.leon.model.both.FormCompleted;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.repository.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.FormCompletedRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.OptionRepository;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.DropdownQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LineScaleQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.LongAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.MultipleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.ShortAnswerQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.SingleChoiceQuestionAnswerEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Duration.ZERO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class FormSubmitCompletedTest {

    @Autowired
    private FormCompletedRepository formCompletedRepository;

    @Autowired
    private FormRepository formRepository;

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
    private OptionRepository optionRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private QuestionMapperManager questionMapperManager;

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/api/form";

    private static final String QUESTION_CONTENT = "Pytanie na śniadanie";
    private static final String ANSWER_CONTENT_1 = "Odpowiedz 1";
    private static final String ANSWER_CONTENT_2 = "Odpowiedz 2";
    private static final String ANSWER_CONTENT_3 = "Odpowiedz 3";
    private static final String ANSWER_CONTENT_4 = "Odpowiedz 4";
    private static final Long ANSWER_DEFAULT_COUNT = 1L;
    private static final Long EXPECTED_ANSWER_COUNT = 2L;

    private static final Long QUESTION_DEFAULT_COUNT = 4L;
    private static final Long EXPECTED_QUESTION_COUNT = 5L;

    private static final String ANSWER_FOR_TEXT_QUESTION_1 = "Odpowiedz konkretna 1";
    private static final String ANSWER_FOR_TEXT_QUESTION_2 = "Odpowiedz konkretna 2";
    private static final String ANSWER_FOR_TEXT_QUESTION_3 = "Odpowiedz konkretna 3";
    private static final String ANSWER_FOR_TEXT_QUESTION_4 = "Odpowiedz konkretna 4";

    private static DropdownQuestionEntity dropdownQuestion1;
    private static DropdownQuestionEntity dropdownQuestion2;
    private static DropdownQuestionEntity dropdownQuestion3;
    private static DropdownQuestionEntity dropdownQuestion4;

    private static List<DropdownQuestionEntity> dropdownQuestions;

    private static LineScaleQuestionEntity lineScaleQuestion1;
    private static LineScaleQuestionEntity lineScaleQuestion2;
    private static LineScaleQuestionEntity lineScaleQuestion3;
    private static LineScaleQuestionEntity lineScaleQuestion4;

    private static List<LineScaleQuestionEntity> lineScaleQuestions;

    private static LongAnswerQuestionEntity longAnswerQuestion1;
    private static LongAnswerQuestionEntity longAnswerQuestion2;
    private static LongAnswerQuestionEntity longAnswerQuestion3;
    private static LongAnswerQuestionEntity longAnswerQuestion4;

    private static List<LongAnswerQuestionEntity> longAnswerQuestions;

    private static MultipleChoiceQuestionEntity multipleChoiceQuestion1;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion2;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion3;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion4;

    private static List<MultipleChoiceQuestionEntity> multipleChoiceQuestions;

    private static ShortAnswerQuestionEntity shortAnswerQuestion1;
    private static ShortAnswerQuestionEntity shortAnswerQuestion2;
    private static ShortAnswerQuestionEntity shortAnswerQuestion3;
    private static ShortAnswerQuestionEntity shortAnswerQuestion4;

    private static List<ShortAnswerQuestionEntity> shortAnswerQuestions;

    private static SingleChoiceQuestionEntity singleChoiceQuestion1;
    private static SingleChoiceQuestionEntity singleChoiceQuestion2;
    private static SingleChoiceQuestionEntity singleChoiceQuestion3;
    private static SingleChoiceQuestionEntity singleChoiceQuestion4;

    private static List<SingleChoiceQuestionEntity> singleChoiceQuestions;

    private static List<String> options = List.of(ANSWER_CONTENT_1, ANSWER_CONTENT_2, ANSWER_CONTENT_3, ANSWER_CONTENT_4);

    private static FormEntity form1 = new FormEntity();
    private static FormEntity form2;
    private static FormEntity form3;

    private static Stream<Arguments> questionProvider() {
        return Stream.of(
                Arguments.of(
                        form1,
                        List.of(
                                DropdownQuestionAnswerEntity.builder().question(dropdownQuestion1).option(dropdownQuestion1.getOptions().get(2)).durationToAnswer(ZERO).build(),
                                DropdownQuestionAnswerEntity.builder().question(dropdownQuestion3).option(dropdownQuestion3.getOptions().get(1)).durationToAnswer(ZERO).build()
                        ),
                        List.of(
                                LineScaleQuestionAnswerEntity.builder().question(lineScaleQuestion2).option(lineScaleQuestion2.getOptions().get(0)).durationToAnswer(ZERO).build()
                        ),
                        List.of(
                                MultipleChoiceQuestionAnswerEntity.builder().question(multipleChoiceQuestion3).options(List.of(multipleChoiceQuestion3.getOptions().get(3))).build()
                        ),
                        List.of(
                                SingleChoiceQuestionAnswerEntity.builder().question(singleChoiceQuestion1).option(singleChoiceQuestion1.getOptions().get(1)).build()
                        ),
                        List.of(
                                ShortAnswerQuestionAnswerEntity.builder().question(shortAnswerQuestion1).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_1).build()).build(),
                                ShortAnswerQuestionAnswerEntity.builder().question(shortAnswerQuestion4).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_3).build()).build()
                        ),
                        List.of(
                                LongAnswerQuestionAnswerEntity.builder().question(longAnswerQuestion2).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_2).build()).build(),
                                LongAnswerQuestionAnswerEntity.builder().question(longAnswerQuestion4).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_4).build()).build()
                        )
                ),
                Arguments.of(
                        null,
                        List.of(
                                DropdownQuestionAnswerEntity.builder().question(dropdownQuestion1).option(dropdownQuestion1.getOptions().get(0)).durationToAnswer(ZERO).build(),
                                DropdownQuestionAnswerEntity.builder().question(dropdownQuestion2).option(dropdownQuestion2.getOptions().get(1)).durationToAnswer(ZERO).build()
                        ),
                        List.of(
                                LineScaleQuestionAnswerEntity.builder().question(lineScaleQuestion1).option(lineScaleQuestion1.getOptions().get(2)).durationToAnswer(ZERO).build(),
                                LineScaleQuestionAnswerEntity.builder().question(lineScaleQuestion3).option(lineScaleQuestion3.getOptions().get(3)).durationToAnswer(ZERO).build()
                        ),
                        List.of(
                                MultipleChoiceQuestionAnswerEntity.builder().question(multipleChoiceQuestion3).options(List.of(multipleChoiceQuestion3.getOptions().get(1))).build(),
                                MultipleChoiceQuestionAnswerEntity.builder().question(multipleChoiceQuestion4).options(List.of(multipleChoiceQuestion4.getOptions().get(2))).build()
                        ),
                        List.of(
                                SingleChoiceQuestionAnswerEntity.builder().question(singleChoiceQuestion1).option(singleChoiceQuestion1.getOptions().get(0)).build()
                        ),
                        List.of(
                                ShortAnswerQuestionAnswerEntity.builder().question(shortAnswerQuestion1).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_1).build()).build(),
                                ShortAnswerQuestionAnswerEntity.builder().question(shortAnswerQuestion3).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_2).build()).build(),
                                ShortAnswerQuestionAnswerEntity.builder().question(shortAnswerQuestion4).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_3).build()).build()
                        ),
                        List.of(
                                LongAnswerQuestionAnswerEntity.builder().question(longAnswerQuestion4).answer(AnswerEntity.builder().content(ANSWER_FOR_TEXT_QUESTION_4).build()).build()
                        )
                )
        );
    }

    @BeforeAll
    @Transactional
    void beforeAll() {
        dropdownQuestion1 = DropdownQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        dropdownQuestion2 = DropdownQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        dropdownQuestion3 = DropdownQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        dropdownQuestion4 = DropdownQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        dropdownQuestions = List.of(dropdownQuestion1, dropdownQuestion2, dropdownQuestion3, dropdownQuestion4);
        dropdownQuestions = dropdownQuestionRepository.saveAll(dropdownQuestions);

        lineScaleQuestion1 = LineScaleQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        lineScaleQuestion2 = LineScaleQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        lineScaleQuestion3 = LineScaleQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        lineScaleQuestion4 = LineScaleQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        lineScaleQuestions = List.of(lineScaleQuestion1, lineScaleQuestion2, lineScaleQuestion3, lineScaleQuestion4);
        lineScaleQuestions = lineScaleQuestionRepository.saveAll(lineScaleQuestions);

        multipleChoiceQuestion1 = MultipleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        multipleChoiceQuestion2 = MultipleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        multipleChoiceQuestion3 = MultipleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        multipleChoiceQuestion4 = MultipleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        multipleChoiceQuestions = List.of(multipleChoiceQuestion1, multipleChoiceQuestion2, multipleChoiceQuestion3, multipleChoiceQuestion4);
        multipleChoiceQuestions = multipleChoiceQuestionRepository.saveAll(multipleChoiceQuestions);

        singleChoiceQuestion1 = SingleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        singleChoiceQuestion2 = SingleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        singleChoiceQuestion3 = SingleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        singleChoiceQuestion4 = SingleChoiceQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        singleChoiceQuestions = List.of(singleChoiceQuestion1, singleChoiceQuestion2, singleChoiceQuestion3, singleChoiceQuestion4);
        singleChoiceQuestions = singleChoiceQuestionRepository.saveAll(singleChoiceQuestions);

        shortAnswerQuestion1 = ShortAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();
        shortAnswerQuestion2 = ShortAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();
        shortAnswerQuestion3 = ShortAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();
        shortAnswerQuestion4 = ShortAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();

        shortAnswerQuestions = List.of(shortAnswerQuestion1, shortAnswerQuestion2, shortAnswerQuestion3, shortAnswerQuestion4);
        shortAnswerQuestions = shortAnswerQuestionRepository.saveAll(shortAnswerQuestions);

        longAnswerQuestion1 = LongAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();
        longAnswerQuestion2 = LongAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();
        longAnswerQuestion3 = LongAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();
        longAnswerQuestion4 = LongAnswerQuestionEntity.builder().countAnswers(QUESTION_DEFAULT_COUNT).question(QUESTION_CONTENT).build();

        longAnswerQuestions = List.of(longAnswerQuestion1, longAnswerQuestion2, longAnswerQuestion3, longAnswerQuestion4);
        longAnswerQuestions = longAnswerQuestionRepository.saveAll(longAnswerQuestions);

        form1 = FormEntity.builder()
                .dropdownQuestions(new ArrayList<>(List.of(dropdownQuestion1, dropdownQuestion3)))
                .lineScaleQuestions(new ArrayList<>(List.of(lineScaleQuestion2)))
                .multipleChoiceQuestions(new ArrayList<>(List.of(multipleChoiceQuestion3)))
                .singleChoiceQuestions(new ArrayList<>(List.of(singleChoiceQuestion1)))
                .shortAnswerQuestions(new ArrayList<>(List.of(shortAnswerQuestion1, shortAnswerQuestion4)))
                .longAnswerQuestions(new ArrayList<>(List.of(longAnswerQuestion2, longAnswerQuestion4))).build();
        form1 = formRepository.save(form1);

        dropdownQuestion1.setForm(form1);
        dropdownQuestionRepository.save(dropdownQuestion1);
        dropdownQuestion3.setForm(form1);
        dropdownQuestionRepository.save(dropdownQuestion3);
        lineScaleQuestion2.setForm(form1);
        lineScaleQuestionRepository.save(lineScaleQuestion2);
        multipleChoiceQuestion3.setForm(form1);
        multipleChoiceQuestionRepository.save(multipleChoiceQuestion3);
        singleChoiceQuestion1.setForm(form1);
        singleChoiceQuestionRepository.save(singleChoiceQuestion1);
        shortAnswerQuestion1.setForm(form1);
        shortAnswerQuestionRepository.save(shortAnswerQuestion1);
        shortAnswerQuestion4.setForm(form1);
        shortAnswerQuestionRepository.save(shortAnswerQuestion4);
        longAnswerQuestion2.setForm(form1);
        longAnswerQuestionRepository.save(longAnswerQuestion2);
        longAnswerQuestion4.setForm(form1);
        longAnswerQuestionRepository.save(longAnswerQuestion4);

        form2 = FormEntity.builder()
                .dropdownQuestions(new ArrayList<>(List.of(dropdownQuestion4)))
                .lineScaleQuestions(new ArrayList<>(List.of(lineScaleQuestion1, lineScaleQuestion3)))
                .multipleChoiceQuestions(new ArrayList<>(List.of(multipleChoiceQuestion1)))
                .singleChoiceQuestions(new ArrayList<>(List.of(singleChoiceQuestion4)))
                .shortAnswerQuestions(new ArrayList<>(List.of()))
                .longAnswerQuestions(new ArrayList<>(List.of(longAnswerQuestion1))).build();
        form2 = formRepository.save(form2);

        form3 = FormEntity.builder()
                .dropdownQuestions(new ArrayList<>(List.of(dropdownQuestion2)))
                .lineScaleQuestions(new ArrayList<>(List.of(lineScaleQuestion4)))
                .multipleChoiceQuestions(new ArrayList<>(List.of(multipleChoiceQuestion2, multipleChoiceQuestion4)))
                .singleChoiceQuestions(new ArrayList<>(List.of(singleChoiceQuestion2, singleChoiceQuestion3)))
                .shortAnswerQuestions(new ArrayList<>(List.of(shortAnswerQuestion2, shortAnswerQuestion3)))
                .longAnswerQuestions(new ArrayList<>(List.of(longAnswerQuestion3))).build();
        form3 = formRepository.save(form3);

    }

    @AfterAll
    void afterAll() {
        formCompletedRepository.deleteAll();

        formRepository.deleteAll();

        dropdownQuestionRepository.deleteAll();
        lineScaleQuestionRepository.deleteAll();
        multipleChoiceQuestionRepository.deleteAll();
        singleChoiceQuestionRepository.deleteAll();
        shortAnswerQuestionRepository.deleteAll();
        longAnswerQuestionRepository.deleteAll();
    }

    @Transactional
    @ParameterizedTest
    @MethodSource("questionProvider")
    void givenCompletedForm_whenSubmit_thenConcreteFormSubmittedAndOptionsIncrementedAndAnswersAdded(
            FormEntity submittedForm,
            List<DropdownQuestionAnswerEntity> dropdownAnswering,
            List<LineScaleQuestionAnswerEntity> lineScaleAnswering,
            List<MultipleChoiceQuestionAnswerEntity> multipleChoiceAnswering,
            List<SingleChoiceQuestionAnswerEntity> singleChoiceAnswering,
            List<ShortAnswerQuestionAnswerEntity> shortAnswerAnswering,
            List<LongAnswerQuestionAnswerEntity> longAnswerAnswering
    ) throws Exception {
        // given
        Stream<QuestionAnswering> dropdownQuestionAnsweringStream = dropdownAnswering.stream().map(map -> questionMapperManager.mapToAnswering(map));
        Stream<QuestionAnswering> lineScaleQuestionAnsweringStream = lineScaleAnswering.stream().map(map -> questionMapperManager.mapToAnswering(map));
        Stream<QuestionAnswering> multipleChoiceQuestionAnsweringStream = multipleChoiceAnswering.stream().map(map -> questionMapperManager.mapToAnswering(map));
        Stream<QuestionAnswering> singleChoiceQuestionAnsweringStream = singleChoiceAnswering.stream().map(map -> questionMapperManager.mapToAnswering(map));
        Stream<QuestionAnswering> shortAnswerQuestionAnsweringStream = shortAnswerAnswering.stream().map(map -> questionMapperManager.mapToAnswering(map));
        Stream<QuestionAnswering> longAnswerQuestionAnsweringStream = longAnswerAnswering.stream().map(map -> questionMapperManager.mapToAnswering(map));

        List<QuestionAnswering> answeredQuestion = Stream.of(
                        dropdownQuestionAnsweringStream,
                        lineScaleQuestionAnsweringStream,
                        multipleChoiceQuestionAnsweringStream,
                        singleChoiceQuestionAnsweringStream,
                        shortAnswerQuestionAnsweringStream,
                        longAnswerQuestionAnsweringStream)
                .flatMap(questionStream -> questionStream).collect(Collectors.toList());

        FormCompleted formCompleted = FormCompleted.builder()
                .completedFormId(submittedForm == null ? null : submittedForm.getId())
                .answers(answeredQuestion)
                .build();

        String requestJson = mapper.writeValueAsString(formCompleted);

        // when

        String responseJson = mockMvc.perform(post(URL + "/submit")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        FormCompleted responseFormCompleted = mapper.readValue(responseJson, FormCompleted.class);

        List<Long> dropdownChosenAnswerCount = dropdownAnswering.stream().map(dropdownQuestionConcreteAnswering -> optionRepository.getById(dropdownQuestionConcreteAnswering.getOption().getId()).getCount()).collect(Collectors.toList());
        List<Long> lineScaleChosenAnswerCount = lineScaleAnswering.stream().map(lineScaleQuestionConcreteAnswering -> optionRepository.getById(lineScaleQuestionConcreteAnswering.getOption().getId()).getCount()).collect(Collectors.toList());
        List<Long> multipleChoiceChosenAnswerCount = multipleChoiceAnswering.stream().flatMap(multipleChoiceQuestionConcreteAnswering -> multipleChoiceQuestionConcreteAnswering.getOptions().stream().map(m -> optionRepository.getById(m.getId()).getCount())).collect(Collectors.toList());
        List<Long> singleChoiceChosenAnswerCount = singleChoiceAnswering.stream().map(singleChoiceQuestionConcreteAnswering -> optionRepository.getById(singleChoiceQuestionConcreteAnswering.getOption().getId()).getCount()).collect(Collectors.toList());

        List<Long> dropdownQuestionAnswersCount = dropdownAnswering.stream().map(integer -> dropdownQuestionRepository.getById(integer.getQuestion().getId()).getCountAnswers()).collect(Collectors.toList());
        List<Long> lineScaleQuestionAnswersCount = lineScaleAnswering.stream().map(integer -> lineScaleQuestionRepository.getById(integer.getQuestion().getId()).getCountAnswers()).collect(Collectors.toList());
        List<Long> multipleChoiceQuestionAnswersCount = multipleChoiceAnswering.stream().map(integer -> multipleChoiceQuestionRepository.getById(integer.getQuestion().getId()).getCountAnswers()).collect(Collectors.toList());
        List<Long> singleChoiceQuestionAnswersCount = singleChoiceAnswering.stream().map(integer -> singleChoiceQuestionRepository.getById(integer.getQuestion().getId()).getCountAnswers()).collect(Collectors.toList());
        List<Long> shortAnswerQuestionAnswersCount = shortAnswerAnswering.stream().map(integer -> shortAnswerQuestionRepository.getById(integer.getQuestion().getId()).getCountAnswers()).collect(Collectors.toList());
        List<Long> longAnswerQuestionAnswersCount = longAnswerAnswering.stream().map(integer -> longAnswerQuestionRepository.getById(integer.getQuestion().getId()).getCountAnswers()).collect(Collectors.toList());

        // then
        assertAll(
                () -> assertNotNull(responseFormCompleted),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> DROPDOWN.equals(answer.getType()))
                        .allMatch(dropdownAnswer -> {
                            Optional<DropdownQuestionAnswerEntity> foundDropdownCorrespondingEntity = dropdownAnswering
                                    .stream()
                                    .filter(dropdownEntity -> Objects.equals(dropdownEntity.getQuestion().getId(), dropdownAnswer.getId()))
                                    .findFirst();
                            return foundDropdownCorrespondingEntity.isPresent() &&
                                    foundDropdownCorrespondingEntity.get().getOption().getContent().equals(dropdownAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> LINE_SCALE.equals(answer.getType()))
                        .allMatch(lineScaleAnswer -> {
                            Optional<LineScaleQuestionAnswerEntity> foundLineScaleCorrespondingEntity = lineScaleAnswering
                                    .stream()
                                    .filter(dropdownEntity -> Objects.equals(dropdownEntity.getQuestion().getId(), lineScaleAnswer.getId()))
                                    .findFirst();
                            return foundLineScaleCorrespondingEntity.isPresent() &&
                                    foundLineScaleCorrespondingEntity.get().getOption().getContent().equals(lineScaleAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> MULTIPLE_CHOICE.equals(answer.getType()))
                        .allMatch(multipleChoiceAnswer -> {
                            Optional<MultipleChoiceQuestionAnswerEntity> foundMultipleChoiceCorrespondingEntity = multipleChoiceAnswering
                                    .stream()
                                    .filter(multipleChoiceEntity -> Objects.equals(multipleChoiceEntity.getQuestion().getId(), multipleChoiceAnswer.getId()))
                                    .findFirst();
                            return foundMultipleChoiceCorrespondingEntity.isPresent() &&
                                    foundMultipleChoiceCorrespondingEntity.get().getOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent().equals(multipleChoiceAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> SINGLE_CHOICE.equals(answer.getType()))
                        .allMatch(singleChoiceAnswer -> {
                            Optional<SingleChoiceQuestionAnswerEntity> foundSingleChoiceCorrespondingEntity = singleChoiceAnswering
                                    .stream()
                                    .filter(singleChoiceEntity -> Objects.equals(singleChoiceEntity.getQuestion().getId(), singleChoiceAnswer.getId()))
                                    .findFirst();
                            return foundSingleChoiceCorrespondingEntity.isPresent() &&
                                    foundSingleChoiceCorrespondingEntity.get().getOption().getContent().equals(singleChoiceAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> SHORT_ANSWER.equals(answer.getType()))
                        .allMatch(shortAnswer -> {
                            Optional<ShortAnswerQuestionAnswerEntity> foundShortAnswerCorrespondingEntity = shortAnswerAnswering
                                    .stream()
                                    .filter(shortAnswerEntity -> Objects.equals(shortAnswerEntity.getQuestion().getId(), shortAnswer.getId()))
                                    .findFirst();

                            return foundShortAnswerCorrespondingEntity.isPresent() &&
                                    foundShortAnswerCorrespondingEntity.get().getAnswer().getContent().equals(shortAnswer.getAnswer());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> LONG_ANSWER.equals(answer.getType()))
                        .allMatch(longAnswer -> {
                            Optional<LongAnswerQuestionAnswerEntity> foundShortAnswerCorrespondingEntity = longAnswerAnswering
                                    .stream()
                                    .filter(longAnswerEntity -> Objects.equals(longAnswerEntity.getQuestion().getId(), longAnswer.getId()))
                                    .findFirst();

                            return foundShortAnswerCorrespondingEntity.isPresent() &&
                                    foundShortAnswerCorrespondingEntity.get().getAnswer().getContent().equals(longAnswer.getAnswer());
                        })),

                () -> assertTrue(dropdownChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count))),
                () -> assertTrue(lineScaleChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count))),
                () -> assertTrue(multipleChoiceChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count))),
                () -> assertTrue(singleChoiceChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count))),

                () -> assertTrue(dropdownQuestionAnswersCount.stream().allMatch(count -> Objects.equals(EXPECTED_QUESTION_COUNT, count))),
                () -> assertTrue(lineScaleQuestionAnswersCount.stream().allMatch(count -> Objects.equals(EXPECTED_QUESTION_COUNT, count))),
                () -> assertTrue(multipleChoiceQuestionAnswersCount.stream().allMatch(count -> Objects.equals(EXPECTED_QUESTION_COUNT, count))),
                () -> assertTrue(singleChoiceQuestionAnswersCount.stream().allMatch(count -> Objects.equals(EXPECTED_QUESTION_COUNT, count))),
                () -> assertTrue(shortAnswerQuestionAnswersCount.stream().allMatch(count -> Objects.equals(EXPECTED_QUESTION_COUNT, count))),
                () -> assertTrue(longAnswerQuestionAnswersCount.stream().allMatch(count -> Objects.equals(EXPECTED_QUESTION_COUNT, count)))
        );
    }
}
