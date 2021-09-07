import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'src/app/services/message.service';
import { ShopingCartService } from 'src/app/services/shoping-cart.service';
import { AppConstants } from 'src/app/util/app_constants';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss'],
})
export class ProductItemComponent implements OnInit {
  @Input() product: any = {};

  constructor(
    private window: Window,
    private router: Router,
    private shopingService: ShopingCartService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.product.quantity = 1;
  }

  get name() {
    return this.product.title.length > 10
      ? `${this.product.title.substring(0, 10)}...`
      : this.product.title;
  }

  get title() {
    return this.product.title;
  }

  get image() {
    return this.product.thumbnail;
  }

  get stock() {
    return this.product.sold_quantity;
  }

  detail() {
    this.shopingService.products.push(this.product);
    this.messageService.commonMessage(
      AppConstants.GlobalSuccess,
      AppConstants.productAdded(this.product.title),
      AppConstants.successIcon
    );
    //this.window.open(this.product.permalink);
  }

  get price() {
    return this.product.price;
  }

  increment() {
    console.log('chasdf');
    if (this.product.sold_quantity > this.product.quantity) {
      this.product.quantity++;
    }
  }

  decrement() {
    if (this.product.quantity > 0) {
      this.product.quantity--;
    }
  }

  get showAddButton() {
    return !this.router.url.match('products/shop');
  }

  get maximun() {
    return this.product.sold_quantity;
  }

  validMaximum() {
    if (this.product.quantity > this.product.sold_quantity) {
      this.product.quantity = this.product.sold_quantity;
    }
  }
}
