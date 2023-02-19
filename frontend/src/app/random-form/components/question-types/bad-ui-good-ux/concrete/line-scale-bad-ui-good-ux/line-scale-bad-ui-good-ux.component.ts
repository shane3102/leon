import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-line-scale-bad-ui-good-ux',
  templateUrl: './line-scale-bad-ui-good-ux.component.html',
  styleUrls: ['../../style/bad-ui-good-ux-style.css', './line-scale-bad-ui-good-ux.component.css']
})
export class LineScaleBadUiGoodUxComponent implements OnInit {

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

    let option: HTMLInputElement = event.currentTarget as HTMLInputElement

    (this.getChosenOptionArray).clear()

    this.getChosenOptionArray.push(new FormControl(JSON.parse(option.value)))

  }

  touchedNotFilled(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.errors?.['required'])
  }

  touchedValid(): boolean {
    return (this.getChosenOptionArray && this.getChosenOptionArray.touched && this.getChosenOptionArray.valid)
  }

}
