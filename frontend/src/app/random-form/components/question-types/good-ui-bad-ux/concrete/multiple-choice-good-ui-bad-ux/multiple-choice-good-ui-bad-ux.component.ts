import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatRadioChange } from '@angular/material/radio';
import { Observable, Subscription } from 'rxjs';
import { OptionResponse } from 'src/app/random-form/models/option-response';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-multiple-choice-good-ui-bad-ux',
  templateUrl: './multiple-choice-good-ui-bad-ux.component.html',
  styleUrls: ['../../style/good-ui-bad-ux-style.css', './multiple-choice-good-ui-bad-ux.component.css']
})
export class MultipleChoiceGoodUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() resetFormSubject: Observable<void>;

  private resetFormSubscription: Subscription;

  private id: number;
  private type: string;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required]);

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;

    this.resetFormSubscription = this.resetFormSubject.subscribe(() => {
      this.onReset();
    })

    this.question.options.forEach(option => { option.checked = false })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onChange(option: OptionResponse) {
    setTimeout(() => {
      option.checked = !(option.checked);

      let indexOfCheckedElement = this.getChosenOptionArray.value.findIndex((o: any) => {
        return o.id === option.id;
      })

      if (indexOfCheckedElement == -1) {
        this.getChosenOptionArray.push(new FormControl(option))
      } else {
        this.getChosenOptionArray.removeAt(indexOfCheckedElement);
      }
    })
  }

  onReset() {
    this.question.options.forEach(o => { o.checked = false });
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id));
      this.questionFormGroup.setControl('type', new FormControl(this.type));
      (this.questionFormGroup.get('chosenOptions') as FormArray).clear();
    }, 0);
  }

}
