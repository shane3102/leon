import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { Observable, Subscription } from 'rxjs';
import { OptionResponse } from 'src/app/form-random/models/option-response';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-multiple-choice-good-ui-good-ux',
  templateUrl: './multiple-choice-good-ui-good-ux.component.html',
  styleUrls: ['../../style/good-ui-good-ux-style.css', './multiple-choice-good-ui-good-ux.component.css']
})
export class MultipleChoiceGoodUiGoodUxComponent implements OnInit {
  
  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() triedSubmiting: Observable<void>;

  private triedSubmittingSubscription: Subscription;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required])
    this.triedSubmittingSubscription = this.triedSubmiting.subscribe(() => {
      this.getChosenOptionArray.markAsTouched();
    })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onChange(checkbox: MatCheckboxChange, option: OptionResponse) {

    this.getChosenOptionArray.markAsTouched();

    if (checkbox.checked) {
      this.getChosenOptionArray.push(new FormControl(option))
    } else {
      let indexOfCheckedElement = this.getChosenOptionArray.value.findIndex((optionTmp: any) => optionTmp == option)
      this.getChosenOptionArray.removeAt(indexOfCheckedElement);
    }

  }

  touchedNotFilled(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.errors?.['required'])
  }

  touchedValid(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.valid)
  }

}
