import { Component, OnInit } from '@angular/core';
import { SearcherService } from 'src/app/services/searcher.service';

@Component({
  selector: 'app-searcher',
  templateUrl: './searcher.component.html',
  styleUrls: ['./searcher.component.scss'],
})
export class SearcherComponent implements OnInit {
  value = '';
  constructor(private searcherService: SearcherService) {}

  ngOnInit(): void {}

  search() {
    this.searcherService.next(this.value);
  }
}
