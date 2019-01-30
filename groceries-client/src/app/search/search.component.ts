import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../search-results/product.model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  productsEnpoint : string = 'http://localhost:8080/groceries/api/products';

  isMoreOptionsExpanded : boolean = false;

  descriptionField : string;
  idField : string;
  departmentField : string;
  priceField : string;
  lastSoldField : string;
  shelfLifeField : string;
  unitField : string;
  xForField : string;
  costField : string;

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

    this.trimAllFields();

    if (this.descriptionField) {
      searchParams.append('descriptionContains', this.descriptionField);
    }

    if (this.idField) {
      searchParams.append('id', this.idField);
    }
    
    if (this.departmentField) {
      searchParams.append('department', this.departmentField);
    }

    if (this.priceField) {
      let priceMin : string;
      let priceMax : string;
      
      [priceMin, priceMax] = this.getMinAndMax(this.priceField);

      searchParams.append('priceMin', priceMin);
      searchParams.append('priceMax', priceMax);
    }

    if (this.lastSoldField) {
      searchParams.append('lastSoldDateFrom', this.lastSoldField);
      searchParams.append('lastSoldDateUntil', this.lastSoldField);
    }

    if (this.shelfLifeField) {
      let shelfLifeMin : string;
      let shelfLifeMax : string;

      [shelfLifeMin, shelfLifeMax] = this.getMinAndMax(this.shelfLifeField);

      searchParams.append('shelfLifeMin', shelfLifeMin);
      searchParams.append('shelfLifeMax', shelfLifeMax);
    }

    if (this.unitField) {
      searchParams.append('unit', this.unitField);
    }

    if (this.xForField) {
      let xForMin : string;
      let xForMax : string;

      [xForMin, xForMax] = this.getMinAndMax(this.xForField);

      searchParams.append('xForMin', xForMin);
      searchParams.append('xForMax', xForMax);
    }

    if (this.costField) {
      let costMin : string;
      let costMax : string;

      [costMin, costMax] = this.getMinAndMax(this.costField);

      searchParams.append('costMin', costMin);
      searchParams.append('costMax', costMax);
    }
    
    return searchParams;
  }

  trimAllFields() {
    if (this.descriptionField) { this.descriptionField = this.descriptionField.trim(); }
    if (this.idField) { this.idField = this.idField.trim(); }
    if (this.departmentField) { this.departmentField = this.departmentField.trim(); }
    if (this.priceField) { this.priceField = this.priceField.trim(); }
    if (this.lastSoldField) { this.lastSoldField = this.lastSoldField.trim(); }
    if (this.shelfLifeField) { this.shelfLifeField = this.shelfLifeField.trim(); }
    if (this.unitField) { this.unitField = this.unitField.trim(); }
    if (this.xForField) { this.xForField = this.xForField.trim(); }
    if (this.costField) { this.costField = this.costField.trim(); }
  }

  getMinAndMax(range : string) {
    let normalizedRange = range.replace(/ /gi, '');

    if (normalizedRange.indexOf('-') != -1) {
      return [normalizedRange.split('-')[0], normalizedRange.split('-')[1]];
    }

    return [normalizedRange, normalizedRange];
  }

  findProducts() {
    let searchParams : URLSearchParams = this.buildSearchParams();
    let searchUrl = new URL(this.productsEnpoint.concat('?', searchParams.toString()));

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
