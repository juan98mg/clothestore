import { Component, OnInit } from '@angular/core';
import { LoadingService } from 'src/app/services/loading.service';
import { MessageService } from 'src/app/services/message.service';
import { ProductService } from 'src/app/services/product.service';
import { SearcherService } from 'src/app/services/searcher.service';
import { AppConstants } from 'src/app/util/app_constants';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  products: Array<any> = [];
  searchedParameter = '';
  constructor(
    private productService: ProductService,
    private loading: LoadingService,
    private messageService: MessageService,
    private window: Window,
    private searcherService: SearcherService
  ) {}

  ngOnInit(): void {
    this.observe();
  }

  observe() {
    this.searcherService.getObs().subscribe((data) => {
      if (data != null) {
        this.searchedParameter = data;
        this.getProducts(data);
      }
    });
  }

  getProducts(parameter: string) {
    this.loading.showLoding();
    this.productService.search(parameter).subscribe(
      (data) => {
        this.loading.closeLoading();
        this.products = data.results;
      },
      (error) => {
        this.loading.closeLoading();
        this.messageService.commonMessage(
          AppConstants.globalError,
          AppConstants.unknowError,
          AppConstants.sweetIcons.error
        );
      }
    );
  }
}
