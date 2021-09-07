import { Component, OnInit } from '@angular/core';
import { CategoriesService } from 'src/app/services/categories.service';
import { SearcherService } from 'src/app/services/searcher.service';

@Component({
  selector: 'app-sidenav-items',
  templateUrl: './sidenav-items.component.html',
  styleUrls: ['./sidenav-items.component.scss'],
})
export class SidenavItemsComponent implements OnInit {
  constructor(
    private categoriesService: CategoriesService,
    private searcherService: SearcherService
  ) {}

  ngOnInit(): void {}

  get categories() {
    return this.categoriesService.categories;
  }

  search(category: string) {
    this.searcherService.next(category);
  }
}
