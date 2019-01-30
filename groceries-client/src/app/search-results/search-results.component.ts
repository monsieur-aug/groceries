import { Component, OnInit, Input } from '@angular/core';
import { Product } from './product.model';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  @Input('productsToDisplay') products : Product[];

  constructor() { 
    
  }

  ngOnInit() {
  }

  toTitleCase(unformattedString : string) {
    let formattedString = unformattedString.toLowerCase().split(' ');

    for (var i = 0; i < formattedString.length; i++) {
      formattedString[i] = formattedString[i].charAt(0).toUpperCase() + formattedString[i].slice(1); 
    }

    return formattedString.join(' ');
  }
}
