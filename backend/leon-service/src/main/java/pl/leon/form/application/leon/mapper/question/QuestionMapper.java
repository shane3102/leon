package pl.leon.form.application.leon.mapper.question;

import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.options.OptionStatisticsResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.model.response.questions.QuestionStatisticsResponse;
import pl.leon.form.application.leon.repository.entities.OptionEntity;
import pl.leon.form.application.leon.repository.entities.questions.DropdownQuestionEntity;
import pl.leon.form.application.leon.repository.entities.questions.QuestionMethodsInterface;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public interface QuestionMapper<QuestionType extends QuestionMethodsInterface, AnsweringType> {
    QuestionResponse mapToResponse(QuestionType entity);

    QuestionStatisticsResponse mapToStatisticsResponse(QuestionType entity);

    QuestionAnswering mapToAnswering(AnsweringType entity);

    default Set<Option> mapToOption(OptionEntity optionEntity) {
        return new HashSet<>(Set.of(
                Option.builder()
                        .id(optionEntity.getId())
                        .content(optionEntity.getContent())
                        .build())
        );
    }

    default List<OptionStatisticsResponse> mapToOptionsStatistics(QuestionType questionEntity) {
        return questionEntity.getOptions().stream().map(optionEntity -> OptionStatisticsResponse.builder()
                .id(optionEntity.getId())
                .content(optionEntity.getContent())
                .count(optionEntity.getCount())
                .percentageOfAnswers(
                        (questionEntity.getCountAnswers() == null || questionEntity.getCountAnswers()==0) ? 0d : ((double) optionEntity.getCount() / questionEntity.getCountAnswers())
                )
                .build()).collect(Collectors.toList());
    }
}
