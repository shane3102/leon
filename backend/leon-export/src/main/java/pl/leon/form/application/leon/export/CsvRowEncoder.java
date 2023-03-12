package pl.leon.form.application.leon.export;

import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.QuestionAnswerMethodsInterface;
import pl.leon.form.application.leon.repository.entities.questions.QuestionMethodsInterface;

import java.util.List;

@Component
public class CsvRowEncoder {

    public final String FORM_COMPLETED_HEADLINE = "Wygląd interfejsu;" +
            "UX interfejsu;" +
            "Czas wypełniania formularza (milisekundy);" +
            "Ilość pytań z listą rozwijaną;" +
            "Ilość pytań ze skalą liniową;" +
            "Ilość pytań z długą odpowiedzią;" +
            "Ilość pytań z wielokrotnym wyborem;" +
            "Ilość pytań z krótką odpowiedzią;" +
            "Ilość pytań z jednokrotnym wyborem\n";

    public final String QUESTION_ANSWERED_HEADLINE = "Wygląd interfejsu;" +
            "UX interfejsu;" +
            "Czas wypełniania pytania;" +
            "Typ pytania;" +
            "Treść pytania;" +
            "Liczba wybranych opcji;" +
            "Odpowiedź tekstowa\n";

    public String returnFormCompletedRow(FormCompletedEntity completedForm) {
        StringBuilder sb = new StringBuilder();
        sb.append(completedForm.getUiLevel())
                .append(';')
                .append(completedForm.getUxLevel())
                .append(';')
                .append(completedForm.getCompleteDurationInMilliseconds())
                .append(';')
                .append(completedForm.getAnsweredDropdownQuestions().size())
                .append(';')
                .append(completedForm.getAnsweredLineScaleQuestions().size())
                .append(';')
                .append(completedForm.getAnsweredLongAnswerQuestions().size())
                .append(';')
                .append(completedForm.getAnsweredMultipleChoiceQuestions().size())
                .append(';')
                .append(completedForm.getAnsweredShortAnswerQuestions().size())
                .append(';')
                .append(completedForm.getAnsweredSingleChoiceQuestions().size())
                .append('\n');

        return sb.toString();
    }

    public String returnQuestionAnsweredRow(QuestionAnswerMethodsInterface questionAnswer) {
        StringBuilder sb = new StringBuilder();
        sb.append(questionAnswer.getFormCompleted().getUiLevel())
                .append(';')
                .append(questionAnswer.getFormCompleted().getUxLevel())
                .append(';')
                .append(questionAnswer.getDurationToAnswerInMilliseconds())
                .append(';')
                .append(questionAnswer.getQuestion().getQuestionTypeName())
                .append(';')
                .append(questionAnswer.getQuestion().getQuestion())
                .append(';')
                .append(questionAnswer.getOptionCount())
                .append(';')
                .append(questionAnswer.getTextAnswer())
                .append('\n');

        return sb.toString();
    }

    public String returnFormCompletedResultsRowByFormQuestions(List<QuestionMethodsInterface> allQuestions) {
        StringBuilder sb = new StringBuilder();
        sb.append("Data wypełnienia formularza;");

        for (QuestionMethodsInterface question : allQuestions) {
            sb.append(question.getQuestion())
                    .append(';');
        }
        sb.append('\n');

        return sb.toString();
    }

    public String returnFormCompletedResultsRow(FormCompletedEntity formCompleted, List<QuestionMethodsInterface> allQuestions) {

        StringBuilder sb = new StringBuilder();
        sb.append(formCompleted.getDateAdded())
                .append(';');

        for (QuestionMethodsInterface question : allQuestions) {
            sb.append(formCompleted.getAnsweredQuestionByQuestion(question).getAnswersAsText())
                    .append(';');
        }

        String result = sb.substring(0, sb.length() - 1);

        result = result + '\n';

        return result;
    }
}
