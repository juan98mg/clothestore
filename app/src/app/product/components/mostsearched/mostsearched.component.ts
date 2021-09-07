import { Component, OnInit } from '@angular/core';
import { LoadingService } from 'src/app/services/loading.service';
import { MessageService } from 'src/app/services/message.service';
import { ProductService } from 'src/app/services/product.service';
import { AppConstants } from 'src/app/util/app_constants';

@Component({
  selector: 'app-mostsearched',
  templateUrl: './mostsearched.component.html',
  styleUrls: ['./mostsearched.component.scss'],
})
export class MostsearchedComponent implements OnInit {
  products: Array<any> = new Array<any>();

  constructor(
    private productService: ProductService,
    private loading: LoadingService,
    private messageService: MessageService,
    private window: Window
  ) {}

  ngOnInit(): void {
    this.getRamdom();
  }

  getRamdom() {
    let ctx = this;
    ctx.loading.showLoding();
    this.productService.search().subscribe(
      (data) => {
        console.log('esto es lo raro', data);
        ctx.loading.closeLoading();

        console.log('sliced', data.results.slice(0, 100), data.results);
        ctx.sliceBy(ctx.window.innerWidth >= 430 ? 3 : 1, data.results);
      },
      (error) => {
        ctx.loading.closeLoading();
        this.messageService.commonMessage(
          AppConstants.globalError,
          AppConstants.unknowError,
          AppConstants.sweetIcons.error
        );
      },
      () => {
        this.loading.closeLoading();
      }
    );
  }

  sliceBy(index: number, data: Array<any>) {
    let times = data.length / index;

    for (let i = 0; i < times; i++) {
      let removed = data.splice(0, index);
      this.products.push(removed);
    }

    console.log(this.products, 'esosañkdjfkjsadfsadflkjsa');
  }
}
