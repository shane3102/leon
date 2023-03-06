import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, Subscription } from 'rxjs';
import { QuestionResponse } from 'src/app/models/question-response';
import { maxOneOptionChosen } from '../../../../../validators/form.validation';

@Component({
  selector: 'app-dropdown-bad-ui-bad-ux',
  templateUrl: './dropdown-bad-ui-bad-ux.component.html',
  styleUrls: ['../../style/bad-ui-bad-ux-style.css', './dropdown-bad-ui-bad-ux.component.css']
})
export class DropdownBadUiBadUxComponent implements OnInit {

  @Input() question: QuestionResponse;
  @Input() questionFormGroup: FormGroup;
  @Input() resetFormSubject: Observable<void>;

  private resetFormSubscription: Subscription;

  private id: number;
  private type: string;

  constructor() { }

  ngOnInit(): void {
    this.questionFormGroup.get('chosenOptions')?.addValidators([Validators.required, maxOneOptionChosen()])

    this.id = this.questionFormGroup.get('id')?.value;
    this.type = this.questionFormGroup.get('type')?.value;

    this.resetFormSubscription = this.resetFormSubject.subscribe(() => {
      
      let dropdown = document.getElementById("dropdownElement") as HTMLSelectElement

      dropdown.selectedIndex = 0;
      
      this.onReset();
    })
  }

  get getChosenOptionArray(): FormArray {
    return this.questionFormGroup.get('chosenOptions') as FormArray
  }

  onSelect(option: HTMLSelectElement) {

    (this.getChosenOptionArray).clear()

    this.getChosenOptionArray.push(new FormControl(JSON.parse(option.value)))

  }

  onReset() {
    setTimeout(() => {
      this.questionFormGroup.setControl('id', new FormControl(this.id));
      this.questionFormGroup.setControl('type', new FormControl(this.type));
      (this.questionFormGroup.get('chosenOptions') as FormArray).clear();
    }, 0);
  }

}
