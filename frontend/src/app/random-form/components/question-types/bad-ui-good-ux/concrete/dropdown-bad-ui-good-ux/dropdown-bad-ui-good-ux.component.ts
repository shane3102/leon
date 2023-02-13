import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { QuestionResponse } from 'src/app/random-form/models/question-response';

@Component({
  selector: 'app-dropdown-bad-ui-good-ux',
  templateUrl: './dropdown-bad-ui-good-ux.component.html',
  styleUrls: ['../../style/bad-ui-good-ux-style.css','./dropdown-bad-ui-good-ux.component.css']
})
export class DropdownBadUiGoodUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required])
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onSelect(option: HTMLSelectElement) {

    (this.getChosenOptionArray).clear()

    this.getChosenOptionArray.push(new FormControl(JSON.parse(option.value)))

  }

}
