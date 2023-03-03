package pl.leon.form.application.leon.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {
    // bad request
    TOO_MANY_QUESTIONS_TO_GENERATE("Too many questions were requested"),
    COULD_NOT_PARSE_LOGIN_REQUEST_ATTEMPT("Login request attempt was badly shaped"),
    CHOSEN_OPTION_WAS_NOT_FOUND_IN_AVAILABLE_OPTIONS("Chosen answer option for question was not found in available options"),

    // i am a teapot
    OTHER_EXCEPTION_WHILE_EXTRACTING_TOKEN("Unexpected exception while extracting access token. Exception message: "),
    NEWLY_ADDED_ANSWER_WAS_NOT_ATTACHED_TO_QUESTION("Newly added answer was not attached to question"),
    THERE_IS_NO_QUESTION_SERVICE("While obtaining there is no question service found. Probably critical error"),

    // internal server error
    EXCEPTION_WHEN_FILTER_CHAINING_AUTHENTICATION("There was an exception when filter chaining authentication. Exception message: "),

    // not found
    FORM_NOT_FOUND("There is no form with given id"),

    // unauthorized
    STATISTICS_NOT_AVAILABLE_TO_USER("User is not authorized to check statistics of form");

    private final String message;
}
