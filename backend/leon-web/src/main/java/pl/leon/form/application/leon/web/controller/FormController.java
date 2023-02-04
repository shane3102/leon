package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<?> getRandomForm(@RequestParam(name = "question-count") Short questionToGenerateCount) {
        log.info("getRandomForm({})", questionToGenerateCount);
        FormToCompleteResponse response = formToCompleteService.generateFormToComplete(questionToGenerateCount);
        log.info("getRandomForm({}) = {}", questionToGenerateCount, response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-each-random-form")
    public ResponseEntity<?> getEachRandomForm(@RequestParam(name = "form-count") Short formCount, @RequestParam(name = "question-count") Short questionToGenerateCount) {
        log.info("getEachRandomForm()");
        List<FormToCompleteResponse> response = formToCompleteService.generateFormsToComplete(formCount, questionToGenerateCount);
        log.info("getEachRandomForm() = {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConcreteForm(@PathVariable Long id) {
        log.info("getConcreteForm({})", id);
        FormResponse formResponse = formService.readConcreteForm(id);
        log.info("getConcreteForm({}) = {}", id, formResponse);
        return ResponseEntity.ok(formResponse);
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
