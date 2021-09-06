import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class BannerService {
  private banners = [
    'assets/images/paris.jpg',
    'assets/images/fashion.jpeg',
    'assets/images/man.jpeg',
    'assets/images/woman.jpg',
    'assets/images/accesories.jpg',
    'assets/images/arrivals.jpg',
  ];

  constructor() {}

  getBanners() {
    return this.banners;
  }
}
