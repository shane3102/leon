import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/models/question-response';
import { maxOneOptionChosen } from '../../../../../validators/form.validation';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';

@Component({
  selector: 'app-dropdown-bad-ui-bad-ux',
  templateUrl: './dropdown-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './dropdown-bad-ui-bad-ux.component.css']
})
export class DropdownBadUiBadUxComponent implements OnInit {

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

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required, maxOneOptionChosen()])

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;

    this.resetFormSubscription = this.resetFormSubject.subscribe(() => {

      let dropdown = document.getElementById("dropdownElement") as HTMLSelectElement

      dropdown.selectedIndex = 0;

      this.onReset();
    })

    this.formResultChangedSubscription = this.formResultChanged.subscribe(change => {
      this.currentFormResultChange = change;
    })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onSelect(option: HTMLSelectElement) {

    (this.getChosenOptionArray).clear()

    this.getChosenOptionArray.push(new FormControl(JSON.parse(option.value)))

    this.formChange();

  }

  get getDurationToAnswerControl(): FormControl {
    return this.questionFormGroup.get('durationToAnswerInMilliseconds') as FormControl;
  }

  formChange() {

    let changeTime: number = Date.now();

    let changeTimeInMilliseconds: number;

    if (this.currentFormResultChange.id == this.question.id) {
      changeTimeInMilliseconds = changeTime - this.currentFormResultChange.startedFillingForm - this.currentFormResultChange.startedFillingQuestion;
    } else {
      changeTimeInMilliseconds = changeTime - this.currentFormResultChange.startedFillingForm - this.currentFormResultChange.finishedFillingQuestion - this.currentFormResultChange.startedFillingQuestion;
    }

    this.questionFormGroup.get('durationToAnswerInMilliseconds')?.setValue(changeTimeInMilliseconds)

    this.formChanged.emit(new FormChanged(this.question.id, changeTimeInMilliseconds));

    console.log(changeTimeInMilliseconds);
  }

  onReset() {
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id));
      this.questionFormGroup.setControl('type', new FormControl(this.type));
      (this.questionFormGroup.get('chosenOptions') as FormArray).clear();
    }, 0);
  }

}
