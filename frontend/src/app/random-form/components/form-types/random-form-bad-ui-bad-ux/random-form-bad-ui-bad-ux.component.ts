import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
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

  constructor() { }

  ngOnInit(): void {
    this.randomFormGroup= new FormGroup({
      'answers': new FormArray(this.formToComplete.questions.map(q => {
        return new FormGroup({
          'id': new FormControl(q.id),
          'chosenOptions': new FormArray([]),
          'answer': new FormControl(),
          'type': new FormControl(q.type),
          'durationToAnswer': new FormControl(null)//TODO liczenie tego
        })
      })),
      'uxLevel': new FormControl('BAD'),
      'uiLevel': new FormControl('BAD'),
      'durationToAnswer': new FormControl(null) //TODO liczenie tego 
    })
  }

  public getFormGroupOfQuestion(index: number): FormGroup{
    return (this.randomFormGroup.get('answers')as FormArray).at(index) as FormGroup
  }

}
