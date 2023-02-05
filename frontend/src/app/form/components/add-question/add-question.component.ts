import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { QuestionTypes } from '../../model/question-types';
import { faMinus } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css', '../../style/add-question-style.css']
})
export class AddQuestionComponent implements OnInit {

  @Input() questionControls: FormGroup;
  @Input() index: number;

  faMinus = faMinus;

  @Output() deleteEvent = new EventEmitter<number>();

  selectedType: string;

  questionTypes: QuestionTypes = new QuestionTypes();

  constructor() { }

  ngOnInit(): void {
  }

  deleteQuestion() {
    this.deleteEvent.emit(this.index);
  }

  get getTypeControl(): FormControl {
    return this.questionControls.get('type') as FormControl
  }

}
