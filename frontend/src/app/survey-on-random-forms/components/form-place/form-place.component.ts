import { CdkDragDrop, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormUiUxRequest } from 'src/app/models/form-ui-ux-request';
import { EmmittForm } from '../../model/emitt-form';

@Component({
  selector: 'app-form-place',
  templateUrl: './form-place.component.html',
  styleUrls: ['./form-place.component.css']
})
export class FormPlaceComponent implements OnInit {

  @Input() place: number;

  @Output() placedOutput: EventEmitter<EmmittForm> = new EventEmitter();

  form: FormUiUxRequest[] = [];

  constructor() { }

  ngOnInit(): void {
  }

  onDrop(event: CdkDragDrop<FormUiUxRequest[]>) {
    if (this.form.length <= 0) {
      transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex)
    }
    this.placedOutput.emit({ 'place': this.place, 'form': this.form[0] })
  }

}
