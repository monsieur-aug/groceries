import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../search-results/product.model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  isMoreOptionsExpanded : boolean = false;

  descriptionField : string;
  idField : string;
  departmentField : string;
  priceField : string;

  retrievedProducts : Product[];

  constructor(private http : HttpClient) {

  }

  ngOnInit() {
  }

  toggleMoreSearchOptions() {
    this.isMoreOptionsExpanded = !this.isMoreOptionsExpanded;
  }

  buildSearchParams() : URLSearchParams {
    let searchParams = new URLSearchParams();

    if (this.descriptionField) {
      searchParams.append('descriptionContains', this.descriptionField);
    }

    if (this.departmentField) {
      searchParams.append('department', this.departmentField);
    }

    if (this.priceField) {
      searchParams.append('priceMin', this.priceField);
      searchParams.append('priceMax', this.priceField);
    }

    return searchParams;
  }

  findProducts() {
    let searchParams : URLSearchParams = this.buildSearchParams();
    let searchUrl = new URL('http://localhost:8080/groceries/api/products'.concat('?', searchParams.toString()));

    console.log('contacting ' + searchUrl.toString());
    this.http.get(searchUrl.toString()).subscribe((response) => this.handleResponse(response));
  }

  handleResponse(response : any) {
    console.log('handling response...');

    if (Array.isArray(response)) {
      this.retrievedProducts = response;
    } else if (response.id) {
      this.retrievedProducts = [ response ];
    }

    console.log(this.retrievedProducts);
  }
}
