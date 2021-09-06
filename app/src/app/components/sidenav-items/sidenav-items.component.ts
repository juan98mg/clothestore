import { Component, OnInit } from '@angular/core';
import { CategoriesService } from 'src/app/services/categories.service';

@Component({
  selector: 'app-sidenav-items',
  templateUrl: './sidenav-items.component.html',
  styleUrls: ['./sidenav-items.component.scss'],
})
export class SidenavItemsComponent implements OnInit {
  constructor(private categoriesService: CategoriesService) {}

  ngOnInit(): void {}

  get categories() {
    return this.categoriesService.categories;
  }
}
