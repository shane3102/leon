import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable, of, Subject } from 'rxjs';
import { FormToCompleteResponse } from 'src/app/random-form/models/form-to-complete-response';
import { RandomFormService } from 'src/app/random-form/services/random-form.service';

@Component({
  selector: 'app-random-form-bad-ui-bad-ux',
  templateUrl: './random-form-bad-ui-bad-ux.component.html',
  styleUrls: ['./random-form-bad-ui-bad-ux.component.css']
})
export class RandomFormBadUiBadUxComponent implements OnInit {

  @Input() formToComplete: FormToCompleteResponse = new FormToCompleteResponse();

  @Output() formSentEvent = new EventEmitter();

  randomFormGroup: FormGroup;
  triedSubmiting: Observable<boolean> = of(false)

  resetFormSubject: Subject<void> = new Subject<void>()

  constructor(private randomFormService: RandomFormService) { }

  ngOnInit(): void {
    this.randomFormGroup = new FormGroup({
      'answers': new FormArray([]),
      'uxLevel': new FormControl('BAD'),
      'uiLevel': new FormControl('BAD'),
      'durationToAnswer': new FormControl(null) //TODO liczenie tego 
    })
  }

  resetForm() {
    setTimeout(() => {
      this.randomFormGroup.setControl('uxLevel', new FormControl('BAD'))
      this.randomFormGroup.setControl('uiLevel', new FormControl('BAD'))
      this.resetFormSubject.next();
    }, 0);
  }

  submitForm(request: any) {
    if (this.randomFormGroup.invalid) {
      this.triedSubmiting = of(true)
    } else {
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
