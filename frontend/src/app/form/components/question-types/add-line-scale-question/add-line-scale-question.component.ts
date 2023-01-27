import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { faMinus } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-add-line-scale-question',
  templateUrl: './add-line-scale-question.component.html',
  styleUrls: ['./add-line-scale-question.component.css','../../../style/style.css']
})
export class AddLineScaleQuestionComponent implements OnInit {

  @Input() questionControls: FormGroup;

  faMinus = faMinus;

  constructor() { }

  ngOnInit(): void {
    this.questionControls.addControl('question', new FormControl('', Validators.required));
    this.questionControls.addControl('options', new FormArray([new FormGroup({ content: new FormControl('', Validators.required) })]))
  }

  private createOptionFormGroup() {
    return new FormGroup({ content: new FormControl('', Validators.required) })
  }

  public addOption() {
    const options = this.questionControls.get('options') as FormArray
    options.push(this.createOptionFormGroup())
  }

  public removeOption(index: number) {
    const options = this.questionControls.get('options') as FormArray
    if (options.length > 1) {
      options.removeAt(index);
    } else {
      options.reset();
    }
  }

  get getOptions(): FormArray<FormGroup> {
    return this.questionControls.get('options') as FormArray<FormGroup>
  }

}
