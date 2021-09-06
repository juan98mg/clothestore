import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss'],
})
export class ProductItemComponent implements OnInit {
  @Input() product: any;

  constructor(private window: Window) {}

  ngOnInit(): void {}

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
    this.window.open(this.product.permalink);
  }

  get price() {
    return this.product.price;
  }
}
