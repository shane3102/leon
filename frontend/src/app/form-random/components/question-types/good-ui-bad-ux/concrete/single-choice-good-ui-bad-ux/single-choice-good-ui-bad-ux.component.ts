import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { Observable, Subscription } from 'rxjs';
import { OptionResponse } from 'src/app/form-random/models/option-response';
import { QuestionResponse } from 'src/app/models/question-response';
import { maxOneOptionChosen } from 'src/app/form-random/validators/form.validation';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';

@Component({
  selector: 'app-single-choice-good-ui-bad-ux',
  templateUrl: './single-choice-good-ui-bad-ux.component.html',
  styleUrls: ['../../style/good-ui-bad-ux-style.css', './single-choice-good-ui-bad-ux.component.css']
})
export class SingleChoiceGoodUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() resetFormSubject: Observable<void>;
  @Input() formResultChanged: Observable<FormChangeSubject> = new Observable<FormChangeSubject>();

  @Output() formChanged = new EventEmitter<FormChanged>()

  private resetFormSubscription: Subscription;
  private formResultChangedSubscription: Subscription;

  private currentFormResultChange: FormChangeSubject = new FormChangeSubject(undefined, 0, 0, Date.now());

  private id: number;
  private type: string;
  private timeSpent: number;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required, maxOneOptionChosen()]);

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;

    this.resetFormSubscription = this.resetFormSubject.subscribe(() => {
      this.onReset();
    });
    this.question.options.forEach(option => { option.checked = false })

    this.formResultChangedSubscription = this.formResultChanged.subscribe(change => {
      this.currentFormResultChange = change;
    })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onChange(checkbox: MatCheckboxChange, value: OptionResponse) {

    if (checkbox.checked) {
      this.getChosenOptionArray.push(new FormControl(value))
    } else {
      let indexOfCheckedElement = this.getChosenOptionArray.value.findIndex((option: any) => option = value)
      this.getChosenOptionArray.removeAt(indexOfCheckedElement);
    }

    this.formChange();

  }

  formChange() {

    let changeTime: number = Date.now();

    let changeTimeInMilliseconds: number;

    if (this.currentFormResultChange.id == this.question.id) {
      changeTimeInMilliseconds = changeTime - this.currentFormResultChange.startedFillingForm - this.currentFormResultChange.startedFillingQuestion;
      this.questionFormGroup.get('durationToAnswerInMilliseconds')?.setValue(changeTimeInMilliseconds)
      this.timeSpent = changeTimeInMilliseconds;
    } else {
      changeTimeInMilliseconds = changeTime - this.currentFormResultChange.startedFillingForm - this.currentFormResultChange.finishedFillingQuestion - this.currentFormResultChange.startedFillingQuestion;
      this.questionFormGroup.get('durationToAnswerInMilliseconds')?.setValue(changeTimeInMilliseconds + this.questionFormGroup.get('durationToAnswerInMilliseconds')?.value)
      this.timeSpent = changeTimeInMilliseconds + this.questionFormGroup.get('durationToAnswerInMilliseconds')?.value;
    }

    this.formChanged.emit(new FormChanged(this.question.id, changeTimeInMilliseconds));
  }

  onReset() {

    this.question.options.forEach(o => {
      o.checked = false;
    });
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id));
      this.questionFormGroup.setControl('type', new FormControl(this.type));
      this.questionFormGroup.setControl('durationToAnswerInMilliseconds', new FormControl(this.timeSpent));
      (this.questionFormGroup.get('chosenOptions') as FormArray).clear();
    }, 0);
  }

}
