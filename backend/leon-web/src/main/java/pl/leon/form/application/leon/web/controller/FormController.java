package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.leon.form.application.leon.model.response.FormToCompleteResponse;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.service.FormService;
import pl.leon.form.application.leon.service.FormToCompleteService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/form")
public class FormController {

    private final FormService formService;
    private final FormToCompleteService formToCompleteService;

    @GetMapping("/get-random-form")
    public ResponseEntity<FormToCompleteResponse> getRandomForm(@RequestParam(name = "question-count") Short questionPerTypeCount) {
        log.info("getRandomForm()");
        FormToCompleteResponse response = formToCompleteService.generateFormToComplete(questionPerTypeCount);
        log.info("getRandomForm() = {}", response);
        return ResponseEntity.ok(response);
    }
}
