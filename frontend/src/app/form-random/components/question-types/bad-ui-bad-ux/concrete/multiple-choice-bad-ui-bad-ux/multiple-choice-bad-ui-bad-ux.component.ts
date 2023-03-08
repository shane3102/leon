import { JsonPipe } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, Subscription } from 'rxjs';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-multiple-choice-bad-ui-bad-ux',
  templateUrl: './multiple-choice-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './multiple-choice-bad-ui-bad-ux.component.css']
})
export class MultipleChoiceBadUiBadUxComponent implements OnInit {

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
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required]);

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;

    this.resetFormSubscription = this.resetFormSubject.subscribe(() => {
      document.querySelectorAll(".multipleChoiceRadio").forEach(
        el => {
          let radio: HTMLInputElement = el as HTMLInputElement;
          if (radio.checked) {
            radio.checked = false;
          }
        }
      )
      this.onReset();
    })

    this.formResultChangedSubscription = this.formResultChanged.subscribe(change => {
      this.currentFormResultChange = change;
    })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onChange(event: Event) {
    let checkbox = (event.currentTarget as HTMLInputElement)

    let indexOfCheckedElement = this.getChosenOptionArray.value.findIndex((option: any) => {
      return option.id === JSON.parse(checkbox.value).id
    })

    if (indexOfCheckedElement == -1) {
      this.getChosenOptionArray.push(new FormControl(JSON.parse(checkbox.value)))
    } else {
      this.getChosenOptionArray.removeAt(indexOfCheckedElement);
      checkbox.checked = false;
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
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id));
      this.questionFormGroup.setControl('type', new FormControl(this.type));
      this.questionFormGroup.setControl('durationToAnswerInMilliseconds', new FormControl(this.timeSpent));
      (this.questionFormGroup.get('chosenOptions') as FormArray).clear();
    }, 0);
  }

}
