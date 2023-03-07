import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable, of, Subject } from 'rxjs';
import { FormToCompleteResponse } from 'src/app/models/form-to-complete-response';
import { RandomFormService } from 'src/app/form-random/services/random-form.service';

@Component({
  selector: 'app-random-form-good-ui-bad-ux',
  templateUrl: './random-form-good-ui-bad-ux.component.html',
  styleUrls: ['./random-form-good-ui-bad-ux.component.css']
})
export class RandomFormGoodUiBadUxComponent implements OnInit {

  @Input() formToComplete: FormToCompleteResponse = new FormToCompleteResponse();

  @Output() formSentEvent = new EventEmitter();

  formStartCompletingTime: number = Date.now();

  randomFormGroup: FormGroup;
  triedSubmiting: Observable<boolean> = of(false)

  resetFormSubject: Subject<void> = new Subject<void>()

  constructor(private randomFormService: RandomFormService) { }

  ngOnInit(): void {
    this.randomFormGroup = new FormGroup({
      'answers': new FormArray([]),
      'uxLevel': new FormControl('BAD'),
      'uiLevel': new FormControl('GOOD'),
      'durationToAnswer': new FormControl(null) //TODO liczenie tego 
    })
  }

  resetForm() {
    setTimeout(() => {
      this.randomFormGroup.setControl('uxLevel', new FormControl('BAD'))
      this.randomFormGroup.setControl('uiLevel', new FormControl('GOOD'))
      this.resetFormSubject.next();
    }, 0);
  }

  submitForm(request: any) {
    if (this.randomFormGroup.invalid) {
      this.triedSubmiting = of(true)
    } else {
      request.completeDurationInMilliseconds = Date.now() - this.formStartCompletingTime;

      this.randomFormService.submitRandomForm(request).subscribe({
        next: res => {
          this.formSentEvent.emit()
        },
        error: error => {
          this.resetTriedSubmitting()
        }
      })
    }
  }

  get getAnswersFormArray(): FormArray {
    return this.randomFormGroup.get('answers') as FormArray
  }

  resetTriedSubmitting() {
    this.triedSubmiting = of(false);
  }

}
