import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Observable, of, Subject } from 'rxjs';
import { FormToCompleteResponse } from 'src/app/models/form-to-complete-response';
import { RandomFormService } from 'src/app/form-random/services/random-form.service';
import { FormChangeSubject } from 'src/app/form-random/models/form-change-subject';
import { FormChanged } from 'src/app/form-random/models/form-changed';
import { NgxCaptureService } from 'ngx-capture';

@Component({
  selector: 'app-random-form-good-ui-good-ux',
  templateUrl: './random-form-good-ui-good-ux.component.html',
  styleUrls: ['./random-form-good-ui-good-ux.component.css']
})
export class RandomFormGoodUiGoodUxComponent implements OnInit {

  @ViewChild('screen', { static: true }) screen: any;

  @Input() formId: number;
  @Input() formToComplete: FormToCompleteResponse = new FormToCompleteResponse();

  @Output() formSentEvent = new EventEmitter();
  @Output() screenshotMade = new EventEmitter<string>();

  formStartCompletingTime: number = Date.now();
  currentFormChange: FormChangeSubject = new FormChangeSubject(undefined, 0, 0, Date.now());

  randomFormGroup: FormGroup;

  triedSubmitting: Observable<boolean> = of(false);
  submitting: Observable<boolean> = of(false);

  triedSubmitingSubject: Subject<void> = new Subject<void>();
  formResultChanged: Subject<FormChangeSubject> = new Subject<FormChangeSubject>();

  constructor(private randomFormService: RandomFormService, private captureService: NgxCaptureService) { }

  ngOnInit(): void {
    this.randomFormGroup = new FormGroup({
      'answers': new FormArray([]),
      'uxLevel': new FormControl('GOOD'),
      'uiLevel': new FormControl('GOOD'),
    })

    if (this.formId != undefined) {
      this.randomFormGroup.addControl('completedFormId', new FormControl(this.formId));
    }

    setTimeout(() => {
      this.captureService.getImage(this.screen.nativeElement, true).subscribe(img => {
        this.screenshotMade.emit(img);
      })
    })
  }

  questionFilled(formChange: FormChanged) {

    if (formChange.id != this.currentFormChange.id) {
      this.currentFormChange.startedFillingQuestion = this.currentFormChange.startedFillingQuestion + this.currentFormChange.finishedFillingQuestion;
    }
    if (this.currentFormChange.id == undefined) {
      this.currentFormChange.startedFillingQuestion = 0;
    }

    this.currentFormChange.finishedFillingQuestion = formChange.userFilled;
    this.currentFormChange.id = formChange.id;

    this.formResultChanged.next(this.currentFormChange);
  }

  submitForm(request: any) {
    if (!this.randomFormGroup.invalid) {

      request.completeDurationInMilliseconds = Date.now() - this.formStartCompletingTime;

      if (this.formId != undefined) {
        request.completeDurationInMilliseconds = undefined;
        request.answers.forEach((a: any) => {
          a.durationToAnswerInMilliseconds = undefined;
        })
      }

      this.submitting = of(true);
      this.randomFormService.submitRandomForm(request).subscribe({
        next: res => {
          this.formSentEvent.emit();
          this.submitting = of(false);
        }
      });
    } else {
      this.triedSubmitingSubject.next();
      this.triedSubmitting = of(true);
    }
  }

  touchedNotFilled(): boolean {
    return this.randomFormGroup && this.randomFormGroup.touched && this.randomFormGroup.invalid
  }
}
