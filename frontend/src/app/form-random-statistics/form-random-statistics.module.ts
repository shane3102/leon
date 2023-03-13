import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormRandomStatisticsComponent } from './components/form-random-statistics/form-random-statistics.component';
import { FormRandomPlacesComponent } from './components/form-random-places/form-random-places.component';
import { FormRandomTimesComponent } from './components/form-random-times/form-random-times.component';
import { FormRandomPlacesBarGraphComponent } from './components/form-random-places-bar-graph/form-random-places-bar-graph.component';
import { FormRandomTimesTableComponent } from './components/form-random-times-table/form-random-times-table.component';
import { FormRandomDownloadCsvComponent } from './components/form-random-download-csv/form-random-download-csv.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';



@NgModule({
  declarations: [
    FormRandomStatisticsComponent,
    FormRandomPlacesComponent,
    FormRandomTimesComponent,
    FormRandomPlacesBarGraphComponent,
    FormRandomTimesTableComponent,
    FormRandomDownloadCsvComponent
  ],
  imports: [
    CommonModule,
    FontAwesomeModule
  ],
  exports: [
    FormRandomStatisticsComponent
  ]
})
export class FormRandomStatisticsModule { }
