package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.leon.form.application.leon.model.both.forms.FormUiUxRanking;
import pl.leon.form.application.leon.model.response.forms.FormStatisticsResponse;
import pl.leon.form.application.leon.model.response.forms.FormUiUxRandomCompletingStatisticsResponse;
import pl.leon.form.application.leon.model.response.forms.FormUiUxTypeRandomTimeStatisticsResponse;
import pl.leon.form.application.leon.service.FormCompletedService;
import pl.leon.form.application.leon.service.FormService;
import pl.leon.form.application.leon.service.FormUiUxRankingService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/form/statistics")
public class FormStatisticsController {

    private final FormService formService;
    private final FormCompletedService formCompletedService;
    private final FormUiUxRankingService formUiUxRankingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getFormWithStatistics(@PathVariable Long id) {
        log.info("getFormWithStatistics({})", id);
        FormStatisticsResponse formStatisticsResponse = formService.readConcreteFormWithStatistics(id);
        log.info("getFormWithStatistics({}) = {}", id, formStatisticsResponse);
        return ResponseEntity.ok(formStatisticsResponse);
    }

    @GetMapping("/random-forms/rankings")
    public ResponseEntity<?> getRandomFormFillingStatistics() {
        log.info("getRandomFormFillingStatistics()");
        List<FormUiUxRandomCompletingStatisticsResponse> response = formUiUxRankingService.getRankingsWithPlaceCounts();
        log.info("getRandomFormFillingStatistics() = {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/random-forms/list-each-added-ranking")
    public ResponseEntity<?> getEachRanking() {
        log.info("getEachRanking()");
        List<FormUiUxRanking> response = formUiUxRankingService.getAllRankings();
        log.info("getEachRanking() = {}", response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/random-forms/times-to-answer")
    public ResponseEntity<?> getFillingRandomFormTimes() {
        log.info("getFillingRandomFormTimes()");
        List<FormUiUxTypeRandomTimeStatisticsResponse> response = formCompletedService.getRankingsWithAverageTimes();
        log.info("getFillingRandomFormTimes() = {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-ranking")
    public ResponseEntity<?> addNewRanking(@RequestBody FormUiUxRanking request) {
        log.info("addNewRanking({})", request);
        FormUiUxRanking response = formUiUxRankingService.addRanking(request);
        log.info("addNewRanking({})", request);
        return ResponseEntity.ok(response);
    }
}
