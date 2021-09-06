import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { FooterColumnTextComponent } from './footer-column-text/footer-column-text.component';
import { FooterComponent } from './footer/footer.component';
import { GlobalToolbarComponent } from './global-toolbar/global-toolbar.component';
import { SearcherComponent } from './searcher/searcher.component';
import { SidenavItemsComponent } from './sidenav-items/sidenav-items.component';
import { LoadingComponent } from './loading/loading.component';

@NgModule({
  declarations: [
    GlobalToolbarComponent,
    SearcherComponent,
    SidenavItemsComponent,
    FooterComponent,
    FooterColumnTextComponent,
    LoadingComponent,
  ],
  imports: [CommonModule, SharedModule, FormsModule],
  exports: [GlobalToolbarComponent, SidenavItemsComponent, FooterComponent],
})
export class ComponentsModule {}
