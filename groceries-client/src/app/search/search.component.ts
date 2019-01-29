import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit() {
  }

  toggleMoreSearchOptions() {
    this.isMoreOptionsExpanded = !this.isMoreOptionsExpanded;
  }
}
