import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/random-form/models/question-response';
import { maxOneOptionChosen } from '../../../../../validators/form.validation';

@Component({
  selector: 'app-line-scale-bad-ui-bad-ux',
  templateUrl: './line-scale-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './line-scale-bad-ui-bad-ux.component.css']
})
export class LineScaleBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() resetFormSubject: Observable<void>;

  private resetFormSubscription: Subscription;

  private id: number;
  private type: string;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required, maxOneOptionChosen()]);

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;

    this.resetFormSubscription = this.resetFormSubject.subscribe(() => {
      document.querySelectorAll(".lineScaleCheckbox").forEach(
        el => {
          let checkbox: HTMLInputElement = el as HTMLInputElement;
          if (checkbox.checked) {
            checkbox.checked = false;
          }
        }
      )
      this.onReset();
    })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onChange(event: Event) {

    let checkbox = (event.currentTarget as HTMLInputElement)

    if (checkbox.checked) {
      this.getChosenOptionArray.push(new FormControl(JSON.parse(checkbox.value)))
    } else {
      let indexOfCheckedElement = this.getChosenOptionArray.value.findIndex((option: any) => option = checkbox.value)
      this.getChosenOptionArray.removeAt(indexOfCheckedElement);
    }

  }

  onReset() {
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id));
      this.questionFormGroup.setControl('type', new FormControl(this.type));
      (this.questionFormGroup.get('chosenOptions') as FormArray).clear();
    }, 0);
  }

}
