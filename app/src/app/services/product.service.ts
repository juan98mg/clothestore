import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Page } from '../models/page';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  search(searcher: string = 'mujer') {
    return this.http.get<Page>(`${environment.apiUrl}search?q=${searcher}`);
  }
}
