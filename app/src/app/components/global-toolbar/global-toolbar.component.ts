import { Component, Input, OnInit } from '@angular/core';
import { CategoriesService } from 'src/app/services/categories.service';
import { ShopingCartService } from 'src/app/services/shoping-cart.service';

@Component({
  selector: 'app-global-toolbar',
  templateUrl: './global-toolbar.component.html',
  styleUrls: ['./global-toolbar.component.scss'],
})
export class GlobalToolbarComponent implements OnInit {
  @Input() sidenave: any;
  constructor(
    private shopingService: ShopingCartService,
    private categorieService: CategoriesService
  ) {}

  ngOnInit(): void {}

  get boughtItems() {
    return this.shopingService.products.length;
  }

  get categories() {
    return this.categorieService.categories;
  }

  menuEvent() {
    this.sidenave.toggle();
  }
}
