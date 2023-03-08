import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable, of, Subject } from 'rxjs';
import { FormToCompleteResponse } from 'src/app/models/form-to-complete-response';
import { RandomFormService } from 'src/app/form-random/services/random-form.service';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';

@Component({
  selector: 'app-random-form-bad-ui-bad-ux',
  templateUrl: './random-form-bad-ui-bad-ux.component.html',
  styleUrls: ['./random-form-bad-ui-bad-ux.component.css']
})
export class RandomFormBadUiBadUxComponent implements OnInit {

  @Input() formToComplete: FormToCompleteResponse = new FormToCompleteResponse();

  @Output() formSentEvent = new EventEmitter();

  formStartCompletingTime: number = Date.now();
  currentFormChange: FormChangeSubject = new FormChangeSubject(undefined, 0, 0, Date.now());

  randomFormGroup: FormGroup;
  triedSubmiting: Observable<boolean> = of(false)

  resetFormSubject: Subject<void> = new Subject<void>();
  formResultChanged: Subject<FormChangeSubject> = new Subject<FormChangeSubject>();

  constructor(private randomFormService: RandomFormService) { }

  ngOnInit(): void {
    this.randomFormGroup = new FormGroup({
      'answers': new FormArray([]),
      'uxLevel': new FormControl('BAD'),
      'uiLevel': new FormControl('BAD')
    })

    this.formResultChanged.next(this.currentFormChange);
  }

  resetForm() {
    setTimeout(() => {
      this.randomFormGroup.setControl('uxLevel', new FormControl('BAD'))
      this.randomFormGroup.setControl('uiLevel', new FormControl('BAD'))
      this.resetFormSubject.next();
    }, 0);
  }

  questionFilled(formChange: FormChanged) {

    if (formChange.id != this.currentFormChange.id) {
      this.currentFormChange.startedFillingQuestion = this.currentFormChange.startedFillingQuestion + this.currentFormChange.finishedFillingQuestion;
    }
    if (this.currentFormChange.id == undefined) {
      this.currentFormChange.startedFillingQuestion = 0;
    }

    this.currentFormChange.finishedFillingQuestion = formChange.userFilled;
    this.currentFormChange.id = formChange.id;

    this.formResultChanged.next(this.currentFormChange);
    console.log(this.currentFormChange);

  }

  submitForm(request: any) {
    if (this.randomFormGroup.invalid) {
      this.triedSubmiting = of(true)
    } else {
      request.completeDurationInMilliseconds = Date.now() - this.formStartCompletingTime;

      this.randomFormService.submitRandomForm(request).subscribe({
        next: res => {
          this.formSentEvent.emit()
        },
        error: error => {
          this.resetTriedSubmitting()
        }
      })
    }
  }

  get getAnswersFormArray(): FormArray {
    return this.randomFormGroup.get('answers') as FormArray
  }

  resetTriedSubmitting() {
    this.triedSubmiting = of(false);
  }

}
