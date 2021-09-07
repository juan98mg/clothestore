import { Component, Input, OnInit } from '@angular/core';
import { CategoriesService } from 'src/app/services/categories.service';
import { SearcherService } from 'src/app/services/searcher.service';
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
    private categorieService: CategoriesService,
    private searcher: SearcherService
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

  search(category: string) {
    this.searcher.next(category);
  }
}
