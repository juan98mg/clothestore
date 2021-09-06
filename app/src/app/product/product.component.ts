import { Component, OnInit } from '@angular/core';
import { BannerService } from '../products/services/banner.service';
import { SearcherService } from '../services/searcher.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {
  showList = false;
  constructor(
    private bannerService: BannerService,
    private searcherService: SearcherService
  ) {}

  ngOnInit(): void {
    this.observeSearch();
  }

  get banners() {
    return this.bannerService.getBanners();
  }

  observeSearch() {
    this.searcherService.getObs().subscribe((data) => {
      this.showList = data != null && data != '';
    });
  }
}
