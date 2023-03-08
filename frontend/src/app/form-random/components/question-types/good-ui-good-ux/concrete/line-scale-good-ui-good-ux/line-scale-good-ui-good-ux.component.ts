import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatRadioChange } from '@angular/material/radio';
import { Observable, Subscription } from 'rxjs';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';
import { OptionResponse } from 'src/app/form-random/models/option-response';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-line-scale-good-ui-good-ux',
  templateUrl: './line-scale-good-ui-good-ux.component.html',
  styleUrls: ['../../style/good-ui-good-ux-style.css', './line-scale-good-ui-good-ux.component.css']
})
export class LineScaleGoodUiGoodUxComponent implements OnInit {

  focus = false;

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() triedSubmiting: Observable<void>;
  @Input() formResultChanged: Observable<FormChangeSubject> = new Observable<FormChangeSubject>();

  @Output() formChanged = new EventEmitter<FormChanged>()

  private triedSubmittingSubscription: Subscription;
  private formResultChangedSubscription: Subscription;

  private currentFormResultChange: FormChangeSubject = new FormChangeSubject(undefined, 0, 0, Date.now());

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required]);
    this.triedSubmittingSubscription = this.triedSubmiting.subscribe(() => {
      this.getChosenOptionArray.markAsTouched();
    })

    this.formResultChangedSubscription = this.formResultChanged.subscribe(change => {
      this.currentFormResultChange = change;
    })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onChange(value: OptionResponse) {

    let indexOfCheckedElement = this.getChosenOptionArray.value.findIndex((option: any) => option.id == value.id)

    if (indexOfCheckedElement == -1) {
      this.getChosenOptionArray.clear();
      this.getChosenOptionArray.push(new FormControl(value))
    }

    this.formChange();

  }

  formChange() {

    let changeTime: number = Date.now();

    let changeTimeInMilliseconds: number;

    if (this.currentFormResultChange.id == this.question.id) {
      changeTimeInMilliseconds = changeTime - this.currentFormResultChange.startedFillingForm - this.currentFormResultChange.startedFillingQuestion;
      this.questionFormGroup.get('durationToAnswerInMilliseconds')?.setValue(changeTimeInMilliseconds)
    } else {
      changeTimeInMilliseconds = changeTime - this.currentFormResultChange.startedFillingForm - this.currentFormResultChange.finishedFillingQuestion - this.currentFormResultChange.startedFillingQuestion;
      this.questionFormGroup.get('durationToAnswerInMilliseconds')?.setValue(changeTimeInMilliseconds + this.questionFormGroup.get('durationToAnswerInMilliseconds')?.value)
    }

    this.formChanged.emit(new FormChanged(this.question.id, changeTimeInMilliseconds));
  }

  touchedNotFilled(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.errors?.['required'])
  }

  valid(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.valid)
  }
}
