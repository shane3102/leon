import { Component, OnInit } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { FormToCompleteResponse } from '../../models/form-to-complete-response';
import { RandomFormService } from '../../services/random-form.service';

@Component({
  selector: 'app-fill-random-form',
  templateUrl: './fill-random-form.component.html',
  styleUrls: ['./fill-random-form.component.css']
})
export class FillRandomFormComponent implements OnInit {

  private formsToFillOrder: string[] = [
    "BadUIBadUX",
    "BadUIGoodUX",
    "GoodUIBadUX",
    "GoodUIGoodUX"
  ];

  badUiBadUxForm: FormToCompleteResponse
  badUiGoodUxForm: FormToCompleteResponse
  goodUiBadUxForm: FormToCompleteResponse
  goodUiGoodUxForm: FormToCompleteResponse

  index: number = 0;
  responseCame: Observable<boolean> = of(false);

  constructor(private randomFormService: RandomFormService) { }

  ngOnInit(): void {
    this.shuffle(this.formsToFillOrder);
    this.randomFormService.getEachRandomForm(4, 10).subscribe({
      next: res => {
        this.badUiBadUxForm = res[0];
        this.badUiGoodUxForm = res[1];
        this.goodUiBadUxForm = res[2];
        this.goodUiGoodUxForm = res[3];
        this.responseCame = of(true);
      }
    })
  }

  public getCurrentForm(): Observable<string> {
    return of(this.formsToFillOrder[this.index]);
  }

  public nextFormOrFinnish() {
    this.index++
    if (this.index > 3) {
      //TODO zrób coś
    }
  }

  // Shuffle the order of forms
  shuffle(array: any[]) {
    let currentIndex = array.length, randomIndex;

    // While there remain elements to shuffle.
    while (currentIndex != 0) {

      // Pick a remaining element.
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex--;

      // And swap it with the current element.
      [array[currentIndex], array[randomIndex]] = [
        array[randomIndex], array[currentIndex]];
    }

    return array;
  }
}
