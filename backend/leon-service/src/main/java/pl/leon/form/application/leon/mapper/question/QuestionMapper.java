package pl.leon.form.application.leon.mapper.question;

import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.both.Option;
import pl.leon.form.application.leon.model.both.questions.QuestionAnswering;
import pl.leon.form.application.leon.model.response.questions.QuestionResponse;
import pl.leon.form.application.leon.repository.entities.OptionsEntity;
import pl.leon.form.application.leon.repository.entities.AnswerEntity;
import pl.leon.form.application.leon.repository.entities.OptionEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public interface QuestionMapper<QuestionType, AnsweringType> {
    QuestionResponse mapToResponse(QuestionType entity);

    QuestionAnswering mapToAnswering(AnsweringType entity);

    default Set<Option> mapToOption(OptionEntity optionEntity) {
        return new HashSet<>(Set.of(
                Option.builder()
                        .id(optionEntity.getId())
                        .content(optionEntity.getContent())
                        .build())
        );
    }

    default Set<Option> mapToOption(OptionsEntity optionEntity) {
        return optionEntity.getChosenOptions()
                .stream()
                .map(option -> Option.builder()
                        .id(option.getId())
                        .content(option.getContent())
                        .build())
                .collect(Collectors.toSet());
    }
}
