import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ShopingCartService {
  products: Array<any> = new Array<any>();

  constructor() {}
}
