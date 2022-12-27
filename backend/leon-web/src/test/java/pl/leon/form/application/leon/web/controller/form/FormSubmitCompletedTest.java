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
import pl.leon.form.application.leon.repository.OptionsEntity;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                        Map.of(
                                dropdownQuestion1, 2,
                                dropdownQuestion3, 1
                        ),
                        Map.of(
                                lineScaleQuestion2, 0
                        ),
                        Map.of(
                                multipleChoiceQuestion3, 3
                        ),
                        Map.of(singleChoiceQuestion1, 1),
                        Map.of(
                                shortAnswerQuestion1, ANSWER_FOR_TEXT_QUESTION_1,
                                shortAnswerQuestion4, ANSWER_FOR_TEXT_QUESTION_3
                        ),
                        Map.of(
                                longAnswerQuestion2, ANSWER_FOR_TEXT_QUESTION_2,
                                longAnswerQuestion4, ANSWER_FOR_TEXT_QUESTION_4
                        )
                ),
                Arguments.of(
                        null,
                        Map.of(
                                dropdownQuestion1, 0,
                                dropdownQuestion2, 1
                        ),
                        Map.of(
                                lineScaleQuestion1, 2,
                                lineScaleQuestion3, 3
                        ),
                        Map.of(
                                multipleChoiceQuestion3, 1,
                                multipleChoiceQuestion4, 2
                        ),
                        Map.of(singleChoiceQuestion1, 0),
                        Map.of(
                                shortAnswerQuestion1, ANSWER_FOR_TEXT_QUESTION_1,
                                shortAnswerQuestion3, ANSWER_FOR_TEXT_QUESTION_2,
                                shortAnswerQuestion4, ANSWER_FOR_TEXT_QUESTION_3
                        ),
                        Map.of(longAnswerQuestion4, ANSWER_FOR_TEXT_QUESTION_4)
                )
        );
    }

    @BeforeAll
    @Transactional
    void beforeAll() {
        dropdownQuestion1 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        dropdownQuestion2 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        dropdownQuestion3 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        dropdownQuestion4 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        dropdownQuestions = List.of(dropdownQuestion1, dropdownQuestion2, dropdownQuestion3, dropdownQuestion4);
        dropdownQuestions = dropdownQuestionRepository.saveAll(dropdownQuestions);

        lineScaleQuestion1 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        lineScaleQuestion2 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        lineScaleQuestion3 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        lineScaleQuestion4 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        lineScaleQuestions = List.of(lineScaleQuestion1, lineScaleQuestion2, lineScaleQuestion3, lineScaleQuestion4);
        lineScaleQuestions = lineScaleQuestionRepository.saveAll(lineScaleQuestions);

        multipleChoiceQuestion1 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        multipleChoiceQuestion2 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        multipleChoiceQuestion3 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        multipleChoiceQuestion4 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        multipleChoiceQuestions = List.of(multipleChoiceQuestion1, multipleChoiceQuestion2, multipleChoiceQuestion3, multipleChoiceQuestion4);
        multipleChoiceQuestions = multipleChoiceQuestionRepository.saveAll(multipleChoiceQuestions);

        singleChoiceQuestion1 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        singleChoiceQuestion2 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        singleChoiceQuestion3 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();
        singleChoiceQuestion4 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).count(ANSWER_DEFAULT_COUNT).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).count(ANSWER_DEFAULT_COUNT).build()))).build();

        singleChoiceQuestions = List.of(singleChoiceQuestion1, singleChoiceQuestion2, singleChoiceQuestion3, singleChoiceQuestion4);
        singleChoiceQuestions = singleChoiceQuestionRepository.saveAll(singleChoiceQuestions);

        shortAnswerQuestion1 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        shortAnswerQuestion2 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        shortAnswerQuestion3 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        shortAnswerQuestion4 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();

        shortAnswerQuestions = List.of(shortAnswerQuestion1, shortAnswerQuestion2, shortAnswerQuestion3, shortAnswerQuestion4);
        shortAnswerQuestions = shortAnswerQuestionRepository.saveAll(shortAnswerQuestions);

        longAnswerQuestion1 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        longAnswerQuestion2 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        longAnswerQuestion3 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        longAnswerQuestion4 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();

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
            Map<DropdownQuestionEntity, Integer> dropdownQuestionAndAnswerNumber,
            Map<LineScaleQuestionEntity, Integer> lineScaleQuestionAndAnswerNumber,
            Map<MultipleChoiceQuestionEntity, Integer> multipleChoiceQuestionAndAnswerNumber,
            Map<SingleChoiceQuestionEntity, Integer> singleChoiceQuestionAndAnswerNumber,
            Map<ShortAnswerQuestionEntity, String> shortAnswerQuestionAndAnswerNumber,
            Map<LongAnswerQuestionEntity, String> longAnswerQuestionAndAnswerNumber
    ) throws Exception {
        // given
        Stream<QuestionAnswering> dropdownQuestionAnsweringStream = dropdownQuestionAndAnswerNumber.entrySet().stream().map(map -> questionMapperManager.mapToAnswering(new AbstractMap.SimpleEntry<>(map.getKey(), map.getKey().getOptions().get(map.getValue()))));
        Stream<QuestionAnswering> lineScaleQuestionAnsweringStream = lineScaleQuestionAndAnswerNumber.entrySet().stream().map(map -> questionMapperManager.mapToAnswering(new AbstractMap.SimpleEntry<>(map.getKey(), map.getKey().getOptions().get(map.getValue()))));
        Stream<QuestionAnswering> multipleChoiceQuestionAnsweringStream = multipleChoiceQuestionAndAnswerNumber.entrySet().stream().map(map -> questionMapperManager.mapToAnswering(new AbstractMap.SimpleEntry<>(map.getKey(), OptionsEntity.builder().chosenOptions(new ArrayList<>(List.of(map.getKey().getOptions().get(map.getValue())))).build())));
        Stream<QuestionAnswering> singleChoiceQuestionAnsweringStream = singleChoiceQuestionAndAnswerNumber.entrySet().stream().map(map -> questionMapperManager.mapToAnswering(new AbstractMap.SimpleEntry<>(map.getKey(), map.getKey().getOptions().get(map.getValue()))));
        Stream<QuestionAnswering> shortAnswerQuestionAnsweringStream = shortAnswerQuestionAndAnswerNumber.entrySet().stream().map(map -> questionMapperManager.mapToAnswering(new AbstractMap.SimpleEntry<>(map.getKey(), AnswerEntity.builder().content(map.getValue()).build())));
        Stream<QuestionAnswering> longAnswerQuestionAnsweringStream = longAnswerQuestionAndAnswerNumber.entrySet().stream().map(map -> questionMapperManager.mapToAnswering(new AbstractMap.SimpleEntry<>(map.getKey(), AnswerEntity.builder().content(map.getValue()).build())));

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

        List<Long> dropdownChosenAnswerCount = dropdownQuestionAndAnswerNumber.entrySet().stream().map(keyValue -> dropdownQuestionRepository.getById(keyValue.getKey().getId()).getOptions().get(keyValue.getValue()).getCount()).collect(Collectors.toList());
        List<Long> lineScaleChosenAnswerCount = lineScaleQuestionAndAnswerNumber.entrySet().stream().map(keyValue -> lineScaleQuestionRepository.getById(keyValue.getKey().getId()).getOptions().get(keyValue.getValue()).getCount()).collect(Collectors.toList());
        List<Long> multipleChoiceChosenAnswerCount = multipleChoiceQuestionAndAnswerNumber.entrySet().stream().map(keyValue -> multipleChoiceQuestionRepository.getById(keyValue.getKey().getId()).getOptions().get(keyValue.getValue()).getCount()).collect(Collectors.toList());
        List<Long> singleChoiceChosenAnswerCount = singleChoiceQuestionAndAnswerNumber.entrySet().stream().map(keyValue -> singleChoiceQuestionRepository.getById(keyValue.getKey().getId()).getOptions().get(keyValue.getValue()).getCount()).collect(Collectors.toList());

        // then
        assertAll(
                () -> assertNotNull(responseFormCompleted),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> DROPDOWN.equals(answer.getType()))
                        .allMatch(dropdownAnswer -> {
                            Optional<Map.Entry<DropdownQuestionEntity, Integer>> foundDropdownCorrespondingEntity = dropdownQuestionAndAnswerNumber
                                    .entrySet().stream()
                                    .filter(dropdownEntity -> Objects.equals(dropdownEntity.getKey().getId(), dropdownAnswer.getId()))
                                    .findFirst();
                            return foundDropdownCorrespondingEntity.isPresent() &&
                                    options.get(foundDropdownCorrespondingEntity.get().getValue()).equals(dropdownAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> LINE_SCALE.equals(answer.getType()))
                        .allMatch(lineScaleAnswer -> {
                            Optional<Map.Entry<LineScaleQuestionEntity, Integer>> foundLineScaleCorrespondingEntity = lineScaleQuestionAndAnswerNumber
                                    .entrySet().stream()
                                    .filter(dropdownEntity -> Objects.equals(dropdownEntity.getKey().getId(), lineScaleAnswer.getId()))
                                    .findFirst();
                            return foundLineScaleCorrespondingEntity.isPresent() &&
                                    options.get(foundLineScaleCorrespondingEntity.get().getValue()).equals(lineScaleAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> MULTIPLE_CHOICE.equals(answer.getType()))
                        .allMatch(multipleChoiceAnswer -> {
                            Optional<Map.Entry<MultipleChoiceQuestionEntity, Integer>> foundMultipleChoiceCorrespondingEntity = multipleChoiceQuestionAndAnswerNumber
                                    .entrySet().stream()
                                    .filter(multipleChoiceEntity -> Objects.equals(multipleChoiceEntity.getKey().getId(), multipleChoiceAnswer.getId()))
                                    .findFirst();
                            return foundMultipleChoiceCorrespondingEntity.isPresent() &&
                                    options.get(foundMultipleChoiceCorrespondingEntity.get().getValue()).equals(multipleChoiceAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> SINGLE_CHOICE.equals(answer.getType()))
                        .allMatch(singleChoiceAnswer -> {
                            Optional<Map.Entry<SingleChoiceQuestionEntity, Integer>> foundSingleChoiceCorrespondingEntity = singleChoiceQuestionAndAnswerNumber
                                    .entrySet().stream()
                                    .filter(singleChoiceEntity -> Objects.equals(singleChoiceEntity.getKey().getId(), singleChoiceAnswer.getId()))
                                    .findFirst();
                            return foundSingleChoiceCorrespondingEntity.isPresent() &&
                                    options.get(foundSingleChoiceCorrespondingEntity.get().getValue()).equals(singleChoiceAnswer.getChosenOptions().stream().findFirst().orElseThrow(AssertionError::new).getContent());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> SHORT_ANSWER.equals(answer.getType()))
                        .allMatch(shortAnswer -> {
                            Optional<Map.Entry<ShortAnswerQuestionEntity, String>> foundShortAnswerCorrespondingEntity = shortAnswerQuestionAndAnswerNumber
                                    .entrySet().stream()
                                    .filter(shortAnswerEntity -> Objects.equals(shortAnswerEntity.getKey().getId(), shortAnswer.getId()))
                                    .findFirst();

                            return foundShortAnswerCorrespondingEntity.isPresent() &&
                                    foundShortAnswerCorrespondingEntity.get().getValue().equals(shortAnswer.getAnswer());
                        })),
                () -> assertTrue(responseFormCompleted.getAnswers().stream()
                        .filter(answer -> LONG_ANSWER.equals(answer.getType()))
                        .allMatch(longAnswer -> {
                            Optional<Map.Entry<LongAnswerQuestionEntity, String>> foundShortAnswerCorrespondingEntity = longAnswerQuestionAndAnswerNumber
                                    .entrySet().stream()
                                    .filter(longAnswerEntity -> Objects.equals(longAnswerEntity.getKey().getId(), longAnswer.getId()))
                                    .findFirst();

                            return foundShortAnswerCorrespondingEntity.isPresent() &&
                                    foundShortAnswerCorrespondingEntity.get().getValue().equals(longAnswer.getAnswer());
                        })),
                () -> assertTrue(dropdownChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count))),
                () -> assertTrue(lineScaleChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count))),
                () -> assertTrue(multipleChoiceChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count))),
                () -> assertTrue(singleChoiceChosenAnswerCount.stream().allMatch(count -> Objects.equals(EXPECTED_ANSWER_COUNT, count)))
        );
    }
}
