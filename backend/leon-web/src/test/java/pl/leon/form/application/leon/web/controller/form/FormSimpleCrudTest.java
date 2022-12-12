package pl.leon.form.application.leon.web.controller.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pl.leon.form.application.leon.model.request.FormRequest;
import pl.leon.form.application.leon.model.request.questions.OptionRequest;
import pl.leon.form.application.leon.model.request.questions.QuestionRequest;
import pl.leon.form.application.leon.model.response.FormResponse;
import pl.leon.form.application.leon.repository.DropdownQuestionRepository;
import pl.leon.form.application.leon.repository.LineScaleQuestionRepository;
import pl.leon.form.application.leon.repository.LongAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.MultipleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.ShortAnswerQuestionRepository;
import pl.leon.form.application.leon.repository.SingleChoiceQuestionRepository;
import pl.leon.form.application.leon.repository.UserRepository;
import pl.leon.form.application.leon.repository.entities.UserEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class FormSimpleCrudTest {

    private static final String URL = "/api/form";

    private static final LocalDate FORM_DATE_TO = LocalDate.now().plusMonths(1);
    private static final boolean DISABLE_FORM_QUESTIONS_AFTER_DATE_TO = true;

    private static final String QUESTION_1_CONTENT_DROPDOWN = "Pytanie 1 lista wybieralna";
    private static final String QUESTION_2_CONTENT_DROPDOWN = "Pytanie 2 lista wybieralna";
    private static final String QUESTION_3_CONTENT_DROPDOWN = "Pytanie 3 lista wybieralna";
    private static final String QUESTION_1_CONTENT_LINE_SCALE = "Pytanie 1 skala liniowa";
    private static final String QUESTION_2_CONTENT_LINE_SCALE = "Pytanie 2 skala liniowa";
    private static final String QUESTION_1_CONTENT_LONG_ANSWER = "Pytanie 1 długa odpowiedz";
    private static final String QUESTION_2_CONTENT_LONG_ANSWER = "Pytanie 2 długa odpowiedz";
    private static final String QUESTION_3_CONTENT_LONG_ANSWER = "Pytanie 3 długa odpowiedz";
    private static final String QUESTION_4_CONTENT_LONG_ANSWER = "Pytanie 4 długa odpowiedz";
    private static final String QUESTION_1_CONTENT_MULTIPLE_CHOICE = "Pytanie 1 wielokrotnego wyboru";
    private static final String QUESTION_2_CONTENT_MULTIPLE_CHOICE = "Pytanie 2 wielokrotnego wyboru";
    private static final String QUESTION_1_CONTENT_SHORT_ANSWER = "Pytanie 1 krótka odpowiedz";
    private static final String QUESTION_1_CONTENT_SINGLE_CHOICE = "Pytanie 1 jednokrotnego wyboru";
    private static final String QUESTION_2_CONTENT_SINGLE_CHOICE = "Pytanie 2 jednokrotnego wyboru";

    private static final List<String> DROPDOWN_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_DROPDOWN, QUESTION_2_CONTENT_DROPDOWN, QUESTION_3_CONTENT_DROPDOWN);
    private static final List<String> LINE_SCALE_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_LINE_SCALE, QUESTION_2_CONTENT_LINE_SCALE);
    private static final List<String> LONG_ANSWER_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_LONG_ANSWER, QUESTION_2_CONTENT_LONG_ANSWER, QUESTION_3_CONTENT_LONG_ANSWER, QUESTION_4_CONTENT_LONG_ANSWER);
    private static final List<String> MULTIPLE_CHOICE_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_MULTIPLE_CHOICE, QUESTION_2_CONTENT_MULTIPLE_CHOICE);
    private static final List<String> SHORT_ANSWER_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_SHORT_ANSWER);
    private static final List<String> SINGLE_CHOICE_QUESTION_CONTENTS = List.of(QUESTION_1_CONTENT_SINGLE_CHOICE, QUESTION_2_CONTENT_SINGLE_CHOICE);

    private static final String USERNAME = "user";

    private static final String ANSWER_1 = "Odpowiedz 1";
    private static final String ANSWER_2 = "Odpowiedz 2";
    private static final String ANSWER_3 = "Odpowiedz 3";
    private static final String ANSWER_4 = "Odpowiedz 4";

    private static final Long EXPECTED_DROPDOWN_QUESTIONS = 3L;
    private static final Long EXPECTED_LINE_SCALE_QUESTIONS = 2L;
    private static final Long EXPECTED_LONG_ANSWER_QUESTIONS = 4L;
    private static final Long EXPECTED_MULTIPLE_CHOICE_QUESTIONS = 2L;
    private static final Long EXPECTED_SHORT_ANSWER_QUESTIONS = 1L;
    private static final Long EXPECTED_SINGLE_CHOICE_QUESTIONS = 2L;


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
    private ObjectMapper mapper;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class AddNewFormTest {

        private FormRequest formRequest;

        @BeforeAll
        void beforeAll() {
            userRepository.save(UserEntity.builder().username(USERNAME).build());

            QuestionRequest dropDownQuestion1 = QuestionRequest.builder()
                    .question(QUESTION_1_CONTENT_DROPDOWN)
                    .type(DROPDOWN)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    ))
                    .build();

            QuestionRequest dropDownQuestion2 = QuestionRequest.builder()
                    .question(QUESTION_2_CONTENT_DROPDOWN)
                    .type(DROPDOWN)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    )).build();

            QuestionRequest dropDownQuestion3 = QuestionRequest.builder()
                    .question(QUESTION_3_CONTENT_DROPDOWN)
                    .type(DROPDOWN)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    )).build();

            QuestionRequest lineScaleQuestion1 = QuestionRequest.builder()
                    .question(QUESTION_1_CONTENT_LINE_SCALE)
                    .type(LINE_SCALE)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    )).build();

            QuestionRequest lineScaleQuestion2 = QuestionRequest.builder()
                    .question(QUESTION_2_CONTENT_LINE_SCALE)
                    .type(LINE_SCALE)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    )).build();

            QuestionRequest longAnswerQuestion1 = QuestionRequest.builder()
                    .question(QUESTION_1_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionRequest longAnswerQuestion2 = QuestionRequest.builder()
                    .question(QUESTION_2_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionRequest longAnswerQuestion3 = QuestionRequest.builder()
                    .question(QUESTION_3_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionRequest longAnswerQuestion4 = QuestionRequest.builder()
                    .question(QUESTION_4_CONTENT_LONG_ANSWER)
                    .type(LONG_ANSWER)
                    .build();

            QuestionRequest multipleChoiceQuestion1 = QuestionRequest.builder()
                    .question(QUESTION_1_CONTENT_MULTIPLE_CHOICE)
                    .type(MULTIPLE_CHOICE)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    )).build();

            QuestionRequest multipleChoiceQuestion2 = QuestionRequest.builder()
                    .question(QUESTION_2_CONTENT_MULTIPLE_CHOICE)
                    .type(MULTIPLE_CHOICE)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    )).build();

            QuestionRequest shortAnswerQuestion1 = QuestionRequest.builder()
                    .question(QUESTION_1_CONTENT_SHORT_ANSWER)
                    .type(SHORT_ANSWER)
                    .build();

            QuestionRequest singleChoiceQuestion1 = QuestionRequest.builder()
                    .question(QUESTION_1_CONTENT_SINGLE_CHOICE)
                    .type(SINGLE_CHOICE)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    ))
                    .build();

            QuestionRequest singleChoiceQuestion2 = QuestionRequest.builder()
                    .question(QUESTION_2_CONTENT_SINGLE_CHOICE)
                    .type(SINGLE_CHOICE)
                    .options(List.of(
                            OptionRequest.builder().content(ANSWER_1).build(),
                            OptionRequest.builder().content(ANSWER_2).build(),
                            OptionRequest.builder().content(ANSWER_3).build(),
                            OptionRequest.builder().content(ANSWER_4).build()
                    ))
                    .build();

            formRequest = FormRequest.builder()
                    .dateTo(FORM_DATE_TO)
                    .disableQuestionsAfterDateTo(DISABLE_FORM_QUESTIONS_AFTER_DATE_TO)
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
        void givenFormRequest_whenAddNewForm_thenResponseContentEqualAndEachQuestionInDatabase() throws Exception {
            // given
            String requestBody = mapper.writeValueAsString(formRequest);

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
                    () -> assertEquals(DISABLE_FORM_QUESTIONS_AFTER_DATE_TO, formResponse.isDisableQuestionsAfterDateTo()),
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
}
