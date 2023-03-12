package pl.leon.form.application.leon.export;

import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.repository.entities.FormCompletedEntity;
import pl.leon.form.application.leon.repository.entities.question_answers.QuestionAnswerMethodsInterface;

@Component
public class CsvRowEncoder {

    public final String FORM_COMPLETED_HEADLINE = "Wygląd interfejsu," +
            "UX interfejsu," +
            "Czas wypełniania formularza (milisekundy)," +
            "Ilość pytań z listą rozwijaną," +
            "Ilość pytań ze skalą liniową," +
            "Ilość pytań z długą odpowiedzią," +
            "Ilość pytań z wielokrotnym wyborem," +
            "Ilość pytań z krótką odpowiedzią," +
            "Ilość pytań z jednokrotnym wyborem\n";

    public final String QUESTION_ANSWERED_HEADLINE = "Wygląd interfejsu," +
            "UX interfejsu," +
            "Czas wypełniania pytania," +
            "Typ pytania," +
            "Treść pytania," +
            "Liczba wybranych opcji," +
            "Odpowiedź tekstowa\n";

    public String returnFormCompletedRow(FormCompletedEntity completedForm) {
        StringBuilder sb = new StringBuilder();
        sb.append(completedForm.getUiLevel())
                .append(',')
                .append(completedForm.getUxLevel())
                .append(',')
                .append(completedForm.getCompleteDurationInMilliseconds())
                .append(',')
                .append(completedForm.getAnsweredDropdownQuestions().size())
                .append(',')
                .append(completedForm.getAnsweredLineScaleQuestions().size())
                .append(',')
                .append(completedForm.getAnsweredLongAnswerQuestions().size())
                .append(',')
                .append(completedForm.getAnsweredMultipleChoiceQuestions().size())
                .append(',')
                .append(completedForm.getAnsweredShortAnswerQuestions().size())
                .append(',')
                .append(completedForm.getAnsweredSingleChoiceQuestions().size())
                .append('\n');

        return sb.toString();
    }

    public String returnQuestionAnsweredRow(QuestionAnswerMethodsInterface questionAnswer) {
        StringBuilder sb = new StringBuilder();
        sb.append(questionAnswer.getFormCompleted().getUiLevel())
                .append(',')
                .append(questionAnswer.getFormCompleted().getUxLevel())
                .append(',')
                .append(questionAnswer.getDurationToAnswerInMilliseconds())
                .append(',')
                .append(questionAnswer.getQuestion().getQuestionTypeName())
                .append(',')
                .append(questionAnswer.getQuestion().getQuestion())
                .append(',')
                .append(questionAnswer.getOptionCount())
                .append(',')
                .append(questionAnswer.getTextAnswer())
                .append('\n');

        return sb.toString();
    }
}
