package pl.leon.form.application.leon.web.controller.form;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.leon.form.application.leon.model.request.forms.FormCreateRequest;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.request.questions.QuestionCreateRequest;
import pl.leon.form.application.leon.model.response.forms.FormResponse;
import pl.leon.form.application.leon.model.response.forms.FormSnippetResponse;
import pl.leon.form.application.leon.repository.DbMocker;
import pl.leon.form.application.leon.repository.question.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.question.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.question.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.question.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.question.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.question.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.UserRepository;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.repository.entities.UserEntity;
import pl.leon.form.application.leon.util.RestPageImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.DROPDOWN;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LINE_SCALE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.LONG_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.MULTIPLE_CHOICE;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SHORT_ANSWER;
import static pl.leon.form.application.leon.model.both.questions.type.QuestionType.SINGLE_CHOICE;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class FormSimpleCrudTest {

    private static final String URL = "/api/form";

    private static final String USERNAME = "user";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

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
    private FormRepository formRepository;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DbMocker dbMocker;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class AddNewFormTest {

        private final String ANSWER_1 = "Odpowiedz 1";
        private final String ANSWER_2 = "Odpowiedz 2";
        private final String ANSWER_3 = "Odpowiedz 3";
        private final String ANSWER_4 = "Odpowiedz 4";

        private final Long EXPECTED_DROPDOWN_QUESTIONS = 3L;
        private final Long EXPECTED_LINE_SCALE_QUESTIONS = 2L;
        private final Long EXPECTED_LONG_ANSWER_QUESTIONS = 4L;
        private final Long EXPECTED_MULTIPLE_CHOICE_QUESTIONS = 2L;
        private final Long EXPECTED_SHORT_ANSWER_QUESTIONS = 1L;
        private final Long EXPECTED_SINGLE_CHOICE_QUESTIONS = 2L;

        private final LocalDate FORM_DATE_TO = LocalDate.now().plusMonths(1);

        private final String QUESTION_1_CONTENT_DROPDOWN = "Pytanie 1 lista wybieralna";
        private final String QUESTION_2_CONTENT_DROPDOWN = "Pytanie 2 lista wybieralna";
        private final String QUESTION_3_CONTENT_DROPDOWN = "Pytanie 3 lista wybieralna";
        private final String QUESTION_1_CONTENT_LINE_SCALE = "Pytanie 1 skala liniowa";
        private final String QUESTION_2_CONTENT_LINE_SCALE = "Pytanie 2 skala liniowa";
        private final String QUESTION_1_CONTENT_LONG_ANSWER = "Pytanie 1 długa odpowiedz";
        private final String QUESTION_2_CONTENT_LONG_ANSWER = "Pytanie 2 długa odpowiedz";
        private final String QUESTION_3_CONTENT_LONG_ANSWER = "Pytanie 3 długa odpowiedz";
        private final String QUESTION_4_CONTENT_LONG_ANSWER = "Pytanie 4 długa odpowiedz";
        private final String QUESTION_1_CONTENT_MULTIPLE_CHOICE = "Pytanie 1 wielokrotnego wyboru";
        private final String QUESTION_2_CONTENT_MULTIPLE_CHOICE = "Pytanie 2 wielokrotnego wyboru";
        private final String QUESTION_1_CONTENT_SHORT_ANSWER = "Pytanie 1 krótka odpowiedz";
        private final String QUESTION_1_CONTENT_SINGLE_CHOICE = "Pytanie 1 jednokrotnego wyboru";
        private final String QUESTION_2_CONTENT_SINGLE_CHOICE = "Pytanie 2 jednokrotnego wyboru";

        private final List<String> DROPDOWN_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_DROPDOWN, QUESTION_2_CONTENT_DROPDOWN, QUESTION_3_CONTENT_DROPDOWN);
        private final List<String> LINE_SCALE_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_LINE_SCALE, QUESTION_2_CONTENT_LINE_SCALE);
        private final List<String> LONG_ANSWER_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_LONG_ANSWER, QUESTION_2_CONTENT_LONG_ANSWER, QUESTION_3_CONTENT_LONG_ANSWER, QUESTION_4_CONTENT_LONG_ANSWER);
        private final List<String> MULTIPLE_CHOICE_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_MULTIPLE_CHOICE, QUESTION_2_CONTENT_MULTIPLE_CHOICE);
        private final List<String> SHORT_ANSWER_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_SHORT_ANSWER);
        private final List<String> SINGLE_CHOICE_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_SINGLE_CHOICE, QUESTION_2_CONTENT_SINGLE_CHOICE);

        private FormCreateRequest formCreateRequest;

        @BeforeAll
        void beforeAll() {
            userRepository.save(UserEntity.builder().username(USERNAME).build());

            QuestionCreateRequest dropDownQuestion1 = QuestionCreateRequest.builder()
                    .question(QUESTION_1_CONTENT_DROPDOWN)
                    .type(DROPDOWN)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    ))
                    .build();

            QuestionCreateRequest dropDownQuestion2 = QuestionCreateRequest.builder()
                    .question(QUESTION_2_CONTENT_DROPDOWN)
                    .type(DROPDOWN)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    )).build();

            QuestionCreateRequest dropDownQuestion3 = QuestionCreateRequest.builder()
                    .question(QUESTION_3_CONTENT_DROPDOWN)
                    .type(DROPDOWN)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    )).build();

            QuestionCreateRequest lineScaleQuestion1 = QuestionCreateRequest.builder()
                    .question(QUESTION_1_CONTENT_LINE_SCALE)
                    .type(LINE_SCALE)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    )).build();

            QuestionCreateRequest lineScaleQuestion2 = QuestionCreateRequest.builder()
                    .question(QUESTION_2_CONTENT_LINE_SCALE)
                    .type(LINE_SCALE)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    )).build();

            QuestionCreateRequest longAnswerQuestion1 = QuestionCreateRequest.builder()
                    .question(QUESTION_1_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionCreateRequest longAnswerQuestion2 = QuestionCreateRequest.builder()
                    .question(QUESTION_2_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionCreateRequest longAnswerQuestion3 = QuestionCreateRequest.builder()
                    .question(QUESTION_3_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionCreateRequest longAnswerQuestion4 = QuestionCreateRequest.builder()
                    .question(QUESTION_4_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionCreateRequest multipleChoiceQuestion1 = QuestionCreateRequest.builder()
                    .question(QUESTION_1_CONTENT_MULTIPLE_CHOICE)
                    .type(MULTIPLE_CHOICE)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    )).build();

            QuestionCreateRequest multipleChoiceQuestion2 = QuestionCreateRequest.builder()
                    .question(QUESTION_2_CONTENT_MULTIPLE_CHOICE)
                    .type(MULTIPLE_CHOICE)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    )).build();

            QuestionCreateRequest shortAnswerQuestion1 = QuestionCreateRequest.builder()
                    .question(QUESTION_1_CONTENT_SHORT_ANSWER)
                    .type(SHORT_ANSWER)
                    .build();

            QuestionCreateRequest singleChoiceQuestion1 = QuestionCreateRequest.builder()
                    .question(QUESTION_1_CONTENT_SINGLE_CHOICE)
                    .type(SINGLE_CHOICE)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    ))
                    .build();

            QuestionCreateRequest singleChoiceQuestion2 = QuestionCreateRequest.builder()
                    .question(QUESTION_2_CONTENT_SINGLE_CHOICE)
                    .type(SINGLE_CHOICE)
                    .options(List.of(
                            Option.builder().content(ANSWER_1).build(),
                            Option.builder().content(ANSWER_2).build(),
                            Option.builder().content(ANSWER_3).build(),
                            Option.builder().content(ANSWER_4).build()
                    ))
                    .build();

            formCreateRequest = FormCreateRequest.builder()
                    .dateTo(FORM_DATE_TO)
                    .questions(List.of(
                            dropDownQuestion1,
                            dropDownQuestion2,
                            dropDownQuestion3,
                            lineScaleQuestion1,
                            lineScaleQuestion2,
                            longAnswerQuestion1,
                            longAnswerQuestion2,
                            longAnswerQuestion3,
                            longAnswerQuestion4,
                            multipleChoiceQuestion1,
                            multipleChoiceQuestion2,
                            shortAnswerQuestion1,
                            singleChoiceQuestion1,
                            singleChoiceQuestion2
                    ))
                    .build();
        }

        @Test
        @WithMockUser
        @Disabled("In production user is simply String (username) but here it is principal...")
        void givenFormRequest_whenAddNewForm_thenResponseContentEqualAndEachQuestionInDatabase() throws Exception {
            // given
            String requestBody = mapper.writeValueAsString(formCreateRequest);

            // when
            String jsonResponse = mockMvc.perform(post(URL + "/add")
                            .contentType(APPLICATION_JSON)
                            .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            FormResponse formResponse = mapper.readValue(jsonResponse, FormResponse.class);

            // then
            assertAll(
                    () -> assertNotNull(formResponse),
                    () -> assertEquals(FORM_DATE_TO, formResponse.getDateTo()),
                    () -> assertEquals(EXPECTED_DROPDOWN_QUESTIONS, dropdownQuestionRepository.count()),
                    () -> assertTrue(dropdownQuestionRepository.findAll().stream().allMatch(dropdownQuestion -> DROPDOWN_QUESTION_CONTENTS.contains(dropdownQuestion.getQuestion()))),
                    () -> assertEquals(EXPECTED_LINE_SCALE_QUESTIONS, lineScaleQuestionRepository.count()),
                    () -> assertTrue(lineScaleQuestionRepository.findAll().stream().allMatch(dropdownQuestion -> LINE_SCALE_QUESTION_CONTENTS.contains(dropdownQuestion.getQuestion()))),
                    () -> assertEquals(EXPECTED_LONG_ANSWER_QUESTIONS, longAnswerQuestionRepository.count()),
                    () -> assertTrue(longAnswerQuestionRepository.findAll().stream().allMatch(dropdownQuestion -> LONG_ANSWER_QUESTION_CONTENTS.contains(dropdownQuestion.getQuestion()))),
                    () -> assertEquals(EXPECTED_MULTIPLE_CHOICE_QUESTIONS, multipleChoiceQuestionRepository.count()),
                    () -> assertTrue(multipleChoiceQuestionRepository.findAll().stream().allMatch(dropdownQuestion -> MULTIPLE_CHOICE_QUESTION_CONTENTS.contains(dropdownQuestion.getQuestion()))),
                    () -> assertEquals(EXPECTED_SHORT_ANSWER_QUESTIONS, shortAnswerQuestionRepository.count()),
                    () -> assertTrue(shortAnswerQuestionRepository.findAll().stream().allMatch(dropdownQuestion -> SHORT_ANSWER_QUESTION_CONTENTS.contains(dropdownQuestion.getQuestion()))),
                    () -> assertEquals(EXPECTED_SINGLE_CHOICE_QUESTIONS, singleChoiceQuestionRepository.count()),
                    () -> assertTrue(singleChoiceQuestionRepository.findAll().stream().allMatch(dropdownQuestion -> SINGLE_CHOICE_QUESTION_CONTENTS.contains(dropdownQuestion.getQuestion())))
            );
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ListFormsTest {

        private final Long EXPECTED_FORM_LIST_LENGTH = 4L;

        private final String FORM_1_TOPIC = "Formularz temat 1";
        private final String FORM_2_TOPIC = "Formularz temat 2";
        private final String FORM_3_TOPIC = "Formularz temat 3";
        private final String FORM_4_TOPIC = "Formularz temat 4";

        private final List<String> FORM_TOPICS = List.of(FORM_1_TOPIC, FORM_2_TOPIC, FORM_3_TOPIC, FORM_4_TOPIC);

        private final String USERNAME_1 = "user1";
        private final String USERNAME_2 = "user2";
        private final String USERNAME_3 = "user3";

        @BeforeAll
        void beforeAll() {
            UserEntity user1 = userRepository.save(UserEntity.builder().username(USERNAME_1).build());
            UserEntity user2 = userRepository.save(UserEntity.builder().username(USERNAME_2).build());
            UserEntity user3 = userRepository.save(UserEntity.builder().username(USERNAME_3).build());

            formRepository.save(FormEntity.builder().user(user1).title(FORM_1_TOPIC).build());
            formRepository.save(FormEntity.builder().user(user2).title(FORM_2_TOPIC).build());
            formRepository.save(FormEntity.builder().user(user3).title(FORM_3_TOPIC).build());
            formRepository.save(FormEntity.builder().user(user1).title(FORM_4_TOPIC).build());

        }

        @Test
        void givenPersistedForms_whenListForms_thenAllPersistedFormsListed() throws Exception {
            // given

            // when
            String jsonResponse = mockMvc.perform(get(URL + "/list"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            Page<FormSnippetResponse> responseList = mapper.readValue(jsonResponse, new TypeReference<RestPageImpl<FormSnippetResponse>>() {
            });

            // then
            assertAll(
                    () -> assertNotNull(responseList),
                    () -> assertEquals(EXPECTED_FORM_LIST_LENGTH, responseList.getContent().size()),
                    () -> assertTrue(responseList.stream().allMatch(form -> FORM_TOPICS.contains(form.getTitle())))
            );
        }
    }
}
