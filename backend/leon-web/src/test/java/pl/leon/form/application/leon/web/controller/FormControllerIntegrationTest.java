package pl.leon.form.application.leon.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.leon.form.application.leon.model.response.FormToCompleteResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.leon.form.application.leon.model.response.questions.type.QuestionType.DROPDOWN;
import static pl.leon.form.application.leon.model.response.questions.type.QuestionType.LINE_SCALE;
import static pl.leon.form.application.leon.model.response.questions.type.QuestionType.LONG_ANSWER;
import static pl.leon.form.application.leon.model.response.questions.type.QuestionType.MULTIPLE_CHOICE;
import static pl.leon.form.application.leon.model.response.questions.type.QuestionType.SHORT_ANSWER;
import static pl.leon.form.application.leon.model.response.questions.type.QuestionType.SINGLE_CHOICE;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FormControllerIntegrationTest {

    private static final String URL = "/api/form";

    private static final String QUESTION_CONTENT = "Pytanie na śniadanie";
    private static final String ANSWER_CONTENT_1 = "Odpowiedz 1";
    private static final String ANSWER_CONTENT_2 = "Odpowiedz 2";
    private static final String ANSWER_CONTENT_3 = "Odpowiedz 3";
    private static final String ANSWER_CONTENT_4 = "Odpowiedz 4";

    private DropdownQuestionEntity dropdownQuestion1;
    private DropdownQuestionEntity dropdownQuestion2;
    private DropdownQuestionEntity dropdownQuestion3;
    private DropdownQuestionEntity dropdownQuestion4;

    private LineScaleQuestionEntity lineScaleQuestion1;
    private LineScaleQuestionEntity lineScaleQuestion2;
    private LineScaleQuestionEntity lineScaleQuestion3;
    private LineScaleQuestionEntity lineScaleQuestion4;

    private LongAnswerQuestionEntity longAnswerQuestion1;
    private LongAnswerQuestionEntity longAnswerQuestion2;
    private LongAnswerQuestionEntity longAnswerQuestion3;
    private LongAnswerQuestionEntity longAnswerQuestion4;

    private MultipleChoiceQuestionEntity multipleChoiceQuestion1;
    private MultipleChoiceQuestionEntity multipleChoiceQuestion2;
    private MultipleChoiceQuestionEntity multipleChoiceQuestion3;
    private MultipleChoiceQuestionEntity multipleChoiceQuestion4;

    private ShortAnswerQuestionEntity shortAnswerQuestion1;
    private ShortAnswerQuestionEntity shortAnswerQuestion2;
    private ShortAnswerQuestionEntity shortAnswerQuestion3;
    private ShortAnswerQuestionEntity shortAnswerQuestion4;

    private SingleChoiceQuestionEntity singleChoiceQuestion1;
    private SingleChoiceQuestionEntity singleChoiceQuestion2;
    private SingleChoiceQuestionEntity singleChoiceQuestion3;
    private SingleChoiceQuestionEntity singleChoiceQuestion4;

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

    @BeforeAll
    void beforeAll() {
        dropdownQuestion1 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion1 = dropdownQuestionRepository.save(dropdownQuestion1);
        dropdownQuestion2 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion2 = dropdownQuestionRepository.save(dropdownQuestion2);
        dropdownQuestion3 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion3 = dropdownQuestionRepository.save(dropdownQuestion3);
        dropdownQuestion4 = DropdownQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        dropdownQuestion4 = dropdownQuestionRepository.save(dropdownQuestion4);

        lineScaleQuestion1 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion1 = lineScaleQuestionRepository.save(lineScaleQuestion1);
        lineScaleQuestion2 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion2 = lineScaleQuestionRepository.save(lineScaleQuestion2);
        lineScaleQuestion3 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion3 = lineScaleQuestionRepository.save(lineScaleQuestion3);
        lineScaleQuestion4 = LineScaleQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        lineScaleQuestion4 = lineScaleQuestionRepository.save(lineScaleQuestion4);

        multipleChoiceQuestion1 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion1 = multipleChoiceQuestionRepository.save(multipleChoiceQuestion1);
        multipleChoiceQuestion2 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion2 = multipleChoiceQuestionRepository.save(multipleChoiceQuestion2);
        multipleChoiceQuestion3 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion3 = multipleChoiceQuestionRepository.save(multipleChoiceQuestion3);
        multipleChoiceQuestion4 = MultipleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        multipleChoiceQuestion4 = multipleChoiceQuestionRepository.save(multipleChoiceQuestion4);

        singleChoiceQuestion1 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion1 = singleChoiceQuestionRepository.save(singleChoiceQuestion1);
        singleChoiceQuestion2 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion2 = singleChoiceQuestionRepository.save(singleChoiceQuestion2);
        singleChoiceQuestion3 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion3 = singleChoiceQuestionRepository.save(singleChoiceQuestion3);
        singleChoiceQuestion4 = SingleChoiceQuestionEntity.builder().question(QUESTION_CONTENT).options(new ArrayList<>(Arrays.asList(OptionEntity.builder().content(ANSWER_CONTENT_1).build(), OptionEntity.builder().content(ANSWER_CONTENT_2).build(), OptionEntity.builder().content(ANSWER_CONTENT_3).build(), OptionEntity.builder().content(ANSWER_CONTENT_4).build()))).build();
        singleChoiceQuestion4 = singleChoiceQuestionRepository.save(singleChoiceQuestion4);

        shortAnswerQuestion1 = ShortAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        shortAnswerQuestion1 = shortAnswerQuestionRepository.save(shortAnswerQuestion1);
        shortAnswerQuestion2 = ShortAnswerQuestionEntity.builder().build();
        shortAnswerQuestion2 = shortAnswerQuestionRepository.save(shortAnswerQuestion2);
        shortAnswerQuestion3 = ShortAnswerQuestionEntity.builder().build();
        shortAnswerQuestion3 = shortAnswerQuestionRepository.save(shortAnswerQuestion3);
        shortAnswerQuestion4 = ShortAnswerQuestionEntity.builder().build();
        shortAnswerQuestion4 = shortAnswerQuestionRepository.save(shortAnswerQuestion4);

        longAnswerQuestion1 = LongAnswerQuestionEntity.builder().question(QUESTION_CONTENT).build();
        longAnswerQuestion1 = longAnswerQuestionRepository.save(longAnswerQuestion1);
        longAnswerQuestion2 = LongAnswerQuestionEntity.builder().build();
        longAnswerQuestion2 = longAnswerQuestionRepository.save(longAnswerQuestion2);
        longAnswerQuestion3 = LongAnswerQuestionEntity.builder().build();
        longAnswerQuestion3 = longAnswerQuestionRepository.save(longAnswerQuestion3);
        longAnswerQuestion4 = LongAnswerQuestionEntity.builder().build();
        longAnswerQuestion4 = longAnswerQuestionRepository.save(longAnswerQuestion4);

    }

    //TODO parametrize and enchance (and name xd)
    @Test
    void given_when_then() throws Exception {
        // given

        // when
        String responseJson = mockMvc.perform(get(URL + "/get-random-form").param("answer-count", "24"))
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
                () -> assertEquals(24, formToCompleteResponse.getQuestions().size(), "Question count not equal expected"),
                () -> assertEquals(4, formToCompleteResponse.getQuestions().stream().filter(q -> DROPDOWN.equals(q.getType())).count(), "Question count not equal expected"),
                () -> assertEquals(4, formToCompleteResponse.getQuestions().stream().filter(q -> LINE_SCALE.equals(q.getType())).count(), "Question count not equal expected"),
                () -> assertEquals(4, formToCompleteResponse.getQuestions().stream().filter(q -> MULTIPLE_CHOICE.equals(q.getType())).count(), "Question count not equal expected"),
                () -> assertEquals(4, formToCompleteResponse.getQuestions().stream().filter(q -> SINGLE_CHOICE.equals(q.getType())).count(), "Question count not equal expected"),
                () -> assertEquals(4, formToCompleteResponse.getQuestions().stream().filter(q -> SHORT_ANSWER.equals(q.getType())).count(), "Question count not equal expected"),
                () -> assertEquals(4, formToCompleteResponse.getQuestions().stream().filter(q -> LONG_ANSWER.equals(q.getType())).count(), "Question count not equal expected")
        );
    }
}