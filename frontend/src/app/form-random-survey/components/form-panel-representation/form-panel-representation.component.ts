import { Component, Input, OnInit } from '@angular/core';
import { FormUiUxRequest } from '../../../models/form-ui-ux-request';

@Component({
  selector: 'app-form-panel-representation',
  templateUrl: './form-panel-representation.component.html',
  styleUrls: ['./form-panel-representation.component.css']
})
export class FormPanelRepresentationComponent implements OnInit {

  @Input() uiUxrequest: FormUiUxRequest;

  constructor() { }

  ngOnInit(): void {
  }

}
