import { Component, Input, OnInit } from '@angular/core';
import { FormUiUxRandomCompletingStatisticsResponse } from '../../models/form-ui-ux-random-completing-statistics-response';

@Component({
  selector: 'app-form-random-places-bar-graph',
  templateUrl: './form-random-places-bar-graph.component.html',
  styleUrls: ['./form-random-places-bar-graph.component.css']
})
export class FormRandomPlacesBarGraphComponent implements OnInit {

  @Input() formPlaces: FormUiUxRandomCompletingStatisticsResponse;

  total: number

  constructor() { }

  ngOnInit(): void {
    this.total = this.formPlaces.firstPlaceCount + this.formPlaces.secondPlaceCount + this.formPlaces.thirdPlaceCount + this.formPlaces.fourthPlaceCount;
  }

}
