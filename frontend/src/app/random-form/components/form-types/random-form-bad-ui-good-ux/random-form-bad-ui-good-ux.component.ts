import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { FormToCompleteResponse } from 'src/app/random-form/models/form-to-complete-response';
import { RandomFormService } from 'src/app/random-form/services/random-form.service';

@Component({
  selector: 'app-random-form-bad-ui-good-ux',
  templateUrl: './random-form-bad-ui-good-ux.component.html',
  styleUrls: ['./random-form-bad-ui-good-ux.component.css']
})
export class RandomFormBadUiGoodUxComponent implements OnInit {

  @Input() formToComplete: FormToCompleteResponse = new FormToCompleteResponse();

  @Output() formSentEvent = new EventEmitter();

  randomFormGroup: FormGroup;

  constructor(private randomFormService: RandomFormService) { }

  ngOnInit(): void {
    this.randomFormGroup = new FormGroup({
      'answers': new FormArray([]),
      'uxLevel': new FormControl('GOOD'),
      'uiLevel': new FormControl('BAD'),
      'durationToAnswer': new FormControl(null) //TODO liczenie tego 
    })
  }

  submitForm(request: any) {
    if (!this.randomFormGroup.invalid) {
      this.randomFormService.submitRandomForm(request).subscribe({
        next: res => {
          this.formSentEvent.emit();
        }
      })
    }
  }

}
