import { Component, OnInit } from '@angular/core';
import { BannerService } from '../products/services/banner.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {
  constructor(private bannerService: BannerService) {}

  ngOnInit(): void {}

  get banners() {
    return this.bannerService.getBanners();
  }
}
