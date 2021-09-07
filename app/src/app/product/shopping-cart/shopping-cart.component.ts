import { Component, OnInit } from '@angular/core';
import { ShopingCartService } from 'src/app/services/shoping-cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss'],
})
export class ShoppingCartComponent implements OnInit {
  constructor(
    private shopingcart: ShopingCartService,
    private window: Window
  ) {}

  ngOnInit(): void {}

  get products() {
    return this.shopingcart.products;
  }

  get height() {
    return this.window.innerWidth <= 430 ? '420px' : '90%';
  }

  get total() {
    return this.products.reduce(
      (total, product) => total + product.quantity * product.price,
      0
    );
  }
}
