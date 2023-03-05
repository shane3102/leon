import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-long-answer-question',
  templateUrl: './add-long-answer-question.component.html',
  styleUrls: ['../../../style/add-question-style.css','./add-long-answer-question.component.css']
})
export class AddLongAnswerQuestionComponent implements OnInit {

  @Input() questionControls: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.questionControls.addControl('question', new FormControl('', Validators.required));
  }

}
