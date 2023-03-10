import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { FormUiUxRandomCompletingStatisticsResponse } from '../../models/form-ui-ux-random-completing-statistics-response';
import { RandomFormStatisticsService } from '../../services/random-form-statistics-service';

@Component({
  selector: 'app-form-random-places',
  templateUrl: './form-random-places.component.html',
  styleUrls: ['./form-random-places.component.css']
})
export class FormRandomPlacesComponent implements OnInit {

  formsPlaces: FormUiUxRandomCompletingStatisticsResponse[]

  isLoadingPlaces: Observable<boolean> = of(true);

  constructor(private randomFormStatisticsService: RandomFormStatisticsService) { }

  ngOnInit(): void {
    this.randomFormStatisticsService.getFormUiUxRandomCompletingStatisticsResponse().subscribe({
      next: res => {
        this.formsPlaces = res;
        this.isLoadingPlaces = of(false);
      }
    })
  }

}
