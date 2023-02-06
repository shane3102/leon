import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { OptionResponse } from 'src/app/random-form/models/option-response';
import { QuestionResponse } from 'src/app/random-form/models/question-response';
import { maxOneOptionChosen } from '../../validators/bad-ui-bad-ux.validation';

@Component({
  selector: 'app-dropdown-bad-ui-bad-ux',
  templateUrl: './dropdown-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './dropdown-bad-ui-bad-ux.component.css']
})
export class DropdownBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required, maxOneOptionChosen])
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  getOptionControl(index: number): FormControl {
    return this.getChosenOptionArray.at(index) as FormControl;
  }

  onSelect(option: HTMLSelectElement) {

    (this.getChosenOptionArray).clear()

    this.getChosenOptionArray.push(new FormControl(JSON.parse(option.value)))

  }

}
