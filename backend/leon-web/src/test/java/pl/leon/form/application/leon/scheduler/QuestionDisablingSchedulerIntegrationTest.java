package pl.leon.form.application.leon.scheduler;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import pl.leon.form.application.leon.repository.DbMocker;
import pl.leon.form.application.leon.repository.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LineScaleQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.LongAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.MultipleChoiceQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.ShortAnswerQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.SingleChoiceQuestionEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionDisablingSchedulerIntegrationTest {

    @Autowired
    private QuestionDisablingScheduler questionDisablingScheduler;

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
    @MockBean
    private DbMocker dbMocker;

    private static final String QUESTION_CONTENT = "Pytanie na śniadanie";
    private static final String ANSWER_CONTENT_1 = "Odpowiedz 1";
    private static final String ANSWER_CONTENT_2 = "Odpowiedz 2";
    private static final String ANSWER_CONTENT_3 = "Odpowiedz 3";
    private static final String ANSWER_CONTENT_4 = "Odpowiedz 4";

    private static DropdownQuestionEntity dropdownQuestion1;
    private static DropdownQuestionEntity dropdownQuestion2;
    private static DropdownQuestionEntity dropdownQuestion3;
    private static DropdownQuestionEntity dropdownQuestion4;

    private static List<DropdownQuestionEntity> dropdownQuestionList;

    private static LineScaleQuestionEntity lineScaleQuestion1;
    private static LineScaleQuestionEntity lineScaleQuestion2;
    private static LineScaleQuestionEntity lineScaleQuestion3;
    private static LineScaleQuestionEntity lineScaleQuestion4;

    private static List<LineScaleQuestionEntity> lineScaleQuestionList;

    private static LongAnswerQuestionEntity longAnswerQuestion1;
    private static LongAnswerQuestionEntity longAnswerQuestion2;
    private static LongAnswerQuestionEntity longAnswerQuestion3;
    private static LongAnswerQuestionEntity longAnswerQuestion4;

    private static List<LongAnswerQuestionEntity> longAnswerQuestionList;

    private static MultipleChoiceQuestionEntity multipleChoiceQuestion1;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion2;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion3;
    private static MultipleChoiceQuestionEntity multipleChoiceQuestion4;

    private static List<MultipleChoiceQuestionEntity> multipleChoiceQuestionList;

    private static ShortAnswerQuestionEntity shortAnswerQuestion1;
    private static ShortAnswerQuestionEntity shortAnswerQuestion2;
    private static ShortAnswerQuestionEntity shortAnswerQuestion3;
    private static ShortAnswerQuestionEntity shortAnswerQuestion4;

    private static List<ShortAnswerQuestionEntity> shortAnswerQuestionList;

    private static SingleChoiceQuestionEntity singleChoiceQuestion1;
    private static SingleChoiceQuestionEntity singleChoiceQuestion2;
    private static SingleChoiceQuestionEntity singleChoiceQuestion3;
    private static SingleChoiceQuestionEntity singleChoiceQuestion4;

    private static List<SingleChoiceQuestionEntity> singleChoiceQuestionList;

    private static FormEntity form;

    private static Stream<Arguments> disablingFormQuestionsProvider() {
        return Stream.of(
                // form date to, should questions be disabled
                Arguments.of(LocalDate.now(), true),
                Arguments.of(LocalDate.now().plusDays(1), false)
        );
    }

    @BeforeEach
    void setUp() {
        form = FormEntity.builder().disabled(false).build();
        formRepository.save(form);

        dropdownQuestion1 = DropdownQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion1 = dropdownQuestionRepository.save(dropdownQuestion1);
        dropdownQuestion2 = DropdownQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion2 = dropdownQuestionRepository.save(dropdownQuestion2);
        dropdownQuestion3 = DropdownQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion3 = dropdownQuestionRepository.save(dropdownQuestion3);
        dropdownQuestion4 = DropdownQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion4 = dropdownQuestionRepository.save(dropdownQuestion4);

        dropdownQuestionList = List.of(dropdownQuestion1, dropdownQuestion2, dropdownQuestion3, dropdownQuestion4);
        dropdownQuestionList = dropdownQuestionRepository.saveAll(dropdownQuestionList);

        lineScaleQuestion1 = LineScaleQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion2 = LineScaleQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion3 = LineScaleQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion4 = LineScaleQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();

        lineScaleQuestionList = List.of(lineScaleQuestion1, lineScaleQuestion2, lineScaleQuestion3, lineScaleQuestion4);
        lineScaleQuestionList = lineScaleQuestionRepository.saveAll(lineScaleQuestionList);

        multipleChoiceQuestion1 = MultipleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion2 = MultipleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion3 = MultipleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion4 = MultipleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();

        multipleChoiceQuestionList = List.of(multipleChoiceQuestion1, multipleChoiceQuestion2, multipleChoiceQuestion3, multipleChoiceQuestion4);
        multipleChoiceQuestionList = multipleChoiceQuestionRepository.saveAll(multipleChoiceQuestionList);

        singleChoiceQuestion1 = SingleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion2 = SingleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion3 = SingleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion4 = SingleChoiceQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();

        singleChoiceQuestionList = List.of(singleChoiceQuestion1, singleChoiceQuestion2, singleChoiceQuestion3, singleChoiceQuestion4);
        singleChoiceQuestionList = singleChoiceQuestionRepository.saveAll(singleChoiceQuestionList);

        shortAnswerQuestion1 = ShortAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();
        shortAnswerQuestion2 = ShortAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();
        shortAnswerQuestion3 = ShortAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();
        shortAnswerQuestion4 = ShortAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();

        shortAnswerQuestionList = List.of(shortAnswerQuestion1, shortAnswerQuestion2, shortAnswerQuestion3, shortAnswerQuestion4);
        shortAnswerQuestionList = shortAnswerQuestionRepository.saveAll(shortAnswerQuestionList);

        longAnswerQuestion1 = LongAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();
        longAnswerQuestion2 = LongAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();
        longAnswerQuestion3 = LongAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();
        longAnswerQuestion4 = LongAnswerQuestionEntity.builder().form(form).disabled(false).question(QUESTION_CONTENT).build();

        longAnswerQuestionList = List.of(longAnswerQuestion1, longAnswerQuestion2, longAnswerQuestion3, longAnswerQuestion4);
        longAnswerQuestionList = longAnswerQuestionRepository.saveAll(longAnswerQuestionList);
    }

    @AfterAll
    void tearDown() {
        dropdownQuestionRepository.deleteAll();
        lineScaleQuestionRepository.deleteAll();
        multipleChoiceQuestionRepository.deleteAll();
        singleChoiceQuestionRepository.deleteAll();
        shortAnswerQuestionRepository.deleteAll();
        longAnswerQuestionRepository.deleteAll();

        formRepository.deleteAll();
    }

    @Transactional
    @ParameterizedTest
    @MethodSource("disablingFormQuestionsProvider")
    void givenFormWithDateTo_whenDisableQuestions_thenQuestionsDisabledProperly(LocalDate disableDate,
                                                                                boolean shouldQuestionsBeDisabled) {
        // given
        form.setDateTo(disableDate);
        form = formRepository.save(form);

        // when
        questionDisablingScheduler.disableExpiredQuestionsAndForms();

        FormEntity persistedForm = formRepository.getById(form.getId());

        //then
        assertAll(
                () -> assertEquals(shouldQuestionsBeDisabled, persistedForm.isDisabled()),
                () -> assertTrue(dropdownQuestionList.stream().allMatch(question -> dropdownQuestionRepository.getById(question.getId()).isDisabled() == shouldQuestionsBeDisabled)),
                () -> assertTrue(lineScaleQuestionList.stream().allMatch(question -> lineScaleQuestionRepository.getById(question.getId()).isDisabled() == shouldQuestionsBeDisabled)),
                () -> assertTrue(multipleChoiceQuestionList.stream().allMatch(question -> multipleChoiceQuestionRepository.getById(question.getId()).isDisabled() == shouldQuestionsBeDisabled)),
                () -> assertTrue(singleChoiceQuestionList.stream().allMatch(question -> singleChoiceQuestionRepository.getById(question.getId()).isDisabled() == shouldQuestionsBeDisabled)),
                () -> assertTrue(shortAnswerQuestionList.stream().allMatch(question -> shortAnswerQuestionRepository.getById(question.getId()).isDisabled() == shouldQuestionsBeDisabled)),
                () -> assertTrue(longAnswerQuestionList.stream().allMatch(question -> longAnswerQuestionRepository.getById(question.getId()).isDisabled() == shouldQuestionsBeDisabled))
        );
    }
}
