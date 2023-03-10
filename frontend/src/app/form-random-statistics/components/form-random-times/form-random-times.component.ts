import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { FormUiUxTypeRandomTimeStatisticsResponse } from '../../models/form-ui-ux-type-random-time-statistics-response';
import { RandomFormStatisticsService } from '../../services/random-form-statistics-service';

@Component({
  selector: 'app-form-random-times',
  templateUrl: './form-random-times.component.html',
  styleUrls: ['./form-random-times.component.css']
})
export class FormRandomTimesComponent implements OnInit {

  timeStatisticsResponse: FormUiUxTypeRandomTimeStatisticsResponse[];

  isLoadingTimes: Observable<boolean> = of(true);

  constructor(private randomFormStatisticsService: RandomFormStatisticsService) { }

  ngOnInit(): void {
    this.randomFormStatisticsService.getFillingRandomFormTimes().subscribe({
      next: (res) => {
        this.timeStatisticsResponse = res;
        this.isLoadingTimes = of(false);
      }
    })
  }

}
