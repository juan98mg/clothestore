import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CategoriesService {
  categories = [
    {
      name: 'Hombre',
      iconPath: 'assets/icons/man.svg',
    },

    {
      name: 'Mujer',
      iconPath: 'assets/icons/woman.svg',
    },

    {
      name: 'Junior',
      iconPath: 'assets/icons/junior.svg',
    },

    {
      name: 'Niños',
      iconPath: 'assets/icons/childrens.svg',
    },

    {
      name: 'Accesorios',
      iconPath: 'assets/icons/accessories.svg',
    },

    {
      name: 'Ofertas',
      iconPath: 'assets/icons/offer.svg',
    },
  ];

  constructor() {}
}
