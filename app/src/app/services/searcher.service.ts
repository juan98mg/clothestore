import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SearcherService {
  private action = new BehaviorSubject<string>('');

  constructor() {}

  getValue() {
    return this.action.getValue();
  }

  getObs(): Observable<string> {
    return this.action;
  }

  next(newValue: string) {
    this.action.next(newValue);
  }
}
