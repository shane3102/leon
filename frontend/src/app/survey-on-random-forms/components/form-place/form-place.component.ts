import { CdkDragDrop, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable, of } from 'rxjs';
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
  triedAddingTwoFormsOnOnePlace: Observable<boolean> = of(false);

  constructor() { }

  ngOnInit(): void {
  }

  onDrop(event: CdkDragDrop<FormUiUxRequest[]>) {
    if (this.form.length <= 0) {
      transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex)
      this.placedOutput.emit({ 'place': this.place, 'form': this.form[0] })
    } else {
      this.triedAddingTwoFormsOnOnePlace = of(true);
      setTimeout(() => {
        this.triedAddingTwoFormsOnOnePlace = of(false);
      }, 2000)
    }
  }

  getRomanNumber() {
    switch (this.place) {
      case 0:
        return 'I';
      case 1:
        return 'II';
      case 2:
        return 'III';
      case 3:
        return 'IV';
    }
    return "IMPLEMENT YOU MORON";
  }

}
