import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-short-answer-question',
  templateUrl: './add-short-answer-question.component.html',
  styleUrls: ['../../../style/add-question-style.css', './add-short-answer-question.component.css']
})
export class AddShortAnswerQuestionComponent implements OnInit {

  @Input() questionControls: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.questionControls.addControl('question', new FormControl('', Validators.required));
  }

}
