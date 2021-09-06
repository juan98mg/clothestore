import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { MostsearchedComponent } from './components/mostsearched/mostsearched.component';
import { ProductItemComponent } from './components/product-item/product-item.component';
import { ProductRoutingModule } from './product-routing.module';
import { ProductComponent } from './product.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { ListComponent } from './components/list/list.component';

@NgModule({
  declarations: [ProductComponent, MostsearchedComponent, ProductItemComponent, CarouselComponent, ListComponent],
  imports: [CommonModule, ProductRoutingModule, SharedModule],
  exports: [MostsearchedComponent],
})
export class ProductModule {}
