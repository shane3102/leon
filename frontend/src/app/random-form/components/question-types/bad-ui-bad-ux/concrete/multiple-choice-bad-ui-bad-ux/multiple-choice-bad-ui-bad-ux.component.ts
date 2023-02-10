import { JsonPipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-multiple-choice-bad-ui-bad-ux',
  templateUrl: './multiple-choice-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './multiple-choice-bad-ui-bad-ux.component.css']
})
export class MultipleChoiceBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  private id: number;
  private type: string;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required]);

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;
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
  }

  onReset() {
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id));
      this.questionFormGroup.setControl('type', new FormControl(this.type));
      (this.questionFormGroup.get('chosenOptions') as FormArray).clear();
    }, 0);
  }

}
