import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/models/question-response';

@Component({
  selector: 'app-multiple-choice-bad-ui-good-ux',
  templateUrl: './multiple-choice-bad-ui-good-ux.component.html',
  styleUrls: ['../../style/bad-ui-good-ux-style.css', '../../style/bad-ui-good-ux-style.css', './multiple-choice-bad-ui-good-ux.component.css']
})
export class MultipleChoiceBadUiGoodUxComponent implements OnInit {

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

  onChange(event: Event) {

    this.getChosenOptionArray.markAsTouched();

    let checkbox = (event.currentTarget as HTMLInputElement)

    if (checkbox.checked) {
      this.getChosenOptionArray.push(new FormControl(JSON.parse(checkbox.value)))
    } else {
      let indexOfCheckedElement = this.getChosenOptionArray.value.findIndex((option: any) => option = checkbox.value)
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
