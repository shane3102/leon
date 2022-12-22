package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.leon.form.application.leon.model.both.FormCompleted;
import pl.leon.form.application.leon.model.request.forms.FormCreateRequest;
import pl.leon.form.application.leon.model.response.forms.FormResponse;
import pl.leon.form.application.leon.model.response.forms.FormSnippetResponse;
import pl.leon.form.application.leon.model.response.forms.FormToCompleteResponse;
import pl.leon.form.application.leon.service.FormService;
import pl.leon.form.application.leon.service.FormToCompleteService;
import pl.leon.form.application.leon.service.FormCompletedService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/form")
public class FormController {

    private final FormService formService;
    private final FormCompletedService formCompletedService;
    private final FormToCompleteService formToCompleteService;

    @GetMapping("/get-random-form")
    public ResponseEntity<FormToCompleteResponse> getRandomForm(@RequestParam(name = "question-count") Short questionPerTypeCount) {
        log.info("getRandomForm()");
        FormToCompleteResponse response = formToCompleteService.generateFormToComplete(questionPerTypeCount);
        log.info("getRandomForm() = {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addNewForm(@RequestBody FormCreateRequest form) {
        log.info("addNewForm({})", form);
        FormResponse response = formService.addNewForm(form);
        log.info("addNewForm({}) = {}", form, response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listConcreteForms() {
        log.info("listConcreteForms()");
        List<FormSnippetResponse> response = formService.list();
        log.info("listConcreteForms() = {}", response == null ? null : response.size());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitForm(@RequestBody FormCompleted formCompleted) {
        log.info("submitForm({})", formCompleted);
        FormCompleted response = formCompletedService.submitCompletedForm(formCompleted);
        log.info("submitForm({}) = {}", formCompleted, response);
        return ResponseEntity.ok(response);
    }
}
