package pl.leon.form.application.leon.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.leon.form.application.leon.repository.entities.FormEntity;
import pl.leon.form.application.leon.service.FormService;

import java.util.List;

@RestController
@RequestMapping("/api/form")
@AllArgsConstructor
public class FormController {

    private final FormService formService;

    @PostMapping("/create")
    public FormEntity create(@RequestBody FormEntity formEntity) {
        return formService.create(formEntity);
    }

    @GetMapping("/{id}")
    public FormEntity read(@PathVariable Long id) {
        return formService.read(id);
    }

    @PutMapping("/update/{id}")
    public FormEntity update(@PathVariable Long id, @RequestBody FormEntity formEntity) {
        return formService.update(id, formEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        formService.delete(id);
    }

    @GetMapping("/list")
    public List<FormEntity> list() {
        return formService.list();
    }
}
