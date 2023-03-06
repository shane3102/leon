import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatRadioChange } from '@angular/material/radio';
import { Observable, Subscription } from 'rxjs';
import { OptionResponse } from 'src/app/form-random/models/option-response';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-line-scale-good-ui-good-ux',
  templateUrl: './line-scale-good-ui-good-ux.component.html',
  styleUrls: ['../../style/good-ui-good-ux-style.css', './line-scale-good-ui-good-ux.component.css']
})
export class LineScaleGoodUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() triedSubmiting: Observable<void>;

  focus = false;

  private triedSubmittingSubscription: Subscription;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required]);
    this.triedSubmittingSubscription = this.triedSubmiting.subscribe(() => {
      this.getChosenOptionArray.markAsTouched();
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

  }

  touchedNotFilled(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.errors?.['required'])
  }

  valid(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.valid)
  }
}
