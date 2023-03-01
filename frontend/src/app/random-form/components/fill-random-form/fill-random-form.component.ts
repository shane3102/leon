import { Component, OnInit } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { FormUiUxRequest } from 'src/app/models/form-ui-ux-request';
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
  allFormsFilled: Observable<boolean> = of(false);
  formsInFillingOrder: FormUiUxRequest[];

  constructor(private randomFormService: RandomFormService) { }

  ngOnInit(): void {
    this.shuffle(this.formsToFillOrder);

    this.formsInFillingOrder = this.formsToFillOrder.map((formString, index) => {
      let uiUxInfo: string[] = formString.toUpperCase().replace('UX', '').split('UI')
      return { uiLevel: uiUxInfo[0], uxLevel: uiUxInfo[1], formNumber: index } as FormUiUxRequest
    })

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

    let result: Observable<string> = of();

    this.allFormsFilled.subscribe(aff => {
      if (!aff) {
        result = of(this.formsToFillOrder[this.index]);
      }
    });

    return result;
  }

  public nextFormOrFinnish() {
    this.index++
    if (this.index > 3) {
      this.allFormsFilled = of(true);
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
