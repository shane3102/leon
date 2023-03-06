import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSelect } from '@angular/material/select';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-dropdown-good-ui-good-ux',
  templateUrl: './dropdown-good-ui-good-ux.component.html',
  styleUrls: ['../../style/good-ui-good-ux-style.css', './dropdown-good-ui-good-ux.component.css']
})
export class DropdownGoodUiGoodUxComponent implements OnInit {

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

  onBlur(){
    this.focus=false;
    this.getChosenOptionArray.markAsTouched()
  }

  onSelect(option: MatSelect) {

    (this.getChosenOptionArray).clear()

    if (option.value != "") {
      this.getChosenOptionArray.push(new FormControl(JSON.parse(option.value)))
    }

  }

  touchedNotFilled(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.errors?.['required'])
  }

  touchedValid(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.valid)
  }


}
