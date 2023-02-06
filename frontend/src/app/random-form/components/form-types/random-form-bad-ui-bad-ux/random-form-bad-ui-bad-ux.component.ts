import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { FormToCompleteResponse } from 'src/app/random-form/models/form-to-complete-response';

@Component({
  selector: 'app-random-form-bad-ui-bad-ux',
  templateUrl: './random-form-bad-ui-bad-ux.component.html',
  styleUrls: ['./random-form-bad-ui-bad-ux.component.css']
})
export class RandomFormBadUiBadUxComponent implements OnInit {

  @Input() formChanged: Observable<string>
  @Input() formToComplete: FormToCompleteResponse = new FormToCompleteResponse();

  @Output() formSentEvent = new EventEmitter();

  randomFormGroup: FormGroup;
  triedSubmiting: Observable<boolean> = of(false)

  constructor() { }

  ngOnInit(): void {
    this.randomFormGroup = new FormGroup({
      'answers': new FormArray([]),
      'uxLevel': new FormControl('BAD'),
      'uiLevel': new FormControl('BAD'),
      'durationToAnswer': new FormControl(null) //TODO liczenie tego 
    })
  }

  submitForm(request: any) {
    if (this.randomFormGroup.invalid) {
      this.triedSubmiting = of(true)
    } else{
      this.formSentEvent.emit()
    }
  }

  get getAnswersFormArray(): FormArray {
    return this.randomFormGroup.get('answers') as FormArray
  }

  resetTriedSubmitting(){
    this.triedSubmiting = of(false);
  }

}
