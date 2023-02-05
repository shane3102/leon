import { Component, ComponentRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { faPlus } from '@fortawesome/free-solid-svg-icons'
import { Observable, of } from 'rxjs';
import { FormService } from '../../services/form.service';
import { minQuestionCount, whiteSpaceOfEmpty } from '../../validators/form.validation';

@Component({
  selector: 'app-add-form',
  templateUrl: './add-form.component.html',
  styleUrls: ['./add-form.component.css', '../../style/add-question-style.css']
})
export class AddFormComponent implements OnInit {

  isAdding: Observable<boolean> = of(false);
  triedSubmitting: Observable<boolean> = of(false);
  formInvalid: Observable<boolean> = of(false);
  noTitleOfSubject: Observable<boolean> = of(false)

  constructor(private formService: FormService, private router: Router) { }

  public faPlus = faPlus;

  addFormForm = new FormGroup({
    'title': new FormControl('', Validators.required),
    'dateTo': new FormControl(''),
    'resultsAvailableForEveryone': new FormControl(''),
    'disableQuestionsFromRandomGeneratedForms': new FormControl(''),
    'subject': new FormControl('', Validators.required),
    'questions': new FormArray([], minQuestionCount(1))
  })

  ngOnInit(): void {
  }

  private createQuestionFormGroup() {
    return new FormGroup({
      type: new FormControl('', [Validators.required, whiteSpaceOfEmpty()])
    })
  }

  public addQuestion() {
    const questions = this.addFormForm.get('questions') as FormArray;
    questions.push(this.createQuestionFormGroup());
  }

  public removeQuestion(index: number) {
    const questions = this.addFormForm.get('questions') as FormArray;
    if (questions.length >= 1) {
      questions.removeAt(index);
    } else {
      questions.reset();
    }
  }

  get getQuestions(): FormArray {
    return this.addFormForm.get('questions') as FormArray
  }

  get getTitle(): FormControl {
    return this.addFormForm.get('title') as FormControl
  }

  get getSubject(): FormControl {
    return this.addFormForm.get('subject') as FormControl
  }

  public submitAddingForm(addFormForm: any) {

    this.isAdding = of(true);

    if (this.addFormForm.invalid) {
      this.isAdding = of(false);
      if (this.getQuestions.errors?.['minQuestionCount']) {
        this.triedSubmitting = of(true);
        setTimeout(() => this.triedSubmitting = of(false), 2000)
      } else if (this.getQuestions.controls.some(control => control.invalid)) {
        this.formInvalid = of(true);
        setTimeout(() => this.formInvalid = of(false), 2000)
      } else if (this.addFormForm.invalid) {
        this.noTitleOfSubject = of(true);
        setTimeout(() => this.noTitleOfSubject = of(false), 2000)
      }
    } else {
      this.formService.addNewForm(addFormForm).subscribe({
        next: res => {
          this.router.navigateByUrl('/main-page')
        },
        error: error => {
          this.isAdding = of(false);
        }
      })
    }

  }

}
