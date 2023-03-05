import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable, of, Subject } from 'rxjs';
import { FormToCompleteResponse } from 'src/app/form-random/models/form-to-complete-response';
import { RandomFormService } from 'src/app/form-random/services/random-form.service';

@Component({
  selector: 'app-random-form-good-ui-good-ux',
  templateUrl: './random-form-good-ui-good-ux.component.html',
  styleUrls: ['./random-form-good-ui-good-ux.component.css']
})
export class RandomFormGoodUiGoodUxComponent implements OnInit {

  @Input() formToComplete: FormToCompleteResponse = new FormToCompleteResponse();

  @Output() formSentEvent = new EventEmitter();

  randomFormGroup: FormGroup;

  triedSubmitingSubject: Subject<void> = new Subject<void>()
  triedSubmitting: Observable<boolean> = of(false);
  submitting: Observable<boolean> = of(false);

  constructor(private randomFormService: RandomFormService) { }

  ngOnInit(): void {
    this.randomFormGroup = new FormGroup({
      'answers': new FormArray([]),
      'uxLevel': new FormControl('GOOD'),
      'uiLevel': new FormControl('GOOD'),
      'durationToAnswer': new FormControl(null) //TODO liczenie tego 
    })
  }

  submitForm(request: any) {
    if (!this.randomFormGroup.invalid) {
      this.submitting = of(true);
      setTimeout(() => {
        this.randomFormService.submitRandomForm(request).subscribe({
          next: res => {
            this.formSentEvent.emit();
            this.submitting = of(false);
          }
        });
      }, 5000)
    } else {
      this.triedSubmitingSubject.next();
      this.triedSubmitting = of(true);
    }
  }

  touchedNotFilled(): boolean {
    return this.randomFormGroup && this.randomFormGroup.touched && this.randomFormGroup.invalid
  }
}
