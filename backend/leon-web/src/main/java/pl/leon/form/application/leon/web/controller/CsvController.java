package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.leon.form.application.leon.export.CsvExporter;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/csv")
public class CsvController {

    private final CsvExporter csvExporter;

    @GetMapping("/completed-forms")
    public byte[] getCompletedFormsCsvReport() {
        log.info("getCompletedFormsCsvReport()");
        byte[] response = csvExporter.csvReportFormCompleted();
        log.info("getCompletedFormsCsvReport() = success");
        return response;
    }

    @GetMapping("/answered-questions")
    public byte[] getAnsweredQuestionsCsvReport() {
        log.info("getAnsweredQuestionsCsvReport()");
        byte[] response = csvExporter.csvReportQuestionsAnswered();
        log.info("getAnsweredQuestionsCsvReport() = success");
        return response;
    }

    @GetMapping("/completed-form-results/{id}")
    public byte[] getCompletedFormResultsById(@PathVariable Long id) {
        log.info("getCompletedFormResultsById({})", id);
        byte[] response = csvExporter.csvReportFormCompletedResults(id);
        log.info("getCompletedFormResultsById({}) = success", id);
        return response;
    }

    //TODO do naprawy
    @GetMapping("/completed-form-results-random-forms/{id}")
    public byte[] getCompletedFormResultsByIdOfRandomForms(@PathVariable Long id) {
        log.info("getCompletedFormResultsById({})", id);
        byte[] response = csvExporter.csvReportFormCompletedResultsOfQuestionsFromRandomForms(id);
        log.info("getCompletedFormResultsById({}) = success", id);
        return response;
    }

    //TODO do naprawy
    @GetMapping("/completed-form-results-all/{id}")
    public byte[] csvReportFormCompletedResultsAllAnswers(@PathVariable Long id) {
        log.info("getCompletedFormResultsById({})", id);
        byte[] response = csvExporter.csvReportFormCompletedResultsAllAnswers(id);
        log.info("getCompletedFormResultsById({}) = success", id);
        return response;
    }

}
