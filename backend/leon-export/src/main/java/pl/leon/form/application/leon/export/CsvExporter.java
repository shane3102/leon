package pl.leon.form.application.leon.export;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.repository.FormCompletedRepository;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.QuestionAnswerMethodsInterface;
import pl.leon.form.application.leon.repository.question_answer.QuestionAnswerRepositoryInterface;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class CsvExporter {

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

}
