import { Component, ComponentRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { faPlus } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-add-form',
  templateUrl: './add-form.component.html',
  styleUrls: ['./add-form.component.css', '../../style/style.css']
})
export class AddFormComponent implements OnInit {

  constructor() { }

  public faPlus = faPlus;

  questions = new FormGroup({
    'questions': new FormArray([])
  })

  ngOnInit(): void {
  }

  private createQuestionFormGroup() {
    return new FormGroup({
      type: new FormControl('')
    })
  }

  public addQuestion() {
    const questions = this.questions.get('questions') as FormArray;
    questions.push(this.createQuestionFormGroup());
  }

  public removeQuestion(index: number) {
    const questions = this.questions.get('questions') as FormArray;
    if (questions.length >= 1) {
      questions.removeAt(index);
    } else {
      questions.reset();
    }
  }

  get getQuestions(): FormArray {
    return this.questions.get('questions') as FormArray
  }

}
