package pl.leon.form.application.leon.export;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.repository.FormCompletedRepository;
import pl.leon.form.application.leon.repository.FormRepository;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.QuestionAnswerMethodsInterface;
import pl.leon.form.application.leon.repository.entities.questions.QuestionMethodsInterface;
import pl.leon.form.application.leon.repository.question_answer.QuestionAnswerRepositoryInterface;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class CsvExporter {

    private final FormRepository formRepository;
    private final FormCompletedRepository formCompletedRepository;
    @Autowired
    private final List<QuestionAnswerRepositoryInterface> questionAnswersRepositories;

    private final CsvRowEncoder csvRowEncoder;

    public byte[] csvReportFormCompleted() {
        List<FormCompletedEntity> allCompletedRandomForms = formCompletedRepository.findAllByCompletedFormNull();

        StringBuilder sb = new StringBuilder();
        sb.append(csvRowEncoder.FORM_COMPLETED_HEADLINE);

        for (FormCompletedEntity completedRandomForm : allCompletedRandomForms) {
            sb.append(csvRowEncoder.returnFormCompletedRow(completedRandomForm));
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    public byte[] csvReportQuestionsAnswered() {
        StringBuilder sb = new StringBuilder();
        sb.append(csvRowEncoder.QUESTION_ANSWERED_HEADLINE);

        for (QuestionAnswerRepositoryInterface questionAnswersRepository : questionAnswersRepositories) {
            List<QuestionAnswerMethodsInterface> questionAnswers = questionAnswersRepository.findAllByFormCompletedCompletedFormNull();
            for (QuestionAnswerMethodsInterface questionAnswer : questionAnswers) {
                sb.append(csvRowEncoder.returnQuestionAnsweredRow(questionAnswer));
            }
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);

    }

    public String csvReportFormCompletedResultsAsString(Long formId) {
        List<QuestionMethodsInterface> allQuestionsOfForm = formRepository.getById(formId).getAllQuestions();

        StringBuilder sb = new StringBuilder();
        sb.append(csvRowEncoder.returnFormCompletedResultsRowByFormQuestions(allQuestionsOfForm));

        List<FormCompletedEntity> completedFormsByFormId = formCompletedRepository.findAllByCompletedFormId(formId);

        for (FormCompletedEntity formCompleted : completedFormsByFormId) {
            sb.append(csvRowEncoder.returnFormCompletedResultsRow(formCompleted, allQuestionsOfForm));
        }

        return sb.toString();
    }

    public String csvReportFormCompletedResultsOfQuestionsFromRandomFormsAsString(Long formId) {
        List<QuestionMethodsInterface> allQuestionsOfForm = formRepository.getById(formId).getAllQuestions();

        List<List<QuestionAnswerMethodsInterface>> listOfAnswersOfEachQuestion = allQuestionsOfForm.stream()
                .map(question -> questionAnswersRepositories.stream()
                        .map(questionRepository -> (List<QuestionAnswerMethodsInterface>) questionRepository.findAllByQuestionId(question.getId()))
                        .flatMap(Collection::stream).collect(Collectors.toList()))
                .collect(Collectors.toList());

        int maxAnswersCount = listOfAnswersOfEachQuestion.stream().max(Comparator.comparingInt(List::size)).orElse(new ArrayList<>()).size();

        StringBuilder sb = new StringBuilder();
        sb.append(csvRowEncoder.returnFormCompletedResultsRowByFormQuestions(allQuestionsOfForm));

        for (int lineNumber = 0; lineNumber < maxAnswersCount; lineNumber++) {
            sb.append(csvRowEncoder.returnFormCompletedAnswersForRandomFormsRow(lineNumber, listOfAnswersOfEachQuestion));
        }

        return sb.toString();
    }

    public byte[] csvReportFormCompletedResultsOfQuestionsFromRandomForms(Long formId) {
        return csvReportFormCompletedResultsOfQuestionsFromRandomFormsAsString(formId).getBytes(StandardCharsets.UTF_8);
    }

    public byte[] csvReportFormCompletedResults(Long formId) {
        return csvReportFormCompletedResultsAsString(formId).getBytes(StandardCharsets.UTF_8);
    }

    public byte[] csvReportFormCompletedResultsAllAnswers(Long formId) {
        return (csvReportFormCompletedResultsOfQuestionsFromRandomFormsAsString(formId) + csvReportFormCompletedResultsAsString(formId)).getBytes(StandardCharsets.UTF_8);
    }

}
